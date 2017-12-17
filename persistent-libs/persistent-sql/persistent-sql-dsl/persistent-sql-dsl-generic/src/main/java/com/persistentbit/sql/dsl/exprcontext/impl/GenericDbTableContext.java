package com.persistentbit.sql.dsl.exprcontext.impl;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbContext;
import com.persistentbit.sql.dsl.exprcontext.DbTableContext;
import com.persistentbit.sql.dsl.generic.expressions.*;
import com.persistentbit.sql.dsl.generic.expressions.impl.date.DDateTableFieldExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.datetime.local.DDateTimeTableFieldExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.datetime.zoned.DZonedDateTimeTableFieldExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.dbitlist.DBitListTableFieldExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.dboolean.DBooleanTableFieldExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.dbytelist.DByteListTableFieldExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.dnumber.*;
import com.persistentbit.sql.dsl.generic.expressions.impl.dstring.DStringTableFieldExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.time.DLocalTimeTableFieldExpr;
import com.persistentbit.sql.dsl.generic.query.Query;
import com.persistentbit.sql.dsl.generic.query.impl.QueryImpl;

import java.util.Objects;
import java.util.Optional;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public class GenericDbTableContext implements DbTableContext{

	private final DbContext dbContext;
	private final String    fullTableName;
	@Nullable
	private final String    tableAlias;

	public GenericDbTableContext(DbContext dbContext, String fullTableName, @Nullable String tableAlias) {
		this.dbContext = Objects.requireNonNull(dbContext);
		this.fullTableName = Objects.requireNonNull(fullTableName);
		this.tableAlias = tableAlias;
	}

	@Override
	public DbContext getDbContext() {
		return dbContext;
	}

	@Override
	public String getTableName() {
		return fullTableName;
	}

	private String getFieldTable() {
		return tableAlias != null ? tableAlias : fullTableName;
	}

	@Override
	public EBool createExprBoolean(String columnName) {
		return new DBooleanTableFieldExpr(new GenericDbTableFieldExprContext(getFieldTable(), columnName));
	}

	@Override
	public EByte createExprByte(String columnName) {
		return new DByteTableFieldExpr(new GenericDbTableFieldExprContext(getFieldTable(), columnName));
	}

	@Override
	public EShort createExprShort(String columnName) {
		return new DShortTableFieldExpr(new GenericDbTableFieldExprContext(getFieldTable(), columnName));
	}

	@Override
	public EInt createExprInt(String columnName) {
		return new DIntTableFieldExpr(new GenericDbTableFieldExprContext(getFieldTable(), columnName));
	}

	@Override
	public ELong createExprLong(String columnName
	) {
		return new DLongTableFieldExpr(new GenericDbTableFieldExprContext(getFieldTable(), columnName));
	}

	@Override
	public EDouble createExprDouble(String columnName
	) {
		return new DDoubleTableFieldExpr(new GenericDbTableFieldExprContext(getFieldTable(), columnName));
	}

	@Override
	public EBigDecimal createExprBigDecimal(String columnName
	) {
		return new DBigDecimalTableFieldExpr(new GenericDbTableFieldExprContext(getFieldTable(), columnName));
	}

	@Override
	public EString createExprString(String columnName
	) {
		return new DStringTableFieldExpr(new GenericDbTableFieldExprContext(getFieldTable(), columnName));

	}

	@Override
	public EDateTime createExprDateTime(String columnName
	) {
		return new DDateTimeTableFieldExpr(new GenericDbTableFieldExprContext(getFieldTable(), columnName));
	}

	public EDate createExprLocalDate(String columnName){
		return new DDateTableFieldExpr(new GenericDbTableFieldExprContext(getFieldTable(),columnName));
	}

	@Override
	public EFloat createExprFloat(String columnName) {
		return new DFloatTableFieldExpr(new GenericDbTableFieldExprContext(getFieldTable(),columnName));
	}

	@Override
	public EByteList createExprByteList(String columnName) {
		return new DByteListTableFieldExpr(new GenericDbTableFieldExprContext(getFieldTable(), columnName));
	}

	@Override
	public EBitList createExprBitList(String columnName) {
		return new DBitListTableFieldExpr(new GenericDbTableFieldExprContext(getFieldTable(), columnName));
	}

	@Override
	public EZonedDateTime createExprZonedDateTime(String columnName) {
		return new DZonedDateTimeTableFieldExpr(new GenericDbTableFieldExprContext(getFieldTable(), columnName));
	}

	@Override
	public ETime createExprTime(String columnName) {
		return new DLocalTimeTableFieldExpr(new GenericDbTableFieldExprContext(getFieldTable(), columnName));
	}

	@Override
	public Query createQuery(DExprTable table) {
		return new QueryImpl(dbContext, PList.val(table));
	}

	@Override
	public DbTableContext withTableAlias(String tableAlias) {
		return new GenericDbTableContext(dbContext, fullTableName, tableAlias);
	}

	@Override
	public Optional<String> getTableAlias() {
		return Optional.ofNullable(tableAlias);
	}
}
