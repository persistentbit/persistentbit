package com.persistentbit.sql.dsl.generic.query.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.impl.dtable.DImplTable;
import com.persistentbit.sql.dsl.generic.query.DSelectionTable;
import com.persistentbit.sql.dsl.generic.query.Query;
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
		this.columnWithAlias = DImpl._get(selection.columns)._withAlias("sel");
	}

	@Override
	public SqlWithParams _toSqlFrom(DbSqlContext context) {
		return new SqlWithParams("(").add(selection.toSql(context,columnWithAlias))
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
	public SqlWithParams _toSqlSelection(DbSqlContext context) {
		//return DImpl._get(columnWithAlias)._toSql(context);
		return _toSql(context);
	}

	@Override
	public SqlWithParams _toSql(DbSqlContext context) {
		return DImpl._get(DImpl._get(columnWithAlias)._withAlias(alias + "."))._toSql(context);
	}

	@Override
	public T _read(DbSqlContext context, RowReader rowReader) {
		return DImpl._get(columnWithAlias)._read(context,rowReader);
	}

	@Override
	public DExpr<T> _withAlias(String alias) {
		throw new ToDo();
	}
}
