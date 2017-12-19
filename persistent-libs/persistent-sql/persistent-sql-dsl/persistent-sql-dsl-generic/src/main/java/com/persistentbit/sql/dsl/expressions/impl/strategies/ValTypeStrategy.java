package com.persistentbit.sql.dsl.expressions.impl.strategies;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.PrepStatParam;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.SqlWithParams;
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
	private final Lazy<ExprTypeJdbcConvert<J>> jdbcConvert;
	private final Lazy<PrepStatParam> prepStatParam;

	public ValTypeStrategy(Class<? extends DExpr<J>> typeClass,
						   ExprTypeFactory<?, J> exprTypeFactory,
						   J value,
						   Lazy<ExprTypeJdbcConvert<J>> jdbcConvert
	) {
		super(typeClass, exprTypeFactory);
		this.value = value;
		this.jdbcConvert = jdbcConvert;

		this.prepStatParam = Lazy.code(() -> {
			ExprTypeJdbcConvert<J> jdbc = jdbcConvert.get();
			return new PrepStatParam(){
				@Override
				public int _setPrepStatement(PreparedStatement stat, int index) throws SQLException {
					jdbc.setParam(index, stat, value);
					return jdbc.columnCount();
				}

				@Override
				public String toString() {
					return value.toString();
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
		return "[" + typeClass.getSimpleName() + ":" + value + "]";
	}
}
