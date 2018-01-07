package com.persistentbit.sql.dsl.assql;

import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;

/**
 * TODOC
 *
 * @author petermuys
 * @since 7/01/18
 */
@FunctionalInterface
public interface SqlConvertibleImpl extends SqlConvertible{

	SqlWithParams toSql(ExprContext context);
}
