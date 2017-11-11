/**
 * TODOC
 *
 * @author petermuys
 * @since 11/11/17
 */
module persistent.core {
	requires persistent.doc.annotations;
	requires persistent.code.annotations;
	requires java.logging;

	exports com.persistentbit.core;

	exports com.persistentbit.core.tuples;

	exports com.persistentbit.core.collections;
	exports com.persistentbit.core.exceptions;
	exports com.persistentbit.core.function;
	exports com.persistentbit.core.io;
	exports com.persistentbit.core.keyvalue;
	exports com.persistentbit.core.language;
	exports com.persistentbit.core.lenses;
	exports com.persistentbit.core.logging;
	exports com.persistentbit.core.logging.entries;
	exports com.persistentbit.core.logging.printing;
	exports com.persistentbit.core.logging.cleaning;
	exports com.persistentbit.core.patternmatching;
	exports com.persistentbit.core.printing;
	exports com.persistentbit.core.properties;
	exports com.persistentbit.core.resources;
	exports com.persistentbit.core.result;


	exports com.persistentbit.core.utils;
	exports com.persistentbit.core.utils.builders;

}