package com.persistentbit.sql.dsl.generic.query;

import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.work.DbWork;
import com.persistentbit.tuples.Tuple6;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public interface DSelection6<T1,T2,T3,T4,T5,T6> extends DbWork<PStream<Tuple6<T1,T2,T3,T4,T5,T6>>>{
	DExpr<T1> v1();
	DExpr<T2>	v2();
	DExpr<T3>	v3();
	DExpr<T4>	v4();
	DExpr<T5>	v5();
	DExpr<T6>	v6();
	DSelection6<T1,T2,T3,T4,T5,T6> withSelectionAlias(String aliasName);

}
