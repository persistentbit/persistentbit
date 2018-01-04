package com.persistentbit.sql.dsl.codegen.importer;

import com.persistentbit.collections.PList;
import com.persistentbit.javacodegen.JJavaFile;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;

/**
 * TODOC
 *
 * @author petermuys
 * @since 25/12/17
 */
public class SimpleTypeDef implements TypeDef{

	private final TypeRef                          typeRef;
	private final TypeRef                          javaTypeRef;
	private final Class<? extends ExprTypeFactory> typeFactoryCls;


	public SimpleTypeDef(TypeRef typeRef, TypeRef javaTypeRef,
						 Class<? extends ExprTypeFactory> typeFactoryCls
	) {
		this.typeRef = typeRef;
		this.javaTypeRef = javaTypeRef;
		this.typeFactoryCls = typeFactoryCls;
	}

	public SimpleTypeDef(Class<? extends DExpr> expr, Class<?> javaType,
						 Class<? extends ExprTypeFactory> typeFactoryCls
	) {
		this(TypeRef.create(expr), TypeRef.create(javaType), typeFactoryCls);
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
	public Result<PList<JJavaFile>> generate(CgContext context) {
		return Result.success(PList.empty());
	}

	@Override
	public void init(CgContext context) {

	}

	@Override
	public DbGenContext generateDb(CgContext context, DbGenContext db) {
		/*String exprContext = db.getExprContext();
		db = db.withCls(db.getCls()
							.addMethod(
								new JMethod("val", getRef(context).getClassName())
									.addArg(getJavaRef(context).getClassName(), "value")
									.asStatic()
									.withAccessLevel(AccessLevel.Public)
									.withCode(pw -> {
										pw.println("return " + exprContext + ".getTypeFactory(" + getRef(context)
											.getClassName() + ".class).buildVal(value);");
									})
							)
		);
		db = db.withCls(db.getCls().addImports(getJavaRef(context).getImports(context)));
		db = db.withCls(db.getCls().addImports(getRef(context).getImports(context)));

		db = db.addInitCode(pw -> {
			pw.println(exprContext + ".registerType(" + getRef(context).getClassName() + ".class, " + typeFactoryCls
				.getSimpleName() + ".class);");
		});
		db = db.withCls(db.getCls()
							.addImport(typeFactoryCls)
							.addImports(getRef(context).getImports(context))
		);*/
		return db;
	}
}
