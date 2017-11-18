/**
 * TODOC
 *
 * @author petermuys
 * @since 11/11/17
 */
module persistent.json {
	requires persistent.collections;
	requires persistent.lenses;
	requires persistent.reflection;
	requires java.sql;
	requires persistent.code.annotations;
	requires persistent.utils;
	requires persistent.io;

	exports com.persistentbit.json.nodes;
	exports com.persistentbit.json.mapping.impl.custom;
	exports com.persistentbit.json.mapping.impl;
	exports com.persistentbit.json.mapping;
	exports com.persistentbit.json.security;
	exports com.persistentbit.json.utils;

}