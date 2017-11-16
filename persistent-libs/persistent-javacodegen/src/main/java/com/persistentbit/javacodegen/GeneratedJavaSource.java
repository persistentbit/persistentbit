package com.persistentbit.javacodegen;

import com.persistentbit.core.io.IOFiles;
import com.persistentbit.core.result.Result;
import com.persistentbit.core.utils.UReflect;
import com.persistentbit.javacodegen.annotations.CaseClass;
import com.persistentbit.printable.PrintTextWriter;
import com.persistentbit.printable.PrintableText;

import java.io.PrintWriter;
import java.nio.file.Path;

/**
 * TODOC
 *
 * @author petermuys
 * @since 22/06/17
 */
@CaseClass
public class GeneratedJavaSource{
	private final String        fullClassName;
	private final PrintableText code;

	public GeneratedJavaSource(String fullClassName, PrintableText code) {
		this.fullClassName = fullClassName;
		this.code = code;
	}

	public String getFullClassName() {
		return fullClassName;
	}

	public PrintableText getCode() {
		return code;
	}

	public Result<Path>	writeSource(Path sourceRoot){
		return
			UReflect.convertClassNameToPath(sourceRoot,fullClassName,"java")
			.flatMap(path -> IOFiles.mkdirsIfNotExisting(path.toFile().getParentFile()).map(f -> path))
			.flatMap(path -> writeAndClose(path, code));
	}

	private static Result<PrintTextWriter> fromPath(Path path){
		return Result.noExceptions(() -> new PrintTextWriter(path.toFile()));
	}

	private static Result<Path> writeAndClose(Path path, PrintableText printableText){
		return Result.function(path,printableText).code(l -> fromPath(path)
			.flatMap(pw -> {
				try(PrintWriter out = pw){
					out.print(printableText.printToString());
				}
				return Result.success(path);
			}));
	}
}
