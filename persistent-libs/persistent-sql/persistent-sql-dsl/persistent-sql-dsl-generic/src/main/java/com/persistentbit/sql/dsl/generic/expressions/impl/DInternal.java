package com.persistentbit.sql.dsl.generic.expressions.impl;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.sql.utils.rowreader.RowReader;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/11/17
 */
public interface DInternal<T>{


	default SqlWithParams _toSqlSelection(DbSqlContext context){
		return _toSql(context);
	}

	default SqlWithParams _toSql(DbSqlContext context) {
		return new SqlWithParams(toString());
	}

	T _read(DbSqlContext context, RowReader rowReader);

	DExpr<T> _withAlias(String alias);

}
