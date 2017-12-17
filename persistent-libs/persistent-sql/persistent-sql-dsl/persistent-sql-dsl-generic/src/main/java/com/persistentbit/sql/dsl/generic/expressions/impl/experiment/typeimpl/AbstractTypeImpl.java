package com.persistentbit.sql.dsl.generic.expressions.impl.experiment.typeimpl;

import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.ExprTypeImpl;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.strategies.TypeStrategy;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public abstract class AbstractTypeImpl<E extends DExpr<J>,J> extends ExprTypeImpl<E,J>{

	public AbstractTypeImpl(Class<E> typeClass,
							TypeStrategy<J> typeStrategy
	) {
		super(typeClass, typeStrategy);
	}
}
