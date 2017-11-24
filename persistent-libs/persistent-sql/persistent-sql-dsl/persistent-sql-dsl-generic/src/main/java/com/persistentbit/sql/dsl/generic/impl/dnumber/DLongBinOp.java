package com.persistentbit.sql.dsl.generic.impl.dnumber;

import com.persistentbit.sql.dsl.generic.DExpr;
import com.persistentbit.sql.dsl.generic.DExprLong;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/11/17
 */
public class DLongBinOp extends DLongAbstract implements DExprLong{
	private final DExpr left;
	private final NumberBinOperator operator;
	private final DExpr right;

	public DLongBinOp(DExpr left, NumberBinOperator operator,
					  DExpr right
	) {
		this.left = left;
		this.operator = operator;
		this.right = right;
	}
}
