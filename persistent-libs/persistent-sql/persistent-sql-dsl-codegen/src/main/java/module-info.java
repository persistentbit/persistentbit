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
	requires persistent.sql.dsl.postgres.rt;
	exports com.persistentbit.sql.dsl.codegen;
	exports com.persistentbit.sql.dsl.codegen.generic;
}