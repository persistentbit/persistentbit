package com.persistentbit.sql.dsl.generic.impl;

import com.persistentbit.sql.dsl.generic.DExpr;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/11/17
 */
public class DImplBooleanEq extends DImplBaseBoolean{
	private DExpr<?>	left;
	private DExpr<?>	right;

	public DImplBooleanEq(DExpr<?> left, DExpr<?> right) {
		this.left = left;
		this.right = right;
	}
}
