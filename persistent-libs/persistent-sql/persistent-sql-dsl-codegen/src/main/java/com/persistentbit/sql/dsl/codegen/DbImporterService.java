package com.persistentbit.sql.dsl.codegen;

import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.meta.data.DbMetaDatabase;

import java.util.ServiceLoader;

/**
 * TODOC
 *
 * @author petermuys
 * @since 9/12/17
 */
public interface DbImporterService{

	DbHandlingLevel getHandlingLevel(DbMetaDatabase db);

	String getDescription();
	Result<DbDefinition> importDb(DbImportSettings settings);

	static PList<DbImporterService> getInstances() {

		ServiceLoader<DbImporterService> services =
			ServiceLoader.load(DbImporterService.class);

		return PList.from(services);
	}
}
