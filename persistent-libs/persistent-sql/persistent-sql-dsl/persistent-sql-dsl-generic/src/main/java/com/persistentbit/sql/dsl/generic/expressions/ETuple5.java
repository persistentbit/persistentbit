package com.persistentbit.sql.dsl.generic.expressions;

import com.persistentbit.sql.dsl.generic.expressions.impl.dtuples.DTuple6;
import com.persistentbit.tuples.Tuple5;

/**
 * TODOC
 *
 * @author petermuys
 * @since 1/12/17
 */
public interface ETuple5<T1,T2,T3,T4,T5> extends DExpr<Tuple5<T1,T2,T3,T4,T5>>{
	DExpr<T1> v1();
	DExpr<T2> v2();
	DExpr<T3> v3();
	DExpr<T4> v4();
	DExpr<T5> v5();

	default <T6> ETuple6<T1,T2,T3,T4,T5,T6> tuple6(DExpr<T6> v){
		return new DTuple6<>(v1(),v2(),v3(),v4(),v5(),v);
	}


}
