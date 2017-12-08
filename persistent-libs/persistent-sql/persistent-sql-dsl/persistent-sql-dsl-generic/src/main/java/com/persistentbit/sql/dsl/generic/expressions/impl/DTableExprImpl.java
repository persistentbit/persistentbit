package com.persistentbit.sql.dsl.generic.expressions.impl;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.sql.utils.rowreader.RowReader;
import com.persistentbit.utils.Lazy;

import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 2/12/17
 */
public abstract class DTableExprImpl<T> implements DExpr<T>{

	final DInternal _internal;
	final DImplTable _internalTable;
	@Nullable
	protected DbTableContext _tableContext;
	protected PList<String> _insertFieldNames = PList.empty();
	protected PList<String>	_autoGenKeyFieldNames = PList.empty();
	private final Lazy<PList<DExpr>> _allExpanded;
	public DTableExprImpl(
		PList<DExpr> all,
		Function<DbSqlContext,Function<RowReader,T>> _recordReader
	) {
		_allExpanded = new Lazy<>(()->
			all.map(e -> DImpl._get(e)._expand()).<DExpr>flatten().plist()
		);

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
			public SqlWithParams _toSql(DbSqlContext context) {
				return SqlWithParams.empty.add(all.map(t ->DImpl._get(t)._toSql(context)),", ");
			}

			@Override
			public DExpr<T> _withAlias(String alias) {
				return _doWithAlias(alias);
			}

			@Override
			public PList<DExpr> _expand() {
				return _allExpanded.get();
			}
		};


		_internalTable = new DImplTable(){
			@Override
			public SqlWithParams _getInsertList(DbSqlContext context) {
				return SqlWithParams.sql(_insertFieldNames.toString(", "));
			}

			@Override
			public SqlWithParams _toSqlFrom(DbSqlContext sqlContext) {
				return SqlWithParams.sql(_tableContext.getTableName())
									.add(_tableContext.getTableAlias().map(a -> " AS " + a).orElse(""));
			}

			@Override
			public String getFullTableName() {
				return _tableContext.getTableName();
			}

			@Override
			public PList<String> _getAutoGenKeyFieldNames() {
				return _autoGenKeyFieldNames;
			}
		};

	}

	protected abstract DExpr<T> _doWithAlias(String alias);
}
