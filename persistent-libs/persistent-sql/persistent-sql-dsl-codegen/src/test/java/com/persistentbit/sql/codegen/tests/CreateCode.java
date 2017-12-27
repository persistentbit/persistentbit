package com.persistentbit.sql.codegen.tests;

import com.persistentbit.collections.PList;
import com.persistentbit.javacodegen.GeneratedJavaSource;
import com.persistentbit.javacodegen.JJavaFile;
import com.persistentbit.sql.dsl.newsystem.codegen.CgContext;

import java.io.File;

/**
 * TODOC
 *
 * @author petermuys
 * @since 26/12/17
 */
public class CreateCode{

	public static void main(String[] args) {
		CgContext context = TableGenTest.cgContext.get();
		File      root    = new File("persistent-libs/persistent-sql/persistent-sql-dsl-codegen/src/test/java");
		if(root.exists() == false) {
			throw new RuntimeException("source root error: " + root.getAbsolutePath());
		}
		System.out.println(root.getAbsolutePath());

		PList<JJavaFile> files = context.generateAll();
		for(GeneratedJavaSource jsource : files.map(f -> f.toJavaSource())) {
			System.out.println(jsource.getFullClassName());
			jsource.writeSource(root.toPath());
		}
	}
}
