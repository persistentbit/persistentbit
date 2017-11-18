
module persistent.remoter {
	requires transitive persistent.result;
	requires transitive persistent.collections;
	requires transitive persistent.remote.annotations;
	requires persistent.code.annotations;
	requires persistent.reflection;
	requires persistent.json;
	requires persistent.io;
	exports com.persistentbit.remoter;
}