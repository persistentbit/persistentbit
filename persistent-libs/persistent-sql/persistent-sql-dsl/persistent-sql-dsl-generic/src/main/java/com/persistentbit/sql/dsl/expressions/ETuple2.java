package com.persistentbit.sql.dsl.expressions;

import com.persistentbit.sql.dsl.expressions.impl.old.dtuples.DTuple3;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.tuples.Tuple2TypeFactory;
import com.persistentbit.tuples.Tuple2;

/**
 * TODOC
 *
 * @author petermuys
 * @since 1/12/17
 */
public interface ETuple2<E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2>
	extends DExpr<Tuple2<J1, J2>>{

	E1 v1();

	E2 v2();




	default <T3> ETuple3<J1, J2, T3> tuple3(DExpr<T3> v3) {
		return new DTuple3<>(v1(), v2(), v3);
	}

	/*static <T1,T2> ETuple2<T1,T2> cast(DExpr<Tuple2<T1,T2>> expr){
		return (ETuple2<T1,T2>)expr;
	}*/

}
