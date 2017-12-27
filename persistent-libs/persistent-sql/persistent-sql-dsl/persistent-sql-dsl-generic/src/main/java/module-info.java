/**
 * TODOC
 * @author petermuys
 * @since 22/11/17
 */
module persistent.sql.dsl.generic {
	requires persistent.collections;
	requires persistent.utils;
	requires persistent.code.annotations;
	requires persistent.javacodegen.annotations;
	requires persistent.sql.work;
	requires persistent.sql.utils;
	requires java.sql;
	exports com.persistentbit.sql.dsl.exprcontext;
	exports com.persistentbit.sql.dsl.exprcontext.impl;
	exports com.persistentbit.sql.dsl.expressions;
	exports com.persistentbit.sql.dsl.annotations;
	exports com.persistentbit.sql.dsl.expressions.impl;
	exports com.persistentbit.sql.dsl.statements;
	exports com.persistentbit.sql.dsl.statements.insert;
	exports com.persistentbit.sql.dsl.statements.delete;
	exports com.persistentbit.sql.dsl.statements.select;
	exports com.persistentbit.sql.dsl.statements.select.impl;
	exports com.persistentbit.sql.dsl.statements.update;
	exports com.persistentbit.sql.dsl.statements.work;
	exports com.persistentbit.sql.dsl.expressions.impl.typeimpl;
	exports com.persistentbit.sql.dsl.tables;
	exports com.persistentbit.sql.dsl;
	exports com.persistentbit.sql.dsl.generic_old;
	exports com.persistentbit.sql.dsl.expressions.impl.typeimpl.numbers;
	exports com.persistentbit.sql.dsl.expressions.impl.typeimpl.others;
	exports com.persistentbit.sql.dsl.expressions.impl.typeimpl.time;
	exports com.persistentbit.sql.dsl.expressions.impl.typeimpl.tuples;

	exports com.persistentbit.sql.dsl.generic_old.query;
	exports com.persistentbit.sql.dsl.generic_old.inserts;
	exports com.persistentbit.sql.dsl.generic_old.updates;
	exports com.persistentbit.sql.dsl.expressions.impl.old;

}