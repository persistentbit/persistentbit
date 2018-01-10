package com.persistentbit.sql.dsl.codegen.service;

import com.persistentbit.collections.PList;
import com.persistentbit.javacodegen.JJavaFile;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.codegen.config.Instance;
import com.persistentbit.sql.transactions.DbTransaction;

import java.io.File;
import java.util.ServiceLoader;
import java.util.function.Supplier;

/**
 * Service definition for importing a database schema
 * and generating Java Code.
 *
 * @author petermuys
 * @since 3/01/18
 */
public interface DbCodeGenService{

	DbHandlingLevel getHandlingLevel(Supplier<DbTransaction> transSup, Instance instance);

	String getDescription();

	Result<PList<JJavaFile>> generateCode(File configBasePath, Supplier<DbTransaction> transSup, Instance instance);

	static PList<DbCodeGenService> getInstances() {

		ServiceLoader<DbCodeGenService> services =
			ServiceLoader.load(DbCodeGenService.class);

		return PList.from(services);
	}
}
