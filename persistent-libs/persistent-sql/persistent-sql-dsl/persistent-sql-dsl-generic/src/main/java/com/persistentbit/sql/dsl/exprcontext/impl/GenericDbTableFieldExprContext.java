package com.persistentbit.sql.dsl.exprcontext.impl;

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

	public GenericDbTableFieldExprContext(DbTableContext tableContext, String columnName) {
		this.tableContext = tableContext;
		this.columnName = columnName;
	}

	@Override
	public String _getFieldSelectionName() {
		return tableContext.getNameOrAlias() + "." + columnName;
	}
}
