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

	exports com.persistentbit.sql.dsl.codegen;
	exports com.persistentbit.sql.dsl.codegen.generic;
	exports com.persistentbit.sql.dsl.codegen.dbjavafields;
	uses com.persistentbit.sql.dsl.codegen.DbImporterService;
	uses com.persistentbit.sql.dsl.codegen.DbJavaGenService;
	provides com.persistentbit.sql.dsl.codegen.DbImporterService with com.persistentbit.sql.dsl.codegen.generic.GenericDbImporterService;
	provides com.persistentbit.sql.dsl.codegen.DbJavaGenService with com.persistentbit.sql.dsl.codegen.generic.GenericDbJavaGenService;
}