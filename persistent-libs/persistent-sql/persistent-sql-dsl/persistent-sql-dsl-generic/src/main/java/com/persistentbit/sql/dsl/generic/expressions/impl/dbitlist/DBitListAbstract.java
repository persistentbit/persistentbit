package com.persistentbit.sql.dsl.generic.expressions.impl.dbitlist;

import com.persistentbit.collections.PBitList;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprBitList;
import com.persistentbit.sql.dsl.generic.expressions.DExprBoolean;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.impl.dboolean.DBooleanBinOp;
import com.persistentbit.sql.dsl.generic.expressions.impl.dboolean.DBooleanSingleOp;
import com.persistentbit.sql.utils.rowreader.RowReader;

/**
 * TODOC
 *
 * @author petermuys
 * @since 15/12/17
 */
public abstract class DBitListAbstract implements DImpl<PBitList>, DExprBitList{
	@Override
	public DExprBoolean eq(DExpr<PBitList> other) {
		return new DBooleanBinOp(this, DBooleanBinOp.Operator.opEq, other);
	}
	@Override
	public DExprBoolean notEq(DExpr<PBitList> other) {
		return new DBooleanBinOp(this, DBooleanBinOp.Operator.opNotEq,other);
	}

	@Override
	public DExprBoolean isNull() {
		return new DBooleanSingleOp(this, DBooleanSingleOp.Operator.opIsNull);
	}

	@Override
	public DExprBoolean isNotNull() {
		return new DBooleanSingleOp(this, DBooleanSingleOp.Operator.opIsNotNull);
	}

	@Override
	public PBitList _read(DbSqlContext context, RowReader rowReader
	) {
		return rowReader.readNext(PBitList.class);
	}

	@Override
	public DExprBitList _withAlias(String alias) {
		return new DBitListAlias(this, alias);
	}
	@Override
	public PList<DExpr> _expand() {
		return PList.val(this);
	}
}
