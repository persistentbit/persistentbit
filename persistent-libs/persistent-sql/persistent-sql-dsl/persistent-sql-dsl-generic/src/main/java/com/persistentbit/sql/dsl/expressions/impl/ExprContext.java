package com.persistentbit.sql.dsl.expressions.impl;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PMap;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.others.ESelectionTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.tuples.*;
import com.persistentbit.sql.dsl.genericdb.GenericBinOps;
import com.persistentbit.sql.dsl.genericdb.GenericTypeFactories;
import com.persistentbit.sql.dsl.statements.select.impl.TypedSelection1Impl;
import com.persistentbit.sql.dsl.tables.Table;
import com.persistentbit.sql.dsl.tables.TableImpl;
import com.persistentbit.sql.utils.rowreader.RowReader;
import com.persistentbit.utils.Lazy;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.util.Optional;
import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 16/12/17
 */
public class ExprContext{

	@FunctionalInterface
	public interface BinOpSqlBuilder{
		SqlWithParams apply(DExpr left, SqlWithParams sqlLeft, DExpr right, SqlWithParams sqlRight);
	}

	@FunctionalInterface
	public interface SingleOpSqlBuilder{
		SqlWithParams apply(DExpr expr, SqlWithParams sql);
	}

	@FunctionalInterface
	public interface JdbcParamSetter<J>{
		int setParam(int index, Class<J> javaClass, PreparedStatement stat, J value);

	}



	@FunctionalInterface
	public interface JdbcReader<J>{
		J read(int index, Class<J> javaClass, RowReader rowReader);
	}


	private PMap<Class<? extends DExpr>, Lazy<ExprTypeFactory>> typeFactories = PMap.empty();
	private PMap<BinOpOperator,BinOpSqlBuilder>                binOpMap      = PMap.empty();
	private PMap<SingleOpOperator,SingleOpSqlBuilder>          singleOpMap   = PMap.empty();

	private PMap<Class, ExprTypeJdbcConvert> javaJdbcConverter = PMap.empty();

	@Nullable
	private String defaultCatalogName;
	@Nullable
	private String defaultSchemaName;

	private PMap<Class,TableImpl>	tables = PMap.empty();

	public ExprContext() {
		GenericTypeFactories.registerAll(this);
		GenericBinOps.setDefaultBinOpBuilders(this);
	}

	public ExprContext setDefaultCatalogName(String defaultCatalogName) {
		this.defaultCatalogName = defaultCatalogName;
		return this;
	}

	public ExprContext setDefaultSchemaName(String defaultSchemaName) {
		this.defaultSchemaName = defaultSchemaName;
		return this;
	}



	public  <EALL extends DExpr<J>,J> ExprContext addTable(TableImpl<EALL,J> tableImpl){
		tables.put(tableImpl.getTypeClass(),tableImpl);
		return this;
	}

	public <J> ExprContext addJdbcConverter(Class<J> javaClass, ExprTypeJdbcConvert<J> converter){
		this.javaJdbcConverter = this.javaJdbcConverter.put(javaClass,converter);
		return this;
	}



	public <J> ExprTypeJdbcConvert<J> getJavaJdbcConverter(Class<J> javaClass){
		return javaJdbcConverter.get(javaClass);
	}

	public void setDefaultSingleOpBuilders() {
		addSingleOpBuilder(SingleOpOperator.opIsNull,(expr, sql)->
			sql.add("IS NULL")
		);
		addSingleOpBuilder(SingleOpOperator.opIsNotNull,(expr, sql)->
			sql.add("IS NOT NULL")
		);
		addSingleOpBuilder(SingleOpOperator.opNot,(expr, sql)->
			SqlWithParams.sql("NOT ").add(sql)
		);
	}

	public Optional<String> getDefaultCatalogName() {
		return Optional.ofNullable(defaultCatalogName);
	}

	public Optional<String> getDefaultSchemaName() {
		return Optional.ofNullable(defaultSchemaName);
	}

	public ExprContext addBinOpBuilder(BinOpOperator operator, BinOpSqlBuilder builder){
		binOpMap = binOpMap.put(operator,builder);
		return this;
	}

	public ExprContext addSingleOpBuilder(SingleOpOperator operator, SingleOpSqlBuilder builder){
		singleOpMap = singleOpMap.put(operator,builder);
		return this;
	}


	public BinOpSqlBuilder getBinOpSqlBuilder(BinOpOperator operator){
		BinOpSqlBuilder sqlBuilder = binOpMap.get(operator);
		if(sqlBuilder == null) {
			throw new RuntimeException(
				"No BinOpSqlBuilder registered for " + operator
			);
		}
		return sqlBuilder;
	}

	public SingleOpSqlBuilder getSingleOpSqlBuilder(SingleOpOperator operator){
		return singleOpMap.get(operator);
	}


