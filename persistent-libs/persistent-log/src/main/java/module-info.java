/**
 * TODOC
 *
 * @author petermuys
 * @since 12/11/17
 */
module persistent.log {
	requires persistent.code.annotations;
	requires persistent.doc.annotations;
	requires transitive persistent.printable;
	requires persistent.runenv;
	exports com.persistentbit.logging;
	exports com.persistentbit.logging.cleaning;
	exports com.persistentbit.logging.entries;
	exports com.persistentbit.logging.printing;
	opens com.persistentbit.logging.entries;
}