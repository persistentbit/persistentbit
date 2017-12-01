package com.persistentbit.sql.dsl.generic.expressions.impl.dstring;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;

/**
 * TODOC
 *
 * @author petermuys
 * @since 30/11/17
 */
public class DStringAlias extends DStringAbstract{
	private final String alias;
	private final DExpr<String> expr;

	public DStringAlias(String alias, DExpr<String> expr) {
		this.alias = alias;
		this.expr = expr;
	}

	@Override
	public SqlWithParams toSql(DbSqlContext context) {
		return new SqlWithParams(alias);
	}

	@Override
	public SqlWithParams toSqlSelection(DbSqlContext context) {
		return DImpl._get(expr).toSqlSelection(context).add(" AS " + alias);
	}

	@Override
	public SqlWithParams toSqlSelectableFrom(DbSqlContext context) {
		return DImpl._get(expr).toSqlSelectableFrom(context);
	}
}
