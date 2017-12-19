package com.persistentbit.sql.dsl.generic.expressions.impl;

import com.persistentbit.sql.dsl.generic.expressions.DExpr;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public interface ExprTypeImpl<E extends DExpr<J>,J> implements DExpr<J>{

	ExprTypeFactory<E,J> getTypeFactory();
}
