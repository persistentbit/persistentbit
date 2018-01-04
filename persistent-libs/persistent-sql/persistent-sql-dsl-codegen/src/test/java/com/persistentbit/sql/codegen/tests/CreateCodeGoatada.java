package com.persistentbit.sql.codegen.tests;

import com.persistentbit.collections.PList;
import com.persistentbit.javacodegen.GeneratedJavaSource;
import com.persistentbit.javacodegen.JJavaFile;
import com.persistentbit.logging.ModuleLogging;
import com.persistentbit.result.OK;
import com.persistentbit.result.Result;
import com.persistentbit.sql.connect.DbConnector;
import com.persistentbit.sql.dsl.codegen.config.*;
import com.persistentbit.sql.dsl.codegen.importer.*;
import com.persistentbit.sql.transactions.DbTransaction;
import com.persistentbit.sql.updater.DbScriptRunner;
import com.persistentbit.sql.updater.SqlSnippets;

import java.io.File;
import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/12/17
 */
public class CreateCodeGoatada{

	static DbConnector con = DbConnector
		.fromUrl("org.h2.Driver", "jdbc:h2:mem:db1;MODE=MYSQL", "sa", null)
		.map(p -> p.pooledConnector(10))
		.orElseThrow();

	static Supplier<DbTransaction> newTrans = () -> DbTransaction.create(con);

	static Result<OK> buildTestDb() {
		return SqlSnippets.load(CreateCodeGoatada.class.getResourceAsStream("/Goatada-def.sql"))
			.map(snip -> {
				System.out.println("GOT SNIPPETS " + snip.getAllSnippetNames());
				System.out.println(snip.getAll("all"));
				new DbScriptRunner(con, "test", snip
				).run("all");
				return OK.inst;
			})
			;
	}

	public static void main(String[] args) {

		ModuleLogging.consoleLogPrint.registerAsGlobalHandler();
		buildTestDb().orElseThrow();

		SchemaDef schemaDef = SchemaDef.build(b -> b
			.setCatalogName("db1")
			.setSchemaName("goatada_test")
			.setJavaName("GoatAda")
		);
		Instance instance = Instance.build(bi -> bi

			.setSchemas(PList.val(schemaDef))
			.setCodeGen(CodeGen.build(bg -> bg
				.setGeneric(true).setRootPackage("be.kbc.fde.db")
				.setOutputDir("persistent-libs/persistent-sql/persistent-sql-dsl-codegen/src/test/java")))
			.setConnector(Connector.build(bc -> bc
				.setPassword(null).setUserName("sa")
				.setDriverClass("org.h2.Driver")
				.setUrl("jdbc:h2:mem:db1;MODE=MYSQL"))
			)
			.setNameConversionType(DbNameToJavaNameType.snakeToMixedCase)
		);

		JavaGenTableSelection tableSelection = DbCodeGenConfigLoader
			.createTableSelection(newTrans, instance).orElseThrow();
		DbNameTransformer nameTransformer = DbCodeGenConfigLoader.createNameTransformer(instance).orElseThrow();
		DbImportSettings importSettings =
			new DbImportSettings(instance, tableSelection, newTrans);
		GenericDbImporter importer = new GenericDbImporter(importSettings);
		Result<CgContext> context  = importer.importDb();
		ModuleLogging.consoleLogPrint.print(context.getLog());
		System.out.println("Got context: " + context);
		PList<JJavaFile> files = context.orElseThrow().generateAll().orElseThrow();
		System.out.println("Got files: " + files);
		//files.forEach(f -> System.out.println(f.print().printToString()));
		for(GeneratedJavaSource jsource : files.map(f -> f.toJavaSource())) {
			System.out.println(jsource.getFullClassName());
			jsource.writeSource(new File(instance.getCodeGen().getOutputDir()).toPath());
		}
	}
}
