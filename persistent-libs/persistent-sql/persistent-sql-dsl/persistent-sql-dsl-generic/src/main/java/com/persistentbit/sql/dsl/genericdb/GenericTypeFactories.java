package com.persistentbit.sql.dsl.genericdb;

import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.numbers.*;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.others.*;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.time.EDateTimeTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.time.EDateTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.time.ETimeTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.tuples.*;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class GenericTypeFactories{


	static public void numberFactories(ExprContext context) {
		context.registerType(EByte.class, EByteTypeFactory.class);
		context.registerType(EShort.class, EShortTypeFactory.class);
		context.registerType(EInt.class, EIntTypeFactory.class);
		context.registerType(ELong.class, ELongTypeFactory.class);
		context.registerType(EFloat.class, EFloatTypeFactory.class);
		context.registerType(EDouble.class,EDoubleTypeFactory.class);
		context.registerType(EBigDecimal.class,EBigDecimalTypeFactory.class);

	}

	static public void timeFactories(ExprContext context) {
		context.registerType(EDate.class,EDateTypeFactory.class);
		context.registerType(EDateTime.class,EDateTimeTypeFactory.class);
		context.registerType(ETime.class,ETimeTypeFactory.class);
	}

	@SuppressWarnings("unchecked")
	static public void otherFactories(ExprContext context) {
		context.registerType(EBool.class,EBoolTypeFactory.class);
		context.registerType(EString.class,EStringTypeFactory.class);
		context.registerType(ESelection.class,ESelectionTypeFactory.class);
		context.registerType(EByteList.class, EByteListTypeFactory.class);
		context.registerType(EArray.class, EArrayTypeFactory.class);
	}
	@SuppressWarnings("unchecked")
	static public void tupleFactories(ExprContext context){
		context.registerType(ETuple2.class,Tuple2TypeFactory.class);
		context.registerType(ETuple3.class, Tuple3TypeFactory.class);
		context.registerType(ETuple4.class, Tuple4TypeFactory.class);
		context.registerType(ETuple5.class, Tuple5TypeFactory.class);
		context.registerType(ETuple6.class, Tuple6TypeFactory.class);
		context.registerType(ETuple7.class, Tuple7TypeFactory.class);

	}


	static public void registerAll(ExprContext context) {
		numberFactories(context);
		timeFactories(context);
		otherFactories(context);
		tupleFactories(context);
	}
}
