package com.persistentbit.sql.dsl.generic.expressions.impl.dtuples;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprTuple3;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.sql.utils.rowreader.RowReader;
import com.persistentbit.tuples.Tuple3;

/**
 * TODOC
 *
 * @author petermuys
 * @since 1/12/17
 */
public class DTuple3<T1,T2,T3> extends DImpl<Tuple3<T1,T2,T3>> implements
																	 DExprTuple3<T1,T2,T3>{
	private final DExpr<T1> v1;
	private final DExpr<T2>	v2;
	private final DExpr<T3>	v3;

	public DTuple3(DExpr<T1> v1, DExpr<T2> v2, DExpr<T3> v3
	) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}

	@Override
	public DTuple3<T1,T2,T3> _withAlias(String alias) {
		return new DTuple3<>(
			DImpl._get(v1)._withAlias("v1_" + alias)
			,DImpl._get(v2)._withAlias("v2_" + alias)
			,DImpl._get(v3)._withAlias("v3_" + alias)
		);
	}

	@Override
	public SqlWithParams _toSqlSelection(DbSqlContext context
	) {
		return
			DImpl._get(v1)._toSqlSelection(context)
				 .add(", ").add(DImpl._get(v2)._toSqlSelection(context))
				 .add(", ").add(DImpl._get(v3)._toSqlSelection(context))
			;
	}

	@Override
	public SqlWithParams _toSql(DbSqlContext context) {
		return
			DImpl._get(v1)._toSql(context)
				 .add(", ").add(DImpl._get(v2)._toSql(context))
				 .add(", ").add(DImpl._get(v3)._toSql(context))
			;
	}

	@Override
	public Tuple3<T1,T2,T3> _read(DbSqlContext context, RowReader rowReader
	) {
		return new Tuple3<>(
			DImpl._get(v1)._read(context,rowReader)
			,DImpl._get(v2)._read(context,rowReader)
			,DImpl._get(v3)._read(context,rowReader)
		);
	}

	@Override
	public DExpr<T1> v1() {
		return v1;
	}

	@Override
	public DExpr<T2> v2() {
		return v2;
	}

	@Override
	public DExpr<T3> v3() {
		return v3;
	}

}
