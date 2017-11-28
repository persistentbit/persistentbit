package com.persistentbit.sql.dsl.generic.expressions.impl.dstring;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.PrepStatParam;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.sql.utils.rowreader.RowReader;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/11/17
 */
public class DStringValue extends DStringAbstract implements PrepStatParam{
	private final String value;

	public DStringValue(String value) {
		this.value = value;
	}

	@Override
	public void _setPrepStatement(PreparedStatement stat, int index) throws SQLException{
		stat.setString(index,value);
	}

	@Override
	public SqlWithParams toSqlSelection(DbSqlContext context) {
		return new SqlWithParams(this);
	}

	@Override
	public String toString() {
		return "$(\'" + value + "\')";
	}

	@Override
	public String read(DbSqlContext context, RowReader rowReader
	) {
		return rowReader.readNext(String.class);
	}
}
