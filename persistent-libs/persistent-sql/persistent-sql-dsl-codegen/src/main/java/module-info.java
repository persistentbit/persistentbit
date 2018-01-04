import com.persistentbit.sql.dsl.codegen.service.DbCodeGenService;
import com.persistentbit.sql.dsl.codegen.service.impl.DbCodeGenServiceProviderGeneric;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/11/17
 */
module persistent.sql.dsl.codegen {
	requires persistent.sql.dsl.generic;
	requires persistent.collections;
	requires persistent.code.annotations;
	requires persistent.javacodegen.annotations;
	requires persistent.sql.meta;
	requires persistent.javacodegen;
	requires persistent.reflection;
	requires persistent.sql.work;
	requires persistent.utils;
	requires java.sql;
	requires persistent.sql.connect;

	exports com.persistentbit.sql.dsl.codegen.config;
	exports com.persistentbit.sql.dsl.codegen.service;
	exports com.persistentbit.sql.dsl.codegen.service.impl;
	exports com.persistentbit.sql.dsl.codegen.importer;

	opens com.persistentbit.sql.dsl.codegen.config;

	uses DbCodeGenService;

	provides DbCodeGenService with DbCodeGenServiceProviderGeneric;

}