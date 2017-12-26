package com.persistentbit.javacodegen;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.reflection.BaseValueClass;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/05/17
 */
public class JArgument extends BaseValueClass{
	private final String type;
	private final String name;
	private boolean isNullable;
	private final PList<String> annotations;
	private final PSet<JImport> imports;
	private final boolean isVarArg;

	public JArgument(String type, String name, boolean isNullable, PList<String> annotations, PSet<JImport> imports, boolean isVarArg) {
		this.type = type;
		this.name = name;
		this.isNullable = isNullable;
		this.annotations = annotations;
		this.imports = imports;
		this.isVarArg = isVarArg;
	}
	public JArgument(String type, String name){
		this(type,name,false, PList.empty(),PSet.empty(),false);
	}

	public JArgument(Class cls, String name) {
		this(cls.getSimpleName(), name);
	}
	public JArgument asNullable(){
		return copyWith("isNullable",true);
	}
	public JArgument asVarArg() {
		return copyWith("isVarArg",true);
	}
	public boolean isNullable(){
		return isNullable;
	}

	public JArgument addAnnotation(String annotation){
		return copyWith("annotations", annotations.plus(annotation));
	}

	public String toString() {
		String annStr = annotations.toString(" ");
		if(isNullable){
			annStr = "@Nullable" + " " + annStr;
		}
		annStr = annStr.trim().isEmpty() ? "" : annStr.trim() + " ";
		return  annStr + type + (isVarArg ? "..." : "") + " " + name;
	}

	public JArgument	addImport(JImport imp){
		return copyWith("imports",imports.plus(imp));
	}

	public PSet<JImport> getAllImports(){
		PSet<JImport> res = imports;
		if(isNullable){
			res = res.plus(JImport.forClass(Nullable.class));
		}
		return res;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public PList<String> getAnnotations() {
		return annotations;
	}
}
