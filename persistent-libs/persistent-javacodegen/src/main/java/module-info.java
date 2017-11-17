/**
 * TODOC
 *
 * @author petermuys
 * @since 11/11/17
 */
module persistent.javacodegen {
	requires persistent.io;
	requires persistent.collections;
	requires persistent.printable;
	requires persistent.javacodegen.annotations;
	requires persistent.reflection;
	requires persistent.code.annotations;

	exports com.persistentbit.javacodegen;
}