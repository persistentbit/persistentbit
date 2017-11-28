package com.persistentbit.sql.dsl.generic.expressions.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprTable;
import com.persistentbit.sql.dsl.generic.query.Query;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.tuples.Tuple2;

/**
 * TODOC
 *
 * @author petermuys
 * @since 26/11/17
 */
public abstract class DTable<T> extends DImpl<T> implements DExprTable<T>{
	protected final DbTableContext	_tableContext;
	protected PList<Tuple2<String,DExpr<?>>> _all;

	protected DTable(DbTableContext tableContext){
		this._tableContext = tableContext;
	}

	public Query	query() {
		return _tableContext.createQuery(this);
	}


	@Override
	public SqlWithParams toSqlSelection(DbSqlContext context
	) {
		return new SqlWithParams(_tableContext.getAlias()
			.orElse(_tableContext.getTableName())
			+ ".*");
	}

	@Override
	public SqlWithParams toSqlSelectableFrom(DbSqlContext context
	) {
		return new SqlWithParams(_tableContext.getTableName())
			.add(_tableContext.getAlias().map(a -> " AS " + a).orElse(""));

	}

	@Override
	public SqlWithParams toSql(DbSqlContext context
	) {
		return toSqlSelection(context);
	}
}
