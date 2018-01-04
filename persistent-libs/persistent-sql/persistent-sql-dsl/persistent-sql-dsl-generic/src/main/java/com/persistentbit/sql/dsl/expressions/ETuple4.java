package com.persistentbit.sql.dsl.expressions;

import com.persistentbit.tuples.Tuple4;

/**
 * TODOC
 *
 * @author petermuys
 * @since 1/12/17
 */
public interface ETuple4<E1 extends DExpr<J1>, J1
	, E2 extends DExpr<J2>, J2
	, E3 extends DExpr<J3>, J3
	, E4 extends DExpr<J4>, J4
	> extends DExpr<Tuple4<J1, J2, J3, J4>>{

	E1 v1();

	E2 v2();

	E3 v3();

	E4 v4();

/*
	default <T5> ETuple5<T1,T2,T3,T4,T5> tuple5(DExpr<T5> v){
		return new DTuple5<>(v1(),v2(),v3(),v4(),v);
	}*/
}
