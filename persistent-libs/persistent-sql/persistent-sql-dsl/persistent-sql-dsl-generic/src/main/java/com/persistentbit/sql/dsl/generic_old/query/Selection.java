package com.persistentbit.sql.dsl.generic_old.query;

import com.persistentbit.collections.PStream;
import com.persistentbit.sql.work.DbWork;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public interface Selection<T> extends DbWork<PStream<T>>{

	DSelectionTable<T> asTableExpr(String aliasName);



}
