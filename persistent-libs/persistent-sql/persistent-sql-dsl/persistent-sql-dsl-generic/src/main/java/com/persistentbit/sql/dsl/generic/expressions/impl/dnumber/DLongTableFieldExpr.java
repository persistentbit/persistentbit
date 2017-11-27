package com.persistentbit.sql.dsl.generic.expressions.impl.dnumber;

import com.persistentbit.sql.dsl.exprcontext.DbTableFieldExprContext;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public class DLongTableFieldExpr extends DLongAbstract{
	private final DbTableFieldExprContext context;

	public DLongTableFieldExpr(DbTableFieldExprContext context) {
		this.context = context;
	}
}
