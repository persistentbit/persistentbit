package com.persistentbit.sql.dsl.expressions.impl.strategies;

import com.persistentbit.sql.dsl.SqlWithParams;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class AliasTypeStrategy<J> extends AbstractTypeStrategy<J>{
	private final String alias;

	public AliasTypeStrategy(String alias) {
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


}
