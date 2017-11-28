package com.persistentbit.sql.dsl.generic.query.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.query.DSelection3;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class DImplSelection3<T1,T2,T3> extends DImplSelectionAbstract implements DSelection3<T1,T2,T3>{

	public DImplSelection3(QueryImpl query,
						   PList<DExpr> columns
	) {
		super(query, columns);
	}

	@Override
	public T1 v1() {
		return (T1)columns.get(0);
	}

	@Override
	public T2 v2() {
		return (T2)columns.get(1);
	}
	@Override
	public T3 v3() {
		return (T3)columns.get(2);
	}
}