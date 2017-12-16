package com.persistentbit.sql.dsl.postgres.rt;

import com.persistentbit.sql.dsl.exprcontext.impl.GenericDbSqlContext;
import com.persistentbit.sql.utils.rowreader.ResultSetRowReader;

import java.sql.ResultSet;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class PostgresSqlContext extends GenericDbSqlContext{

	@Override
	public ResultSetRowReader createResultSetRowReader(ResultSet rs) {
		return new PostgresResultSetRowReader(rs);
	}



}
