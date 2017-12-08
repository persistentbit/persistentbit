package com.persistentbit.sql.dsl.generic.expressions.impl.dnumber;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprBoolean;
import com.persistentbit.sql.dsl.generic.expressions.DExprNumber;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.impl.dboolean.DBooleanBinOp;
import com.persistentbit.sql.dsl.generic.expressions.impl.dboolean.DBooleanIn;
import com.persistentbit.sql.dsl.generic.expressions.impl.dboolean.DBooleanSingleOp;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/11/17
 */
public abstract class DNumberAbstract<N extends Number> implements DImpl<N> , DExprNumber<N>{

	abstract DExprNumber<N> _value(N value);

	@Override
	public DExprBoolean eq(DExpr<? extends Number> other) {
		return new DBooleanBinOp(this, DBooleanBinOp.Operator.opEq,other);
	}

	@Override
	public DExprBoolean eq(N other) {
		return eq(_value(other));
	}

	@Override
	public DExprBoolean notEq(DExpr<? extends Number> other) {
		return new DBooleanBinOp(this, DBooleanBinOp.Operator.opNotEq,other);
	}

	@Override
	public DExprBoolean notEq(N other) {
		return notEq(_value(other));
	}

	@Override
	public DExprBoolean lt(DExpr<? extends Number> other) {
		return new DBooleanBinOp(this, DBooleanBinOp.Operator.opLt,other);
	}

	@Override
	public DExprBoolean lt(N other) {
		return lt(_value(other));
	}

	@Override
	public DExprBoolean gt(DExpr<? extends Number> other) {
		return new DBooleanBinOp(this, DBooleanBinOp.Operator.opGt,other);
	}

	@Override
	public DExprBoolean gt(N other) {
		return gt(_value(other));
	}

	@Override
	public DExprBoolean ltEq(DExpr<? extends Number> other) {
		return new DBooleanBinOp(this, DBooleanBinOp.Operator.opLtEq,other);
	}

	@Override
	public DExprBoolean ltEq(N other) {
		return ltEq(_value(other));
	}

	@Override
	public DExprBoolean gtEq(DExpr<? extends Number> other) {
		return new DBooleanBinOp(this, DBooleanBinOp.Operator.opGtEq,other);
	}

	@Override
	public DExprBoolean gtEq(N other) {
		return gtEq(_value(other));
	}

	@Override
	public DExprBoolean isNull() {
		return new DBooleanSingleOp(this, DBooleanSingleOp.Operator.opIsNull);
	}

	@Override
	public DExprBoolean isNotNull() {
		return new DBooleanSingleOp(this, DBooleanSingleOp.Operator.opIsNotNull);
	}

	@Override
	public DExprBoolean in(PList<DExpr<? extends Number>> values) {
		return new DBooleanIn((PList)values);
	}

	@Override
	public DExprBoolean in(DExpr<? extends Number>... values) {
		return in(PList.val(values));
	}

	@Override
	public PList<DExpr> _expand() {
		return PList.val(this);
	}

}
