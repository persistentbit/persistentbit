package com.persistentbit.sql.dsl.generic.expressions.impl;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/11/17
 */
public interface DInternal<T>{


	default SqlWithParams toSqlSelection(DbSqlContext context){
		return toSql(context);
	}

	default SqlWithParams toSqlSelectableFrom(DbSqlContext context){
		return new SqlWithParams(toString());
	}

	default SqlWithParams toSql(DbSqlContext context) {
		return new SqlWithParams(toString());
	}
}
