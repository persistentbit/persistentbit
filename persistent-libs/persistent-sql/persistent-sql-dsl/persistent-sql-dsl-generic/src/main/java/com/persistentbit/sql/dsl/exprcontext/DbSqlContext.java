package com.persistentbit.sql.dsl.exprcontext;

import com.persistentbit.sql.utils.rowreader.ResultSetRowReader;

import java.sql.ResultSet;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public interface DbSqlContext{

	ResultSetRowReader createResultSetRowReader(ResultSet rs);
}
