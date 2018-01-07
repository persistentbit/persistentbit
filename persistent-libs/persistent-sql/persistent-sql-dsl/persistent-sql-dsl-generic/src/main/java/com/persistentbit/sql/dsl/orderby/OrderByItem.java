package com.persistentbit.sql.dsl.orderby;

import com.persistentbit.sql.dsl.expressions.DExpr;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */

public class OrderByItem{

	private final DExpr<?> expr;
	private final Direction dir;

	public OrderByItem(DExpr<?> expr, Direction dir) {
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
