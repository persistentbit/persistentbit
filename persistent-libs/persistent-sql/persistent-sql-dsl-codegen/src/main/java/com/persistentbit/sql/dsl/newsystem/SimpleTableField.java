package com.persistentbit.sql.dsl.newsystem;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.javacodegen.AccessLevel;
import com.persistentbit.javacodegen.JField;

/**
 * TODOC
 *
 * @author petermuys
 * @since 25/12/17
 */
public class SimpleTableField implements TableField{

	private final TypeRef          typeRef;
	private final CgTableName      tableName;
	private final String           columnName;
	private final boolean          isNullable;
	private final boolean          hasDefault;
	private final boolean          isPrimKey;
	private final StructTableField parent;

	public SimpleTableField(TypeRef typeRef, CgTableName tableName, String columnName,
							boolean isNullable,
							boolean hasDefault,
							boolean isPrimKey,
							StructTableField parent
	) {
		this.typeRef = typeRef;
		this.tableName = tableName;
		this.columnName = columnName;
		this.isNullable = isNullable;
		this.hasDefault = hasDefault;
		this.isPrimKey = isPrimKey;
		this.parent = parent;
	}

	public SimpleTableField(TypeRef typeRef, CgTableName tableName, String columnName,
							boolean isNullable,
							boolean hasDefault,
							boolean isPrimKey
	) {
		this(typeRef, tableName, columnName, isNullable, hasDefault, isPrimKey, null);
	}

	@Override
	public String getColumnName(CgContext context) {
		return columnName;
	}

	@Override
	public boolean isNullable(CgContext context) {
		return isNullable;
	}

	public String getExprCode(CgContext context) {
		String res = getJavaName(context);
		if(parent != null) {
			res = parent.getExprCode(context) + "." + res;
		}
		return res;
	}

	public boolean isPrimKey() {
		return isPrimKey;
	}

	public SimpleTableField withParent(StructTableField parent) {
		return new SimpleTableField(typeRef, tableName, columnName, isNullable, hasDefault, isPrimKey, parent);
	}
	@Override
	public String getJavaName(CgContext context) {
		return context
			.columnNameToJava(tableName, columnName);
	}

	@Override
	public TypeRef getTypeRef(CgContext context) {
		return typeRef;
	}


	public TypeRef getJavaTypeRef(CgContext context) {
		return context.getTypeDef(typeRef).getJavaRef(context);
	}

	@Override
	public PList<SimpleTableField> expand(CgContext context, StructTableField parent) {
		return PList.val(this.withParent(parent));
	}

	@Override
	public JField createJavaField(CgContext context, boolean forceNullable) {
		TypeRef tr          = getTypeRef(context);
		TypeRef javaRef     = getJavaTypeRef(context);
		String  javaClsName = javaRef.getClassName();
		if(isNullable == false && forceNullable == false) {
			switch(javaClsName) {
				case "Byte":
					javaClsName = byte.class.getSimpleName();
					break;
				case "Short":
					javaClsName = short.class.getSimpleName();
					break;
				case "Integer":
					javaClsName = int.class.getSimpleName();
					break;
				case "Long":
					javaClsName = long.class.getSimpleName();
					break;
				case "Float":
					javaClsName = float.class.getSimpleName();
					break;
				case "Double":
					javaClsName = double.class.getSimpleName();
					break;
				case "Boolean":
					javaClsName = boolean.class.getSimpleName();
					break;
				default:
					break;
			}
		}

		JField f = new JField(getJavaName(context), javaClsName)
			.withAccessLevel(AccessLevel.Private).asFinal()
			.addImports(javaRef.getImports(context));
		if(isNullable || forceNullable) {
			f = f.addAnnotation(Nullable.class);
		}
		return f;
	}

	@Override
	public JField createExprField(CgContext context) {
		TypeRef tr = getTypeRef(context);
		return new JField(getJavaName(context), tr.getClassName())
			.withAccessLevel(AccessLevel.Public).asFinal()
			.addImports(tr.getImports(context));
	}
}
