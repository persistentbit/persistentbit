package com.persistentbit.sql.dsl.expressions.impl.strategies;

import com.persistentbit.sql.dsl.Sql;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeImpl;

import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 6/01/18
 */
public class CustomSqlTypeStrategy<J> extends AbstractTypeStrategy<J>{

	private final Supplier<Sql> sqlSupplier;

	public CustomSqlTypeStrategy(Supplier<Sql> sqlSupplier) {

		this.sqlSupplier = sqlSupplier;
	}


	@Override
	public Sql _toSql(AbstractTypeImpl impl) {
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
