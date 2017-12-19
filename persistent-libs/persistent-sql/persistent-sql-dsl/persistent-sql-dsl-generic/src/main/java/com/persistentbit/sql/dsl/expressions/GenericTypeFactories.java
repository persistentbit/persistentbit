package com.persistentbit.sql.dsl.expressions;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.numbers.*;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.others.EBoolTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.others.EStringTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.others.ESelectionTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.time.EDateTimeTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.time.EDateTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.time.ETimeTypeFactory;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class GenericTypeFactories{


	static public PList<ExprTypeFactory> numberFactories(ExprContext context) {
		return PList.val(
			new EByteTypeFactory(context),
			new EShortTypeFactory(context),
			new EIntTypeFactory(context),
			new ELongTypeFactory(context),
			new EFloatTypeFactory(context),
			new EDoubleTypeFactory(context),
			new EBigDecimalTypeFactory(context)
		);

	}

	static public PList<ExprTypeFactory> timeFactories(ExprContext context) {
		return PList.val(
			new EDateTypeFactory(context),
			new EDateTimeTypeFactory(context),
			new ETimeTypeFactory(context)
		);
	}

	static public PList<ExprTypeFactory> otherFactories(ExprContext context) {
		return PList.val(
			new EBoolTypeFactory(context),
			new EStringTypeFactory(context),
			new ESelectionTypeFactory(context)
		);
	}

	static public PList<ExprTypeFactory> all(ExprContext context) {
		return numberFactories(context)
				   .plusAll(timeFactories(context))
				   .plusAll(otherFactories(context));
	}
}
