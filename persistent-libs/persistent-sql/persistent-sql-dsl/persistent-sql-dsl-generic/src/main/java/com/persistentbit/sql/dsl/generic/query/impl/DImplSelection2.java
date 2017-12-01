package com.persistentbit.sql.dsl.generic.query.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.query.DSelection2;
import com.persistentbit.sql.utils.rowreader.RowReader;
import com.persistentbit.tuples.Tuple2;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class DImplSelection2<T1,T2> extends DImplSelectionAbstract<Tuple2<T1,T2>> implements DSelection2<T1,T2>{

	public DImplSelection2(QueryImpl query,
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
	public Tuple2<T1, T2> read(DbSqlContext context, RowReader rr) {
		return Tuple2.of(
			DImpl._get(v1()).read(context,rr),
			DImpl._get(v2()).read(context,rr)
		);
	}

	@Override
	public DSelection2<T1, T2> withSelectionAlias(String aliasName) {
		return new DImplSelection2<>(query,columns,aliasName);
	}
}
