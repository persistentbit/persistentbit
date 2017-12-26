package com.persistentbit.sql.dsl.newsystem;

import com.persistentbit.collections.PList;
import com.persistentbit.javacodegen.JImport;

/**
 * TODOC
 *
 * @author petermuys
 * @since 25/12/17
 */
public final class TypeRef{

	private final String         packageName;
	private final String         className;
	private final PList<TypeRef> generics;
	private final boolean        isAbsolute;

	private TypeRef(String packageName, String className,
					PList<TypeRef> generics,
					boolean isAbsolute
	) {
		this.packageName = packageName;
		this.className = className;
		this.generics = generics;
		this.isAbsolute = isAbsolute;
	}

	public static TypeRef create(Class cls) {
		return new TypeRef(cls.getPackageName(), cls.getSimpleName(), PList.empty(), true);
	}

	public static TypeRef create(String packageName, String className) {
		return new TypeRef(packageName, className, PList.empty(), false);
	}


	public TypeRef withGenerics(PList<TypeRef> generics) {
		return new TypeRef(packageName, className, generics, isAbsolute);
	}

	public TypeRef withGenerics(TypeRef... gen) {
		return withGenerics(PList.val(gen));
	}

	public String getClassName() {
		String gen = generics.isEmpty()
			? ""
			: "<" + generics.map(TypeRef::getClassName).toString(", ") + ">";
		return className + gen;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getFullName() {
		return packageName + "." + className;
	}

	public String getFullName(CgContext context) {
		return isAbsolute
			? getFullName()
			: context.getBasePackage() + "." + getFullName();
	}

	public String getFullPackage(CgContext context) {
		return isAbsolute
			? getPackageName()
			: context.getBasePackage() + "." + getPackageName();
	}

	public PList<JImport> getImports(CgContext context) {
		return PList.val(new JImport(getFullName(context)))
			.plusAll(generics.map(g -> g.getImports(context)).flatten());
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o instanceof TypeRef == false) {
			return false;
		}
		return getFullName().equals(((TypeRef) o).getFullName());
	}

	@Override
	public int hashCode() {
		return getFullName().hashCode();
	}

	@Override
	public String toString() {
		return "REF[" + packageName + "." + getClassName() + "]";
	}
}
