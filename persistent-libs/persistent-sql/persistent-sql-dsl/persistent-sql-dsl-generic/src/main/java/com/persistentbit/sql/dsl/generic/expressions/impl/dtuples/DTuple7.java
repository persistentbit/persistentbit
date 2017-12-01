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
public class DTuple7<T1,T2,T3,T4,T5,T6,T7> implements DImpl<Tuple7<T1,T2,T3,T4,T5,T6,T7>> , DExprTuple7<T1,T2,T3,T4,T5,T6,T7>{
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
	public DTuple7<T1,T2,T3,T4,T5,T6,T7> _withAlias(String alias) {
		return new DTuple7<>(
			DImpl._get(v1)._withAlias("v1_" + alias)
			,DImpl._get(v2)._withAlias("v2_" + alias)
			,DImpl._get(v3)._withAlias("v3_" + alias)
			,DImpl._get(v4)._withAlias("v4_" + alias)
			,DImpl._get(v5)._withAlias("v5_" + alias)
			,DImpl._get(v6)._withAlias("v6_" + alias)
			,DImpl._get(v7)._withAlias("v7_" + alias)
		);
	}

	@Override
	public SqlWithParams _toSqlSelection(DbSqlContext context
	) {
		return
			DImpl._get(v1)._toSqlSelection(context)
				 .add(", ").add(DImpl._get(v2)._toSqlSelection(context))
				 .add(", ").add(DImpl._get(v3)._toSqlSelection(context))
				 .add(", ").add(DImpl._get(v4)._toSqlSelection(context))
				 .add(", ").add(DImpl._get(v5)._toSqlSelection(context))
				 .add(", ").add(DImpl._get(v6)._toSqlSelection(context))
				 .add(", ").add(DImpl._get(v7)._toSqlSelection(context))
			;
	}

	@Override
	public SqlWithParams _toSql(DbSqlContext context) {
		return
			DImpl._get(v1)._toSql(context)
				 .add(", ").add(DImpl._get(v2)._toSql(context))
				 .add(", ").add(DImpl._get(v3)._toSql(context))
				 .add(", ").add(DImpl._get(v4)._toSql(context))
				 .add(", ").add(DImpl._get(v5)._toSql(context))
				 .add(", ").add(DImpl._get(v6)._toSql(context))
				 .add(", ").add(DImpl._get(v7)._toSql(context))			;
	}

	@Override
	public Tuple7<T1,T2,T3,T4,T5,T6,T7> _read(DbSqlContext context, RowReader rowReader
	) {
		return new Tuple7<>(
			DImpl._get(v1)._read(context,rowReader)
			,DImpl._get(v2)._read(context,rowReader)
			,DImpl._get(v3)._read(context,rowReader)
			,DImpl._get(v4)._read(context,rowReader)
			,DImpl._get(v5)._read(context,rowReader)
			,DImpl._get(v6)._read(context,rowReader)
			,DImpl._get(v7)._read(context,rowReader)
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
