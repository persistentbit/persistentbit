/**
 * TODOC
 *
 * @author petermuys
 * @since 11/12/17
 */
module persistent.sql.dsl.codegenh2 {
	requires persistent.collections;
	requires java.sql;
	requires persistent.sql.work;
	requires persistent.sql.meta;
	requires persistent.sql.dsl.codegen;
	requires persistent.sql.dsl.generic;
	requires persistent.sql.dsl.h2.rt;
	provides com.persistentbit.sql.dsl.codegen.DbImporterService with com.persistentbit.sql.dsl.codegen.h2.H2DbImporterService;
	provides com.persistentbit.sql.dsl.codegen.DbJavaGenService with com.persistentbit.sql.dsl.codegen.h2.H2DbJavaGenService;
}