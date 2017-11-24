package com.persistentbit.sql.dsl.generic.impl.dboolean;

import com.persistentbit.sql.dsl.generic.DExpr;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/11/17
 */
public class DBooleanSingleOp extends DBooleanAbstract{
	public enum Operator {
		opIsNull, opIsNotNull, opNot
	}
	private final DExpr<?> expr;
	private final Operator operator;

	public DBooleanSingleOp(DExpr<?> expr, Operator operator) {
		this.expr = expr;
		this.operator = operator;
	}
}
