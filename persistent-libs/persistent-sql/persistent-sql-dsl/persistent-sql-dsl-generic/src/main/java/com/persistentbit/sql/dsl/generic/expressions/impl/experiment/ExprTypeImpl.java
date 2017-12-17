package com.persistentbit.sql.dsl.generic.expressions.impl.experiment;

import com.persistentbit.sql.dsl.generic.expressions.DExpr;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public abstract class ExprTypeImpl<E extends DExpr<J>,J> implements DExpr<J>{

	public abstract Class<E>	getTypeClass();
}
