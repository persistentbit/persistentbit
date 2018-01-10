/**
 * TODOC
 *
 * @author petermuys
 * @since 21/11/17
 */
module persistent.sql.updater {
	requires persistent.result;
	requires persistent.sql.work;
	requires persistent.functions;
	requires persistent.log;
	requires persistent.collections;
	requires java.sql;
	requires persistent.utils;
	requires persistent.code.annotations;
	requires persistent.parser;
	requires persistent.io;

	exports com.persistentbit.sql.updater;
}