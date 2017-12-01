package com.persistentbit.sql.dsl.exprcontext.impl;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.sql.dsl.exprcontext.DbTableFieldExprContext;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public class GenericDbTableFieldExprContext implements DbTableFieldExprContext{
	private final DbTableContext tableContext;
	private final String columnName;
	private final String alias;

	public GenericDbTableFieldExprContext(DbTableContext tableContext, String columnName, @Nullable String alias) {
		this.tableContext = tableContext;
		this.columnName = columnName;
		this.alias = alias;
	}

	@Override
	public String _getFieldSelectionName(DbSqlContext sqlContext) {
		String insideName = tableContext.getTableNameOrAlias() + "." + columnName;
		return insideName;
	}

	@Override
	public String _getFieldName() {
		return columnName;
	}
}
