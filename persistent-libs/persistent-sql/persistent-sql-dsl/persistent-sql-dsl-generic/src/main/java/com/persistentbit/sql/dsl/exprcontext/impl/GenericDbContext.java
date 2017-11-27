package com.persistentbit.sql.dsl.exprcontext.impl;

import com.persistentbit.sql.dsl.exprcontext.DbContext;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public class GenericDbContext implements DbContext{

	private final DbSqlContext	sqlContext;

	public GenericDbContext(DbSqlContext sqlContext) {
		this.sqlContext = sqlContext;
	}

	@Override
	public DbTableContext forTable(String schemaName, String tableName) {
		String fullName = tableName;
		if(schemaName != null){
			fullName = schemaName + "." + tableName;
		}
		return new GenericDbTableContext(sqlContext, fullName);
	}


}
