package com.persistentbit.sql.dsl.generic.expressions.impl.dboolean;

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
 * @since 23/11/17
 */
public class DBooleanValue extends DBooleanAbstract implements PrepStatParam{
	private final Boolean value;

	public DBooleanValue(Boolean value) {
		this.value = value;
	}

	@Override
	public void _setPrepStatement(PreparedStatement stat, int index) throws SQLException{
		if(value == null){
			stat.setNull(index, Types.BOOLEAN);
			return;
		}
		stat.setBoolean(index,value);

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
		return "(Boolean)" + value;
	}
}
