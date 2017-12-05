package com.persistentbit.sql.dsl.generic.expressions.impl.dnumber;

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
 * @since 24/11/17
 */
public class DLongValue extends DLongAbstract implements PrepStatParam{
	private final Long value;


	public DLongValue(Long value) {this.value = value;}

	@Override
	public void _setPrepStatement(PreparedStatement stat, int index) throws SQLException{
		stat.setLong(index, value);
	}

	@Override
	public SqlWithParams _toSqlSelection(DbSqlContext context, String alias) {
		return _toSql(context).add(alias == null ? "" : " AS " + alias);
	}

	@Override
	public Long _read(DbSqlContext context, RowReader rowReader
	) {
		return rowReader.readNext(Long.class);
	}
	@Override
	public SqlWithParams _toSql(DbSqlContext context) {
		return SqlWithParams.param(this);
	}

	@Override
	public String toString() {
		return "$(" + value + ")";
	}
}
