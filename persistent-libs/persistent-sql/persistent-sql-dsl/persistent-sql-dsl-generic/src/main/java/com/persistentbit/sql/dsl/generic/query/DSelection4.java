package com.persistentbit.sql.dsl.generic.query;

import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.work.DbWork;
import com.persistentbit.tuples.Tuple4;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public interface DSelection4<T1,T2,T3,T4> extends DbWork<PStream<Tuple4<T1,T2,T3,T4>>>{
	DExpr<T1> v1();
	DExpr<T2>	v2();
	DExpr<T3>	v3();
	DExpr<T4>	v4();
	DSelection4<T1,T2,T3,T4> withSelectionAlias(String aliasName);

}
