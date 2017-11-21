/**
 * TODOC
 *
 * @author petermuys
 * @since 21/11/17
 */
module persistent.sql.meta {
	requires persistent.javacodegen.annotations;
	requires persistent.code.annotations;
	requires persistent.utils;
	requires persistent.result;
	requires persistent.sql.work;
	requires persistent.sql.utils;
	requires java.sql;
	exports com.persistentbit.sql.meta.data;
	exports com.persistentbit.sql.meta;
}