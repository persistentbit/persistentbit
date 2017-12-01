package com.persistentbit.sql.dsl.exprcontext;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public interface DbTableFieldExprContext{

	String _getFieldSelectionName(DbSqlContext context);
	String _getFieldName();

}
