package com.persistentbit.sql.dsl.generic.expressions.impl.dnumber;

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
public class DLongAlias extends DLongAbstract{
	private final String alias;
	private final DExpr<Long> expr;

	public DLongAlias(String alias, DExpr<Long> expr) {
		this.alias = alias;
		this.expr = expr;
	}

	@Override
	public SqlWithParams _toSql(DbSqlContext context) {
		return SqlWithParams.sql(alias);
	}

	@Override
	public SqlWithParams _toSqlSelection(DbSqlContext context, String alias) {
		return DImpl._get(expr)._toSqlSelection(context,alias);
	}

}
