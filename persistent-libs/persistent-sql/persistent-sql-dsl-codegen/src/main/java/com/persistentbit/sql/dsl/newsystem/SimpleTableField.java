package com.persistentbit.sql.dsl.newsystem;

/**
 * TODOC
 *
 * @author petermuys
 * @since 25/12/17
 */
public class SimpleTableField implements TableField{

	private final TypeRef typeRef;
	private final String  columnName;
	private final boolean isNullable;
	private final boolean hasDefault;
	private final boolean isPrimKey;

	public SimpleTableField(TypeRef typeRef, String columnName, boolean isNullable,
							boolean hasDefault,
							boolean isPrimKey
	) {
		this.typeRef = typeRef;
		this.columnName = columnName;
		this.isNullable = isNullable;
		this.hasDefault = hasDefault;
		this.isPrimKey = isPrimKey;
	}

	@Override
	public String getJavaName(TableTypeDef table, CgContext context) {
		return context
			.columnNameToJava(table.getCatalogName(), table.getSchemaName(), table.getTableName(), columnName);
	}

	@Override
	public TypeRef getTypeRef(TableTypeDef table, CgContext context) {
		return typeRef;
	}
}
