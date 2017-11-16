package com.persistentbit.core.io;

import com.persistentbit.core.collections.PList;
import com.persistentbit.result.Result;

import java.io.File;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Utility class for working win jar or zip files
 *
 * @author petermuys
 * @since 15/04/17
 */
public final class IOJar{

	public static Result<PList<String>> findInJar(Path jarPath, Predicate<String> namePredicate){
		return Result.function(jarPath,namePredicate).code(l->
			IOStreams.closeAfter(()-> new JarFile(jarPath.toFile()), jarFile -> {
				Enumeration<JarEntry> entries = jarFile.entries();
				PList<String>         result  = PList.empty();
				while(entries.hasMoreElements()) {
					JarEntry entry = entries.nextElement();
					String   name  = "/" + entry.getName();
					name = name.replace('/', File.separatorChar);
					if(namePredicate.test(name)) {
						result = result.plus(name);
					}
				}
				return Result.success(result);
			})
		);
	}


}
