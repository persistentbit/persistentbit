package com.persistentbit.sql.dsl.generic.expressions.impl.time;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.PrepStatParam;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public class DLocalTimeValue extends DLocalTimeAbstract implements PrepStatParam{
	private final LocalTime value;

	public DLocalTimeValue(LocalTime value) {
		this.value = value;
	}

	@Override
	public void _setPrepStatement(PreparedStatement stat, int index) throws SQLException {
		if(value == null){
			stat.setTime(index,null);
		} else {
			stat.setTime(index, Time.valueOf(value));
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

	@Override
	public String toString() {
		return "(LocalTime)" + value;
	}
}
