package com.persistentbit.sql.dsl.exprcontext.impl;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.exprcontext.DbTableFieldExprContext;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public class GenericDbTableFieldExprContext implements DbTableFieldExprContext{
	private final String fullTableName;
	private final String columnName;

	public GenericDbTableFieldExprContext(String fullTableName, String columnName) {
		this.fullTableName = fullTableName;
		this.columnName = columnName;
	}

	@Override
	public String _getFieldSelectionName(DbSqlContext sqlContext) {
		return fullTableName + "." + columnName;
	}

	@Override
	public String _getFieldName() {
		return columnName;
	}
}
