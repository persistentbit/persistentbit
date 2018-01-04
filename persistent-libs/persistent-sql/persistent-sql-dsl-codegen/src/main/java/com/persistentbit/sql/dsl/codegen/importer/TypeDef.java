package com.persistentbit.sql.dsl.codegen.importer;

import com.persistentbit.collections.PList;
import com.persistentbit.javacodegen.JJavaFile;
import com.persistentbit.result.Result;

/**
 * TODOC
 *
 * @author petermuys
 * @since 25/12/17
 */
public interface TypeDef{

	TypeRef getRef(CgContext context);

	TypeRef getJavaRef(CgContext context);

	void init(CgContext context);

	Result<PList<JJavaFile>> generate(CgContext context);

	DbGenContext generateDb(CgContext context, DbGenContext db);
}
