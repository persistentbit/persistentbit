package com.persistentbit.sql.dsl.generic.query.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.query.DSelection6;
import com.persistentbit.sql.utils.rowreader.RowReader;
import com.persistentbit.tuples.Tuple6;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class DImplSelection6<T1,T2,T3,T4,T5,T6> extends DImplSelectionAbstract<Tuple6<T1,T2,T3,T4,T5,T6>> implements DSelection6<T1,T2,T3,T4,T5,T6>{

	public DImplSelection6(QueryImpl query,
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

	@Override
	public Tuple6<T1, T2, T3, T4, T5, T6> read(DbSqlContext context, RowReader rr
	) {
		return Tuple6.of(
			DImpl._get(v1()).read(query.sqlContext,rr),
			DImpl._get(v2()).read(query.sqlContext,rr),
			DImpl._get(v3()).read(query.sqlContext,rr),
			DImpl._get(v4()).read(query.sqlContext,rr),
			DImpl._get(v5()).read(query.sqlContext,rr),
			DImpl._get(v6()).read(query.sqlContext,rr)
		);
	}

	@Override
	public DSelection6<T1, T2, T3, T4, T5, T6> withAlias(String aliasName) {
		return new DImplSelection6<>(query,columns,aliasName);

	}
}