package com.persistentbit.javacodegen.mavenplugin;

import com.persistentbit.core.OK;
import com.persistentbit.core.collections.PList;
import com.persistentbit.core.io.IO;
import com.persistentbit.core.io.IOFiles;
import com.persistentbit.core.result.Result;
import com.persistentbit.javacodegen.JClass;
import com.persistentbit.javacodegen.JJavaFile;

import java.nio.file.Path;

/**
 * TODOC
 *
 * @author petermuys
 * @since 11/06/17
 */
public class CaseClassCodeGenerator{

	/**
	 * The Result of regenerating the case classes in a java source file file.<br>
	 * The Result is empty if no case class was found, otherwise OK if regenerated or Empty if no case classes where found
	 */
	public static class CaseClassGenResult{
		private final Path file;
		private final Result<OK>	result;

		public CaseClassGenResult(Path file, Result<OK> result) {
			this.file = file;
			this.result = result;
		}

		public Path getFile() {
			return file;
		}

		public Result<OK> getResult() {
			return result;
		}
	}

	/**
	 * (Re)generate all java source files found in the given directory tree.
	 * @param sourceDirPath Root of the source dir
	 * @return Result of a list with {@link CaseClassGenResult}
	 */
	public static Result<PList<CaseClassGenResult>> makeCaseClassesRecursive(Path sourceDirPath){
		return Result.function(sourceDirPath).codePresentResult(l ->
			JavaSourceReader.findSourceFiles(sourceDirPath)
				.map(paths ->
					paths.map(CaseClassCodeGenerator::makeCaseClass)
				)
		);
	}

	/**
	 * Parse and regenerate the case classes for a given java source file
	 * @param sourceFilePath Path to the java source file
	 * @return A {@link CaseClassGenResult}
	 */
	public static CaseClassGenResult	makeCaseClass(Path sourceFilePath){
		Result<OK> resOK = JavaSourceReader.importJavaSource(sourceFilePath)
			.flatMap(orgJFile -> makeCaseClasses(orgJFile)
				.flatMap(newJFile -> {
					if(newJFile.equals(orgJFile)){
						return Result.empty("Nog Case classes to convert");
					} else {
						return IOFiles.write(newJFile.print().printToString(),sourceFilePath.toFile(), IO.utf8);
					}
				}));
		return new CaseClassGenResult(sourceFilePath,resOK);
	}


	private static Result<JJavaFile>	makeCaseClasses(JJavaFile javaFile){
		return Result.function(javaFile).code(l -> {
			return Result.success(javaFile
				.withClasses(javaFile.getClasses().map(CaseClassCodeGenerator::makeCaseClass))
			);
		});
	}

	private static JClass makeCaseClass(JClass cls){
		cls = cls.withClasses(cls.getInternalClasses().map(CaseClassCodeGenerator::makeCaseClass));
		if(cls.hasAnnotation("CaseClass") == false){
			return cls;
		}
		return cls.makeCaseClass();
	}


}
