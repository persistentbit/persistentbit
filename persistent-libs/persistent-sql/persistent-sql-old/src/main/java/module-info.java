/**
 * TODOC
 * @author petermuys
 * @since 19/11/17
 */
module persistent.sql.old {
	requires transitive persistent.core;
	requires transitive java.sql;
	requires transitive persistent.javacodegen.annotations;
	requires persistent.sql.connect;
}