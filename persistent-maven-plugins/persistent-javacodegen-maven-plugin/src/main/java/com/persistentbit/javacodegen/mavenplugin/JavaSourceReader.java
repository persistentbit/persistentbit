package com.persistentbit.javacodegen.mavenplugin;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.io.IO;
import com.persistentbit.io.IOFiles;
import com.persistentbit.io.IORead;
import com.persistentbit.javacodegen.*;
import com.persistentbit.result.Result;
import com.persistentbit.string.UString;

import java.nio.file.Path;
import java.util.EnumSet;
import java.util.Optional;

/**
 * Read a Java Source file and build the corresponding {@link JJavaFile} structure.
 *
 * @author petermuys
 * @since 10/06/17
 */
public class JavaSourceReader{


	/*public static Result<PList<Tuple2<File,JJavaFile>>>	importJavaSourceDirectory(File javaSourceDirectory){
		return Result.function(javaSourceDirectory).codeNoResultLog(l -> {
			return IOFiles.findPathsInTree(javaSourceDirectory.toPath(),IOFiles.fileExtensionPredicate("java")).flatMap(paths -> {
				l.info("Found " + paths.size() + " Java Source files");
				PList<Tuple2<File,JJavaFile>> res = PList.empty();
				for(Path path : paths){
					l.info("Importing source " + path);
					Result<JJavaFile> jf = importJavaSource(path.toFile());
					if(jf.isPresent()){
						res = res.plus(Tuple2.of(path.toFile(),jf.orElseThrow()));
					} else {
						l.add(jf);
					}
				}
				return Result.success(res);
			});
		});

	}*/


	public static Result<PList<Path>> findSourceFiles(Path javaSourceDirectory){
		return IOFiles.findPathsInTree(javaSourceDirectory,IOFiles.fileExtensionPredicate("java"));
	}

	public static Result<JJavaFile>	importJavaSource(Path javaSourceFile){
		return Result.function(javaSourceFile).code(l ->
			IORead.readTextFile(javaSourceFile.toFile(),IO.utf8)
				  .flatMap(src -> parseJava(javaSourceFile.toString(),src))
				  .flatMap(JavaSourceReader::importJavaFile)
		);
	}

	private static Result<CompilationUnit> parseJava(String sourceName, String code){
		return Result.function(sourceName, UString.presentEscaped(code,40)).codePresentResult(l -> {
			try{
				return Result.success(JavaParser.parse(code));
			}catch(Exception e){
				return Result.failure(e);
			}
		});
	}



	private static AccessLevel getAccessLevel(EnumSet<Modifier> modifiers){
		if(modifiers.contains(Modifier.PRIVATE)){
			return AccessLevel.Private;
		}
		if(modifiers.contains(Modifier.PROTECTED)){
			return AccessLevel.Protected;
		}
		if(modifiers.contains(Modifier.PUBLIC)){
			return AccessLevel.Public;
		}
		return AccessLevel.Package;
	}

	private static Optional<Class> getPrimitiveClass(Type type){
		switch(type.toString()){
			case "boolean": return Optional.of(boolean.class);
			case "byte": return Optional.of(byte.class);
			case "char": return Optional.of(char.class);
			case "short": return Optional.of(short.class);
			case "int": return Optional.of(int.class);
			case "long": return Optional.of(long.class);
			case "float": return Optional.of(float.class);
			case "double": return Optional.of(double.class);
			default: return Optional.empty();
		}
	}

	private static boolean isNullable(NodeList<AnnotationExpr> annList){
		return PStream.from(annList)
			.find(ann -> "Nullable".equals(ann.getNameAsString()))
			.isPresent();
	}

	private static Result<JJavaFile> importJavaFile(CompilationUnit cu){
		return Result.function(cu).code(l -> {
			NodeList<TypeDeclaration<?>> types    = cu.getTypes();
			JJavaFile                    javaFile = new JJavaFile(cu.getPackageDeclaration().map(pd -> pd.getNameAsString()).orElse(""));


			if(cu.getComment().isPresent()){
				javaFile = javaFile.withDoc(cu.getComment().get().toString());
			}
			for(ImportDeclaration imp : cu.getImports()){
				String name = imp.getNameAsString();
				if(imp.isAsterisk()){
					name += ".*";
				}
				javaFile = javaFile.addImport(new JImport(name, imp.isStatic()));
			}
			for (TypeDeclaration<?> type : types) {
				if(type instanceof ClassOrInterfaceDeclaration){
					javaFile = javaFile.addClass(importClass((ClassOrInterfaceDeclaration)type));
				}
			/*if(type instanceof EnumDeclaration){
				javaFile = javaFile.addEnum(importEnum((EnumDeclaration)type));
			}*/
			}
			return Result.success(javaFile);
		});

	}
	private static JEnum importEnum(EnumDeclaration e){
		throw new RuntimeException("TODO: read enum");
	}


