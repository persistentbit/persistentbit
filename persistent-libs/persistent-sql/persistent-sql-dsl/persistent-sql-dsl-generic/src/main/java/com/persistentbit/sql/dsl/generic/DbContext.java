package com.persistentbit.sql.dsl.generic;

/**
 * TODOC
 *
 * @author petermuys
 * @since 26/11/17
 */
public interface DbContext{
	DbTableContext forTable(String schemaName, String tableName);
}
