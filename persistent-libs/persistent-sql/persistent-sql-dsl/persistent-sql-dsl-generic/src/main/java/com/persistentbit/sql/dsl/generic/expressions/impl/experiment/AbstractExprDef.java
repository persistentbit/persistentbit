package com.persistentbit.sql.dsl.generic.expressions.impl.experiment;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.sql.utils.rowreader.RowReader;

/**
 * TODOC
 *
 * @author petermuys
 * @since 16/12/17
 */
public abstract class AbstractExprDef<E extends DExpr<J>,J> implements ExprDef<E,J>{
	private final ExprContext	context;
	private final DExprInternal<J>	internal;

	public AbstractExprDef(ExprContext context, DExprInternal<J> internal) {
		this.context = context;
		this.internal = internal;
	}

	@Override
	public SqlWithParams _toSqlSelection(String alias) {
		return internal._toSqlSelection(alias);
	}

	@Override
	public SqlWithParams _toSql() {
		return internal._toSql();
	}

	@Override
	public SqlWithParams _toSqlValues() {
		return internal._toSqlValues();
	}

	@Override
	public J _read(RowReader rowReader) {
		return internal._read(rowReader);
	}

	@Override
	public DExpr<J> _withAlias(String alias) {
		return internal._withAlias(alias);
	}

	@Override
	public PList<DExpr> _expand() {
		return null;
	}

	@Override
	public String _getColumnName() {
		return null;
	}
}
