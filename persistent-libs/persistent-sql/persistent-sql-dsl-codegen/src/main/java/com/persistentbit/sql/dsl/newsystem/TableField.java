package com.persistentbit.sql.dsl.newsystem;

import com.persistentbit.javacodegen.AccessLevel;
import com.persistentbit.javacodegen.JArgument;
import com.persistentbit.javacodegen.JField;

/**
 * TODOC
 *
 * @author petermuys
 * @since 25/12/17
 */
public interface TableField{

	String getJavaName(TableTypeDef table, CgContext context);

	TypeRef getTypeRef(TableTypeDef table, CgContext context);

	default JField createExprField(TableTypeDef table, CgContext context) {
		TypeRef tr = getTypeRef(table, context);
		return new JField(getJavaName(table, context), tr.getClassName())
			.withAccessLevel(AccessLevel.Public).asFinal()
			.addImports(tr.getImports(context));
	}

	default JArgument createExprArg(TableTypeDef table, CgContext context) {
		return createExprField(table, context).asArgument();
	}
}
