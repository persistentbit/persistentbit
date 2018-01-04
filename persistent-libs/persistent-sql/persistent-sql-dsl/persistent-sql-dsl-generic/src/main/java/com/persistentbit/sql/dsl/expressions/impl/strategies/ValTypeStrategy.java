package com.persistentbit.sql.dsl.expressions.impl.strategies;

import com.persistentbit.collections.PMap;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.impl.PrepStatParam;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.utils.Lazy;

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
	private final ExprTypeJdbcConvert<J> jdbcConvert;
	private final Lazy<PrepStatParam> prepStatParam;

	public ValTypeStrategy(J value,
						   ExprTypeJdbcConvert<J> jdbcConvert
	) {
		this.value = value;
		this.jdbcConvert = jdbcConvert;

		this.prepStatParam = Lazy.code(() -> {

			return new PrepStatParam(){
				@Override
				public int _setPrepStatement(PMap<String,Object> extParams, PreparedStatement stat, int index) throws SQLException {
					jdbcConvert.setParam(index, stat, value);
					return jdbcConvert.columnCount();
				}

				@Override
				public String toString() {
					return value == null ? "null" : value.toString();
				}
			};
		});
	}


	@Override
	public SqlWithParams _toSql() {
		return SqlWithParams.param(prepStatParam.get());
	}

	@Override
	public String toString() {
		return "[" + value + "]";
	}

	@Override
	public String _createAliasName(String aliasPrefix) {
		return aliasPrefix;
	}

	@Override
	public TypeStrategy<J> onlyColumnName() {
		return this;
	}
}
