package com.persistentbit.sql.dsl.generic.expressions.impl.dnumber;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.PrepStatParam;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.utils.exceptions.ToDo;

import java.math.BigDecimal;
import java.sql.PreparedStatement;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/11/17
 */
public class DBigDecimalValue extends DBigDecimalAbstract implements PrepStatParam{
	private final BigDecimal value;

	public DBigDecimalValue(BigDecimal value) {
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
