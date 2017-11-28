package com.persistentbit.sql.dsl.generic.query;

import com.persistentbit.sql.dsl.generic.expressions.DExprSelectable;
import com.persistentbit.tuples.Tuple3;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public interface DSelection3<T1,T2,T3> extends DExprSelectable<Tuple3<T1,T2,T3>>{
	T1	v1();
	T2	v2();
	T3	v3();

}
