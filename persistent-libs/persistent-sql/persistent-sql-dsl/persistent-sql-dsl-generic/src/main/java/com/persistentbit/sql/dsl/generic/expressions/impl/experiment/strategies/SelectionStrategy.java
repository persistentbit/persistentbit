package com.persistentbit.sql.dsl.generic.expressions.impl.experiment.strategies;

import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.ExprTypeFactory;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;

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
			.add(" AS " + alias);
	}

	@Override
	public String _createAliasName(String aliasPrefix) {
		return aliasPrefix;
	}
}
