package com.persistentbit.sql.dsl.generic.expressions.impl.datetime;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.PrepStatParam;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.utils.exceptions.ToDo;

import java.sql.PreparedStatement;
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
	public void _setPrepStatement(PreparedStatement stat, int index) {
		throw new ToDo();
	}

	@Override
	public SqlWithParams toSqlSelection(DbSqlContext context) {
		return new SqlWithParams(this);
	}

	@Override
	public SqlWithParams toSql(DbSqlContext context) {
		return new SqlWithParams(this);
	}
}
