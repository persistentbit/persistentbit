package com.persistentbit.sql.dsl.generic.expressions.impl;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExprTable;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;

/**
 * TODOC
 *
 * @author petermuys
 * @since 1/12/17
 */
public interface DImplTable {

	SqlWithParams _toSqlFrom(DbSqlContext context);
	SqlWithParams _getInsertList(DbSqlContext context);

	static DImplTable _get(DExprTable tableExpr){
		if(tableExpr instanceof DTableExprImpl){
			return ((DTableExprImpl) tableExpr)._internalTable;
		}
		return (DImplTable)tableExpr;
	}
}
