package com.persistentbit.sql.dsl.expressions.impl.strategies;

import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeImpl;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class SelectionStrategy<E extends DExpr<J>,J> extends AbstractTypeStrategy<J>{

	private final E           expr;
	private final String      alias;

	public SelectionStrategy(E expr, String alias) {
		this.expr = expr;
		this.alias = alias;
	}

	@Override
	public SqlWithParams _toSql(AbstractTypeImpl impl) {
		return impl.getContext().toSql(expr)
			.add(alias);
	}

	@Override
	public String _createAliasName(AbstractTypeImpl impl, String aliasPrefix) {
		return aliasPrefix;
	}


}
