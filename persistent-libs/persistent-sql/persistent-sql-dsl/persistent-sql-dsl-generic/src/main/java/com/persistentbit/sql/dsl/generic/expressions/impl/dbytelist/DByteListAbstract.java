package com.persistentbit.sql.dsl.generic.expressions.impl.dbytelist;

import com.persistentbit.collections.PBitList;
import com.persistentbit.collections.PByteList;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprBitList;
import com.persistentbit.sql.dsl.generic.expressions.DExprBoolean;
import com.persistentbit.sql.dsl.generic.expressions.DExprByteList;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.impl.dbitlist.DBitListAlias;
import com.persistentbit.sql.dsl.generic.expressions.impl.dboolean.DBooleanBinOp;
import com.persistentbit.sql.dsl.generic.expressions.impl.dboolean.DBooleanSingleOp;
import com.persistentbit.sql.utils.rowreader.RowReader;

/**
 * TODOC
 *
 * @author petermuys
 * @since 15/12/17
 */
public abstract class DByteListAbstract implements DImpl<PByteList>, DExprByteList{
	@Override
	public DExprBoolean eq(DExpr<PByteList> other) {
		return new DBooleanBinOp(this, DBooleanBinOp.Operator.opEq, other);
	}
	@Override
	public DExprBoolean notEq(DExpr<PByteList> other) {
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
	public PByteList _read(DbSqlContext context, RowReader rowReader
	) {
		return rowReader.readNext(PByteList.class);
	}

	@Override
	public DExprByteList _withAlias(String alias) {
		return new DByteListAlias(this, alias);
	}
	@Override
	public PList<DExpr> _expand() {
		return PList.val(this);
	}
}
