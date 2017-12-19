package com.persistentbit.sql.dsl.expressions.impl.old.dtuples;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ETuple4;
import com.persistentbit.sql.dsl.expressions.impl.old.DImpl;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.utils.rowreader.RowReader;
import com.persistentbit.tuples.Tuple4;
import com.persistentbit.utils.Lazy;

/**
 * TODOC
 *
 * @author petermuys
 * @since 1/12/17
 */
public class DTuple4<T1,T2,T3,T4> implements DImpl<Tuple4<T1,T2,T3,T4>> ,ETuple4<T1,T2,T3,T4>{
	private final DExpr<T1> v1;
	private final DExpr<T2>	v2;
	private final DExpr<T3>	v3;
	private final DExpr<T4>	v4;

	public DTuple4(DExpr<T1> v1, DExpr<T2> v2, DExpr<T3> v3, DExpr<T4> v4
	) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
		this.v4 = v4;
	}

	@Override
	public DTuple4<T1,T2,T3,T4> _withAlias(String alias) {
		return new DTuple4<>(
			DImpl._get(v1)._withAlias("v1_" + alias)
			,DImpl._get(v2)._withAlias("v2_" + alias)
			,DImpl._get(v3)._withAlias("v3_" + alias)
			,DImpl._get(v4)._withAlias("v4_" + alias)
		);
	}

	@Override
	public SqlWithParams _toSqlSelection(DbSqlContext context, String alias) {

		return
			DImpl._get(v1)._toSqlSelection(context,alias == null ? null : alias + "_v1")
				 .add(", ").add(DImpl._get(v2)._toSqlSelection(context,alias == null ? null : alias + "_v2"))
				 .add(", ").add(DImpl._get(v3)._toSqlSelection(context,alias == null ? null : alias + "_v3"))
				 .add(", ").add(DImpl._get(v4)._toSqlSelection(context,alias == null ? null : alias + "_v4"))
			;
	}


	@Override
	public SqlWithParams _toSql(DbSqlContext context) {
		return
			DImpl._get(v1)._toSql(context)
				 .add(", ").add(DImpl._get(v2)._toSql(context))
				 .add(", ").add(DImpl._get(v3)._toSql(context))
				 .add(", ").add(DImpl._get(v4)._toSql(context))
			;
	}

	@Override
	public Tuple4<T1,T2,T3,T4> _read(DbSqlContext context, RowReader rowReader
	) {
		return new Tuple4<>(
			DImpl._get(v1)._read(context,rowReader)
			,DImpl._get(v2)._read(context,rowReader)
			,DImpl._get(v3)._read(context,rowReader)
			,DImpl._get(v4)._read(context,rowReader)
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



	private final Lazy<PList<DExpr>> _expandList = new Lazy<>(() ->
		PList.<DExpr>empty()
			.plusAll(DImpl._get(v1())._expand())
			.plusAll(DImpl._get(v2())._expand())
			.plusAll(DImpl._get(v3())._expand())
			.plusAll(DImpl._get(v4())._expand())
	);

	@Override
	public PList<DExpr> _expand() {
		return _expandList.get();
	}

}
