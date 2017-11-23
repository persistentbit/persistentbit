package com.persistentbit.sql.dsl.generic.impl;

import com.persistentbit.sql.dsl.generic.DExpr;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/11/17
 */
public class DImplBooleanNot extends DImplBaseBoolean{
	private DExpr<Boolean> expr;

	public DImplBooleanNot(DExpr<Boolean> expr) {
		this.expr = expr;
	}
}
