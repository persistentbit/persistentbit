package com.persistentbit.sql.dsl.expressions.impl.typeimpl;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.sql.dsl.expressions.EComparableMixIn;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public interface TypeImplComparableMixin<E extends DExpr<J>,J> extends EComparableMixIn<E,J>{

	ExprTypeFactory<E,J> getTypeFactory();
	E getThis();

	@Override
	default EBool eq(E other) {
		return getTypeFactory().getExprContext().eq(getThis(),other);
	}

	@Override
	default EBool eq(J other) {
		return eq(getTypeFactory().getExprContext().buildVal(getThis(), other));
	}

	@Override
	default EBool notEq(E other) {
		return getTypeFactory().getExprContext().notEq(getThis(),other);
	}

	@Override
	default EBool notEq(J other) {
		return notEq(getTypeFactory().getExprContext().buildVal(getThis(), other));

	}

	@Override
	default EBool lt(E other) {
		return getTypeFactory().getExprContext().lt(getThis(),other);
	}

	@Override
	default EBool lt(J other) {
		return lt(getTypeFactory().getExprContext().buildVal(getThis(), other));
	}

	@Override
	default EBool gt(E other) {
		return getTypeFactory().getExprContext().gt(getThis(),other);
	}

	@Override
	default EBool gt(J other) {
		return gt(getTypeFactory().getExprContext().buildVal(getThis(), other));
	}

	@Override
	default EBool ltEq(E other) {
		return getTypeFactory().getExprContext().ltEq(getThis(),other);
	}

	@Override
	default EBool ltEq(J other) {
		return ltEq(getTypeFactory().getExprContext().buildVal(getThis(), other));
	}

	@Override
	default EBool gtEq(E other) {
		return getTypeFactory().getExprContext().gtEq(getThis(),other);
	}

	@Override
	default EBool gtEq(J other) {
		return gtEq(getTypeFactory().getExprContext().buildVal(getThis(), other));
	}

	@Override
	default EBool isNull() {
		return getTypeFactory().getExprContext().isNull(getThis());
	}

	@Override
	default EBool isNotNull() {
		return getTypeFactory().getExprContext().isNotNull(getThis());
	}
}
