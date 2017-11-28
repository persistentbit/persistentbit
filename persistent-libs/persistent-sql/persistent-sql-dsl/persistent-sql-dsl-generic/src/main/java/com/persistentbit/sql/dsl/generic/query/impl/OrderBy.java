package com.persistentbit.sql.dsl.generic.query.impl;

import com.persistentbit.sql.dsl.generic.expressions.DExpr;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */

public class OrderBy{

	private final DExpr<?> expr;
	private final Direction dir;
	public OrderBy(DExpr<?> expr, Direction dir) {
		this.expr = expr;
		this.dir = dir;
	}

	public DExpr<?> getExpr() {
		return expr;
	}

	public Direction getDir() {
		return dir;
	}

	public enum Direction{
		asc, desc
	}
}
