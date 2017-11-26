package com.persistentbit.sql.dsl.generic.expressions.impl.dboolean;

import com.persistentbit.sql.dsl.generic.expressions.DExpr;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/11/17
 */
public class DBooleanBinOp extends DBooleanAbstract{
	public enum Operator {
		opLt, opLtEq, opGt, opGtEq, opEq, opNotEq, like
	}
	private final DExpr left;
	private final Operator operator;
	private final DExpr right;

	public DBooleanBinOp(DExpr left, Operator operator, DExpr right) {
		this.left = left;
		this.operator = operator;
		this.right = right;
	}
}
