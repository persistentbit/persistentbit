package com.persistentbit.sql.dsl.codegen;


import com.persistentbit.collections.PList;
import com.persistentbit.javacodegen.GeneratedJavaSource;
import com.persistentbit.result.Result;
import com.persistentbit.sql.meta.DbMetaDataImporter;
import com.persistentbit.sql.meta.data.DbMetaDatabase;
import com.persistentbit.sql.transactions.DbTransaction;
import com.persistentbit.utils.exceptions.ToDo;

import java.sql.Connection;
import java.util.function.Supplier;

/**
 * Generate Java code for a Database Substema
 *
 * @author Peter Muys
 * @since 14/09/16
 */
public  class DbJavaGen{
	static public Result<DbJavaGen> createGenerator(Supplier<Result<Connection>> connector, DbJavaGenOptions options){
		return Result.function(options).code(log ->
			findImporterService(connector,options)
				.flatMap(importer ->
					findJavaGenService(connector,options)
						.map(javaGenService -> new DbJavaGen(importer,javaGenService,connector,options))
				)

		);

	}

	static private Result<DbJavaGenService> findJavaGenService(Supplier<Result<Connection>> connector,DbJavaGenOptions options){
		return Result.function().code(log -> {
			PList<DbJavaGenService> services = DbJavaGenService.getInstances();
			services.map(i -> i.getDescription()).forEach(i -> {
				log.info("Got DbJavaGenService: " + i);
			});
			Supplier<DbTransaction> transSup = ()-> DbTransaction.create(connector);
			DbMetaDatabase db = DbMetaDataImporter.getDatabase().run(transSup.get()).orElseThrow();

			boolean fullSupport = options.getFullDbSupport();
			DbJavaGenService result = services.find(i -> {
				switch(i.getHandlingLevel(db)){
					case not:
						return false;
					case onlyGeneric:
						return fullSupport ? false : true;
					case full:
						return true;
					default: throw new ToDo(i.getHandlingLevel(db).toString());
				}
			}).orElseThrow(() -> new RuntimeException("Can't find db javagen service"));
			return Result.success(result);
		});
	}

	static private Result<DbImporterService> findImporterService(Supplier<Result<Connection>> connector,DbJavaGenOptions options){
		return Result.function().code(log -> {
			PList<DbImporterService> importers = DbImporterService.getInstances();
			importers.map(i -> i.getDescription()).forEach(i -> {
				log.info("Got Import service : " + i);
			});
			Supplier<DbTransaction> transSup = ()-> DbTransaction.create(connector);
			DbMetaDatabase db = DbMetaDataImporter.getDatabase().run(transSup.get()).orElseThrow();

			boolean fullSupport = options.getFullDbSupport();
			DbImporterService importerService = importers.find(i -> {
				switch(i.getHandlingLevel(db)){
					case not:
						return false;
					case onlyGeneric:
						return fullSupport ? false : true;
					case full:
						return true;
					default: throw new ToDo(i.getHandlingLevel(db).toString());
				}
			}).orElseThrow(() -> new RuntimeException("Can't find db importer service"));
			return Result.success(importerService);
		});
	}


	private final DbImporterService importerService;
	private final DbJavaGenService javaGenService;
	private final Supplier<Result<Connection>> connector;
	private final DbJavaGenOptions options;

	private DbJavaGen(DbImporterService importerService, DbJavaGenService javaGenService,
					 Supplier<Result<Connection>> connector,
					 DbJavaGenOptions options
	) {
		this.importerService = importerService;
		this.javaGenService = javaGenService;
		this.connector = connector;
		this.options = options;
	}

	public Result<PList<GeneratedJavaSource>> generate(){
		return Result.function().code(log -> {
			DbImportSettings importSettings = DbImportSettings.build(b -> b
				.setNameTransformer(options.getNameTransformer())
				.setRootPackage(options.getRootPackage())
				.setTableSelection(options.getSelection())
				.setTransactionSupplier(()-> DbTransaction.create(connector))
			);
			return importerService.importDb(importSettings)
				.flatMap(def ->
					javaGenService.generate(options,def)
				);
		});

	}


}
