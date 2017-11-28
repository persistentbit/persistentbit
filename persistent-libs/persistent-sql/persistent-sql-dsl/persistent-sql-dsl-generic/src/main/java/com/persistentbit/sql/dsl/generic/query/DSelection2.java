package com.persistentbit.sql.dsl.generic.query;

import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprSelectable;
import com.persistentbit.sql.work.DbWork;
import com.persistentbit.tuples.Tuple2;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public interface DSelection2<T1,T2> extends DExprSelectable<Tuple2<T1,T2>>,DbWork<PStream<Tuple2<T1,T2>>>{
	DExpr<T1> 	v1();
	DExpr<T2>	v2();
	DSelection2<T1,T2> withAlias(String aliasName);

}
