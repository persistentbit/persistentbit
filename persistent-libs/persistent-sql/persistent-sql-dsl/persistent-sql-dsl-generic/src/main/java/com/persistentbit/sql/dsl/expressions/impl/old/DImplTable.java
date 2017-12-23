package com.persistentbit.sql.dsl.expressions.impl.old;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.expressions.DExprTable;
import com.persistentbit.sql.dsl.SqlWithParams;

/**
 * TODOC
 *
 * @author petermuys
 * @since 1/12/17
 */
public interface DImplTable {

	SqlWithParams _toSqlFrom(DbSqlContext context);
	SqlWithParams _getInsertList(DbSqlContext context);
	PList<String> _getAutoGenKeyFieldNames();
	String getFullTableName();

	static DImplTable _get(DExprTable tableExpr){
		if(tableExpr instanceof DTableExprImpl){
			return ((DTableExprImpl) tableExpr)._internalTable;
		}
		return (DImplTable)tableExpr;
	}
}