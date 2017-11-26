package com.persistentbit.sql.dsl.generic.expressions.impl.dboolean;

import com.persistentbit.sql.dsl.generic.expressions.DExpr;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/11/17
 */
public class DBooleanAndOr extends DBooleanAbstract{
	public enum Operator{
		opAnd, opOr
	}
	private final DExpr<Boolean> left;
	private final Operator	operator;
	private final DExpr<Boolean> right;

	public DBooleanAndOr(DExpr<Boolean> left,
						 Operator operator,
						 DExpr<Boolean> right
	) {
		this.left = left;
		this.operator = operator;
		this.right = right;
	}
}
