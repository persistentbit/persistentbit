package com.persistentbit.sql.dsl.statements.select.impl;

import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ESelection;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.SqlStatement;
import com.persistentbit.sql.dsl.statements.select.TypedSelection1;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class TypedSelection1Impl<E1 extends DExpr<J1>, J1> implements TypedSelection1<E1, J1>, SqlStatement{

	final QueryImpl query;
	final E1 col1;

	final ExprContext context;

	public TypedSelection1Impl(QueryImpl query, E1 col1) {
		this.query = query;
		this.context = query.context;
		this.col1 = col1;
	}


	@Override
	public SqlWithParams toSql() {
		return toSql(null);
	}

	protected SqlWithParams toSql(String preFixAlias) {
		E1     colAsSelection = context.toSqlSelection(col1, preFixAlias);
		String from           = query.from.map(t -> context.getFromTableName(t)).toString(", ");

		SqlWithParams joins =
			SqlWithParams
				.empty
				.add(query.joins.map(j -> j.toSql()), System.lineSeparator());
		SqlWithParams res = SqlWithParams.sql("SELECT ");
		res = res.add(context.toSql(colAsSelection));
		res = res.add(SqlWithParams.nl);
		res = res.add(" FROM " + from);
		res = res.add(SqlWithParams.nl);
		res = res.add(joins);
		if(query.where != null){
			res = res.add(" WHERE ").add(context.toSql(query.where));
		}
		return res;
	}

	@Override
	public SubQuery1<E1, J1> asSubQuery(String name) {
		return new SubQuery1<>(this,name);
	}

	@Override
	public ESelection<J1> asExpr() {
		return context.createESelection(this);
	}

	@Override
	public String toString() {
		return toSql().toString();
	}


}
