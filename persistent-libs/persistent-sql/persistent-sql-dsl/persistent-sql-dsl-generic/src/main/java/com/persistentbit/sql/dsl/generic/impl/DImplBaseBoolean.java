package com.persistentbit.sql.dsl.generic.impl;

import com.persistentbit.sql.dsl.generic.DExprBoolean;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/11/17
 */
public abstract class DImplBaseBoolean extends DImpl<Boolean> implements DExprBoolean{

	@Override
	public DExprBoolean not() {
		return new DImplBooleanNot(this);
	}

	@Override
	public DExprBoolean and(DExprBoolean other) {
		return new DImplBooleanAnd(this, other);
	}

	@Override
	public DExprBoolean or(DExprBoolean other) {
		return new DImplBooleanOr(this, other);
	}

	@Override
	public DExprBoolean eq(DExprBoolean other) {
		return new DImplBooleanEq(this, other);
	}
}
