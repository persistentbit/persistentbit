package com.persistentbit.sql.dsl.exprcontext;

import com.persistentbit.sql.dsl.generic.expressions.*;
import com.persistentbit.sql.dsl.generic.expressions.impl.DTable;
import com.persistentbit.sql.dsl.generic.query.Query;

import java.util.Optional;

/**
 * TODOC
 *
 * @author petermuys
 * @since 26/11/17
 */
public interface DbTableContext{
	String	getTableName();
	DExprBoolean createExprBoolean(DTable table, String columnName);
	DExprByte createExprByte(DTable table, String columnName);
	DExprShort createExprShort(DTable table, String columnName);
	DExprInt createExprInt(DTable table, String columnName);
	DExprLong createExprLong(DTable table,String columnName);
	DExprDouble createExprDouble(DTable table,String columnName);
	DExprBigDecimal createExprBigDecimal(DTable table,String columnName);
	DExprString createExprString(DTable table,String columnName);
	DExprDateTime createExprDateTime(DTable table, String columnName);


	Query	createQuery(DTable table);

	DbTableContext	withAlias(String alias);
	Optional<String> getAlias();

	DbTableContext withTableAlias(String tableAlias);
	Optional<String> getTableAlias();


	default String getTableNameOrAlias() {
		return getTableAlias().orElse(getTableName());
	}

}
