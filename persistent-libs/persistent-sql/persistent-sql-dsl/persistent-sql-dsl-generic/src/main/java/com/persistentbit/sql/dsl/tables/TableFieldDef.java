package com.persistentbit.sql.dsl.tables;

import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/12/17
 */
public class TableFieldDef{

	private final String              fieldName;
	private final String              columnName;
	private final ExprTypeJdbcConvert jdbcConverter;
	private final boolean             hasDefault;
	private final boolean             isNullable;
	private final boolean             isPrimKey;

	public TableFieldDef(String fieldName, String columnName,
						 ExprTypeJdbcConvert jdbcConverter,
						 boolean hasDefault,
						 boolean isNullable,
						 boolean isPrimKey
	) {
		this.fieldName = fieldName;
		this.columnName = columnName;
		this.jdbcConverter = jdbcConverter;
		this.hasDefault = hasDefault;
		this.isNullable = isNullable;
		this.isPrimKey = isPrimKey;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getColumnName() {
		return columnName;
	}

	public ExprTypeJdbcConvert getJdbcConverter() {
		return jdbcConverter;
	}

	public boolean isHasDefault() {
		return hasDefault;
	}

	public boolean isNullable() {
		return isNullable;
	}

	public boolean isPrimKey() {
		return isPrimKey;
	}
}
