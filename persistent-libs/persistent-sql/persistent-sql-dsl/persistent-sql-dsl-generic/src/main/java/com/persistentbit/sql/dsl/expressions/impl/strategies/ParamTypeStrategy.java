package com.persistentbit.sql.dsl.expressions.impl.strategies;

import com.persistentbit.collections.PMap;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.PrepStatParam;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.utils.Lazy;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 21/12/17
 */
public class ParamTypeStrategy<J> extends AbstractTypeStrategy<J>{

	private final Function<PMap<String,Object>,Object> paramGetter;
	private final Lazy<ExprTypeJdbcConvert<J>> jdbcConvert;
	private final Lazy<PrepStatParam> prepStatParam;

	public ParamTypeStrategy(Class<? extends DExpr<J>> typeClass,
							 ExprTypeFactory<?, J> exprTypeFactory,
							 Function<PMap<String, Object>, Object> paramGetter,
							 Lazy<ExprTypeJdbcConvert<J>> jdbcConvert
	) {
		super(typeClass, exprTypeFactory);
		this.paramGetter = paramGetter;
		this.jdbcConvert = jdbcConvert;
		this.prepStatParam = Lazy.code(() -> {
			ExprTypeJdbcConvert<J> jdbc = jdbcConvert.get();
			return new PrepStatParam(){
				@Override
				public int _setPrepStatement(PMap<String,Object> extParams, PreparedStatement stat, int index) throws SQLException {
					jdbc.setParam(index, stat, (J)paramGetter.apply(extParams));
					return jdbc.columnCount();
				}

				@Override
				public String toString() {
					return "Param  of type " + typeClass;
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
		return "Param[" + typeClass.getSimpleName() + "]";
	}
}
