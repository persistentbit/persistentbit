package com.persistentbit.sql.dsl.generic.expressions.impl.date;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.PrepStatParam;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.utils.exceptions.ToDo;

import java.sql.PreparedStatement;
import java.time.LocalDate;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public class DDateValue extends DDateAbstract implements PrepStatParam{
	private final LocalDate value;

	public DDateValue(LocalDate value) {
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
