package com.persistentbit.sql.dsl.generic.impl.dstring;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.DExpr;
import com.persistentbit.sql.dsl.generic.DExprBoolean;
import com.persistentbit.sql.dsl.generic.DExprString;
import com.persistentbit.sql.dsl.generic.impl.DImpl;
import com.persistentbit.sql.dsl.generic.impl.dboolean.DBooleanBinOp;
import com.persistentbit.sql.dsl.generic.impl.dboolean.DBooleanIn;
import com.persistentbit.sql.dsl.generic.impl.dboolean.DBooleanSingleOp;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/11/17
 */
public abstract class DStringAbstract extends DImpl<String> implements DExprString{


	DExprString _value(String value){
		return new DStringValue(value);
	}

	@Override
	public DExprString concat(DExpr<String> other) {
		return new DStringFunction("CONCAT", PList.val(this,other));
	}

	@Override
	public DExprString concat(String other) {
		return concat(_value(other));
	}

	@Override
	public DExprBoolean like(DExpr<String> likeOther) {
		return new DBooleanBinOp(this, DBooleanBinOp.Operator.like, likeOther);
	}

	@Override
	public DExprBoolean eq(DExpr<String> other) {
		return new DBooleanBinOp(this, DBooleanBinOp.Operator.opEq, other);
	}

	@Override
	public DExprBoolean eq(String other) {
		return eq(_value(other));
	}

	@Override
	public DExprBoolean notEq(DExpr<String> other) {
		return new DBooleanBinOp(this, DBooleanBinOp.Operator.opNotEq, other);
	}

	@Override
	public DExprBoolean lt(DExpr<String> other) {
		return new DBooleanBinOp(this, DBooleanBinOp.Operator.opLt, other);
	}

	@Override
	public DExprBoolean gt(DExpr<String> other) {
		return new DBooleanBinOp(this, DBooleanBinOp.Operator.opGt, other);
	}

	@Override
	public DExprBoolean ltEq(DExpr<String> other) {
		return new DBooleanBinOp(this, DBooleanBinOp.Operator.opLtEq, other);
	}

	@Override
	public DExprBoolean gtEq(DExpr<String> other) {
		return new DBooleanBinOp(this, DBooleanBinOp.Operator.opGtEq, other);
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
	public DExprBoolean like(String likeOther) {
		return like(_value(likeOther));
	}

	@Override
	public DExprBoolean notEq(String other) {
		return notEq(_value(other));
	}

	@Override
	public DExprBoolean lt(String other) {
		return lt(_value(other));
	}

	@Override
	public DExprBoolean gt(String other) {
		return gt(_value(other));
	}

	@Override
	public DExprBoolean ltEq(String other) {
		return ltEq(_value(other));
	}

	@Override
	public DExprBoolean gtEq(String other) {
		return gtEq(_value(other));
	}


	@Override
	public DExprBoolean in(PList<DExpr<String>> values) {
		return new DBooleanIn((PList)values);
	}

	@Override
	public DExprBoolean in(DExpr<String>... values) {
		return in(PList.val(values));
	}
}