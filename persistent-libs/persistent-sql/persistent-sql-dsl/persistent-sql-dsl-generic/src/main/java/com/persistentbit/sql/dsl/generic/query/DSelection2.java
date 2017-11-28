package com.persistentbit.sql.dsl.generic.query;

import com.persistentbit.sql.dsl.generic.expressions.DExprSelectable;
import com.persistentbit.tuples.Tuple2;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public interface DSelection2<T1,T2> extends DExprSelectable<Tuple2<T1,T2>>{
	T1	v1();
	T2	v2();

}
