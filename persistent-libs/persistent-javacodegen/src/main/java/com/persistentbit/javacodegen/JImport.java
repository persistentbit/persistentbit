package com.persistentbit.javacodegen;

import com.persistentbit.printable.PrintableText;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/05/17
 */
public class JImport{
	private final String name;
	private final boolean isStatic;

	public JImport(String name, boolean isStatic) {
		this.name = name;
		this.isStatic = isStatic;
	}

	public static JImport forClass(Class cls){
		return new JImport(cls);
	}

	public JImport(String name){
		this(name,false);
	}
	public JImport(Class cls){
		this(cls.getName());
	}
	public JImport asStatic() {
		return new JImport(name,true);
	}
	public PrintableText print() {
		return out -> {
			String res = "import ";
			res += isStatic ? " static " : "";
			res += name;
			res += ";";
			out.println(res);
		};
	}

	public boolean includeForPackage(String packageName){
		if(isStatic){
			return true;
		}
		return name.startsWith(packageName) == false;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		JImport jImport = (JImport) o;

		if(isStatic != jImport.isStatic) return false;
		return name.equals(jImport.name);
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + (isStatic ? 1 : 0);
		return result;
	}

	@Override
	public String toString() {
		return "JImport{" +
			"name='" + name + '\'' +
			", isStatic=" + isStatic +
			'}';
	}
}
