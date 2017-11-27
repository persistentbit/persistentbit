package com.persistentbit.sql.dsl.generic.expressions.impl.dstring;

import com.persistentbit.sql.dsl.exprcontext.DbTableFieldExprContext;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public class DStringTableFieldExpr extends DStringAbstract{
	private final DbTableFieldExprContext context;

	public DStringTableFieldExpr(DbTableFieldExprContext context) {
		this.context = context;
	}
}
