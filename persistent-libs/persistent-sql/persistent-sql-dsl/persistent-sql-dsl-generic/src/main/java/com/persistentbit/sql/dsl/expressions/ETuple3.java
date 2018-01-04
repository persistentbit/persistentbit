package com.persistentbit.sql.dsl.expressions;

import com.persistentbit.tuples.Tuple3;

/**
 * TODOC
 *
 * @author petermuys
 * @since 1/12/17
 */
public interface ETuple3<E1 extends DExpr<J1>, J1
	, E2 extends DExpr<J2>, J2
	, E3 extends DExpr<J3>, J3
	> extends DExpr<Tuple3<J1, J2, J3>>{

	E1 v1();

	E2 v2();

	E3 v3();

/*
	default <T4> ETuple4<T1,T2,T3,T4> tuple4(DExpr<T4> v4){
		return new DTuple4<>(v1(),v2(),v3(),v4);
	}
*/

}
