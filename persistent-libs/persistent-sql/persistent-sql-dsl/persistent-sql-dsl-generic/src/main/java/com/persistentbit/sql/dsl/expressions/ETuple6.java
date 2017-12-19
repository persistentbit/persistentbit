package com.persistentbit.sql.dsl.expressions;

import com.persistentbit.sql.dsl.expressions.impl.old.dtuples.DTuple7;
import com.persistentbit.tuples.Tuple6;

/**
 * TODOC
 *
 * @author petermuys
 * @since 1/12/17
 */
public interface ETuple6<T1,T2,T3,T4,T5,T6> extends DExpr<Tuple6<T1,T2,T3,T4,T5,T6>>{
	DExpr<T1> v1();
	DExpr<T2> v2();
	DExpr<T3> v3();
	DExpr<T4> v4();
	DExpr<T5> v5();
	DExpr<T6> v6();

	default <T7> ETuple7<T1,T2,T3,T4,T5,T6,T7> tuple7(DExpr<T7> v){
		return new DTuple7<>(v1(),v2(),v3(),v4(),v5(),v6(),v);
	}

}
