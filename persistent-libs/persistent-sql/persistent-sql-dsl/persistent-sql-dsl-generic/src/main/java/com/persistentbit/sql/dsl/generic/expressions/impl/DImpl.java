package com.persistentbit.sql.dsl.generic.expressions.impl;

import com.persistentbit.sql.dsl.generic.expressions.DExpr;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/11/17
 */
public interface DImpl<T> extends DExpr<T>,DInternal<T>{


	@SuppressWarnings("unchecked")
	static public <T> DInternal<T> _get(DExpr<T> expr){
		return (DInternal<T>)expr;
	}
}
