package com.persistentbit.sql.dsl.codegen;

import com.persistentbit.collections.PList;
import com.persistentbit.javacodegen.GeneratedJavaSource;
import com.persistentbit.result.Result;
import com.persistentbit.sql.meta.data.DbMetaDatabase;

import java.util.ServiceLoader;

/**
 * TODOC
 *
 * @author petermuys
 * @since 9/12/17
 */
public interface DbJavaGenService{


	DbHandlingLevel getHandlingLevel(DbMetaDatabase db);

	Result<PList<GeneratedJavaSource>> generate(DbJavaGenOptions options, DbDefinition data);

	String getDescription();

	static PList<DbJavaGenService> getInstances() {

		ServiceLoader<DbJavaGenService> services =
			ServiceLoader.load(DbJavaGenService.class);

		return PList.from(services);
	}
}