	public <E extends DExpr<J>,J,TF extends ExprTypeFactory<E,J>> ExprContext registerType(Class<E> exprClass,Class<TF> typeFactory){

		typeFactories = typeFactories.put(exprClass,Lazy.code(() -> {
			try {
				return typeFactory.getDeclaredConstructor(this.getClass()).newInstance(this);
			} catch(Exception e) {
				throw new RuntimeException("Exception while creating type factory " + typeFactory, e);
			}
		}));
		return this;
	}

	private <E extends DExpr<J>,J> ExprTypeFactory<E,J> get(Class<E> cls){
		Lazy<ExprTypeFactory> lazy = typeFactories.get(cls);
		if(lazy == null){
			throw new RuntimeException("No ExprTypeFactory registered for " + cls);
		}
		return lazy.get();
	}

	public <E extends DExpr<J>, J> E singleOp(Class<E> resultTypeClass, DExpr expr, SingleOpOperator operator) {
		return get(resultTypeClass).buildSingleOp(expr,operator);
	}

	public <E extends DExpr<J>, J> E binOp(Class<E> resultTypeClass, DExpr left, BinOpOperator operator, DExpr right) {
		return get(resultTypeClass).buildBinOp(left,operator,right);
	}

	public <E extends DExpr<J>, J> E val(Class<E> typeClass, J value) {
		return get(typeClass).buildVal(value);
	}


	public EBool booleanSingleOp(DExpr expr, SingleOpOperator operator){
		return singleOp(EBool.class, expr, operator);
	}

	public EBool booleanBinOp(DExpr left, BinOpOperator operator, DExpr right){
		return binOp(EBool.class, left, operator, right);

	}
	public EByte byteBinOp(DExpr left, BinOpOperator operator, DExpr right){
		return binOp(EByte.class, left, operator,right);
	}

	public EShort shortBinOp(DExpr left, BinOpOperator operator, DExpr right){
		return binOp(EShort.class, left, operator, right);
	}
	public EInt intBinOp(DExpr left, BinOpOperator operator, DExpr right){
		return binOp(EInt.class, left, operator, right);
	}
	public ELong longBinOp(DExpr left, BinOpOperator operator, DExpr right){
		return binOp(ELong.class, left, operator, right);
	}
	public EFloat floatBinOp(DExpr left, BinOpOperator operator, DExpr right){
		return binOp(EFloat.class, left, operator, right);
	}
	public EDouble doubleBinOp(DExpr left, BinOpOperator operator, DExpr right){
		return binOp(EDouble.class, left, operator, right);
	}
	public EBigDecimal bigDecimalBinOp(DExpr left, BinOpOperator operator, DExpr right){
		return binOp(EBigDecimal.class, left, operator, right);
	}

	public EBool val(Boolean v) { return get(EBool.class).buildVal(v); }
	public EByte val(Byte v) { return get(EByte.class).buildVal(v); }
	public EShort val(Short v) { return get(EShort.class).buildVal(v); }
	public EInt val(Integer v) { return get(EInt.class).buildVal(v); }
	public ELong val(Long v) { return get(ELong.class).buildVal(v); }
	public EFloat val(Float v) { return get(EFloat.class).buildVal(v); }
	public EDouble val(Double v) { return get(EDouble.class).buildVal(v); }
	public EBigDecimal val(BigDecimal v) { return get(EBigDecimal.class).buildVal(v); }
	public EString val(String v) { return get(EString.class).buildVal(v);}


	public <E1 extends DExpr<J1>,J1> ESelection<J1> createESelection(TypedSelection1Impl<?,J1> selection1){
		ESelectionTypeFactory tf = (ESelectionTypeFactory)getTypeFactory(ESelection.class);
		return tf.create(selection1);
	}


	public EBool eq(DExpr left, DExpr right){
		return booleanBinOp(left, BinOpOperator.opEq,right);
	}
	public EBool notEq(DExpr left, DExpr right){
		return booleanBinOp(left, BinOpOperator.opNotEq,right);
	}
	public EBool lt(DExpr left, DExpr right){
		return booleanBinOp(left, BinOpOperator.opLt,right);
	}
	public EBool ltEq(DExpr left, DExpr right){
		return booleanBinOp(left, BinOpOperator.opLtEq,right);
	}
	public EBool gt(DExpr left, DExpr right){
		return booleanBinOp(left, BinOpOperator.opGt,right);
	}
	public EBool gtEq(DExpr left, DExpr right){
		return booleanBinOp(left, BinOpOperator.opGtEq,right);
	}
	public EBool isNull(DExpr expr) { return booleanSingleOp(expr,SingleOpOperator.opIsNull);}
	public EBool isNotNull(DExpr expr) { return booleanSingleOp(expr,SingleOpOperator.opIsNotNull);}


	public SqlWithParams	toSql(DExpr expr){
		return getTypeFactory(expr).toSql(expr);
	}
	public <E extends DExpr<J>,J> E toSqlSelection(E expr, String preFixAlias){
		return getTypeFactory(expr).buildSelection(expr,preFixAlias);
	}


