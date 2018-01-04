package com.persistentbit.sql.dsl.codegen.postgresql.provider;

import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.codegen.config.Instance;
import com.persistentbit.sql.dsl.codegen.importer.CgContext;
import com.persistentbit.sql.dsl.codegen.importer.DbImportSettings;
import com.persistentbit.sql.dsl.codegen.postgresql.PostgresDbImporter;
import com.persistentbit.sql.dsl.codegen.service.DbHandlingLevel;
import com.persistentbit.sql.dsl.codegen.service.impl.DbCodeGenServiceProviderGeneric;
import com.persistentbit.sql.meta.DbMetaDataImporter;

/**
 * TODOC
 *
 * @author petermuys
 * @since 3/01/18
 */
public class DbCodeGenServiceProviderPostgres extends DbCodeGenServiceProviderGeneric{


	@Override
	public DbHandlingLevel getHandlingLevel(Instance instance) {
		return createTransSupplier(instance.getConnector())
			.flatMap(tranSup -> DbMetaDataImporter.getDatabase().run(tranSup.get()))
			.map(db -> {
				if(db.getProductName().equals("PostgreSQL")) {
					return DbHandlingLevel.full;
				}
				return DbHandlingLevel.not;
			})
			.orElse(DbHandlingLevel.not)
			;
	}

	@Override
	public String getDescription() {
		return "POSTGRES database import service";
	}


	protected Result<CgContext> importDb(DbImportSettings importSettings) {
		return Result.function(importSettings).code(log -> {
			PostgresDbImporter importer = new PostgresDbImporter(importSettings);
			return importer.importDb();
		});
	}


}
