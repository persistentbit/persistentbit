package com.persistentbit.sql.dsl.generic.expressions.impl.typeimpl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.ExprTypeFactory;
import com.persistentbit.sql.dsl.generic.expressions.impl.typeimpl.numbers.*;
import com.persistentbit.sql.dsl.generic.expressions.impl.typeimpl.others.EBoolTypeFactory;
import com.persistentbit.sql.dsl.generic.expressions.impl.typeimpl.others.EStringTypeFactory;
import com.persistentbit.sql.dsl.generic.expressions.impl.typeimpl.time.EDateTimeTypeFactory;
import com.persistentbit.sql.dsl.generic.expressions.impl.typeimpl.time.EDateTypeFactory;
import com.persistentbit.sql.dsl.generic.expressions.impl.typeimpl.time.ETimeTypeFactory;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class GenericTypeFactories{



	public PList<ExprTypeFactory> numberFactories(ExprContext context) {
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

	public PList<ExprTypeFactory> timeFactories(ExprContext context){
		return PList.val(
			new EDateTypeFactory(context),
			new EDateTimeTypeFactory(context),
			new ETimeTypeFactory(context)
		);
	}
	public PList<ExprTypeFactory> otherFactories(ExprContext context){
		return PList.val(
			new EBoolTypeFactory(context),
			new EStringTypeFactory(context)
		);
	}

}
