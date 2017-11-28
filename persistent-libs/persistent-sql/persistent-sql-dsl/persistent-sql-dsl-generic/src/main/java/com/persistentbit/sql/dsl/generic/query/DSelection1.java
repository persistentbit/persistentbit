package com.persistentbit.sql.dsl.generic.query;

import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprSelectable;
import com.persistentbit.sql.work.DbWork;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public interface DSelection1<T> extends DExprSelectable<T>,DbWork<PStream<T>>{
	DExpr<T> v1();

	DSelection1<T> withAlias(String aliasName);
}
