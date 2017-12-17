package com.persistentbit.sql.dsl.generic.expressions;

import com.persistentbit.sql.dsl.generic.expressions.impl.dtuples.DTuple3;
import com.persistentbit.tuples.Tuple2;

/**
 * TODOC
 *
 * @author petermuys
 * @since 1/12/17
 */
public interface ETuple2<E1 extends DExpr,E2 extends DExpr<J2>,J1,J2> extends DExpr<Tuple2<? extends J1,? extends J2>>{
	E1 v1();
	E2 v2();

	default <T3> ETuple3<J1,J2,T3> tuple3(DExpr<T3> v3){
		return new DTuple3<>(v1(),v2(),v3);
	}

	/*static <T1,T2> ETuple2<T1,T2> cast(DExpr<Tuple2<T1,T2>> expr){
		return (ETuple2<T1,T2>)expr;
	}*/

}
