package com.persistentbit.sql.dsl.generic.query;

import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprSelectable;
import com.persistentbit.sql.work.DbWork;
import com.persistentbit.tuples.Tuple3;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public interface DSelection3<T1,T2,T3> extends DExprSelectable<Tuple3<T1,T2,T3>>,DbWork<PStream<Tuple3<T1,T2,T3>>>{
	DExpr<T1> v1();
	DExpr<T2>	v2();
	DExpr<T3>	v3();
	DSelection3<T1,T2,T3> withAlias(String aliasName);

}
