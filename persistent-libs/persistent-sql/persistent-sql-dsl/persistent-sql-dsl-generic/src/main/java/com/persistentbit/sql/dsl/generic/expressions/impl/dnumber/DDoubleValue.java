package com.persistentbit.sql.dsl.generic.expressions.impl.dnumber;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.PrepStatParam;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.utils.exceptions.ToDo;

import java.sql.PreparedStatement;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/11/17
 */
public class DDoubleValue extends DDoubleAbstract implements PrepStatParam{
	private final Double value;

	public DDoubleValue(Double value) {
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
