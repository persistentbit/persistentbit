package com.persistentbit.sql.dsl.generic.expressions.impl.dbytelist;

import com.persistentbit.collections.PByteList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;

/**
 * TODOC
 *
 * @author petermuys
 * @since 16/12/17
 */
public class DByteListAlias extends DByteListAbstract{
	private final DExpr<PByteList> expr;
	private final String           alias;

	public DByteListAlias(DExpr<PByteList> expr, String alias) {
		this.expr = expr;
		this.alias = alias;
	}

	@Override
	public SqlWithParams _toSql(DbSqlContext context) {
		return SqlWithParams.sql(alias);
	}

	@Override
	public SqlWithParams _toSqlSelection(DbSqlContext context, String alias) {
		return _toSql(context).add(alias == null ? "" : " AS " + alias);
	}
}
