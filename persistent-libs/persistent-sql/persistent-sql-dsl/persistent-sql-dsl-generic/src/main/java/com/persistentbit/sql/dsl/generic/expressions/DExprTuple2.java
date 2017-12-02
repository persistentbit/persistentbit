package com.persistentbit.sql.dsl.generic.expressions;

import com.persistentbit.sql.dsl.generic.expressions.impl.dtuples.DTuple3;
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

	default <T3> DExprTuple3<T1,T2,T3>	tuple3(DExpr<T3> v3){
		return new DTuple3<>(v1(),v2(),v3);
	}
}
