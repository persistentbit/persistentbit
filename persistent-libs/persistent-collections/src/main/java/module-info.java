/**
 * TODOC
 *
 * @author petermuys
 * @since 17/11/17
 */
module persistent.collections {
	requires transitive persistent.tuples;
	requires transitive persistent.result;
	requires transitive persistent.log;
	requires transitive persistent.functions;
	requires transitive persistent.string;
	requires persistent.doc.annotations;
	requires persistent.code.annotations;
	exports com.persistentbit.collections;
}