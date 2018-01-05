package com.persistentbit.sql.dsl.expressions.impl.strategies;

import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.SingleOpOperator;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeImpl;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class SingleOpTypeStrategy<T> extends AbstractTypeStrategy<T>{

	private final DExpr            expr;
	private final SingleOpOperator operator;

	public SingleOpTypeStrategy(DExpr expr,
								SingleOpOperator operator
	) {
		this.expr = expr;
		this.operator = operator;
	}


	@Override
	public SqlWithParams _toSql(AbstractTypeImpl impl) {
		ExprContext   context = impl.getContext();
		SqlWithParams sql = context.toSql(expr);
		return context.getSingleOpSqlBuilder(operator)
			.apply(expr,sql);
	}

	@Override
	public String _createAliasName(AbstractTypeImpl impl, String aliasPrefix) {
		return aliasPrefix;
	}

	@Override
	public TypeStrategy<T> onlyColumnName(AbstractTypeImpl impl) {
		return this;
	}
}
