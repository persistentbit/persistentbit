package com.persistentbit.sql.dsl.generic.expressions.impl.old;

import com.persistentbit.sql.dsl.generic.expressions.DExpr;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/11/17
 */
public interface DImpl<T> extends DExpr<T>,DInternal<T>{


	@SuppressWarnings("unchecked")
	static <T> DInternal<T> _get(DExpr<T> expr){
		if(expr instanceof DTableExprImpl){
			return ((DTableExprImpl) expr)._internal;
		}
		return (DInternal<T>)expr;
	}
}
