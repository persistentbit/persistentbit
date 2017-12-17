package com.persistentbit.sql.dsl.generic.expressions.impl.experiment.typeimpl.time;

import com.persistentbit.sql.dsl.generic.expressions.ETime;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.AbstractTypeFactory;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.ExprContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.ExprTypeFactory;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.strategies.TypeStrategy;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.typeimpl.AbstractTypeImpl;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.typeimpl.TypeImplComparableMixin;

import java.time.LocalTime;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class ETimeTypeFactory extends AbstractTypeFactory<ETime,LocalTime>{
	public ETimeTypeFactory(ExprContext context) {
		super(context, ETime.class, context.getJavaJdbcConverter(LocalTime.class));
	}

	@Override
	protected ETime buildWithStrategy(TypeStrategy<LocalTime> strategy
	) {
		return new ETimeImpl(typeClass, strategy);
	}
	private class ETimeImpl extends AbstractTypeImpl<ETime,LocalTime> implements ETime,TypeImplComparableMixin<ETime,LocalTime>{

		public ETimeImpl(Class<ETime> typeClass,
						 TypeStrategy<LocalTime> typeStrategy
		) {
			super(typeClass, typeStrategy);
		}

		@Override
		public ExprContext getContext() {
			return context;
		}

		@Override
		public ExprTypeFactory<ETime, LocalTime> getTypeFactory() {
			return ETimeTypeFactory.this;
		}

		@Override
		public ETime getThis() {
			return this;
		}
	}
}
