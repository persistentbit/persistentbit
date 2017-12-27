package com.persistentbit.dsl.tests;

import com.persistentbit.collections.PMap;
import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;

import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/12/17
 */
public class Db{

	static private final ExprContext context = new ExprContext();
	static public final  TPerson     person  = new TPerson(context);
	static public final  TTag        tags    = new TTag(context);

	static {

		context.registerType(EPerson.class, PersonTypeFactory.class);
		context.registerType(ETag.class, TagTypeFactory.class);
		context.registerType(EAddress.class, AddressTypeFactory.class);

		context.addTable(person);

		context.addTable(tags);

	}


	static public EBool val(Boolean v) {
		return context.val(v);
	}


	static public EInt val(Integer value) {
		return context.getTypeFactory(EInt.class).buildVal(value);
	}

	static public ELong val(Long v) {
		return context.val(v);
	}

	static public EString val(String v) {
		return context.val(v);
	}

	static public <E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2> ETuple2<E1, J1, E2, J2> tupleOf(E1 e1, E2 e2) {
		return context.of(e1, e2);
	}

	static public <E1 extends DExpr<J>, J> Param<E1> param(String name, Class<E1> cls) {
		ExprTypeFactory<E1, J>                 tf     = context.getTypeFactory(cls);
		Function<PMap<String, Object>, Object> getter = m -> m.get(name);
		return new Param<>(name, tf.buildParam(getter));
	}
}
