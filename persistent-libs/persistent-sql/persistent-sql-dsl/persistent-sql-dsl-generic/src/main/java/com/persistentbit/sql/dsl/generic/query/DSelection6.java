package com.persistentbit.sql.dsl.generic.query;

import com.persistentbit.sql.dsl.generic.expressions.DExprSelectable;
import com.persistentbit.tuples.Tuple6;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public interface DSelection6<T1,T2,T3,T4,T5,T6> extends DExprSelectable<Tuple6<T1,T2,T3,T4,T5,T6>>{
	T1	v1();
	T2	v2();
	T3	v3();
	T4	v4();
	T5	v5();
	T6	v6();

}
