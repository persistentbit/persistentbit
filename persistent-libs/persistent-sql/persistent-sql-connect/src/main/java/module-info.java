/**
 * TODOC
 * @author petermuys
 * @since 19/11/17
 */
module persistent.sql.connect {
	requires transitive persistent.result;
	requires transitive persistent.log;
	requires java.sql;
	requires persistent.doc.annotations;
	exports com.persistentbit.sql.connect;
}