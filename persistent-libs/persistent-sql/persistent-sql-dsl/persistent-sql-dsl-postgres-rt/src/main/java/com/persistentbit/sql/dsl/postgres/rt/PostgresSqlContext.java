package com.persistentbit.sql.dsl.postgres.rt;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.utils.rowreader.ResultSetRowReader;

import java.sql.ResultSet;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class PostgresSqlContext implements DbSqlContext{
	private boolean isOutsideSelection;

	public PostgresSqlContext(boolean isOutsideSelection) {
		this.isOutsideSelection = isOutsideSelection;
	}
	public PostgresSqlContext(){
		this(false);
	}


	@Override
	public ResultSetRowReader createResultSetRowReader(ResultSet rs) {
		return new ResultSetRowReader(rs);
	}
}
