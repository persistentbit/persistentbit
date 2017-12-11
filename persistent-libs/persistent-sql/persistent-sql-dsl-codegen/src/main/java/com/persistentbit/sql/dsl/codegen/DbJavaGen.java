package com.persistentbit.sql.dsl.codegen;


import com.persistentbit.collections.PList;
import com.persistentbit.javacodegen.GeneratedJavaSource;
import com.persistentbit.result.Result;
import com.persistentbit.sql.meta.DbMetaDataImporter;
import com.persistentbit.sql.meta.data.DbMetaDatabase;
import com.persistentbit.utils.exceptions.ToDo;

/**
 * Generate Java code for a Database Substema
 *
 * @author Peter Muys
 * @since 14/09/16
 */
public  class DbJavaGen{
	static public Result<DbJavaGen> createGenerator(DbJavaGenOptions options){
		return Result.function(options).code(log ->
			findImporterService(options)
				.flatMap(importer ->
					findJavaGenService(options)
						.map(javaGenService -> new DbJavaGen(importer,javaGenService,options))
				)

		);

	}

	static private Result<DbJavaGenService> findJavaGenService(DbJavaGenOptions options){
		return Result.function().code(log -> {
			PList<DbJavaGenService> services = DbJavaGenService.getInstances();
			services.map(i -> i.getDescription()).forEach(i -> {
				log.info("Got DbJavaGenService: " + i);
			});

			DbMetaDatabase db = DbMetaDataImporter.getDatabase().run(options.getTransSupplier().get()).orElseThrow();

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

	static private Result<DbImporterService> findImporterService(DbJavaGenOptions options){
		return Result.function().code(log -> {
			PList<DbImporterService> importers = DbImporterService.getInstances();
			importers.map(i -> i.getDescription()).forEach(i -> {
				log.info("Got Import service : " + i);
			});

			DbMetaDatabase db = DbMetaDataImporter.getDatabase().run(options.getTransSupplier().get()).orElseThrow();

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
	private final DbJavaGenOptions options;

	private DbJavaGen(DbImporterService importerService, DbJavaGenService javaGenService,
					 DbJavaGenOptions options
	) {
		this.importerService = importerService;
		this.javaGenService = javaGenService;
		this.options = options;
	}

	public Result<PList<GeneratedJavaSource>> generate(){
		return Result.function().code(log -> {
			DbImportSettings importSettings = DbImportSettings.build(b -> b
				.setNameTransformer(options.getNameTransformer())
				.setRootPackage(options.getRootPackage())
				.setTableSelection(options.getSelection())
				.setTransactionSupplier(options.getTransSupplier())
			);
			return importerService.importDb(importSettings)
				.flatMap(def ->
					javaGenService.generate(options,def)
				);
		});

	}


}
