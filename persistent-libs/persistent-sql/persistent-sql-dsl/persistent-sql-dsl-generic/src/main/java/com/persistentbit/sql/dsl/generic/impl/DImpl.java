package com.persistentbit.sql.dsl.generic.impl;

import com.persistentbit.sql.dsl.generic.DExpr;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/11/17
 */
public abstract class DImpl<T> implements DExpr<T>,DInternal<T>{


	@SuppressWarnings("unchecked")
	static <T> DInternal<T> _get(DExpr<T> expr){
		return (DInternal<T>)expr;
	}
}
