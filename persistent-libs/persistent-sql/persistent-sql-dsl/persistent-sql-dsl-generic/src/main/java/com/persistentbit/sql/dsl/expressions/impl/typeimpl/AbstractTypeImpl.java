package com.persistentbit.sql.dsl.expressions.impl.typeimpl;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeImpl;
import com.persistentbit.sql.dsl.expressions.impl.strategies.TypeStrategy;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public abstract class AbstractTypeImpl<E extends DExpr<J>, J> implements ExprTypeImpl<E, J>{

	protected final TypeStrategy<J> typeStrategy;

	public AbstractTypeImpl(TypeStrategy<J> typeStrategy) {
		this.typeStrategy = typeStrategy;
	}

	public TypeStrategy<J> getTypeStrategy() {
		return typeStrategy;
	}

	@Override
	public String toString() {
		return typeStrategy.toString();
	}
}
