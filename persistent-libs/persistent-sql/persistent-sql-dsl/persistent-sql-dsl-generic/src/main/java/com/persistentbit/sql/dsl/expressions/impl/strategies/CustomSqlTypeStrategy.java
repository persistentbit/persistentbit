package com.persistentbit.sql.dsl.expressions.impl.strategies;

import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeImpl;

import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 6/01/18
 */
public class CustomSqlTypeStrategy<J> extends AbstractTypeStrategy<J>{

	private final Supplier<SqlWithParams> sqlSupplier;

	public CustomSqlTypeStrategy(Supplier<SqlWithParams> sqlSupplier) {

		this.sqlSupplier = sqlSupplier;
	}


	@Override
	public SqlWithParams _toSql(AbstractTypeImpl impl) {
		return sqlSupplier.get();
	}

	@Override
	public String _createAliasName(AbstractTypeImpl impl, String aliasPrefix) {
		return aliasPrefix;
	}

	@Override
	public TypeStrategy<J> onlyColumnName(AbstractTypeImpl impl) {
		return this;
	}

	@Override
	public String toString() {
		return sqlSupplier.get().toString();
	}
}
