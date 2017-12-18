package com.persistentbit.sql.dsl.generic.expressions.impl.typeimpl;

import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.ExprTypeImpl;
import com.persistentbit.sql.dsl.generic.expressions.impl.strategies.TypeStrategy;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public abstract class AbstractTypeImpl<E extends DExpr<J>, J> extends ExprTypeImpl<E, J>{

	protected final TypeStrategy<J> typeStrategy;

	public AbstractTypeImpl(TypeStrategy<J> typeStrategy) {
		this.typeStrategy = typeStrategy;
	}

	public TypeStrategy<J> getTypeStrategy() {
		return typeStrategy;
	}
}
