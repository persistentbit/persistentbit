package com.persistentbit.sql.dsl.expressions.impl.typeimpl;

import com.persistentbit.collections.PList;
import com.persistentbit.collections.PMap;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.BinOpOperator;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.SingleOpOperator;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.expressions.impl.strategies.*;

import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public abstract class AbstractTypeFactory<E extends DExpr<J>, J> implements ExprTypeFactory<E, J>{

	protected final ExprContext            context;
	private final   ExprTypeJdbcConvert<J> jdbcConvert;

	public AbstractTypeFactory(ExprContext context, ExprTypeJdbcConvert<J> jdbcConvert) {
		this.context = context;
		this.jdbcConvert = jdbcConvert;
	}


	protected abstract E buildWithStrategy(TypeStrategy<J> strategy);


	@Override
	public ExprTypeJdbcConvert<J> getJdbcConverter(DExpr expr) {
		return jdbcConvert;
	}

	@Override
	public ExprContext getExprContext() {
		return context;
	}


	public TypeStrategy<J> getTypeStrategy(DExpr<J> expr) {
		return ((AbstractTypeImpl<?, J>) expr).getTypeStrategy();
	}

	@Override
	public <V extends J> E buildVal(V value) {
		return buildWithStrategy(
			new ValTypeStrategy<>(
				getTypeClass(), this, value, jdbcConvert));
	}

	@Override
	public E buildParam(Function<PMap<String, Object>, Object> paramGetter) {
		return buildWithStrategy(
			new ParamTypeStrategy<>(getTypeClass(), this, paramGetter, jdbcConvert)
		);
	}

	@Override
	public E buildCall(String callName, DExpr[] params) {
		return buildWithStrategy(
			new FunctionCallTypeStrategy<>(getTypeClass(), this, callName, params)
		);
	}

	@Override
	public E buildAlias(String alias) {
		return buildWithStrategy(
			new AliasTypeStrategy<>(
				getTypeClass(), this, alias));
	}

	@Override
	public E buildBinOp(DExpr left, BinOpOperator op, DExpr right) {
		return buildWithStrategy(
			new BinOpTypeStrategy<>(
				getTypeClass(), this, left, op, right));
	}

	@Override
	public E buildSingleOp(DExpr expr, SingleOpOperator op) {
		return buildWithStrategy(
			new SingleOpTypeStrategy<>(
				getTypeClass(), this, expr, op));
	}

	@Override
	public E buildTableField(String fieldSelectionName, String fieldName, String columnName) {
		return buildWithStrategy(
			new TableColumnTypeStrategy<>(
				getTypeClass(), this, fieldSelectionName, fieldName, columnName));
	}

	@Override
	public E onlyTableColumn(E expr) {
		return buildWithStrategy(getTypeStrategy(expr).onlyColumnName());
	}

	@Override
	public E buildSelection(E expr, String prefixAlias) {
		if(prefixAlias == null) {
			return expr;
		}
		String newAlias = getTypeStrategy(expr)._createAliasName(prefixAlias);
		return buildWithStrategy(
			new SelectionStrategy<>(
				getTypeClass(), this, expr, newAlias));
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
