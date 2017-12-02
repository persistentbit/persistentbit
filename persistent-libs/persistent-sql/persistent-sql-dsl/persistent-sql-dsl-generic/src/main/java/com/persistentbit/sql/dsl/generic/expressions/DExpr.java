package com.persistentbit.sql.dsl.generic.expressions;

import com.persistentbit.sql.dsl.generic.expressions.impl.dtuples.DTuple2;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/11/17
 */
public interface DExpr<T>{



	default <T2> DExprTuple2<T,T2> tuple2(DExpr<T2> v2){
		return new DTuple2<>(this,v2);
	}
}
