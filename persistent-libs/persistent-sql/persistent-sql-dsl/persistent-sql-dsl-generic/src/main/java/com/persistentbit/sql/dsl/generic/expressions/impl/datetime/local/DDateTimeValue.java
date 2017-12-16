package com.persistentbit.sql.dsl.generic.expressions.impl.datetime.local;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.PrepStatParam;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public class DDateTimeValue extends DDateTimeAbstract implements PrepStatParam{
	private final LocalDateTime value;

	public DDateTimeValue(LocalDateTime value) {
		this.value = value;
	}

	@Override
	public void _setPrepStatement(PreparedStatement stat, int index) throws SQLException {
		if(value == null){
			stat.setDate(index,null);
		} else {
			java.sql.Date d = java.sql.Date.valueOf(value.toLocalDate());
			stat.setDate(index, d);
		}
	}

	@Override
	public SqlWithParams _toSqlSelection(DbSqlContext context, String alias) {
		return SqlWithParams.param(this).add(alias == null ? "" : " AS " + alias);
	}

	@Override
	public SqlWithParams _toSql(DbSqlContext context) {
		return SqlWithParams.param(this);
	}
}
