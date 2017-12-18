package com.persistentbit.sql.dsl.generic.expressions.impl.typeimpl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.BinOpOperator;
import com.persistentbit.sql.dsl.generic.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.ExprTypeFactory;
import com.persistentbit.sql.dsl.generic.expressions.impl.SingleOpOperator;
import com.persistentbit.sql.dsl.generic.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.generic.expressions.impl.strategies.*;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.utils.Lazy;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public abstract class AbstractTypeFactory<E extends DExpr<J>,J> implements ExprTypeFactory<E,J>{
	protected final ExprContext context;
	private final Lazy<ExprTypeJdbcConvert<J>> jdbcConvert;

	public AbstractTypeFactory(ExprContext context) {
		this.context = context;
		this.jdbcConvert = Lazy.code(()-> doGetJdbcConverter());
	}


	protected abstract E buildWithStrategy(TypeStrategy<J> strategy);
	protected abstract ExprTypeJdbcConvert	doGetJdbcConverter();


	@Override
	public ExprContext getExprContext() {
		return context;
	}



	public TypeStrategy<J> getTypeStrategy(DExpr<J> expr) {
		return ((AbstractTypeImpl<?,J>)expr).getTypeStrategy();
	}

	@Override
	public <V extends J> E buildVal(V value) {
		return buildWithStrategy(
			new ValTypeStrategy<>(
				getTypeClass(), this, value, jdbcConvert));
	}
	@Override
	public E buildAlias(String alias) {
		return buildWithStrategy(
			new AliasTypeStrategie<>(
				getTypeClass(),this,alias));
	}

	@Override
	public E buildBinOp(DExpr left, BinOpOperator op, DExpr right) {
		return buildWithStrategy(
			new BinOpTypeStrategy<>(
				getTypeClass(),this,left,op,right));
	}

	@Override
	public E buildSingleOp(DExpr expr, SingleOpOperator op) {
		return buildWithStrategy(
			new SingleOpTypeStrategy<>(
				getTypeClass(),this,expr,op));
	}

	@Override
	public E buildTableField(String fieldSelectionName, String fieldName) {
		return buildWithStrategy(
			new TableColumnTypeStrategy<>(
				getTypeClass(),this,fieldSelectionName,fieldName));
	}

	@Override
	public E buildSelection(E expr, String prefixAlias) {
		if(prefixAlias == null){
			return expr;
		}
		String newAlias = getTypeStrategy(expr)._createAliasName(prefixAlias);
		return buildWithStrategy(
			new SelectionStrategy<>(
				getTypeClass(),this,expr,newAlias));
	}

	@Override
	public PList<DExpr> expand(E expr) {
		return PList.val(expr);
	}

	@Override
	public SqlWithParams toSql(E expr) {
		return getTypeStrategy(expr)._toSql();
	}
}
