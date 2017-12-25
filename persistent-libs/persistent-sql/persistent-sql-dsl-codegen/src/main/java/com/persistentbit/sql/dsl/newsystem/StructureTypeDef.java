package com.persistentbit.sql.dsl.newsystem;

import com.persistentbit.collections.PList;
import com.persistentbit.javacodegen.JClass;
import com.persistentbit.javacodegen.JJavaFile;
import com.persistentbit.javacodegen.JMethod;
import com.persistentbit.sql.dsl.expressions.DExpr;

/**
 * TODOC
 *
 * @author petermuys
 * @since 25/12/17
 */
public class StructureTypeDef implements TypeDef{

	private final TypeRef            ref;
	private final TypeRef            javaRef;
	private final PList<StructField> fields;

	public StructureTypeDef(TypeRef ref, TypeRef javaRef,
							PList<StructField> fields
	) {
		this.ref = ref;
		this.javaRef = javaRef;
		this.fields = fields;
	}

	@Override
	public TypeRef getRef(CgContext context) {
		return ref;
	}

	@Override
	public TypeRef getJavaRef(CgContext context) {
		return javaRef;
	}

	@Override
	public PList<JJavaFile> generate(CgContext context) {
		JClass cls = new JClass(ref.getClassName())
			.asAbstract()
			.extendsDef("implements DExpr<" + getJavaRef(context).getClassName() + ">")
			.addImports(ref.getImports(context))
			.addImports(javaRef.getImports(context))
			.addImport(DExpr.class);

		//   CREATE FIELDS

		for(StructField sf : fields) {
			cls = cls.addField(sf.asExprField(context));
		}

		//   CREATE CONSTRUCTOR;
		JMethod constr = new JMethod(ref.getClassName());
		for(StructField sf : fields) {
			constr = constr.addArg(sf.asArgument(context));
		}
		constr = constr.withCode(pw -> {
			for(StructField sf : fields) {
				pw.println(sf.assign("this.", sf.getFieldName()));
			}
		});

		cls = cls.addMethod(constr);

		JJavaFile jf = new JJavaFile(ref.getPackageName());
		jf = jf.addClass(cls);
		return PList.val(jf);
	}
}
