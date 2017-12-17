package com.persistentbit.sql.dsl.generic.expressions.impl.experiment.strategies;

import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.PrepStatParam;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.ExprTypeFactory;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.utils.Lazy;

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

	public ValTypeStrategy(Class<? extends DExpr<J>> typeClass,
						   ExprTypeFactory<?,J> exprTypeFactory,
						   J value,
						   ExprTypeJdbcConvert<J> jdbcConvert
	) {
		super(typeClass, exprTypeFactory);
		this.value = value;
		this.jdbcConvert = jdbcConvert;

		this.prepStatParam = Lazy.code(() ->
		   (PrepStatParam) (stat, index) -> {
				jdbcConvert.setParam(index,stat,value);
				return jdbcConvert.columnCount();
		   }
		);
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
