/**
 * TODOC
 *
 * @author petermuys
 * @since 16/11/17
 */
module persistent.result {
	requires persistent.log;
	requires persistent.code.annotations;
	requires persistent.tuples;
	requires persistent.doc.annotations;
	requires persistent.string;
	requires persistent.functions;
	exports com.persistentbit.result;
}