package com.persistentbit.sql.dsl.generic.expressions.impl.experiment;

import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.strategies.TypeStrategy;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class ExprTypeImpl<E extends DExpr<J>,J> implements DExpr<J>{

	protected final Class<E> typeClass;
	protected final TypeStrategy<J>	typeStrategy;

	public ExprTypeImpl(Class<E> typeClass,
						TypeStrategy<J> typeStrategy
	) {
		this.typeClass = typeClass;
		this.typeStrategy = typeStrategy;
	}
	public Class<E> getTypeClass(){
		return typeClass;
	}
	public TypeStrategy<J> getStrategy(){
		return typeStrategy;
	}
}
