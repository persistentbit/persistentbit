package com.persistentbit.sql.dsl.codegen.h2.provider;

import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.codegen.config.Instance;
import com.persistentbit.sql.dsl.codegen.h2.H2DbImporter;
import com.persistentbit.sql.dsl.codegen.importer.CgContext;
import com.persistentbit.sql.dsl.codegen.service.DbHandlingLevel;
import com.persistentbit.sql.dsl.codegen.service.impl.DbCodeGenServiceProviderGeneric;
import com.persistentbit.sql.meta.DbMetaDataImporter;
import com.persistentbit.sql.transactions.DbTransaction;

import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 3/01/18
 */
public class DbCodeGenServiceProviderH2 extends DbCodeGenServiceProviderGeneric{


	@Override
	public DbHandlingLevel getHandlingLevel(Supplier<DbTransaction> transSup, Instance instance) {
		return DbMetaDataImporter.getDatabase().run(transSup.get())
			.map(db -> {
				if(db.getProductName().equals("H2")) {
					return DbHandlingLevel.full;
				}
				return DbHandlingLevel.not;
			})
			.orElse(DbHandlingLevel.not)
			;
	}

	@Override
	public String getDescription() {
		return "H2 database import service";
	}

	@Override
	protected Result<CgContext> importDb(Instance instance, Supplier<DbTransaction> transSup) {
		return Result.function(instance).code(log -> {
			H2DbImporter importer = new H2DbImporter(instance, transSup);
			return importer.importDb();
		});
	}


}
