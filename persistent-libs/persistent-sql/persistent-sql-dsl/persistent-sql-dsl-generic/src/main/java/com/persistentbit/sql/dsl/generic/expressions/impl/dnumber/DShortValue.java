package com.persistentbit.sql.dsl.generic.expressions.impl.dnumber;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.PrepStatParam;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/11/17
 */
public class DShortValue extends DShortAbstract implements PrepStatParam{
	private final Short value;

	public DShortValue(Short value) {
		this.value = value;
	}

	@Override
	public void _setPrepStatement(PreparedStatement stat, int index) throws SQLException {
		if(value == null){
			stat.setNull(index, Types.SMALLINT);
			return;
		}
		stat.setShort(index,value);
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
		return "(Short)" + value ;
	}
}
