package com.persistentbit.sql.dsl.generic.expressions;

import com.persistentbit.sql.dsl.generic.expressions.impl.dtuples.DTuple5;
import com.persistentbit.tuples.Tuple4;

/**
 * TODOC
 *
 * @author petermuys
 * @since 1/12/17
 */
public interface ETuple4<T1,T2,T3,T4> extends DExpr<Tuple4<T1,T2,T3,T4>>{
	DExpr<T1> v1();
	DExpr<T2> v2();
	DExpr<T3> v3();
	DExpr<T4> v4();

	default <T5> ETuple5<T1,T2,T3,T4,T5> tuple5(DExpr<T5> v){
		return new DTuple5<>(v1(),v2(),v3(),v4(),v);
	}
}
