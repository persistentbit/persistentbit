package com.persistentbit.sql.dsl.generic.query.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.query.DSelection4;
import com.persistentbit.sql.utils.rowreader.RowReader;
import com.persistentbit.tuples.Tuple4;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class DImplSelection4<T1,T2,T3,T4> extends DImplSelectionAbstract<Tuple4<T1,T2,T3,T4>> implements DSelection4<T1,T2,T3,T4>{

	public DImplSelection4(QueryImpl query,
						   PList<DExpr> columns,String aliasName
	) {
		super(query, columns,aliasName);
	}

	@Override
	public DExpr<T1> v1() {
		return (DExpr<T1>)columns.get(0);
	}

	@Override
	public DExpr<T2> v2() {
		return (DExpr<T2>)columns.get(1);
	}
	@Override
	public DExpr<T3> v3() {
		return (DExpr<T3>)columns.get(2);
	}
	@Override
	public DExpr<T4> v4() {
		return (DExpr<T4>)columns.get(3);
	}

	@Override
	public Tuple4<T1, T2, T3, T4> read(DbSqlContext context, RowReader rr
	) {
		return Tuple4.of(
			DImpl._get(v1()).read(query.sqlContext,rr),
			DImpl._get(v2()).read(query.sqlContext,rr),
			DImpl._get(v3()).read(query.sqlContext,rr),
			DImpl._get(v4()).read(query.sqlContext,rr)
		);
	}

	@Override
	public DSelection4<T1, T2, T3, T4> withAlias(String aliasName) {
		return new DImplSelection4<>(query,columns,aliasName);

	}
}