	private static PList<JField> importField(FieldDeclaration field){
		PList<JField> fields = PList.empty();
		for(VariableDeclarator var : field.getVariables()){
			JField f = new JField(var.getNameAsString(),var.getType().asString(),getPrimitiveClass(var.getType()).orElse(null));
			if(field.getModifiers().contains(Modifier.STATIC)){
				f = f.asStatic();
			}
			if(field.getModifiers().contains(Modifier.FINAL) == false){
				f = f.notFinal();
			}
			AccessLevel accessLevel = getAccessLevel(field.getModifiers());
			f = f.withAccessLevel(accessLevel);

			//fields = fields.plus(f);
			if(field.getJavadoc().isPresent()){
				f = f.javaDoc(field.getJavadoc().get().toComment("").toString());
			}
			for(AnnotationExpr ann : field.getAnnotations()){
				f = f.addAnnotation(ann.toString());
			}
			if(var.getInitializer().isPresent()){
				f = f.initValue(var.getInitializer().get().toString());
			}
			fields = fields.plus(f);
		}
		return fields;
	}

	private static JClass importClass(ClassOrInterfaceDeclaration type){
		String name = type.getNameAsString();
		String pt   = PStream.from(type.getTypeParameters()).map(tp -> tp.toString()).toString(", ");
		if(pt.isEmpty() == false){
			name = name + "<" + pt + ">";
		}
		JClass cls = new JClass(name);
		if(type.getModifiers().contains(Modifier.STATIC)){
			cls = cls.asStatic();
		}
		for(AnnotationExpr ann : type.getAnnotations()){
			cls = cls.addAnnotation(ann.toString());
		}
		if(type.getJavadoc().isPresent()){
			cls = cls.javaDoc(type.getJavadoc().get().toComment("").toString());
		}

		for(ClassOrInterfaceType impl :type.getImplementedTypes()){
			cls = cls.addImplements(impl.toString());
		}
		for(ClassOrInterfaceType ci :type.getExtendedTypes()){
			cls = cls.extendsDef(ci.toString());
		}


		// Go through all fields, methods, etc. in this type
		NodeList<BodyDeclaration<?>> members = type.getMembers();
		for (BodyDeclaration<?> member : members) {
			if (member instanceof MethodDeclaration) {
				cls = cls.addMethod(importMethod((MethodDeclaration)member));

			}
			if (member instanceof FieldDeclaration){

				FieldDeclaration field = (FieldDeclaration) member;
				cls = cls.withFields(cls.getFields().plusAll(importField(field)));
			}
			if(member instanceof ClassOrInterfaceDeclaration){
				cls = cls.addInternalClass(importClass((ClassOrInterfaceDeclaration)member));
			}
			if(member instanceof ConstructorDeclaration){
				cls = cls.addMethod(importConstructor((ConstructorDeclaration)member));
			}

		}
		return cls;
	}

	private static JMethod importMethod(MethodDeclaration method){
		JMethod m = new JMethod(method.getNameAsString());
		m = m.withAccessLevel(getAccessLevel(method.getModifiers()));
		m = m.withResultType(method.getType().toString());
		String code = method.getBody().map(body -> body.toString()).orElse(null);
		m = m.withFullCode(code);
		if(method.getModifiers().contains(Modifier.STATIC)){
			m = m.asStatic();
		}
		if(method.getModifiers().contains(Modifier.FINAL)){
			m = m.asFinal();
		}
		for(AnnotationExpr ann : method.getAnnotations()){
			m = m.addAnnotation(ann.toString());
		}
		for(Parameter param : method.getParameters()){

			JArgument arg = new JArgument(param.getType().asString(),param.getNameAsString());
			if(param.isVarArgs()){
				arg = arg.asVarArg();
			}
			for(AnnotationExpr ann : param.getAnnotations()){
				arg = arg.addAnnotation(ann.toString());
			}
			m = m.addArg(arg);
		}
		return m;
	}
	private static JMethod importConstructor(ConstructorDeclaration method){
		JMethod m = new JMethod(method.getNameAsString());
		m = m.withAccessLevel(getAccessLevel(method.getModifiers()));

		String code = method.getBody().toString();
		m = m.withFullCode(code);

		for(AnnotationExpr ann : method.getAnnotations()){
			m = m.addAnnotation(ann.toString());
		}
		for(Parameter param : method.getParameters()){
			param.getAnnotations();
			param.getNameAsString();
			param.getType();
			JArgument arg = new JArgument(param.getType().asString(),param.getNameAsString());
			for(AnnotationExpr ann : param.getAnnotations()){
				arg = arg.addAnnotation(ann.toString());
			}
			m = m.addArg(arg);
		}
		return m;
	}
}
