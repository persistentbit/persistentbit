package com.persistentbit.sql.dsl.generic.expressions.impl.experiment;

import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprBoolean;
import com.persistentbit.sql.dsl.generic.expressions.impl.dboolean.DBooleanBinOp;
import com.persistentbit.sql.dsl.generic.expressions.impl.dboolean.DBooleanSingleOp;
import com.persistentbit.utils.exceptions.ToDo;

/**
 * TODOC
 *
 * @author petermuys
 * @since 16/12/17
 */
public class EBoolean implements ExprDef<DExprBoolean,Boolean>, DExprBoolean{
	private final ExprContext context;

	public EBoolean(ExprContext context) {
		this.context = context;
	}

	@Override
	public DExprBoolean not() {
		return context.booleanSingleOp(this,DBooleanSingleOp.Operator.opNot);
	}

	@Override
	public DExprBoolean and(DExpr<Boolean> other) {
		throw new ToDo();//return context.booleanAndOr(this, DBooleanAndOr.Operator.opAnd, other);
	}

	@Override
	public DExprBoolean or(DExpr<Boolean> other) {
		throw new ToDo();//return context.booleanAndOr(this, DBooleanAndOr.Operator.opOr, other);
	}

	@Override
	public DExprBoolean eq(DExpr<Boolean> other) {
		return context.booleanBinOp(this, DBooleanBinOp.Operator.opEq,other);
	}
	@Override
	public DExprBoolean notEq(DExpr<Boolean> other) {
		return context.booleanBinOp(this, DBooleanBinOp.Operator.opNotEq,other);
	}

	@Override
	public DExprBoolean isNull() {
		return context.booleanSingleOp(this, DBooleanSingleOp.Operator.opIsNull);
	}

	@Override
	public DExprBoolean isNotNull() {
		return context.booleanSingleOp(this, DBooleanSingleOp.Operator.opIsNotNull);
	}
}
