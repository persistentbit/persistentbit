package com.persistentbit.sql.dsl.statements;

import com.persistentbit.sql.dsl.SqlWithParams;

/**
 * TODOC
 *
 * @author petermuys
 * @since 19/12/17
 */
public interface SqlStatement{
	SqlWithParams	toSql();
}
