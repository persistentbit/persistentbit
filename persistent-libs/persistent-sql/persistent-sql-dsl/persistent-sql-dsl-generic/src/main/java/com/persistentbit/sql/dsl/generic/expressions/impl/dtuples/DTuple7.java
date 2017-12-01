package com.persistentbit.sql.dsl.generic.expressions.impl.dtuples;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprTuple7;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.sql.utils.rowreader.RowReader;
import com.persistentbit.tuples.Tuple7;

/**
 * TODOC
 *
 * @author petermuys
 * @since 1/12/17
 */
public class DTuple7<T1,T2,T3,T4,T5,T6,T7> extends DImpl<Tuple7<T1,T2,T3,T4,T5,T6,T7>> implements DExprTuple7<T1,T2,T3,T4,T5,T6,T7>{
	private final DExpr<T1> v1;
	private final DExpr<T2>	v2;
	private final DExpr<T3>	v3;
	private final DExpr<T4>	v4;
	private final DExpr<T5>	v5;
	private final DExpr<T6>	v6;
	private final DExpr<T7>	v7;

	public DTuple7(DExpr<T1> v1, DExpr<T2> v2, DExpr<T3> v3, DExpr<T4> v4,
				   DExpr<T5> v5,
				   DExpr<T6> v6,
				   DExpr<T7> v7
	) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
		this.v4 = v4;
		this.v5 = v5;
		this.v6 = v6;
		this.v7 = v7;
	}

	@Override
	public DTuple7<T1,T2,T3,T4,T5,T6,T7> withSelectionAlias(String alias) {
		return new DTuple7<>(
			v1.withSelectionAlias("v1_" + alias)
			,v2.withSelectionAlias("v2_" + alias)
			,v3.withSelectionAlias("v3_" + alias)
			,v4.withSelectionAlias("v4_" + alias)
			,v5.withSelectionAlias("v5_" + alias)
			,v6.withSelectionAlias("v6_" + alias)
			,v7.withSelectionAlias("v7_" + alias)
		);
	}

	@Override
	public SqlWithParams toSqlSelection(DbSqlContext context
	) {
		return
			DImpl._get(v1).toSqlSelection(context)
				 .add(", ").add(DImpl._get(v2).toSqlSelection(context))
				 .add(", ").add(DImpl._get(v3).toSqlSelection(context))
				 .add(", ").add(DImpl._get(v4).toSqlSelection(context))
				 .add(", ").add(DImpl._get(v5).toSqlSelection(context))
				 .add(", ").add(DImpl._get(v6).toSqlSelection(context))
				 .add(", ").add(DImpl._get(v7).toSqlSelection(context))
			;
	}

	@Override
	public SqlWithParams toSql(DbSqlContext context) {
		return
			DImpl._get(v1).toSql(context)
				 .add(", ").add(DImpl._get(v2).toSql(context))
				 .add(", ").add(DImpl._get(v4).toSql(context))
				 .add(", ").add(DImpl._get(v5).toSql(context))
				 .add(", ").add(DImpl._get(v6).toSql(context))
				 .add(", ").add(DImpl._get(v7).toSql(context))			;
	}

	@Override
	public Tuple7<T1,T2,T3,T4,T5,T6,T7> read(DbSqlContext context, RowReader rowReader
	) {
		return new Tuple7<>(
			DImpl._get(v1).read(context,rowReader)
			,DImpl._get(v2).read(context,rowReader)
			,DImpl._get(v3).read(context,rowReader)
			,DImpl._get(v4).read(context,rowReader)
			,DImpl._get(v5).read(context,rowReader)
			,DImpl._get(v6).read(context,rowReader)
			,DImpl._get(v7).read(context,rowReader)
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

	@Override
	public DExpr<T4> v4() {
		return v4;
	}

	@Override
	public DExpr<T5> v5() {
		return v5;
	}

	@Override
	public DExpr<T6> v6() {
		return v6;
	}

	@Override
	public DExpr<T7> v7() {
		return v7;
	}

}
