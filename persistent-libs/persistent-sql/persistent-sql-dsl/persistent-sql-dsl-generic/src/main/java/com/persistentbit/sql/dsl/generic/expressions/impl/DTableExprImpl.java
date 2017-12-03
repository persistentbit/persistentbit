package com.persistentbit.sql.dsl.generic.expressions.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.sql.utils.rowreader.RowReader;

import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 2/12/17
 */
public abstract class DTableExprImpl<T> implements DExpr<T>{

	final DInternal _internal;

	public DTableExprImpl(
		PList<DExpr> all,
		Function<DbSqlContext,Function<RowReader,T>> _recordReader
	) {

		_internal = new DInternal<T>(){
			@Override
			public SqlWithParams _toSqlSelection(DbSqlContext context, String alias
			) {
				return SqlWithParams.empty.add(all.map(t ->DImpl._get(t)._toSqlSelection(context,alias)),", ");
			}

			@Override
			public T _read(DbSqlContext context, RowReader rowReader) {
				return _recordReader.apply(context).apply(rowReader);
			}

			@Override
			public DExpr<T> _withAlias(String alias) {
				return _doWithAlias(alias);
			}
		};
	}

	protected abstract DExpr<T> _doWithAlias(String alias);
}
