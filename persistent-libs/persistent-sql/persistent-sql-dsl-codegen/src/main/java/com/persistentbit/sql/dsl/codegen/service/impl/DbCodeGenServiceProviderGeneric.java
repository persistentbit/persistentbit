package com.persistentbit.sql.dsl.codegen.service.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.javacodegen.JJavaFile;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.codegen.config.Connector;
import com.persistentbit.sql.dsl.codegen.config.DbCodeGenConfigLoader;
import com.persistentbit.sql.dsl.codegen.config.Instance;
import com.persistentbit.sql.dsl.codegen.importer.*;
import com.persistentbit.sql.dsl.codegen.service.DbCodeGenService;
import com.persistentbit.sql.dsl.codegen.service.DbHandlingLevel;
import com.persistentbit.sql.transactions.DbTransaction;

import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 3/01/18
 */
public class DbCodeGenServiceProviderGeneric implements DbCodeGenService{


	protected Result<Supplier<DbTransaction>> createTransSupplier(Connector connector) {
		return DbCodeGenConfigLoader.createTransSup(connector);
	}

	@Override
	public DbHandlingLevel getHandlingLevel(Instance instance) {
		return DbHandlingLevel.onlyGeneric;
	}

	@Override
	public String getDescription() {
		return "Generic Db Importer and code generator";
	}

	@Override
	public Result<PList<JJavaFile>> generateCode(Instance instance) {
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
						  }));
	}

	protected Result<CgContext> importDb(DbImportSettings importSettings) {
		return Result.function(importSettings).code(log -> {
			GenericDbImporter importer = new GenericDbImporter(importSettings);
			return importer.importDb();
		});
	}


}
