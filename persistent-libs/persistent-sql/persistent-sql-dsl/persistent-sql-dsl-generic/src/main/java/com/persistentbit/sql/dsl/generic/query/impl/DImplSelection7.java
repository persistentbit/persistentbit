package com.persistentbit.sql.dsl.generic.query.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.query.DSelection7;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class DImplSelection7<T1,T2,T3,T4,T5,T6,T7> extends DImplSelectionAbstract implements
																				  DSelection7<T1,T2,T3,T4,T5,T6,T7>{

	public DImplSelection7(QueryImpl query,
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
	public T7 v7() {
		return (T7)columns.get(6);
	}

	@Override
	public String toString() {
		//TODO
		return super.toString();
	}
}