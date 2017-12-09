package com.persistentbit.sql.dsl.codegen;

import com.persistentbit.collections.PList;
import com.persistentbit.javacodegen.GeneratedJavaSource;
import com.persistentbit.result.Result;

import java.util.ServiceLoader;

/**
 * TODOC
 *
 * @author petermuys
 * @since 9/12/17
 */
public interface DbJavaGenService{
	Result<PList<GeneratedJavaSource>> generate(SqlImportedData data);

	static PList<DbJavaGenService> getInstances() {

		ServiceLoader<DbJavaGenService> services =
			ServiceLoader.load(DbJavaGenService.class);

		return PList.from(services);
	}
}
