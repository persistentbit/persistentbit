package com.persistentbit.sql.dsl.generic.query.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.query.DSelection7;
import com.persistentbit.sql.utils.rowreader.RowReader;
import com.persistentbit.tuples.Tuple7;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class DImplSelection7<T1,T2,T3,T4,T5,T6,T7> extends DImplSelectionAbstract<Tuple7<T1,T2,T3,T4,T5,T6,T7>> implements
																				  DSelection7<T1,T2,T3,T4,T5,T6,T7>{

	public DImplSelection7(QueryImpl query,
						   PList<DExpr> columns,String aliasName
	) {
		super(query, columns,aliasName);
	}

	@Override
	public DExpr<T1> v1() {
		return (DExpr<T1>)getWithAlias(0);
	}

	@Override
	public DExpr<T2> v2() {
		return (DExpr<T2>)getWithAlias(1);
	}
	@Override
	public DExpr<T3> v3() {
		return (DExpr<T3>)getWithAlias(2);
	}
	@Override
	public DExpr<T4> v4() {
		return (DExpr<T4>)getWithAlias(3);
	}
	public DExpr<T5> v5() {
		return (DExpr<T5>)getWithAlias(4);
	}
	public DExpr<T6> v6() {
		return (DExpr<T6>)getWithAlias(5);
	}
	public DExpr<T7> v7() {
		return (DExpr<T7>)getWithAlias(6);
	}

	@Override
	public String toString() {
		//TODO
		return super.toString();
	}

	@Override
	public Tuple7<T1, T2, T3, T4, T5, T6, T7> read(DbSqlContext context, RowReader rr
	) {
		return Tuple7.of(
			DImpl._get(v1()).read(context,rr),
			DImpl._get(v2()).read(context,rr),
			DImpl._get(v3()).read(context,rr),
			DImpl._get(v4()).read(context,rr),
			DImpl._get(v5()).read(context,rr),
			DImpl._get(v6()).read(context,rr),
			DImpl._get(v7()).read(context,rr)
		);
	}

	@Override
	public DSelection7<T1, T2, T3, T4, T5, T6, T7> withSelectionAlias(String aliasName) {
		return new DImplSelection7<>(query,columns,aliasName);

	}
}