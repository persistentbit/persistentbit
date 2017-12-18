package com.persistentbit.sql.dsl.generic.expressions.impl;

import com.persistentbit.collections.PMap;
import com.persistentbit.sql.dsl.generic.expressions.*;
import com.persistentbit.sql.dsl.generic.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.sql.utils.rowreader.RowReader;

import java.math.BigDecimal;
import java.sql.PreparedStatement;

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


	private PMap<Class<? extends DExpr>, ExprTypeFactory> typeFactories = PMap.empty();
	private PMap<BinOpOperator,BinOpSqlBuilder> binOpMap = PMap.empty();
	private PMap<SingleOpOperator,SingleOpSqlBuilder> singleOpMap = PMap.empty();

	private PMap<Class, ExprTypeJdbcConvert> javaJdbcConverter = PMap.empty();


	public ExprContext() {

	}

	public void setDefaultBinOpBuilders() {
		addBinOpBuilder(BinOpOperator.opEq,(left,sqlLeft,right,sqlRight)->
			sqlLeft.add("=").add(sqlRight)
		);
		addBinOpBuilder(BinOpOperator.opNotEq,(left,sqlLeft,right,sqlRight)->
			sqlLeft.add("<>").add(sqlRight)
		);
		addBinOpBuilder(BinOpOperator.opGt,(left,sqlLeft,right,sqlRight)->
			sqlLeft.add(">").add(sqlRight)
		);
		addBinOpBuilder(BinOpOperator.opGtEq,(left,sqlLeft,right,sqlRight)->
			sqlLeft.add(">=").add(sqlRight)
		);
		addBinOpBuilder(BinOpOperator.opLt,(left,sqlLeft,right,sqlRight)->
			sqlLeft.add("<").add(sqlRight)
		);
		addBinOpBuilder(BinOpOperator.opLtEq,(left,sqlLeft,right,sqlRight)->
			sqlLeft.add("<=").add(sqlRight)
		);
		addBinOpBuilder(BinOpOperator.opAnd,(left,sqlLeft,right,sqlRight)->
			sqlLeft.add(" AND ").add(sqlRight)
		);
		addBinOpBuilder(BinOpOperator.opOr,(left,sqlLeft,right,sqlRight)->
			sqlLeft.add(" OR ").add(sqlRight)
		);
		addBinOpBuilder(BinOpOperator.opLike,(left,sqlLeft,right,sqlRight)->
			sqlLeft.add(" LIKE ").add(sqlRight)
		);
		addBinOpBuilder(BinOpOperator.opConcat,(left,sqlLeft,right,sqlRight)->
			sqlLeft.add(" || ").add(sqlRight)
		);

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


	public ExprContext addBinOpBuilder(BinOpOperator operator, BinOpSqlBuilder builder){
		binOpMap = binOpMap.put(operator,builder);
		return this;
	}

	public ExprContext addSingleOpBuilder(SingleOpOperator operator, SingleOpSqlBuilder builder){
		singleOpMap = singleOpMap.put(operator,builder);
		return this;
	}


	public BinOpSqlBuilder getBinOpSqlBuilder(BinOpOperator operator){
		return binOpMap.get(operator);
	}

	public SingleOpSqlBuilder getSingleOpSqlBuilder(SingleOpOperator operator){
		return singleOpMap.get(operator);
	}


	public <E extends DExpr<J>,J,TF extends ExprTypeFactory<E,J>> ExprContext addType(
		Class<E> typeClass, TF typeFactory){
		typeFactories = typeFactories.put(typeClass,typeFactory);
		return this;
	}

	private <E extends DExpr<J>,J> ExprTypeFactory<E,J> get(Class<E> cls){
		return typeFactories.get(cls);
	}

	<E extends DExpr<J>,J>  E singleOp(Class<E> resultTypeClass, DExpr expr, SingleOpOperator operator){
		return get(resultTypeClass).buildSingleOp(expr,operator);
	}
	<E extends DExpr<J>,J>  E binOp(Class<E> resultTypeClass, DExpr left, BinOpOperator operator, DExpr right){
		return get(resultTypeClass).buildBinOp(left,operator,right);
	}
	<E extends DExpr<J>,J> E val(Class<E> typeClass, J value){
		return ((ExprTypeFactory<E,J>)get(typeClass)).buildVal(value);
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



	public <E extends DExpr<J>,J> ExprTypeFactory<E,J> getTypeFactory(Class<E> typeClass){
		return get(typeClass);
	}
	public <E extends DExpr<J>,J> ExprTypeFactory<E,J> getTypeFactory(E expr){
		return ((ExprTypeImpl)expr).getTypeFactory();
	}
}