	public <E extends DExpr<J>,J> ExprTypeFactory<E,J> getTypeFactory(Class<E> typeClass){
		ExprTypeFactory<E,J> tf = get(typeClass);
		if(tf == null){
			throw new RuntimeException("Unknown Expression Type: " + typeClass);
		}
		return tf;
	}
	public <E extends DExpr<J>,J> ExprTypeFactory<E,J> getTypeFactory(E expr){
		return ((ExprTypeImpl)expr).getTypeFactory();
	}

	public SqlWithParams	getFromTableName(Table table){
		TableImpl impl = (TableImpl)table;
		return impl.getFromName(defaultCatalogName,defaultSchemaName);
	}

	public <
		E1 extends DExpr<J1>, J1,
		E2 extends DExpr<J2>, J2
		>
	ETuple2<E1, J1, E2, J2> tuple(E1 e1, E2 e2) {
		Tuple2TypeFactory tf = (Tuple2TypeFactory)getTypeFactory(ETuple2.class);
		return tf.of(e1,e2);
	}

	public <
		E1 extends DExpr<J1>, J1
		, E2 extends DExpr<J2>, J2
		, E3 extends DExpr<J3>, J3
		>
	ETuple3<E1, J1, E2, J2, E3, J3> tuple(E1 e1, E2 e2, E3 e3) {
		Tuple3TypeFactory tf = (Tuple3TypeFactory) getTypeFactory(ETuple3.class);
		return tf.of(e1, e2, e3);
	}

	public <
		E1 extends DExpr<J1>, J1
		, E2 extends DExpr<J2>, J2
		, E3 extends DExpr<J3>, J3
		, E4 extends DExpr<J4>, J4
		>
	ETuple4<E1, J1, E2, J2, E3, J3, E4, J4> tuple(E1 e1, E2 e2, E3 e3, E4 e4) {
		Tuple4TypeFactory tf = (Tuple4TypeFactory) getTypeFactory(ETuple4.class);
		return tf.of(e1, e2, e3, e4);
	}

	public <
		E1 extends DExpr<J1>, J1
		, E2 extends DExpr<J2>, J2
		, E3 extends DExpr<J3>, J3
		, E4 extends DExpr<J4>, J4
		, E5 extends DExpr<J5>, J5
		>
	ETuple5<E1, J1, E2, J2, E3, J3, E4, J4, E5, J5> tuple(E1 e1, E2 e2, E3 e3, E4 e4, E5 e5) {
		Tuple5TypeFactory tf = (Tuple5TypeFactory) getTypeFactory(ETuple5.class);
		return tf.of(e1, e2, e3, e4, e5);
	}

	public <
		E1 extends DExpr<J1>, J1
		, E2 extends DExpr<J2>, J2
		, E3 extends DExpr<J3>, J3
		, E4 extends DExpr<J4>, J4
		, E5 extends DExpr<J5>, J5
		, E6 extends DExpr<J6>, J6
		>
	ETuple6<E1, J1, E2, J2, E3, J3, E4, J4, E5, J5, E6, J6> tuple(E1 e1, E2 e2, E3 e3, E4 e4, E5 e5, E6 e6) {
		Tuple6TypeFactory tf = (Tuple6TypeFactory) getTypeFactory(ETuple6.class);
		return tf.of(e1, e2, e3, e4, e5, e6);
	}

	public <
		E1 extends DExpr<J1>, J1
		, E2 extends DExpr<J2>, J2
		, E3 extends DExpr<J3>, J3
		, E4 extends DExpr<J4>, J4
		, E5 extends DExpr<J5>, J5
		, E6 extends DExpr<J6>, J6
		, E7 extends DExpr<J7>, J7

		>
	ETuple7<E1, J1, E2, J2, E3, J3, E4, J4, E5, J5, E6, J6, E7, J7> tuple(E1 e1, E2 e2, E3 e3, E4 e4, E5 e5, E6 e6,
																		  E7 e7
	) {
		Tuple7TypeFactory tf = (Tuple7TypeFactory) getTypeFactory(ETuple7.class);
		return tf.of(e1, e2, e3, e4, e5, e6, e7);
	}

	public ExprTypeJdbcConvert	getJdbcConverter(DExpr expr){
		return getTypeFactory(expr).getJdbcConverter(expr);
	}
	static private Object notFound = new Object();
	public <E1 extends DExpr<J1>,J1> Param<E1> param(Class<E1> typeClass, String name){

		ExprTypeFactory<E1,J1> tf =  getTypeFactory(typeClass);
		Function<PMap<String,Object>,Object> getter = map-> {
			Object value = map.getOrDefault(name,notFound);
			if(notFound == value){
				throw new RuntimeException("Param " + name + " Not found in " + map.keys().toString(", "));
			}
			return value;
		};
		return new Param<>(name,tf.buildParam(getter));
	}


}
