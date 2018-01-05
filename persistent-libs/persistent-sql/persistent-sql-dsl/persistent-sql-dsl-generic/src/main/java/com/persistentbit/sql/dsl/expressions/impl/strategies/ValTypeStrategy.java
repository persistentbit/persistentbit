package com.persistentbit.sql.dsl.expressions.impl.strategies;

import com.persistentbit.collections.PMap;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.impl.PrepStatParam;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class ValTypeStrategy<J> extends AbstractTypeStrategy<J>{

	private final J value;

	public ValTypeStrategy(J value) {
		this.value = value;
	}


	@Override
	public SqlWithParams _toSql(AbstractTypeImpl impl) {
		ExprTypeJdbcConvert jdbcConvert = impl.getJdbcConverter();
		PrepStatParam stat = new PrepStatParam(){
			@Override
			public int _setPrepStatement(PMap<String, Object> extParams, PreparedStatement stat,
										 int index) throws SQLException {
				jdbcConvert.setParam(index, stat, value);
				return jdbcConvert.columnCount();
			}

			@Override
			public String toString() {
				return value == null ? "null" : value.toString();
			}
		};
		return SqlWithParams.param(stat);
	}

	@Override
	public String toString() {
		return "[" + value + "]";
	}

	@Override
	public String _createAliasName(AbstractTypeImpl impl, String aliasPrefix) {
		return aliasPrefix;
	}

	@Override
	public TypeStrategy<J> onlyColumnName(AbstractTypeImpl impl) {
		return this;
	}
}
