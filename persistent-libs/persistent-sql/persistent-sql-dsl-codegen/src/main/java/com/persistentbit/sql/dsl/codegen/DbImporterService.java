package com.persistentbit.sql.dsl.codegen;

import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;

import java.util.ServiceLoader;

/**
 * TODOC
 *
 * @author petermuys
 * @since 9/12/17
 */
public interface DbImporterService{
	String getDescription();
	Result<SqlImportedData> importDb(DbImportSettings settings);

	static PList<DbImporterService> getInstances() {

		ServiceLoader<DbImporterService> services =
			ServiceLoader.load(DbImporterService.class);

		return PList.from(services);
	}
}
