package com.persistentbit.sql.dsl.newsystem;

import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.javacodegen.*;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeImpl;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.util.Iterator;

/**
 * TODOC
 *
 * @author petermuys
 * @since 25/12/17
 */
public class StructureTypeDef implements TypeDef{

	private final CgTableName       tableName;
	private final PList<TableField> fields;

	public StructureTypeDef(CgTableName tableName, PList<TableField> fields
	) {
		this.tableName = tableName;
		this.fields = fields;
	}

	@Override
	public void init(CgContext context) {

	}

	public boolean isNullable(CgContext context) {
		return fields.find(f -> f.isNullable(context) == false).isPresent() == false;
	}

	@Override
	public TypeRef getRef(CgContext context) {
		return context.createExprTypeRef(tableName);
	}

	@Override
	public TypeRef getJavaRef(CgContext context) {
		return context.createValueTypeRef(tableName);
	}

	public PList<TableField> getFields() {
		return fields;
	}

	@Override
	public PList<JJavaFile> generate(CgContext context) {

		return PList.val(
			createExpr(context)
			, createJavaCaseClass(context)
			, createTypeFactory(context)
		);
	}

	private JJavaFile createJavaCaseClass(CgContext context) {
		TypeRef javaRef = getJavaRef(context);
		JClass  cls     = new JClass(javaRef.getClassName());
		for(TableField tf : fields) {
			cls = cls.addField(tf.createJavaField(context, false));
		}
		cls = cls.makeCaseClass();
		JJavaFile jf = new JJavaFile(javaRef.getFullPackage(context));
		jf = jf.addClass(cls);
		return jf;

	}

	private JJavaFile createExpr(CgContext context) {
		TypeRef ref     = getRef(context);
		TypeRef javaRef = getJavaRef(context);
		JClass cls = new JClass(ref.getClassName())
			.asAbstract()
			.extendsDef("implements DExpr<" + getJavaRef(context).getClassName() + ">")
			.addImports(ref.getImports(context))
			.addImports(javaRef.getImports(context))
			.addImport(DExpr.class);

		//   CREATE FIELDS

		for(TableField sf : fields) {
			cls = cls.addField(sf.createExprField(context));
		}

		//   CREATE CONSTRUCTOR;
		JMethod constr = new JMethod(ref.getClassName());
		for(TableField sf : fields) {
			constr = constr.addArg(sf.createExprArg(context));
		}
		constr = constr.withCode(pw -> {
			for(TableField sf : fields) {
				String jn = sf.getJavaName(context);
				pw.println("this." + jn + " = " + jn);
			}
		});

		cls = cls.addMethod(constr);

		JJavaFile jf = new JJavaFile(ref.getFullPackage(context));
		jf = jf.addClass(cls);
		return jf;
	}

