package com.persistentbit.sql.dsl.generic.impl.dboolean;

import com.persistentbit.sql.dsl.generic.DExpr;
import com.persistentbit.sql.dsl.generic.DExprBoolean;
import com.persistentbit.sql.dsl.generic.impl.DImpl;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/11/17
 */
public abstract class DBooleanAbstract extends DImpl<Boolean> implements DExprBoolean{

	@Override
	public DExprBoolean not() {
		return new DBooleanSingleOp(this, DBooleanSingleOp.Operator.opNot);
	}

	@Override
	public DExprBoolean and(DExpr<Boolean> other) {
		return new DBooleanAndOr(this, DBooleanAndOr.Operator.opAnd, other);
	}

	@Override
	public DExprBoolean or(DExpr<Boolean> other) {
		return new DBooleanAndOr(this, DBooleanAndOr.Operator.opOr, other);
	}

	@Override
	public DExprBoolean eq(DExpr<Boolean> other) {
		return new DBooleanBinOp(this, DBooleanBinOp.Operator.opEq,other);
	}
	@Override
	public DExprBoolean notEq(DExpr<Boolean> other) {
		return new DBooleanBinOp(this, DBooleanBinOp.Operator.opNotEq,other);
	}

	@Override
	public DExprBoolean isNull() {
		return new DBooleanSingleOp(this, DBooleanSingleOp.Operator.opIsNull);
	}

	@Override
	public DExprBoolean isNotNull() {
		return new DBooleanSingleOp(this, DBooleanSingleOp.Operator.opIsNotNull);
	}
}
