package com.persistentbit.sql.dsl.generic.expressions.impl.dbitlist;

import com.persistentbit.collections.PBitList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;

/**
 * TODOC
 *
 * @author petermuys
 * @since 16/12/17
 */
public class DBitListAlias extends DBitListAbstract{
	private final DExpr<PBitList> expr;
	private final String          alias;

	public DBitListAlias(DExpr<PBitList> expr, String alias) {
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
