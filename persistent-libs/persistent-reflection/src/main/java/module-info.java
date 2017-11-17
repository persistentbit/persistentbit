/**
 * TODOC
 *
 * @author petermuys
 * @since 17/11/17
 */
module persistent.reflection {
	requires persistent.collections;
	requires persistent.lenses;
	requires persistent.utils;
	requires persistent.io;
	requires persistent.code.annotations;
	requires persistent.javacodegen.annotations;
	exports com.persistentbit.reflection.properties;
	exports com.persistentbit.reflection;
}