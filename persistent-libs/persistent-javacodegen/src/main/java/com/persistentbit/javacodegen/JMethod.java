package com.persistentbit.javacodegen;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.printable.PrintableText;
import com.persistentbit.reflection.BaseValueClass;

import java.util.Objects;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/05/17
 */
public class JMethod extends BaseValueClass{

	private final String        name;
	@Nullable
	private final String        resultType;
	@Nullable
	private final PrintableText definition;
	private final boolean       isStatic;
	private final boolean       isFinal;
	private final AccessLevel   accessLevel;

	@Nullable
	private final String doc;
	private final PList<String>    annotations;
	private final PList<JArgument> arguments;
	private final PSet<JImport>    imports;
	private final boolean          overrides;

	public JMethod(String name, String resultType, PrintableText definition, boolean isStatic, boolean isFinal,
				   AccessLevel accessLevel,
				   String doc,
				   PList<String> annotations,
				   PList<JArgument> arguments,
				   PSet<JImport> imports,
				   boolean overrides
	) {
		this.name = name;
		this.resultType = resultType;
		this.definition = definition;
		this.isStatic = isStatic;
		this.isFinal = isFinal;
		this.accessLevel = accessLevel;
		this.doc = doc;
		this.annotations = annotations;
		this.arguments = arguments;
		this.imports = imports;
		this.overrides = overrides;
	}

	public JMethod(String name, String resultType, PrintableText definition){
		this(
			name,
			resultType,
			definition,
			false,
			false,
			AccessLevel.Public,
			null,
			PList.empty(),
			PList.empty(),
			PSet.empty(),
			false
		);
	}

	public boolean isConstructor() {
		return resultType == null;
	}
	public JMethod(String name, String resultType){
		this(name,resultType,null);
	}

	public JMethod(String name){
		this(name,null);
	}

	public JMethod addArg(JArgument arg){
		return copyWith("arguments",arguments.plus(arg));
	}

	public JMethod addArgs(Iterable<JArgument> args) {
		return copyWith("arguments", arguments.plusAll(args));
	}
	public JMethod addArg(String type, String name, boolean isNullable, String...annotations){
		return addArg(new JArgument(type,name,isNullable,PList.val(annotations),PSet.empty(),false));
	}

	public JMethod doc(String doc){
		return copyWith("doc",doc);
	}

	public JMethod doc(PrintableText doc){
		return doc(doc.printToString());
	}

	public boolean isSameSignature(JMethod other){
		return name.equals(other.name)
			&& Objects.equals(resultType,other.resultType)
			&& arguments.map(JArgument::getType).equals(other.arguments.map(JArgument::getType))
			&& isStatic == other.isStatic();
	}

	public JMethod overrides() {
		return copyWith("overrides",true);
	}

	public JMethod withAccessLevel(AccessLevel level){
		return copyWith("accessLevel",level);
	}

	public JMethod withResultType(String resultType){
		return copyWith("resultType",resultType);
	}

	public JMethod withCode(PrintableText code){
		PrintableText fullCode = out -> {
			out.println("{");
			out.indent(code);
			out.println("}");
		};
		return copyWith("definition",fullCode);
	}

	public JMethod withFullCode(String code){
		PrintableText pt = out -> out.println(code);
		return copyWith("definition", pt);
	}

	public JMethod addAnnotation(String annotation){
		return copyWith("annotations",annotations.plus(annotation));
	}

	public PList<String> getAnnotations() {
		return annotations;
	}

	public JMethod asStatic() {
		return copyWith("isStatic",true);
	}

	public boolean isStatic() {
		return isStatic;
	}

	public boolean isAbstract() {
		return definition == null;
	}



	public JMethod asFinal() {
		return copyWith("isFinal",true);
	}

	public JMethod addImport(JImport imp){
		return copyWith("imports",imports.plus(imp));
	}

	public JMethod addImport(Class cls) {
		return addImport(new JImport(cls));
	}

	public JMethod addImports(Iterable<JImport> impList) {
		return copyWith("imports", imports.plusAll(impList));
	}
	public PSet<JImport> getAllImports(){
		return imports
			.plusAll(arguments.map(JArgument::getAllImports).flatten());
	}

	public PrintableText print() {
		return out -> {
			if(doc != null){
				out.print(doc);
			}
			annotations.forEach(out::println);
			if(overrides){
				out.println("@Override");
			}
			String res = accessLevel.label();
			res = res.isEmpty()? res : res + " ";
			res = isStatic ? res + " static" : res;
			res = isFinal ? res + " final" : res;
			if(definition == null){
				res += " abstract ";
			}
			res += (resultType == null ? "" : " " + resultType + "\t") + name;
			res += "(" + arguments.toString(", ") + ")";
			if(definition == null){
				out.println(res + ";");
				return;
			}
			out.print(res);
			out.print(definition);
		};
	}
}
