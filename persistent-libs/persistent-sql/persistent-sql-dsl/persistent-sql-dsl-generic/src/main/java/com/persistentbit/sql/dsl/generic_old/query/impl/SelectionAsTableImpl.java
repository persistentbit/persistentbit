package com.persistentbit.sql.dsl.generic_old.query.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.old.DImpl;
import com.persistentbit.sql.dsl.expressions.impl.old.DImplTable;
import com.persistentbit.sql.dsl.generic_old.query.DSelectionTable;
import com.persistentbit.sql.dsl.generic_old.query.Query;
import com.persistentbit.sql.utils.rowreader.RowReader;
import com.persistentbit.utils.exceptions.ToDo;

/**
 * TODOC
 *
 * @author petermuys
 * @since 1/12/17
 */
public class SelectionAsTableImpl<T> implements DSelectionTable<T>, DImplTable, DImpl<T>{

	private final SelectionImpl<T> selection;
	private final DExpr<T> columnWithAlias;
	private final String alias;

	public SelectionAsTableImpl(SelectionImpl<T> selection, String alias) {
		this.selection = selection;
		this.alias = alias;
		this.columnWithAlias = DImpl._get(selection.columns)
									._withAlias(alias + ".sel_");
	}

	@Override
	public SqlWithParams _toSqlFrom(DbSqlContext context) {
		return SqlWithParams.sql("(")
							.add(selection.toSql(context, "sel"))
							.add(") AS " + alias);
	}

	@Override
	public Query query() {
		return new QueryImpl(selection.query.dbContext, PList.val(this));
	}

	@Override
	public String toString() {
		return _toSqlFrom(selection.query.dbContext.createSqlContext()).toString();
	}

	@Override
	public SqlWithParams _toSqlSelection(DbSqlContext context, String alias) {
		return _toSql(context).add(alias == null ? "" : " AS " + alias);
	}

	@Override
	public SqlWithParams _toSql(DbSqlContext context) {
		return DImpl._get(columnWithAlias)._toSql(context);
	}

	@Override
	public SqlWithParams _toSqlValues(DbSqlContext context) {
		throw new ToDo();
	}

	@Override
	public SqlWithParams _getInsertList(DbSqlContext context) {
		throw new UnsupportedOperationException("Insert not supported on SelectionAsTable");
	}

	@Override
	public PList<String> _getAutoGenKeyFieldNames() {
		throw new UnsupportedOperationException("Not supported on SelectionAsTable");
	}

	@Override
	public String getFullTableName() {
		throw new UnsupportedOperationException("Not supported on SelectionAsTable");
	}

	@Override
	public T _read(DbSqlContext context, RowReader rowReader) {
		return DImpl._get(columnWithAlias)._read(context, rowReader);
	}

	@Override
	public DExpr<T> _withAlias(String alias) {
		throw new ToDo();
	}

	@Override
	public PList<DExpr> _expand() {
		return DImpl._get(columnWithAlias)._expand();
	}

	@Override
	public DExpr<T> all() {
		return columnWithAlias;
	}
}
