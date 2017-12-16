package com.persistentbit.sql.dsl.generic.expressions.impl.datetime.zoned;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.PrepStatParam;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.utils.exceptions.ToDo;

import java.sql.PreparedStatement;
import java.time.ZonedDateTime;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public class DZonedDateTimeValue extends DZonedDateTimeAbstract implements PrepStatParam{
	private final ZonedDateTime value;

	public DZonedDateTimeValue(ZonedDateTime value) {
		this.value = value;
	}

	@Override
	public void _setPrepStatement(PreparedStatement stat, int index) {
		throw new ToDo();
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
