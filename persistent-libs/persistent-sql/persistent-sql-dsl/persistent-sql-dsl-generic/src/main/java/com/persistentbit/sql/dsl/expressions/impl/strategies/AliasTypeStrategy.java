package com.persistentbit.sql.dsl.expressions.impl.strategies;

import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class AliasTypeStrategy<J> extends AbstractTypeStrategy<J>{
	private final String alias;
	public AliasTypeStrategy(Class<? extends DExpr<J>> typeClass,
							 ExprTypeFactory exprTypeFactory,
							 String alias
	) {
		super(typeClass, exprTypeFactory);
		this.alias = alias;
	}
	@Override
	public SqlWithParams _toSql() {
		return SqlWithParams.sql(alias);
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
