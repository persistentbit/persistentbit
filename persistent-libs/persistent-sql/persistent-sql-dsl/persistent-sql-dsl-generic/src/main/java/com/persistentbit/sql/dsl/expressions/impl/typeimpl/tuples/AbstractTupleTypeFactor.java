package com.persistentbit.sql.dsl.expressions.impl.typeimpl.tuples;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.BinOpOperator;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.SingleOpOperator;

import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 4/01/18
 */
public abstract class AbstractTupleTypeFactor implements ExprTypeFactory{

	@Override
	public DExpr buildVal(Object value) {
		return null;
	}

	@Override
	public DExpr buildParam(Function paramGetter) {
		return null;
	}

	@Override
	public DExpr buildCall(String callName, Object... exprOrStringParams) {
		return null;
	}

	@Override
	public DExpr buildTableField(String fieldSelectionName, String fieldName, String columnName) {
		return null;
	}

	@Override
	public DExpr buildBinOp(DExpr left, BinOpOperator op, DExpr right) {
		return null;
	}

	@Override
	public DExpr buildSingleOp(DExpr expr, SingleOpOperator op) {
		return null;
	}

	@Override
	public ExprContext getExprContext() {
		return null;
	}

	@Override
	public Class getTypeClass() {
		return null;
	}
}
