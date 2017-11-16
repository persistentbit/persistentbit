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
	requires java.logging;

	exports com.persistentbit.core;



	exports com.persistentbit.core.collections;
	exports com.persistentbit.core.exceptions;
	exports com.persistentbit.core.function;
	exports com.persistentbit.core.io;
	exports com.persistentbit.core.keyvalue;
	exports com.persistentbit.core.language;
	exports com.persistentbit.core.lenses;
	exports com.persistentbit.core.patternmatching;
	exports com.persistentbit.core.properties;
	exports com.persistentbit.core.resources;
	exports com.persistentbit.core.result;


	exports com.persistentbit.core.utils;
	exports com.persistentbit.core.utils.builders;

}