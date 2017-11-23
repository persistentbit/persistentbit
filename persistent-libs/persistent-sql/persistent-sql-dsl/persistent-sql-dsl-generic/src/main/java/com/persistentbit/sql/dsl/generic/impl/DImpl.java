package com.persistentbit.sql.dsl.generic.impl;

import com.persistentbit.sql.dsl.generic.DExpr;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/11/17
 */
public abstract class DImpl<T> implements DExpr<T>{


	@SuppressWarnings("unchecked")
	static <T> DInternal<T> get(DExpr<T> expr){
		return (DInternal<T>)expr;
	}
}
