package com.persistentbit.sql.dsl.expressions.impl.old;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.utils.rowreader.RowReader;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/11/17
 */
public interface DInternal<T>{


	SqlWithParams _toSqlSelection(DbSqlContext context,String alias);

	default SqlWithParams _toSql(DbSqlContext context) {
		return SqlWithParams.sql(toString());
	}

	default SqlWithParams _toSqlValues(DbSqlContext context) {
		return SqlWithParams.sql("VALUES(").add(_toSql(context)).add(")");
	}

	T _read(DbSqlContext context, RowReader rowReader);

	DExpr<T> _withAlias(String alias);
	PList<DExpr> _expand();

	default String _getColumnName() {
		throw new UnsupportedOperationException("Get Column Name is not supported on " + this.getClass());
	}
}
