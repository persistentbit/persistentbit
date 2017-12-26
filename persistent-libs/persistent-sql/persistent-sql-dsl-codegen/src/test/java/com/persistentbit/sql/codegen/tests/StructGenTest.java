package com.persistentbit.sql.codegen.tests;

import com.persistentbit.collections.PList;
import com.persistentbit.javacodegen.JJavaFile;
import com.persistentbit.logging.ModuleLogging;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.newsystem.*;
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

	static public final Lazy<StructureTypeDef> addressStruct = Lazy.code(() -> {
		CgTableName tn = new CgTableName("address");
		StructureTypeDef res =
			new StructureTypeDef(
				tn,
				PList.val(
					new SimpleTableField(
						TypeRef.create(EString.class),
						tn, "street",
						false, false, false
					),
					new SimpleTableField(
						TypeRef.create(EString.class),
						tn, "postal_code",
						false, false, false
					),
					new SimpleTableField(
						TypeRef.create(EString.class),
						tn, "city",
						false, false, false
					)
				)
			);
		return res;
	});


	static private final Lazy<CgContext> cgContext = Lazy.code(() -> {
		CgContext c = new CgContext("com.generated");
		c.register(addressStruct.get());
		return c;
	});


	static final TestCase testStructGen = TestCase.name("StructGen").code(tr -> {
		CgContext        context = cgContext.get();
		PList<JJavaFile> files   = addressStruct.get().generate(context);
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
