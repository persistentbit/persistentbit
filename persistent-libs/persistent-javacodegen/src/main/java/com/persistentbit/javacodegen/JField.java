package com.persistentbit.javacodegen;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.javacodegen.annotations.*;
import com.persistentbit.reflection.BaseValueClass;
import com.persistentbit.string.UString;

import java.lang.annotation.Annotation;
import java.util.Optional;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/05/17
 */
public class JField extends BaseValueClass{

	private final String name;
	private final String definition;
	private final boolean isStatic;
	private final boolean isFinal;
	private final AccessLevel accessLevel;
	@Nullable
	private final String doc;

	@Nullable
	private final Class primitiveType;
	private final PSet<JImport> imports;
	private final PList<String> annotations;
	private final String initValue;

	public JField(String name, String definition, boolean isStatic, boolean isFinal,
				  AccessLevel accessLevel,
				  String doc,
				  Class primitiveType,
				  PSet<JImport> imports,
				  PList<String> annotations,
				  String initValue
	) {
		this.name = name;
		this.definition = definition;
		this.isStatic = isStatic;
		this.isFinal = isFinal;

		this.doc = doc;
		this.primitiveType = primitiveType;
		this.imports = imports;
		this.accessLevel = accessLevel;
		this.annotations = annotations;
		this.initValue = initValue;
	}

	public boolean hasAnnotation(String name) {
		return getAnnotation(name).isPresent();
	}

	public Optional<String> getAnnotation(String name) {
		return annotations.find(str -> str.startsWith("@" + name));
	}

	public JField(String name, String definition, Class primitiveType) {
		this(name, definition, false, true,
			AccessLevel.Private,
			null,
			primitiveType,
			PSet.empty(),
			PList.empty(),
			null
		);
	}

	public JField(String name, String definition) {
		this(name, definition, asPrimitiveType(name).orElse(null));
	}

	public JField(String name, Class type) {
		this(name, type.getSimpleName(), type.isPrimitive() ? type : null);
	}

	public JField withAccessLevel(AccessLevel accessLevel) {
		return copyWith("accessLevel", accessLevel);
	}

	public JField primitive(Class cls) {
		return copyWith("primitiveType", cls);
	}

	public JField asStatic() {
		return copyWith("isStatic", true);
	}

	public JField asNullable() {
		if(isNullable()) {
			return this;
		}
		return addImport(Nullable.class).addAnnotation("@" + Nullable.class.getSimpleName());
	}

	public JField defaultValue(String defaultValue) {
		return addImport(DefaultValue.class)
			.addAnnotation("@" + DefaultValue.class.getSimpleName() + "(\"" + defaultValue + "\")");
	}

	public JField defaultEmptyValue() {
		return addImport(DefaultEmpty.class).addAnnotation("@" + DefaultEmpty.class.getSimpleName());
	}

	public JField initValue(String initValue) {
		//return addImport(InitValue.class).addAnnotation("@" + InitValue.class.getSimpleName() + "(\"" + initValue + "\")");
		return copyWith("initValue",initValue);
	}

	public JField withFinal(boolean isFinal) {
		return copyWith("isFinal", isFinal);
	}

	public JField notFinal() {
		return withFinal(false);
	}

	public JField asFinal() {
		return withFinal(true);
	}

	public JField noGetter() {
		if(isGenGetter() == false) {
			return this;
		}
		return addImport(NoGet.class).addAnnotation("@" + NoGet.class.getSimpleName());
	}

	public JField noWith() {
		if(isGenWith() == false) {
			return this;
		}
		return addImport(NoWith.class).addAnnotation("@" + NoWith.class.getSimpleName());
	}

	public JField javaDoc(String doc) {
		return copyWith("doc", doc);
	}

	public JField excludeHash() {
		return copyWith("includeInHash", false);
	}

	public String getName() {
		return name;
	}

