package com.persistentbit.sql.dsl.expressions;

import com.persistentbit.sql.dsl.generic_old.query.Query;

/**
 * TODOC
 *
 * @author petermuys
 * @since 26/11/17
 */
public interface DExprTable<T>{
	Query query();

	DExpr<T> all();
}
