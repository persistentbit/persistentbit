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
	private final String fieldName;

	public GenericDbTableFieldExprContext(DbTableContext tableContext, String fieldName) {
		this.tableContext = tableContext;
		this.fieldName = fieldName;
	}
}
