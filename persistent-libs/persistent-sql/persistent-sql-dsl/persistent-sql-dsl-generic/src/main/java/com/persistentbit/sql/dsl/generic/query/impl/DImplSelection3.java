package com.persistentbit.sql.dsl.generic.query.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.query.DSelection3;
import com.persistentbit.sql.utils.rowreader.RowReader;
import com.persistentbit.tuples.Tuple3;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class DImplSelection3<T1,T2,T3> extends DImplSelectionAbstract<Tuple3<T1,T2,T3>> implements DSelection3<T1,T2,T3>{

	public DImplSelection3(QueryImpl query,
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
	public Tuple3<T1, T2, T3> read(DbSqlContext context, RowReader rr
	) {
		return Tuple3.of(
			DImpl._get(v1()).read(query.sqlContext,rr),
			DImpl._get(v2()).read(query.sqlContext,rr),
			DImpl._get(v3()).read(query.sqlContext,rr)
		);
	}

	@Override
	public DSelection3<T1, T2, T3> withAlias(String aliasName) {
		return new DImplSelection3<>(query,columns,aliasName);

	}
}