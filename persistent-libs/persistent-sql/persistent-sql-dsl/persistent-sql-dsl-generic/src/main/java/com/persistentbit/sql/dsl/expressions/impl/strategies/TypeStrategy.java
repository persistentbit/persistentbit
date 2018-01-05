package com.persistentbit.sql.dsl.expressions.impl.strategies;

import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeImpl;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public interface TypeStrategy<J>{


	SqlWithParams _toSql(AbstractTypeImpl impl);


	String _createAliasName(AbstractTypeImpl impl, String aliasPrefix);

	default TypeStrategy<J> onlyColumnName(AbstractTypeImpl impl) {
		return this;
	}
}
