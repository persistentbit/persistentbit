/**
 * TODOC
 * @author petermuys
 * @since 19/11/17
 */
module persistent.sql.connect {
	requires transitive persistent.core;
	requires java.sql;
	exports com.persistentbit.sql.connect;
}