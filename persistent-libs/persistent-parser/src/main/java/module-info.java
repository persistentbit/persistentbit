/**
 * TODOC
 *
 * @author petermuys
 * @since 11/11/17
 */
module persistent.parser {
	requires transitive persistent.result;
	requires persistent.utils;
	requires persistent.io;
	exports com.persistentbit.parser;
	exports com.persistentbit.parser.source;
}