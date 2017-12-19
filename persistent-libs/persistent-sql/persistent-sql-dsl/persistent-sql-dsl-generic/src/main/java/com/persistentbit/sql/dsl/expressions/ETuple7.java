package com.persistentbit.sql.dsl.expressions;

import com.persistentbit.tuples.Tuple7;

/**
 * TODOC
 *
 * @author petermuys
 * @since 1/12/17
 */
public interface ETuple7<T1,T2,T3,T4,T5,T6,T7> extends DExpr<Tuple7<T1,T2,T3,T4,T5,T6,T7>>{
	DExpr<T1> v1();
	DExpr<T2> v2();
	DExpr<T3> v3();
	DExpr<T4> v4();
	DExpr<T5> v5();
	DExpr<T6> v6();
	DExpr<T7> v7();


}
