package com.persistentbit.sql.dsl.expressions.impl;

import com.persistentbit.sql.dsl.expressions.DExpr;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public interface ExprTypeImpl<E extends DExpr<J>,J> extends DExpr<J>{

	ExprTypeFactory<E,J> getTypeFactory();
}
