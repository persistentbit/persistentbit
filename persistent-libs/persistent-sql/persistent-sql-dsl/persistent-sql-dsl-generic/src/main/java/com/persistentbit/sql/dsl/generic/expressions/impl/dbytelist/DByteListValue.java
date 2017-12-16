package com.persistentbit.sql.dsl.generic.expressions.impl.dbytelist;

import com.persistentbit.collections.PByteList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.PrepStatParam;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.string.UString;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 * TODOC
 *
 * @author petermuys
 * @since 16/12/17
 */
public class DByteListValue extends DByteListAbstract implements PrepStatParam{
	private final PByteList value;

	public DByteListValue(PByteList value) {
		this.value = value;
	}

	@Override
	public void _setPrepStatement(PreparedStatement stat, int index) throws SQLException {
		if(value == null){
			stat.setNull(index, Types.BINARY);
		}else {
			stat.setBinaryStream(index,value.getInputStream());
		}
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
		return ("(byte[])" + UString.present((value == null ? "null" : value.toHexString()),80));
	}
}