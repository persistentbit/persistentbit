package com.persistentbit.sql.dsl.generic.query;

import com.persistentbit.sql.dsl.generic.expressions.DExprSelectable;
import com.persistentbit.tuples.Tuple5;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public interface DSelection5<T1,T2,T3,T4,T5> extends DExprSelectable<Tuple5<T1,T2,T3,T4,T5>>{
	T1	v1();
	T2	v2();
	T3	v3();
	T4	v4();
	T5	v5();

}