	public String getDefinition() {
		return definition;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	public boolean isGenGetter() {
		return hasAnnotation(NoGet.class.getSimpleName()) == false;
	}

	public boolean isGenWith() {
		return hasAnnotation(NoWith.class.getSimpleName()) == false;
	}

	public String getDoc() {
		return doc;
	}

	public boolean isIncludeInHash() {
		return hasAnnotation(NoEqual.class.getSimpleName()) == false;
	}

	public boolean isNullable() {
		return hasAnnotation(Nullable.class.getSimpleName());
	}


	public Optional<String> annotationValue(String ann) {
		int start = ann.indexOf("\"");
		int end   = ann.lastIndexOf("\"");
		if(start < 0 || end < 0) {
			return Optional.empty();
		}

		return Optional.of(UString.unEscapeJavaString(ann.substring(start + 1, end)));
	}

	public PList<String> getAnnotations() {
		return annotations;
	}

	public boolean hasDefaultValue() {
		return getAnnotation(DefaultValue.class.getSimpleName()).isPresent()
			|| getAnnotation(DefaultEmpty.class.getSimpleName()).isPresent()
			;
	}


	public Optional<String> getInitValue() {
		return Optional.ofNullable(initValue);
	}

	public JField withAnnotations(PList<String> annotations) {
		return copyWith("annotations", annotations);
	}

	public JField addAnnotation(String annotation) {
		return copyWith("annotations", annotations.plus(annotation));
	}

	public JField addAnnotation(Class<? extends Annotation> annotationCls) {
		return addAnnotation("@" + annotationCls.getSimpleName()).addImport(annotationCls);
	}


	public boolean isRequired() {
		return isNullable() == false && hasDefaultValue() == false;
	}

	static public Optional<Class> asPrimitiveType(String className) {
		switch(className) {
			case "char":
			case "Character":
				return Optional.of(char.class);
			case "Integer":
			case "int":
				return Optional.of(int.class);
			case "Byte":
			case "byte":
				return Optional.of(byte.class);
			case "Short":
			case "short":
				return Optional.of(short.class);
			case "Long":
			case "long":
				return Optional.of(long.class);
			case "Float":
			case "float":
				return Optional.of(float.class);
			case "Double":
			case "double":
				return Optional.of(double.class);
			case "Boolean":
			case "boolean":
				return Optional.of(boolean.class);
			default:
				return Optional.empty();
		}

	}

	public String getNullableDefinition() {
		if(primitiveType == null) {
			return definition;
		}

		switch(primitiveType.getSimpleName()) {
			case "char":
				return "Character";
			case "int":
				return "Integer";
			case "byte":
				return "Byte";
			case "short":
				return "Short";
			case "long":
				return "Long";
			case "float":
				return "Float";
			case "double":
				return "Double";
			case "boolean":
				return "Boolean";
			default:
				throw new RuntimeException("Unknown: " + primitiveType);
		}

	}

	public Optional<Class> getPrimitiveType() {
		return Optional.ofNullable(primitiveType);
	}

	public boolean isArray() {
		return getDefinition().endsWith("[]");
	}

	public boolean isArrayArray() {
		return getDefinition().endsWith("[][]");
	}

	public JMethod createGetter() {
		boolean isOptional = isNullable() && hasDefaultValue() == false;
		String resType = isOptional
			? "Optional<" + getNullableDefinition() + ">"
			: definition;
		JMethod m = new JMethod("get" + UString.firstUpperCase(name), resType);
		if(isOptional) m = m.addImport(JImport.forClass(Optional.class));
		m = m.withCode(out -> {
			if(isOptional) {
				out.println("return Optional.ofNullable(" + (isStatic ? "" : "this.") + name + ");");
			}
			else {
				out.println("return " + (isStatic ? "" : "this.") + name + ";");
			}
		});
		m = m.addAnnotation("@Generated");
		m = m.addImport(JImport.forClass(Generated.class));
		return m;
	}


	public JField addImport(JImport imp) {
		return copyWith("imports", imports.plus(imp));
	}

	public JField addImports(Iterable<JImport> impList) {
		return copyWith("imports", imports.plusAll(impList));
	}

	public JField addImport(Class cls) {
		return addImport(JImport.forClass(cls));
	}

	public PSet<JImport> getAllImports() {
		PSet<JImport> res = imports;
		return res;
	}

	public JArgument asArgument() {
		String def = definition;
		if(isRequired() == false && getPrimitiveType().isPresent()) {
			def = getNullableDefinition();
		}
		return new JArgument(def, name, isRequired() == false, PList.empty(), getAllImports(),false);
	}
}