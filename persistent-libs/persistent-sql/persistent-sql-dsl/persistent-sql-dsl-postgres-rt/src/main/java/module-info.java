/**
 * TODOC
 * @author petermuys
 * @since 26/11/17
 */
module persistent.sql.dsl.postgres.rt {
	requires persistent.javacodegen.annotations;
	requires persistent.code.annotations;
	requires persistent.string;
	requires persistent.sql.dsl.generic;
	requires persistent.sql.utils;
	requires java.sql;
	exports com.persistentbit.sql.dsl.postgres.rt.customtypes;
	exports com.persistentbit.sql.dsl.postgres.rt;
}