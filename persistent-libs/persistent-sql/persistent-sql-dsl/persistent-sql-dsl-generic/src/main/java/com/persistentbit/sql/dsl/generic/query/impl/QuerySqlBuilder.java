package com.persistentbit.sql.dsl.generic.query.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.tuples.Tuple2;
import com.persistentbit.utils.exceptions.ToDo;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class QuerySqlBuilder{
	private final DImplSelectionAbstract	selection;
	private final DbSqlContext sqlContext;

	public QuerySqlBuilder(DImplSelectionAbstract selection) {
		this.selection = selection;
		this.sqlContext = selection.query.sqlContext;
	}

	public Tuple2<String,PList<DExpr>>	generate(){
		//selection.columns.map(expr -> toString()).
		throw new ToDo();
	}
}
