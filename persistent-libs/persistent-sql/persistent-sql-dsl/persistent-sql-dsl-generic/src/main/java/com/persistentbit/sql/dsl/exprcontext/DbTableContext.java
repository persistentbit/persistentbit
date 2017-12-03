package com.persistentbit.sql.dsl.exprcontext;

import com.persistentbit.sql.dsl.generic.expressions.*;
import com.persistentbit.sql.dsl.generic.query.Query;

import java.util.Optional;

/**
 * TODOC
 *
 * @author petermuys
 * @since 26/11/17
 */
public interface DbTableContext{

	String getTableName();

	DExprBoolean createExprBoolean(String columnName);

	DExprByte createExprByte(String columnName);

	DExprShort createExprShort(String columnName);

	DExprInt createExprInt(String columnName);

	DExprLong createExprLong(String columnName);

	DExprDouble createExprDouble(String columnName);

	DExprBigDecimal createExprBigDecimal(String columnName);

	DExprString createExprString(String columnName);

	DExprDateTime createExprDateTime(String columnName);


	Query createQuery(DExprTable table);

	DbTableContext withTableAlias(String tableAlias);

	Optional<String> getTableAlias();


}
