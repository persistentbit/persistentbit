package com.persistentbit.sql.dsl.generic.expressions.impl.dbitlist;

import com.persistentbit.collections.PBitList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.PrepStatParam;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.utils.exceptions.ToDo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 * TODOC
 *
 * @author petermuys
 * @since 16/12/17
 */
public class DBitListValue extends DBitListAbstract implements PrepStatParam{
	private final PBitList value;

	public DBitListValue(PBitList value) {
		this.value = value;
	}

	@Override
	public void _setPrepStatement(PreparedStatement stat, int index) throws SQLException {
		if(value == null){
			stat.setNull(index, Types.BIT);
		} else {
			/*BitSet bs = new BitSet(value.size());
			int t=0;
			for(boolean v : value){
				bs.set(t++, v);
			}
			stat.setObject(index,bs,Types.BIT);*/

			//stat.setObject(index,"0B'" + value.toBinaryString() + "'");
			throw new ToDo("BITLIST is currently not supported...Postgres driver fucks this up");
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
		return "(BitList)" + (value == null ? "null" : value.toBinaryString());
	}
}