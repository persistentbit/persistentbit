package com.persistentbit.sql.dsl.generic.expressions.impl.datetime;

import com.persistentbit.sql.dsl.exprcontext.DbTableFieldExprContext;

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
}
