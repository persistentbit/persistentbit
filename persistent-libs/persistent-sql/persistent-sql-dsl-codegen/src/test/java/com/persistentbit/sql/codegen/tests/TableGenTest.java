package com.persistentbit.sql.codegen.tests;

import com.persistentbit.collections.PList;
import com.persistentbit.javacodegen.JJavaFile;
import com.persistentbit.logging.ModuleLogging;
import com.persistentbit.sql.dsl.expressions.EDateTime;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.newsystem.codegen.*;
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

	/*static private final Lazy<TableTypeDef> tagTable = Lazy.code(() -> {
		TableTypeDef tab = new TableTypeDef(
			dbName, schemaName, "tags",
			PList.val(
				new SimpleTableField(TypeRef.create(ELong.class), tableName, "tag_id", false, true, true),
				new SimpleTableField(TypeRef.create(EString.class), tableName, "tag_name", false, false, false),
				new SimpleTableField(TypeRef.create(EDateTime.class), tableName, "tag_created", false, true, false)
			)
		);
		return tab;
	});*/

	static public final Lazy<TableTypeDef> personTable = Lazy.code(() -> {
		CgTableName tn = new CgTableName(dbName, schemaName, "persons");
		return new TableTypeDef(
			tn,
			PList.val(
				new SimpleTableField(
					TypeRef.create(ELong.class), tn, "person_id", false, true, true,
					true, null
				),
				new SimpleTableField(
					TypeRef.create(EString.class), tn, "first_name", false, false, false,
					false, null
				),
				new SimpleTableField(
					TypeRef.create(EString.class), tn, "middle_name", true, false, false,
					false, null
				),
				new SimpleTableField(
					TypeRef.create(EString.class), tn, "last_Name", false, false, false,
					false, null
				),
				new StructTableField(new CgTableName("address"), "home_"),
				new SimpleTableField(
					TypeRef.create(EDateTime.class), tn, "created", false, true, false,
					false, null
				)
			)
		);
	});


	static public final Lazy<CgContext> cgContext = Lazy.code(() -> {
		CgContext c = new CgContext("com.generated");
		c.register(personTable.get());
		c.register(StructGenTest.addressStruct.get());
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
