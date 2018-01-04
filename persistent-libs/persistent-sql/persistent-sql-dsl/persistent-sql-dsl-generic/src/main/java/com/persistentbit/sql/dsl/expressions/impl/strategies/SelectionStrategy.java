package com.persistentbit.sql.dsl.expressions.impl.strategies;

import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class SelectionStrategy<E extends DExpr<J>,J> extends AbstractTypeStrategy<J>{

	private final ExprContext context;
	private final E           expr;
	private final String      alias;

	public SelectionStrategy(ExprContext context, E expr, String alias) {
		this.context = context;
		this.expr = expr;
		this.alias = alias;
	}
	@Override
	public SqlWithParams _toSql() {
		return context.toSql(expr)
			.add(alias);
	}

	@Override
	public String _createAliasName(String aliasPrefix) {
		return aliasPrefix;
	}


}