	public JJavaFile createTypeFactory(CgContext context) {
		TypeRef ref            = getRef(context);
		TypeRef refTypeFactory = context.createTypeFactoryTypeRef(tableName);
		TypeRef javaRef        = getJavaRef(context);
		JClass cls = new JClass(refTypeFactory.getClassName())
			.extendsDef(AbstractStructureTypeFactory.class.getSimpleName() + "<" + getRef(context)
				.getClassName() + ", " + getJavaRef(context).getClassName() + ">")
			.addImports(ref.getImports(context))
			.addImports(javaRef.getImports(context))
			.addImport(AbstractStructureTypeFactory.class);

		//ADD CONSTRUCTOR
		cls = cls.addMethod(
			new JMethod(refTypeFactory.getClassName())
				.withAccessLevel(AccessLevel.Public)
				.addImport(ExprContext.class)
				.addArg(new JArgument(ExprContext.class, "context"))
				.withCode(pw -> pw.println("super(context);"))
		);
		//ADD BUILD FIELDS
		cls = cls.addMethod(
			new JMethod("buildFields", "PList<StructureField<" + ref.getClassName() + ", " + javaRef
				.getClassName() + ">>")
				.overrides()
				.withAccessLevel(AccessLevel.Protected)
				.addImport(PList.class)
				.addImport(StructureField.class)
				.withCode(pw -> {
					pw.println("return PList.val(");
					boolean first = true;
					for(TableField tf : fields) {
						if(first) {
							first = false;
						}
						else {
							pw.print(",");
						}
						pw.println(tf.createCreateFieldCode(context));
					}
					pw.println(");");
				})
		);
		cls = cls.addImports(fields.map(f -> f.getTypeRef(context).getImports(context)).flatten());
		cls = cls.addImports(fields.map(f -> context.getTypeDef(f.getTypeRef(context)).getJavaRef(context)
			.getImports(context)).flatten());

		//ADD BUILD VALUE
		cls = cls.addMethod(
			new JMethod("buildValue", javaRef.getClassName())
				.overrides()
				.withAccessLevel(AccessLevel.Protected)
				.addArg(new JArgument("Object[]", "fieldValues"))

				.withCode(pw -> {
					pw.println("return new " + javaRef.getClassName() + "(");
					int index = 0;
					for(TableField tf : fields) {
						if(index > 0) {
							pw.print(", ");
						}
						String jt = context.getTypeDef(tf.getTypeRef(context)).getJavaRef(context).getClassName();
						pw.println("\t(" + jt + ")fieldValues[" + (index++) + "]");
					}
					pw.println(");");
				})
		);
		String impl = ref.getClassName() + "Impl";
		//ADD CREATE EXPRESSION
		cls = cls.addMethod(
			new JMethod("createExpression", impl)
				.overrides()
				.withAccessLevel(AccessLevel.Protected)
				.addImport(PStream.class)
				.addImport(DExpr.class)
				.addArg(new JArgument("PStream<DExpr>", "fieldValues"))
				.withCode(pw -> {
					pw.println("return new " + impl + "(fieldValues.iterator());");
				})
		);
		//ADD TYPE CLASS
		cls = cls.addMethod(
			new JMethod("getTypeClass", "Class<? extends DExpr<" + javaRef.getClassName() + ">>")
				.overrides()
				.withAccessLevel(AccessLevel.Public)
				.withCode(pw -> {
					pw.println("return " + ref.getClassName() + ".class;");
				})
		);

		//ADD IMPLEMENTATION
		JClass implCls = new JClass(impl)
			.asFinal()
			.withAccessLevel(AccessLevel.Private)
			.extendsDef(ref.getClassName() + " implements " + ExprTypeImpl.class.getSimpleName() + "<" + ref
				.getClassName() + ", " + javaRef.getClassName() + ">")
			.addImport(ExprTypeImpl.class);
		implCls = implCls.addMethod(
			new JMethod(implCls.getClassName())
				.addArg(new JArgument("Iterator<DExpr>", "iter"))
				.addImport(Iterator.class)
				.addImport(DExpr.class)
				.withCode(pw -> {
					pw.println("super(");
					boolean first = true;
					for(TableField field : fields) {
						if(first) {
							first = false;
						}
						else {
							pw.print(", ");
						}
						String name = field.getTypeRef(context).getClassName();
						pw.println("\t(" + name + ") iter.next()");
					}
					pw.println(");");
				})
		);

		implCls = implCls.addMethod(
			new JMethod("getTypeFactory", ExprTypeFactory.class.getSimpleName() + "<" + cls.getClassName() + ">")
				.overrides()
				.withAccessLevel(AccessLevel.Public)
				.withCode(pw -> {
					pw.println("return " + refTypeFactory.getClassName() + ".this;");
				})
		);

		implCls = implCls.addMethod(
			new JMethod("toString", "String")
				.overrides()
				.withAccessLevel(AccessLevel.Public)
				.withCode(pw -> {
					pw.println("return \"" + ref.getClassName() + "[\" + "
								   + fields.map(f -> f.getJavaName(context)).toString(" + ")
								   + " + \"]\";"
					);
				})
		);


		cls = cls.addInternalClass(implCls);


		JJavaFile jf = new JJavaFile(refTypeFactory.getFullPackage(context));
		jf = jf.addClass(cls);
		return jf;
	}
}
