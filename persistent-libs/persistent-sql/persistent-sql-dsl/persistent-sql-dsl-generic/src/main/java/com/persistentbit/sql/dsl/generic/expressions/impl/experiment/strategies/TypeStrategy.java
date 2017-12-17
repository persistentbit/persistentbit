package com.persistentbit.sql.dsl.generic.expressions.impl.experiment.strategies;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public interface TypeStrategy<J>{
	//SqlWithParams _toSqlSelection(String alias);

	SqlWithParams _toSql();


	//J _read(RowReader rowReader);

	//DExpr<J> _withAlias(String alias);
	//PList<DExpr> _expand(DExpr<J> expr);

	PList<String> _getColumnNames();

	String _createAliasName(String aliasPrefix);
}
