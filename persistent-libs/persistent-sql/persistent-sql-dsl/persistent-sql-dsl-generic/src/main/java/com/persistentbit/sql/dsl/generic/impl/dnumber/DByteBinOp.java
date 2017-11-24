package com.persistentbit.sql.dsl.generic.impl.dnumber;

import com.persistentbit.sql.dsl.generic.DExpr;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/11/17
 */
public class DByteBinOp extends DByteAbstract{
	private final DExpr left;
	private final NumberBinOperator operator;
	private final DExpr right;

	public DByteBinOp(DExpr left, NumberBinOperator operator, DExpr right) {
		this.left = left;
		this.operator = operator;
		this.right = right;
	}
}