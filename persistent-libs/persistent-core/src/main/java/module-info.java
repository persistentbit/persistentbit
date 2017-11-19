/**
 * TODOC
 *
 * @author petermuys
 * @since 11/11/17
 */
module persistent.core {
	requires transitive persistent.doc.annotations;
	requires transitive persistent.code.annotations;
	requires transitive persistent.log;
	requires transitive persistent.tuples;
	requires transitive persistent.result;
	requires transitive persistent.string;
	requires transitive persistent.functions;
	requires transitive persistent.io;
	requires transitive persistent.utils;
	requires transitive persistent.collections;

}