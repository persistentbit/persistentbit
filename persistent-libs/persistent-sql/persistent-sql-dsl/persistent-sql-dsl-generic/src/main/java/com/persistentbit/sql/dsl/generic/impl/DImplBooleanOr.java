package com.persistentbit.sql.dsl.generic.impl;

import com.persistentbit.sql.dsl.generic.DExpr;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/11/17
 */
public class DImplBooleanOr extends DImplBaseBoolean{
	private final DExpr<Boolean> left;
	private final DExpr<Boolean> right;

	public DImplBooleanOr(DExpr<Boolean> left, DExpr<Boolean> right) {
		this.left = left;
		this.right = right;
	}
}
