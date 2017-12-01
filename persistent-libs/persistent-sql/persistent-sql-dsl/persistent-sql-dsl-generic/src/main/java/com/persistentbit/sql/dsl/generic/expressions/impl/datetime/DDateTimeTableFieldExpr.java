package com.persistentbit.sql.dsl.generic.expressions.impl.datetime;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.exprcontext.DbTableFieldExprContext;
import com.persistentbit.sql.dsl.generic.expressions.DExprDateTime;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public class DDateTimeTableFieldExpr extends DDateTimeAbstract{
	private final DbTableFieldExprContext context;

	public DDateTimeTableFieldExpr(DbTableFieldExprContext context) {
		this.context = context;
	}

	@Override
	public SqlWithParams toSql(DbSqlContext context) {
		return new SqlWithParams(this.context._getFieldSelectionName(context));
	}
	@Override
	public DExprDateTime withSelectionAlias(String alias) {
		return alias == null ? this : super.withSelectionAlias(alias + "_" + context._getFieldName());
	}
}
