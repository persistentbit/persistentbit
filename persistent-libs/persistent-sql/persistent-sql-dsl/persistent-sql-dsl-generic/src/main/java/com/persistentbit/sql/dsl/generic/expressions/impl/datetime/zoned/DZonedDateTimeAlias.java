package com.persistentbit.sql.dsl.generic.expressions.impl.datetime.zoned;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;

import java.time.ZonedDateTime;

/**
 * TODOC
 *
 * @author petermuys
 * @since 30/11/17
 */
public class DZonedDateTimeAlias extends DZonedDateTimeAbstract{
	private final String               alias;
	private final DExpr<ZonedDateTime> expr;

	public DZonedDateTimeAlias(String alias, DExpr<ZonedDateTime> expr) {
		this.alias = alias;
		this.expr = expr;
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
