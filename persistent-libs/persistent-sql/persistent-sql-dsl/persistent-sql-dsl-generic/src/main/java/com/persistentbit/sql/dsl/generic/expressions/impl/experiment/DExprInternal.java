package com.persistentbit.sql.dsl.generic.expressions.impl.experiment;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.sql.utils.rowreader.RowReader;

/**
 * TODOC
 *
 * @author petermuys
 * @since 16/12/17
 */
public interface DExprInternal<J>{
	SqlWithParams _toSqlSelection(String alias);

	default SqlWithParams _toSql() {
		return SqlWithParams.sql(toString());
	}

	default SqlWithParams _toSqlValues() {
		return SqlWithParams.sql("VALUES(").add(_toSql()).add(")");
	}

	J _read(RowReader rowReader);

	DExpr<J> _withAlias(String alias);
	PList<DExpr> _expand();

	String _getColumnName();
}
