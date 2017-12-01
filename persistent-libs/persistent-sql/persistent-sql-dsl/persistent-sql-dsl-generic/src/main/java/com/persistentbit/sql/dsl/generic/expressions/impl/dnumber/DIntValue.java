package com.persistentbit.sql.dsl.generic.expressions.impl.dnumber;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.PrepStatParam;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/11/17
 */
public class DIntValue extends DIntAbstract implements PrepStatParam{
	private Integer value;

	public DIntValue(Integer value) {
		this.value = value;
	}

	@Override
	public void _setPrepStatement(PreparedStatement stat, int index) throws SQLException{
		stat.setInt(index, value);
	}

	@Override
	public SqlWithParams toSqlSelection(DbSqlContext context) {
		return new SqlWithParams(this);
	}

	@Override
	public SqlWithParams toSql(DbSqlContext context) {
		return new SqlWithParams(this);
	}

	@Override
	public String toString() {
		return "$(" + value + ")";
	}
}
