package com.persistentbit.sql.dsl.expressions.impl.strategies;

import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.SingleOpOperator;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class SingleOpTypeStrategy<T> extends AbstractTypeStrategy<T>{

	private final ExprContext      context;
	private final DExpr            expr;
	private final SingleOpOperator operator;

	public SingleOpTypeStrategy(ExprContext context,
								DExpr expr,
								SingleOpOperator operator
	) {
		this.context = context;
		this.expr = expr;
		this.operator = operator;
	}


	@Override
	public SqlWithParams _toSql() {
		SqlWithParams sql = context.toSql(expr);
		return context.getSingleOpSqlBuilder(operator)
			.apply(expr,sql);
	}

	@Override
	public String _createAliasName(String aliasPrefix) {
		return aliasPrefix;
	}

	@Override
	public TypeStrategy<T> onlyColumnName() {
		return this;
	}
}
