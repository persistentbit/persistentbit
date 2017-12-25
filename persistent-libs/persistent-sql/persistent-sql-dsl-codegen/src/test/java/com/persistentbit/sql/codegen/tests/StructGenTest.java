package com.persistentbit.sql.codegen.tests;

import com.persistentbit.collections.PList;
import com.persistentbit.javacodegen.JJavaFile;
import com.persistentbit.logging.ModuleLogging;
import com.persistentbit.sql.dsl.expressions.EDateTime;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.newsystem.CgContext;
import com.persistentbit.sql.dsl.newsystem.StructField;
import com.persistentbit.sql.dsl.newsystem.StructureTypeDef;
import com.persistentbit.sql.dsl.newsystem.TypeRef;
import com.persistentbit.test.TestCase;
import com.persistentbit.test.TestRunner;
import com.persistentbit.utils.Lazy;

/**
 * TODOC
 *
 * @author petermuys
 * @since 25/12/17
 */
public class StructGenTest{

	static private final String dbName      = "mydb";
	static private final String schemaName  = "myschema";
	static private final String packageName = dbName + "." + schemaName;

	static private final Lazy<StructureTypeDef> tagStruct = Lazy.code(() -> {
		StructureTypeDef res = new StructureTypeDef(
			TypeRef.create(packageName, "ETag"),
			TypeRef.create(packageName, "Tag"),
			PList.val(
				new StructField(
					TypeRef.create(ELong.class),
					"id", "tag_id"
				)
				, new StructField(
					TypeRef.create(EString.class),
					"name", "tag_name"
				)
				, new StructField(
					TypeRef.create(EDateTime.class),
					"created", "tag_created"
				)
			)
		);
		return res;
	});


	static private final Lazy<CgContext> cgContext = Lazy.code(() -> {
		CgContext c = new CgContext("com.generated");
		c.register(tagStruct.get());
		return c;
	});


	static final TestCase testStructGen = TestCase.name("StructGen").code(tr -> {
		CgContext        context = cgContext.get();
		PList<JJavaFile> files   = tagStruct.get().generate(context);
		for(JJavaFile jf : files) {
			System.out.println(jf.print().printToString());
		}
	});

	public void testAll() {
		TestRunner.runAndPrint(ModuleLogging.consoleLogPrint, TestCase.forTestClass(getClass()));
	}

	public static void main(String[] args) {
		ModuleLogging.consoleLogPrint.registerAsGlobalHandler();
		new StructGenTest().testAll();
	}
}
