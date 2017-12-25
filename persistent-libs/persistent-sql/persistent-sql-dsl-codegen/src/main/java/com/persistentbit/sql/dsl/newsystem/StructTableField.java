package com.persistentbit.sql.dsl.newsystem;

/**
 * TODOC
 *
 * @author petermuys
 * @since 25/12/17
 */
public class StructTableField implements TableField{

	private final TypeRef typeRef;
	private final String  columnName;

	public StructTableField(TypeRef typeRef, String columnName) {
		this.typeRef = typeRef;
		this.columnName = columnName;
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
