package com.persistentbit.sql.dsl.generic.query.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.query.DSelection5;
import com.persistentbit.sql.utils.rowreader.RowReader;
import com.persistentbit.tuples.Tuple5;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class DImplSelection5<T1,T2,T3,T4,T5> extends DImplSelectionAbstract<Tuple5<T1,T2,T3,T4,T5>> implements DSelection5<T1,T2,T3,T4,T5>{

	public DImplSelection5(QueryImpl query,
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



	@Override
	public Tuple5<T1, T2, T3, T4, T5> read(DbSqlContext context, RowReader rr
	) {
		return Tuple5.of(
			DImpl._get(v1()).read(context,rr),
			DImpl._get(v2()).read(context,rr),
			DImpl._get(v3()).read(context,rr),
			DImpl._get(v4()).read(context,rr),
			DImpl._get(v5()).read(context,rr)
		);
	}

	@Override
	public DSelection5<T1, T2, T3, T4, T5> withSelectionAlias(String aliasName) {
		return new DImplSelection5<>(query,columns,aliasName);

	}
}