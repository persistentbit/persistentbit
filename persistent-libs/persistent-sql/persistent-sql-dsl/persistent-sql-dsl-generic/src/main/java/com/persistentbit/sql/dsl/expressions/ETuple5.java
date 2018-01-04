package com.persistentbit.sql.dsl.expressions;

import com.persistentbit.tuples.Tuple5;

/**
 * TODOC
 *
 * @author petermuys
 * @since 1/12/17
 */
public interface ETuple5<E1 extends DExpr<J1>, J1
	, E2 extends DExpr<J2>, J2
	, E3 extends DExpr<J3>, J3
	, E4 extends DExpr<J4>, J4
	, E5 extends DExpr<J5>, J5
	> extends DExpr<Tuple5<J1, J2, J3, J4, J5>>{

	E1 v1();

	E2 v2();

	E3 v3();

	E4 v4();

	E5 v5();

/*
	default <T6> ETuple6<T1,T2,T3,T4,T5,T6> tuple6(DExpr<T6> v){
		return new DTuple6<>(v1(),v2(),v3(),v4(),v5(),v);
	}
*/

}
