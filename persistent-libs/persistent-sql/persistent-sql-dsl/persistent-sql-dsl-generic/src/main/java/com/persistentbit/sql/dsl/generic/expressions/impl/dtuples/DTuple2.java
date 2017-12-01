package com.persistentbit.sql.dsl.generic.expressions.impl.dtuples;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprTuple2;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.sql.utils.rowreader.RowReader;
import com.persistentbit.tuples.Tuple2;

/**
 * TODOC
 *
 * @author petermuys
 * @since 1/12/17
 */
public class DTuple2<T1,T2> extends DImpl<Tuple2<T1,T2>> implements DExprTuple2<T1,T2>{
	private final DExpr<T1>	v1;
	private final DExpr<T2>	v2;

	public DTuple2(DExpr<T1> v1, DExpr<T2> v2) {
		this.v1 = v1;
		this.v2 = v2;
	}

	@Override
	public DExpr<Tuple2<T1, T2>> withSelectionAlias(String alias) {
		return new DTuple2<>(
			v1.withSelectionAlias("v1_" + alias),
			v2.withSelectionAlias("v2_" + alias)
		);
	}

	@Override
	public SqlWithParams toSqlSelection(DbSqlContext context
	) {
		return
			DImpl._get(v1).toSqlSelection(context)
			 .add(", ")
			 .add(DImpl._get(v2).toSqlSelection(context))
		;
	}

	@Override
	public SqlWithParams toSql(DbSqlContext context) {
		return
			DImpl._get(v1).toSql(context)
				 .add(", ")
				 .add(DImpl._get(v2).toSql(context))
			;
	}

	@Override
	public Tuple2<T1, T2> read(DbSqlContext context, RowReader rowReader
	) {
		return new Tuple2<>(
			DImpl._get(v1).read(context,rowReader)
			,DImpl._get(v2).read(context,rowReader)
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

}
