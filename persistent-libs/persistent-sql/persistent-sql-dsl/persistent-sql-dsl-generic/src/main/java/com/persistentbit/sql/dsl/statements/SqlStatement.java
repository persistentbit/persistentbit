package com.persistentbit.sql.dsl.statements;

import com.persistentbit.sql.dsl.Sql;

/**
 * TODOC
 *
 * @author petermuys
 * @since 19/12/17
 */
public interface SqlStatement{

	Sql toSql();
}
