package com.persistentbit.sql.dsl.generic.query;

import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprSelectable;
import com.persistentbit.sql.work.DbWork;
import com.persistentbit.tuples.Tuple5;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public interface DSelection5<T1,T2,T3,T4,T5> extends DExprSelectable<Tuple5<T1,T2,T3,T4,T5>>,DbWork<PStream<Tuple5<T1,T2,T3,T4,T5>>>{
	DExpr<T1> v1();
	DExpr<T2>	v2();
	DExpr<T3>	v3();
	DExpr<T4>	v4();
	DExpr<T5>	v5();
	DSelection5<T1,T2,T3,T4,T5> withAlias(String aliasName);

}
