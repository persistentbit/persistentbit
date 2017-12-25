package com.persistentbit.sql.dsl.newsystem;

import com.persistentbit.javacodegen.AccessLevel;
import com.persistentbit.javacodegen.JArgument;
import com.persistentbit.javacodegen.JField;
import com.persistentbit.printable.PrintableText;

/**
 * TODOC
 *
 * @author petermuys
 * @since 25/12/17
 */
public class StructField{

	private final TypeRef typeRef;
	private final String  fieldName;
	private final String  columnName;

	public StructField(TypeRef typeRef, String fieldName, String columnName) {
		this.typeRef = typeRef;
		this.fieldName = fieldName;
		this.columnName = columnName;
	}

	public TypeRef getTypeRef() {
		return typeRef;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getColumnName() {
		return columnName;
	}

	public JField asExprField(CgContext context) {
		return new JField(fieldName, typeRef.getClassName())
			.asFinal()
			.withAccessLevel(AccessLevel.Public)
			.addImports(typeRef.getImports(context));

	}

	public JArgument asArgument(CgContext context) {
		return asExprField(context).asArgument();
	}

	public PrintableText assign(String left, String name) {
		return pw -> {
			pw.print(left + name + " = " + name + ";");
		};
	}
}
