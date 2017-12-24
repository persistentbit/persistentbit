package com.persistentbit.sql.dsl.expressions.impl.strategies;

import com.persistentbit.sql.dsl.SqlWithParams;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public interface TypeStrategy<J>{

	SqlWithParams _toSql();


	String _createAliasName(String aliasPrefix);
}
