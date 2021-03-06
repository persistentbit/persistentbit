package com.persistentbit.sql.dsl.codegen.importer;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.javacodegen.JArgument;
import com.persistentbit.javacodegen.JField;
import com.persistentbit.result.Result;

/**
 * TODOC
 *
 * @author petermuys
 * @since 25/12/17
 */
public interface TableField{

	String getJavaName(CgContext context);

	String getColumnName(CgContext context);

	TypeRef getTypeRef(CgContext context);

	PList<SimpleTableField> expand(CgContext context, @Nullable StructTableField parentField);

	Result<JField> createJavaField(CgContext context, boolean forceNullable);

	JField createExprField(CgContext context);

	boolean isNullable(CgContext context);


	default JArgument createExprArg(CgContext context) {
		return createExprField(context).asArgument();
	}

	String getJavaGetter(CgContext context);


	default String createCreateFieldCode(CgContext context, String javaValueTypeClass) {

		String res = "createField(" + getTypeRef(context).getClassName() + ".class, ";
		res += "\"" + getColumnName(context) + "\", \"" + getJavaName(context) + "\", ";
		String javaGetter = getJavaGetter(context);

		res += "v -> v." + getJavaGetter(context);
		res += ", v -> v." + getJavaName(context) + ")";
		return res;
	}
/*
	default JField createJavaField(CgContext context){
		TypeRef tr = getTypeRef(table, context);
		TypeRef javaRef = context.getTypeDef(tr).getJavaRef(context);
		return new JField(getJavaName(table, context), javaRef.getClassName())
			.withAccessLevel(AccessLevel.Private).asFinal()
			.addImports(javaRef.getImports(context));
	}

	default JField createExprField(CgContext context) {
		TypeRef tr = getTypeRef(table, context);
		return new JField(getJavaName(table, context), tr.getClassName())
			.withAccessLevel(AccessLevel.Public).asFinal()
			.addImports(tr.getImports(context));
	}

	default JArgument createExprArg(CgContext context) {
		return createExprField(table, context).asArgument();
	}*/
}
