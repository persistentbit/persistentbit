package com.persistentbit.sql.dsl.generic.expressions;

import com.persistentbit.tuples.Tuple2;

/**
 * TODOC
 *
 * @author petermuys
 * @since 1/12/17
 */
public interface DExprTuple2<T1,T2> extends DExpr<Tuple2<T1,T2>>{
	DExpr<T1> v1();
	DExpr<T2> v2();


}
