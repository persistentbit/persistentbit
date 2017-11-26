/**
 * TODOC
 *
 * @author petermuys
 * @since 25/11/17
 */
module persistent.sql.javacodegen.maven.plugin.tests {
	requires persistent.sql.connect;
	requires persistent.sql.utils;
	requires persistent.sql.updater;
	requires persistent.sql.work;
	requires persistent.functions;
	requires java.sql;
	exports com.persistentbit.sql.dsl.maven.tests;
}