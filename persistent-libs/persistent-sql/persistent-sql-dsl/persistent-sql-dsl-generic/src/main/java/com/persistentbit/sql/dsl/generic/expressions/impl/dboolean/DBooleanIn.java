package com.persistentbit.sql.dsl.generic.expressions.impl.dboolean;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.utils.exceptions.ToDo;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/11/17
 */
public class DBooleanIn extends DBooleanAbstract{
	private final PList<DExpr> values;

	public DBooleanIn(PList<DExpr> values) {
		this.values = values;
	}

	@Override
	public SqlWithParams _toSqlSelection(DbSqlContext context, String alias
	) {
		throw new ToDo();
	}
}
