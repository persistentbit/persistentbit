package com.persistentbit.sql.dsl.generic.expressions.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprTable;
import com.persistentbit.sql.dsl.generic.query.Query;
import com.persistentbit.tuples.Tuple2;

/**
 * TODOC
 *
 * @author petermuys
 * @since 26/11/17
 */
public abstract class DTable<T> extends DImpl<T> implements DExprTable<T>{
	private final DbTableContext	_tableContext;
	protected PList<Tuple2<String,DExpr<?>>> _all;

	protected DTable(DbTableContext tableContext){
		this._tableContext = tableContext;
	}

	public Query	query() {
		return _tableContext.createQuery(this);
	}

}
