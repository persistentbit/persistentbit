package com.persistentbit.sql.dsl.expressions.impl.strategies;

import com.persistentbit.collections.PMap;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.PrepStatParam;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeImpl;

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

	private final Class                                typeClass;
	private final Function<PMap<String,Object>,Object> paramGetter;


	public ParamTypeStrategy(Class<? extends DExpr> typeClass,
							 Function<PMap<String, Object>, Object> paramGetter
	) {
		this.typeClass = typeClass;
		this.paramGetter = paramGetter;

	}


	@Override
	public SqlWithParams _toSql(AbstractTypeImpl impl) {
		ExprTypeJdbcConvert jdbcConvert = impl.getJdbcConverter();
		PrepStatParam stat = new PrepStatParam(){
			@Override
			public int _setPrepStatement(PMap<String, Object> extParams, PreparedStatement stat,
										 int index) throws SQLException {
				jdbcConvert.setParam(index, stat, (J) paramGetter.apply(extParams));
				return jdbcConvert.columnCount();
			}

			@Override
			public String toString() {
				return "Param  of type " + typeClass;
			}
		};
		return SqlWithParams.param(stat);
	}

	@Override
	public String toString() {
		return "Param[" + typeClass.getSimpleName() + "]";
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
