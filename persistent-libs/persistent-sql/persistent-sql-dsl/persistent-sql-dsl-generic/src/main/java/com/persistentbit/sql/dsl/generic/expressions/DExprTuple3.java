package com.persistentbit.sql.dsl.generic.expressions;

import com.persistentbit.tuples.Tuple3;

/**
 * TODOC
 *
 * @author petermuys
 * @since 1/12/17
 */
public interface DExprTuple3<T1,T2,T3> extends DExpr<Tuple3<T1,T2,T3>>{
	DExpr<T1> v1();
	DExpr<T2> v2();
	DExpr<T3> v3();


}
