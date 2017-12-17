package com.persistentbit.sql.dsl.generic.expressions.impl.experiment.strategies;

import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.ExprTypeFactory;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class AliasTypeStrategie<J> extends AbstractTypeStrategy<J>{
	private final String alias;
	public AliasTypeStrategie(Class<? extends DExpr<J>> typeClass,
							  ExprTypeFactory exprTypeFactory,
							  String alias
	) {
		super(typeClass, exprTypeFactory);
		this.alias = alias;
	}
	@Override
	public SqlWithParams _toSql() {
		return SqlWithParams.sql(alias);
	}

	@Override
	public SqlWithParams _toSqlSelection(String alias) {
		return _toSql().add(alias == null ? "" : " AS " + alias);
	}
}
