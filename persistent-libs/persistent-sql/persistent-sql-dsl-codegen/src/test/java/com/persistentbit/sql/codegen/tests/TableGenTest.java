package com.persistentbit.sql.codegen.tests;

import com.persistentbit.collections.PList;
import com.persistentbit.javacodegen.JJavaFile;
import com.persistentbit.logging.ModuleLogging;
import com.persistentbit.sql.dsl.expressions.EDateTime;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.newsystem.CgContext;
import com.persistentbit.sql.dsl.newsystem.SimpleTableField;
import com.persistentbit.sql.dsl.newsystem.TableTypeDef;
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
public class TableGenTest{

	static private final String dbName      = "mydb";
	static private final String schemaName  = "myschema";
	static private final String packageName = dbName + "." + schemaName;

	static private final Lazy<TableTypeDef> tagTable = Lazy.code(() -> {
		TableTypeDef tab = new TableTypeDef(
			dbName, schemaName, "tags",
			PList.val(
				new SimpleTableField(TypeRef.create(ELong.class), "tag_id", false, true, true),
				new SimpleTableField(TypeRef.create(EString.class), "tag_name", false, false, false),
				new SimpleTableField(TypeRef.create(EDateTime.class), "tag_created", false, true, false)
			)
		);
		return tab;
	});


	static private final Lazy<CgContext> cgContext = Lazy.code(() -> {
		CgContext c = new CgContext("com.generated");
		c.register(tagTable.get());
		return c;
	});


	static final TestCase testStructGen = TestCase.name("StructGen").code(tr -> {
		CgContext        context = cgContext.get();
		PList<JJavaFile> files   = context.generateAll();
		for(JJavaFile jf : files) {
			System.out.println(jf.print().printToString());
		}
	});

	public void testAll() {
		TestRunner.runAndPrint(ModuleLogging.consoleLogPrint, TestCase.forTestClass(getClass()));
	}

	public static void main(String[] args) {
		ModuleLogging.consoleLogPrint.registerAsGlobalHandler();
		new TableGenTest().testAll();
	}

}
