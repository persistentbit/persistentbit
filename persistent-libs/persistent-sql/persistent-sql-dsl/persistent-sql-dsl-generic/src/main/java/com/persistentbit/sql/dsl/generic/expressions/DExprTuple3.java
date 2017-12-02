package com.persistentbit.sql.dsl.generic.expressions;

import com.persistentbit.sql.dsl.generic.expressions.impl.dtuples.DTuple4;
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

	default <T4> DExprTuple4<T1,T2,T3,T4>	tuple4(DExpr<T4> v4){
		return new DTuple4<>(v1(),v2(),v3(),v4);
	}


}
