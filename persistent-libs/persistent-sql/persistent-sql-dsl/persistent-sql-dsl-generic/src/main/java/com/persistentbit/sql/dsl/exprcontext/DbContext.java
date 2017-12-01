package com.persistentbit.sql.dsl.exprcontext;

/**
 * TODOC
 *
 * @author petermuys
 * @since 26/11/17
 */
public interface DbContext{
	DbTableContext forTable(String schemaName, String tableName);
	DbSqlContext	createSqlContext();
}
