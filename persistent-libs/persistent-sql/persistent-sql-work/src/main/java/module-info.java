/**
 * TODOC
 * @author petermuys
 * @since 19/11/17
 */
module persistent.sql.work {
	requires persistent.javacodegen.annotations;
	requires persistent.sql.connect;
	requires persistent.string;
	requires persistent.functions;
	requires java.sql;
	exports com.persistentbit.sql.work;
}