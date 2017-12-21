package com.persistentbit.sql.dsl.expressions.impl.strategies;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.SqlWithParams;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public interface TypeStrategy<J>{

	SqlWithParams _toSql();

	PList<String> _getColumnNames();

	String _createAliasName(String aliasPrefix);
}
