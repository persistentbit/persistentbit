/**
 * TODOC
 *
 * @author petermuys
 * @since 17/11/17
 */
module persistent.utils {
	requires transitive persistent.collections;
	requires transitive persistent.result;

	exports com.persistentbit.utils;
	exports com.persistentbit.utils.patternmatching;
	exports com.persistentbit.utils.keyvalue;
	exports com.persistentbit.utils.exceptions;
}