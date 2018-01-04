package com.persistentbit.sql.dsl.codegen.importer;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.javacodegen.AccessLevel;
import com.persistentbit.javacodegen.JField;
import com.persistentbit.result.Result;
import com.persistentbit.string.UString;

/**
 * TODOC
 *
 * @author petermuys
 * @since 25/12/17
 */
public class StructTableField implements TableField{

	private final CgTableName      tableName;
	private final String           columnName;
	@Nullable
	private final StructTableField parent;

	public StructTableField(CgTableName tableName, String columnName, @Nullable StructTableField parent) {

		this.tableName = tableName;
		this.columnName = columnName;
		this.parent = parent;
	}

	public StructTableField(CgTableName tableName, String columnName) {
		this(tableName, columnName, null);
	}

	public StructTableField withParent(StructTableField parent) {
		return new StructTableField(tableName, columnName, parent);
	}

	@Override
	public String getColumnName(CgContext context) {
		return columnName;
	}

	@Override
	public boolean isNullable(CgContext context) {
		StructureTypeDef structType = (StructureTypeDef) context.getTypeDef(getTypeRef(context));
		return structType.isNullable(context);
	}

	@Override
	public String getJavaGetter(CgContext context) {
		String thisGetter = "get" + UString.firstUpperCase(getJavaName(context)) + "()";

		if(parent != null) {
			if(parent.isNullable(context)) {
				thisGetter = parent.getJavaGetter(context) + ".map(pv -> pv." + thisGetter + ")";
			}
			else {
				thisGetter = parent.getJavaGetter(context) + "." + thisGetter;
			}
		}
		return thisGetter;
	}

	@Override
	public String getJavaName(CgContext context) {
		return context
			.columnNameToJava(tableName, columnName);
	}

	@Override
	public TypeRef getTypeRef(CgContext context) {
		return context.createExprTypeRef(tableName);
	}

	@Override
	public PList<SimpleTableField> expand(CgContext context, StructTableField parent) {
		StructTableField fullParent = withParent(parent);
		StructureTypeDef structType = (StructureTypeDef) context.getTypeDef(getTypeRef(context));
		return structType.getFields()
			.map(f -> f.expand(context, fullParent))
			.<SimpleTableField>flatten().plist();
	}

	@Override
	public Result<JField> createJavaField(CgContext context, boolean forceNullable) {
		return Result.function(forceNullable).code(log -> {
			TypeRef tr      = getTypeRef(context);
			TypeRef javaRef = context.getTypeDef(tr).getJavaRef(context);
			JField f = new JField(getJavaName(context), javaRef.getClassName())
				.withAccessLevel(AccessLevel.Private).asFinal()
				.addImports(javaRef.getImports(context));
			if(forceNullable) {
				f = f.addAnnotation(Nullable.class);
			}
			return Result.success(f);
		});

	}

	@Override
	public JField createExprField(CgContext context) {
		TypeRef tr = getTypeRef(context);
		return new JField(getJavaName(context), tr.getClassName())
			.withAccessLevel(AccessLevel.Public).asFinal()
			.addImports(tr.getImports(context));
	}

	public String getExprCode(CgContext context) {
		String res = getJavaName(context);
		if(parent == null) {
			res = parent.getExprCode(context) + "." + res;
		}
		return res;
	}
}
