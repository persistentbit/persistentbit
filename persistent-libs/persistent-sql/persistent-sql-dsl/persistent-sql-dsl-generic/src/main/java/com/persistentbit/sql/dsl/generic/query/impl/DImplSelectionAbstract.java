package com.persistentbit.sql.dsl.generic.query.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class DImplSelectionAbstract{
	protected final QueryImpl query;
	protected final PList<DExpr> columns;

	public DImplSelectionAbstract(QueryImpl query,
								  PList<DExpr> columns
	) {
		this.query = query;
		this.columns = columns;
	}

	SqlWithParams toSql(DbSqlContext sqlContext){
		return new SqlWithParams("SELECT ")
			.add(columns.map(e -> DImpl._get(e).toSqlSelection(sqlContext)),", ")
			.add(" FROM ").add(
				query.from.map(e -> DImpl._get(e).toSqlSelectableFrom(sqlContext))
			)
			.add(query.joins.map(j -> j.toSql().nl()))
			.add(query.where == null
				? SqlWithParams.empty()
				: new SqlWithParams(" WHERE ").add(DImpl._get(query.where).toSqlSelection(sqlContext))
			)
		;
	}

	@Override
	public String toString() {
		return toSql(query.sqlContext).toString();
	}
}
