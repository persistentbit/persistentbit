package com.persistentbit.sql.dsl.newsystem;

import com.persistentbit.collections.PList;
import com.persistentbit.javacodegen.JJavaFile;

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

	PList<JJavaFile> generate(CgContext context);
}
