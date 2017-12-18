package com.persistentbit.sql.dsl.generic.expressions.impl.typeimpl;

import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.EBool;
import com.persistentbit.sql.dsl.generic.expressions.EComparableMixIn;
import com.persistentbit.sql.dsl.generic.expressions.impl.ExprTypeFactory;

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
		return null;
	}

	@Override
	default EBool notEq(E other) {
		return getTypeFactory().getExprContext().notEq(getThis(),other);
	}

	@Override
	default EBool notEq(J other) {
		return null;
	}

	@Override
	default EBool lt(E other) {
		return getTypeFactory().getExprContext().lt(getThis(),other);
	}

	@Override
	default EBool lt(J other) {
		return null;
	}

	@Override
	default EBool gt(E other) {
		return getTypeFactory().getExprContext().gt(getThis(),other);
	}

	@Override
	default EBool gt(J other) {
		return null;
	}

	@Override
	default EBool ltEq(E other) {
		return getTypeFactory().getExprContext().ltEq(getThis(),other);
	}

	@Override
	default EBool ltEq(J other) {
		return null;
	}

	@Override
	default EBool gtEq(E other) {
		return getTypeFactory().getExprContext().gtEq(getThis(),other);
	}

	@Override
	default EBool gtEq(J other) {
		return null;
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
