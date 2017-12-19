package com.persistentbit.sql.dsl.expressions.impl.strategies;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;
import com.persistentbit.sql.dsl.SqlWithParams;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class SelectionStrategy<E extends DExpr<J>,J> extends AbstractTypeStrategy<J>{

	private final E expr;
	private final String alias;

	public SelectionStrategy(Class<? extends DExpr<J>> typeClass,
							 ExprTypeFactory exprTypeFactory
							 , E expr, String alias
	) {
		super(typeClass, exprTypeFactory);
		this.expr = expr;
		this.alias = alias;
	}
	@Override
	public SqlWithParams _toSql() {
		return exprTypeFactory.toSql(expr)
			.add(alias);
	}

	@Override
	public String _createAliasName(String aliasPrefix) {
		return aliasPrefix;
	}

	@Override
	public String toString() {
		return _toSql().toString();
	}
}
