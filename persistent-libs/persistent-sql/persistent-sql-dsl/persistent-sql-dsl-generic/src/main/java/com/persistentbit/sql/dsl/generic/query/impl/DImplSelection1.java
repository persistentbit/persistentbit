package com.persistentbit.sql.dsl.generic.query.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.query.DSelection1;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class DImplSelection1<T> extends DImplSelectionAbstract implements DSelection1<T>{

	public DImplSelection1(QueryImpl query,
						   PList<DExpr> columns
	) {
		super(query, columns);
	}

	@Override
	public T v1() {
		return (T)columns.get(0);
	}
}
