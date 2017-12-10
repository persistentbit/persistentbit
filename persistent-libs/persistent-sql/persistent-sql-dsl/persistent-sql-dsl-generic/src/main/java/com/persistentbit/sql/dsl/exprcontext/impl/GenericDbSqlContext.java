package com.persistentbit.sql.dsl.exprcontext.impl;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.utils.rowreader.ResultSetRowReader;

import java.sql.ResultSet;

/**
 * TODOC
 *
 * @author petermuys
 * @since 10/12/17
 */
public class GenericDbSqlContext implements DbSqlContext{

	@Override
	public ResultSetRowReader createResultSetRowReader(ResultSet rs) {
		return new ResultSetRowReader(rs);
	}
}
