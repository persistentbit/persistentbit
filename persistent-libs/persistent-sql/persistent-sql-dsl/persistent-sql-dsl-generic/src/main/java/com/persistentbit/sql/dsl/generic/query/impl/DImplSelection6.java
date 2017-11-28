package com.persistentbit.sql.dsl.generic.query.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.query.DSelection6;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class DImplSelection6<T1,T2,T3,T4,T5,T6> extends DImplSelectionAbstract implements DSelection6<T1,T2,T3,T4,T5,T6>{

	public DImplSelection6(QueryImpl query,
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
	@Override
	public T4 v4() {
		return (T4)columns.get(3);
	}
	public T5 v5() {
		return (T5)columns.get(4);
	}
	public T6 v6() {
		return (T6)columns.get(5);
	}
}