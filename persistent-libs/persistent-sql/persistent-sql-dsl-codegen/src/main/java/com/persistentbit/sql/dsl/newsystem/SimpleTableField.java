package com.persistentbit.sql.dsl.newsystem;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.javacodegen.AccessLevel;
import com.persistentbit.javacodegen.JField;
import com.persistentbit.string.UString;

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
	private final boolean          isAutoGenKey;
	private final StructTableField parent;

	public SimpleTableField(TypeRef typeRef, CgTableName tableName, String columnName,
							boolean isNullable,
							boolean hasDefault,
							boolean isPrimKey,
							boolean isAutoGenKey, StructTableField parent
	) {
		this.typeRef = typeRef;
		this.tableName = tableName;
		this.columnName = columnName;
		this.isNullable = isNullable;
		this.hasDefault = hasDefault;
		this.isPrimKey = isPrimKey;
		this.isAutoGenKey = isAutoGenKey;
		this.parent = parent;
	}

	public SimpleTableField(TypeRef typeRef, CgTableName tableName, String columnName,
							boolean isNullable,
							boolean hasDefault,
							boolean isPrimKey,
							boolean isAutoGenKey
	) {
		this(typeRef, tableName, columnName, isNullable, hasDefault, isPrimKey, isAutoGenKey, null);
	}

	@Override
	public String getColumnName(CgContext context) {
		if(parent != null) {
			return parent.getColumnName(context) + columnName;
		}
		return columnName;
	}

	@Override
	public String getJavaGetter(CgContext context) {
		/*if(isNullable(context)) {
			res += "v -> v." + javaGetter + "().orElse(null)";
		}
		else {
			res += javaValueTypeClass + "::" + javaGetter;
		}*/

		String thisGetter = "get" + UString.firstUpperCase(getJavaName(context)) + "()";

		if(parent != null) {
			if(parent.isNullable(context)) {
				thisGetter = parent.getJavaGetter(context) + ".map(pv -> pv." + thisGetter + ")";

			}
			else {
				thisGetter = parent.getJavaGetter(context) + "." + thisGetter;
			}
		}
		if(isNullable) {
			thisGetter += ".orElse(null)";
		}
		return thisGetter;
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

	public boolean isAutoGenKey() {
		return isAutoGenKey;
	}

	public boolean isPrimKey() {
		return isPrimKey;
	}

	public SimpleTableField withParent(StructTableField parent) {
		return new SimpleTableField(typeRef, tableName, columnName, isNullable, hasDefault, isPrimKey, isAutoGenKey, parent);
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
		TypeRef tr            = getTypeRef(context);
		TypeRef javaRef       = getJavaTypeRef(context);
		String  javaClsName   = javaRef.getClassName();
		Class   primitiveType = null;
		if(isNullable == false && forceNullable == false) {
			switch(javaClsName) {
				case "Byte":
					primitiveType = byte.class;
					break;
				case "Short":
					primitiveType = short.class;
					break;
				case "Integer":
					primitiveType = int.class;
					break;
				case "Long":
					primitiveType = long.class;
					break;
				case "Float":
					primitiveType = float.class;
					break;
				case "Double":
					primitiveType = double.class;
					break;
				case "Boolean":
					primitiveType = boolean.class;
					break;
				default:
					break;
			}
		}

		JField f = primitiveType == null
			? new JField(getJavaName(context), javaClsName)
			: new JField(getJavaName(context), primitiveType).primitive(primitiveType);
		f = f
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
