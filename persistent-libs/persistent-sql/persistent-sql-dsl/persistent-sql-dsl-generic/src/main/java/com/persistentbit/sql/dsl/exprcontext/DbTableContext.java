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

	EBool createExprBoolean(String columnName);

	EByte createExprByte(String columnName);

	EShort createExprShort(String columnName);

	EInt createExprInt(String columnName);

	ELong createExprLong(String columnName);

	EDouble createExprDouble(String columnName);

	EBigDecimal createExprBigDecimal(String columnName);

	EString createExprString(String columnName);

	EDateTime createExprDateTime(String columnName);

	EFloat createExprFloat(String columnName);

	EByteList createExprByteList(String columnName);

	EBitList createExprBitList(String columnName);

	EDate createExprLocalDate(String columnName);

	EZonedDateTime createExprZonedDateTime(String columnName);

	ETime createExprTime(String columnName);


	Query createQuery(DExprTable table);

	DbTableContext withTableAlias(String tableAlias);

	Optional<String> getTableAlias();


	DbContext	getDbContext();
}
