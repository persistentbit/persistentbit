import com.persistentbit.sql.dsl.codegen.postgresql.provider.DbCodeGenServiceProviderPostgres;
import com.persistentbit.sql.dsl.codegen.service.DbCodeGenService;

/**
 * TODOC
 *
 * @author petermuys
 * @since 10/12/17
 */
module persistent.sql.dsl.codegenpostgres {
	requires persistent.collections;
	requires persistent.javacodegen;
	requires persistent.utils;
	requires java.sql;
	requires persistent.sql.work;
	requires persistent.sql.meta;
	requires persistent.sql.dsl.postgres.rt;
	requires persistent.sql.dsl.generic;
	requires persistent.sql.dsl.codegen;
	exports com.persistentbit.sql.dsl.codegen.postgresql;
	exports com.persistentbit.sql.dsl.codegen.postgresql.provider;

	provides DbCodeGenService with DbCodeGenServiceProviderPostgres;
}