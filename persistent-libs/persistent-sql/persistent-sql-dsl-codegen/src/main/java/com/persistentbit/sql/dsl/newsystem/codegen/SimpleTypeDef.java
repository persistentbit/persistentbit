package com.persistentbit.sql.dsl.newsystem.codegen;

import com.persistentbit.collections.PList;
import com.persistentbit.javacodegen.JJavaFile;
import com.persistentbit.sql.dsl.expressions.DExpr;

/**
 * TODOC
 *
 * @author petermuys
 * @since 25/12/17
 */
public class SimpleTypeDef implements TypeDef{

	private final TypeRef typeRef;
	private final TypeRef javaTypeRef;


	public SimpleTypeDef(TypeRef typeRef, TypeRef javaTypeRef) {
		this.typeRef = typeRef;
		this.javaTypeRef = javaTypeRef;
	}

	public SimpleTypeDef(Class<? extends DExpr> expr, Class<?> javaType) {
		this(TypeRef.create(expr), TypeRef.create(javaType));
	}

	@Override
	public TypeRef getRef(CgContext context) {
		return typeRef;
	}

	@Override
	public TypeRef getJavaRef(CgContext context) {
		return javaTypeRef;
	}

	@Override
	public PList<JJavaFile> generate(CgContext context) {
		return PList.empty();
	}

	@Override
	public void init(CgContext context) {

	}
}
