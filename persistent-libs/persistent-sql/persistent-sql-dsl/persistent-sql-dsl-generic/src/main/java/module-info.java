/**
 * TODOC
 * @author petermuys
 * @since 22/11/17
 */
module persistent.sql.dsl.generic {
	requires persistent.collections;
	requires persistent.utils;
	requires persistent.code.annotations;
	requires java.sql;
	exports com.persistentbit.sql.dsl.exprcontext;
	exports com.persistentbit.sql.dsl.exprcontext.impl;
	exports com.persistentbit.sql.dsl.generic.expressions;
	exports com.persistentbit.sql.dsl.annotations;
	exports com.persistentbit.sql.dsl.generic.expressions.impl;
	exports com.persistentbit.sql.dsl.generic.query;

}