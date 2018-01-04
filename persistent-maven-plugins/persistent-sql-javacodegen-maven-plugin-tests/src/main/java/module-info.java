/**
 * TODOC
 *
 * @author petermuys
 * @since 25/11/17
 */
module persistent.sql.javacodegen.maven.plugin.tests {
	requires persistent.sql.connect;
	requires persistent.sql.utils;
	requires persistent.sql.updater;
	requires persistent.sql.work;
	requires persistent.functions;
	requires persistent.collections;
	requires java.sql;
	requires persistent.javacodegen.annotations;
	requires persistent.sql.dsl.generic;
	requires persistent.sql.dsl.postgres.rt;
	requires persistent.code.annotations;
	exports com.persistentbit.sql.dsl.maven.tests;
	exports com.mycompany.db.generated;
	exports com.mycompany.db.generated.expressions;
	exports com.mycompany.db.generated.inserts;
	exports com.mycompany.db.generated.tables;
	exports com.mycompany.db.generated.values;
	exports com.mycompany.db.generated.impl.typefactories;
}