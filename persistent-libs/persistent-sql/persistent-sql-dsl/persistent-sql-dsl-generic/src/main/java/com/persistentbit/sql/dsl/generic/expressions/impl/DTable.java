package com.persistentbit.sql.dsl.generic.expressions.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprTable;
import com.persistentbit.sql.dsl.generic.expressions.impl.dtable.DImplTable;
import com.persistentbit.sql.dsl.generic.query.Query;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.sql.utils.rowreader.RowReader;
import com.persistentbit.tuples.Tuple2;

import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 26/11/17
 */
public abstract class DTable<T, X extends DTable> implements DImpl<T> , DExprTable<T>, DImplTable{
	protected final DbTableContext	_tableContext;
	protected PList<Tuple2<String,DExpr<?>>> _all;
	protected Function<DbSqlContext,Function<RowReader,T>> _recordReader;
	protected Function<String,X> _doWithAlias;
	protected DTable(DbTableContext tableContext){
		this._tableContext = tableContext;
	}

	public Query	query() {
		return _tableContext.createQuery(this);
	}


	@Override
	public SqlWithParams _toSqlSelection(DbSqlContext context, String alias) {
		return SqlWithParams.empty.add(_all.map(t ->DImpl._get(t._2)._toSqlSelection(context,alias)),", ");

	}

	@Override
	public SqlWithParams _toSql(DbSqlContext context
	) {
		return SqlWithParams.empty.add(_all.map(t -> DImpl._get(t._2)._toSql(context)),", ");
	}


	@Override
	public SqlWithParams _toSqlFrom(DbSqlContext context) {
		return SqlWithParams.sql(_tableContext.getTableName())
			.add(_tableContext.getTableAlias().map(a -> " AS " + a).orElse(""));

	}



	@Override
	public T _read(DbSqlContext context, RowReader rowReader
	) {
		return _recordReader.apply(context).apply(rowReader);
	}
	@Override
	public  X _withAlias(String selectionAliasName){
		return _doWithAlias.apply(selectionAliasName);
	}

}
