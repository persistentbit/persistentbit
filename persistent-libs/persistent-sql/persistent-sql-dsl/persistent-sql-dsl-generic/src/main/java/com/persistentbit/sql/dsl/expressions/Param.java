package com.persistentbit.sql.dsl.expressions;

/**
 * TODOC
 *
 * @author petermuys
 * @since 21/12/17
 */
public class Param<E1 extends DExpr>{
	private final String name;
	private final E1 expr;

	public Param(String name, E1 expr) {
		this.name = name;
		this.expr = expr;
	}

	public String getName() {
		return name;
	}

	public E1 getExpr() {
		return expr;
	}
}
