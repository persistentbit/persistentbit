/**
 * TODOC
 * @author petermuys
 * @since 19/11/17
 */
module persistent.sql.old {
	requires transitive persistent.core;
	requires transitive java.sql;
	requires transitive persistent.javacodegen.annotations;
	requires persistent.sql.connect;
	requires persistent.sql.work;
	requires persistent.reflection;
	requires persistent.collections;
	requires persistent.utils;
	requires persistent.javacodegen;
}