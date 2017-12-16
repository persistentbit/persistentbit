package com.persistentbit.sql.dsl.generic.expressions.impl.dstring;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.PrepStatParam;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.sql.utils.rowreader.RowReader;
import com.persistentbit.string.UString;

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
	public SqlWithParams _toSqlSelection(DbSqlContext context, String alias) {
		return _toSql(context).add(alias == null ? "" : " AS " + alias);
	}
	@Override
	public SqlWithParams _toSql(DbSqlContext context) {
		return SqlWithParams.param(this);
	}
	@Override
	public String toString() {
		return (value == null)
			? "(String)null"
			: UString.presentEscaped("\"" + value + "\"",80);
	}

	@Override
	public String _read(DbSqlContext context, RowReader rowReader
	) {
		return rowReader.readNext(String.class);
	}
}
