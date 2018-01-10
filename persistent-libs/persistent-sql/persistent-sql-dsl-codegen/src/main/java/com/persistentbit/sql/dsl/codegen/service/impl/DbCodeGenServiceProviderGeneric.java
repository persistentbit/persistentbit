package com.persistentbit.sql.dsl.codegen.service.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.collections.UPStreams;
import com.persistentbit.io.IO;
import com.persistentbit.javacodegen.JJavaFile;
import com.persistentbit.result.OK;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.codegen.config.Instance;
import com.persistentbit.sql.dsl.codegen.importer.CgContext;
import com.persistentbit.sql.dsl.codegen.importer.GenericDbImporter;
import com.persistentbit.sql.dsl.codegen.service.DbCodeGenService;
import com.persistentbit.sql.dsl.codegen.service.DbHandlingLevel;
import com.persistentbit.sql.transactions.DbTransaction;
import com.persistentbit.sql.updater.SqlFile;

import java.io.File;
import java.sql.Statement;
import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 3/01/18
 */
public class DbCodeGenServiceProviderGeneric implements DbCodeGenService{



	@Override
	public DbHandlingLevel getHandlingLevel(Supplier<DbTransaction> transSup, Instance instance) {
		return DbHandlingLevel.onlyGeneric;
	}

	@Override
	public String getDescription() {
		return "Generic Db Importer and code generator";
	}

	@Override
	public Result<PList<JJavaFile>> generateCode(File baseConfigPath, Supplier<DbTransaction> transSup,
												 Instance instance) {
		return Result.function(baseConfigPath, instance).code(log -> {
			//EXECUTE THE SQL INIT FILES IF ANY
			return UPStreams.fromSequence(instance
											  .getDbInitFiles()
											  .map(path -> initDb(new File(baseConfigPath, path), transSup))
			)
				//IMPORT THE DB
				.andThen(() -> new GenericDbImporter(instance, transSup).importDb())
				//GENERATE THE RESULT
				.flatMap(CgContext::generateAll);

		});


		/*
		return Result.function(instance)
			.code(log ->
					  createTransSupplier(instance.getConnector())
						  .flatMap(transSup -> {
							  log.info("Getting selection ");
							  JavaGenTableSelection tableSelection = DbCodeGenConfigLoader
								  .createTableSelection(transSup, instance)
								  .orElseThrow();
							  log.info("Creating name transformer");
							  DbNameTransformer nameTransformer = DbCodeGenConfigLoader
								  .createNameTransformer(instance)
								  .orElseThrow();
							  log.info("Creating import settings");
							  DbImportSettings importSettings =
								  new DbImportSettings(
									  instance,
									  tableSelection,
									  //nameTransformer,
									  transSup
									  //instance.getCodeGen().getRootPackage(),
									  //instance.getCodeGen().getGeneric()
								  );
							  return importDb(importSettings)
								  .flatMap(CgContext::generateAll);
						  }));*/
	}


	protected Result<OK> initDb(File sqlFile, Supplier<DbTransaction> transSup) {
		return Result.function(sqlFile).code(log ->
												 SqlFile.loadStatements(sqlFile, IO.utf8)
													 .flatMap(statements -> {
														 for(String sql : statements) {
															 log.info("Executing " + sql);
															 Result<OK> runOk = transSup.get().run(con -> {
																 con.setAutoCommit(false);
																 try(Statement stat = con.createStatement()) {
																	 stat.execute(sql);
																 }
																 return OK.result;
															 });
															 if(runOk.isPresent() == false) {
																 return runOk;
															 }
														 }
														 return OK.result;
													 })
		);
	}

	protected Result<CgContext> importDb(Instance instance, Supplier<DbTransaction> transSup) {
		return Result.function(instance).code(log -> {
			GenericDbImporter importer = new GenericDbImporter(instance, transSup);
			return importer.importDb();
		});
	}


}
