/**
 * TODOC
 *
 * @author petermuys
 * @since 21/11/17
 */
module persist.sql.updater {
	requires persistent.result;
	requires persistent.sql.work;
	requires persistent.functions;
	requires persistent.log;
	requires persistent.collections;
	requires java.sql;
	requires persistent.utils;

	exports com.persistentbit.sql.updater;
}