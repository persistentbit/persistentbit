package com.persistentbit.sql.dsl.generic.expressions.impl.dtuples;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.ETuple5;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.sql.utils.rowreader.RowReader;
import com.persistentbit.tuples.Tuple5;
import com.persistentbit.utils.Lazy;

/**
 * TODOC
 *
 * @author petermuys
 * @since 1/12/17
 */
public class DTuple5<T1,T2,T3,T4,T5> implements DImpl<Tuple5<T1,T2,T3,T4,T5>> , ETuple5<T1,T2,T3,T4,T5>{
	private final DExpr<T1> v1;
	private final DExpr<T2>	v2;
	private final DExpr<T3>	v3;
	private final DExpr<T4>	v4;
	private final DExpr<T5>	v5;

	public DTuple5(DExpr<T1> v1, DExpr<T2> v2, DExpr<T3> v3, DExpr<T4> v4,
				   DExpr<T5> v5
	) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
		this.v4 = v4;
		this.v5 = v5;
	}

	@Override
	public DTuple5<T1,T2,T3,T4,T5> _withAlias(String alias) {
		return new DTuple5<>(
			DImpl._get(v1)._withAlias("v1_" + alias)
			,DImpl._get(v2)._withAlias("v2_" + alias)
			,DImpl._get(v3)._withAlias("v3_" + alias)
			,DImpl._get(v4)._withAlias("v4_" + alias)
			,DImpl._get(v5)._withAlias("v5_" + alias)
		);
	}

	@Override
	public SqlWithParams _toSqlSelection(DbSqlContext context, String alias) {

		return
			DImpl._get(v1)._toSqlSelection(context,alias == null ? null : alias + "_v1")
				 .add(", ").add(DImpl._get(v2)._toSqlSelection(context,alias == null ? null : alias + "_v2"))
				 .add(", ").add(DImpl._get(v3)._toSqlSelection(context,alias == null ? null : alias + "_v3"))
				 .add(", ").add(DImpl._get(v4)._toSqlSelection(context,alias == null ? null : alias + "_v4"))
				 .add(", ").add(DImpl._get(v5)._toSqlSelection(context,alias == null ? null : alias + "_v5"))
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
			;
	}

	@Override
	public Tuple5<T1,T2,T3,T4,T5> _read(DbSqlContext context, RowReader rowReader
	) {
		return new Tuple5<>(
			DImpl._get(v1)._read(context,rowReader)
			,DImpl._get(v2)._read(context,rowReader)
			,DImpl._get(v3)._read(context,rowReader)
			,DImpl._get(v4)._read(context,rowReader)
			,DImpl._get(v5)._read(context,rowReader)
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

	private final Lazy<PList<DExpr>> _expandList = new Lazy<>(() ->
		PList.<DExpr>empty()
			.plusAll(DImpl._get(v1())._expand())
			.plusAll(DImpl._get(v2())._expand())
			.plusAll(DImpl._get(v3())._expand())
			.plusAll(DImpl._get(v4())._expand())
			.plusAll(DImpl._get(v5())._expand())
	);

	@Override
	public PList<DExpr> _expand() {
		return _expandList.get();
	}

}
