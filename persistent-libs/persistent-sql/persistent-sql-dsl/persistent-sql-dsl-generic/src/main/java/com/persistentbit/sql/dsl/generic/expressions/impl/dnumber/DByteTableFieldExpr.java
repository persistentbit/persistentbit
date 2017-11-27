package com.persistentbit.sql.dsl.generic.expressions.impl.dnumber;

import com.persistentbit.sql.dsl.exprcontext.DbTableFieldExprContext;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public class DByteTableFieldExpr extends DByteAbstract{
	private final DbTableFieldExprContext context;

	public DByteTableFieldExpr(DbTableFieldExprContext context) {
		this.context = context;
	}
}
