package com.persistentbit.sql.dsl.postgres.rt.customtypes.expressions;

import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.expressions.impl.strategies.TypeStrategy;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeImpl;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.TypeImplComparableMixin;
import com.persistentbit.sql.dsl.postgres.rt.customtypes.Interval;


/**
 * TODOC
 *
 * @author petermuys
 * @since 14/01/18
 */
public class EIntervalTypeFactory extends AbstractTypeFactory<EInterval, Interval>{

	public EIntervalTypeFactory(ExprContext context) {
		super(context);
	}


	@Override
	public Class getValueClass() {
		return Interval.class;
	}

	@Override
	public Class<EInterval> getTypeClass() {
		return EInterval.class;
	}


	@Override
	public EInterval buildWithStrategy(TypeStrategy<Interval> strategy) {
		return new EIntervalTypeFactory.EIntervalImpl(strategy);
	}

	private class EIntervalImpl extends AbstractTypeImpl<EInterval, Interval> implements EInterval,
		TypeImplComparableMixin<EInterval, Interval>{

		public EIntervalImpl(TypeStrategy<Interval> typeStrategy) {
			super(typeStrategy);
		}


		@Override
		public ExprTypeJdbcConvert<Interval> getJdbcConverter() {
			return context.getJavaJdbcConverter(Interval.class);
		}

		@Override
		public AbstractTypeFactory<EInterval, Interval> getTypeFactory() {
			return EIntervalTypeFactory.this;
		}


		@Override
		public EInterval getThis() {
			return this;
		}

		@Override
		public EBool isNull() {
			return getExprContext().isNull(this);
		}

		@Override
		public EBool isNotNull() {
			return getExprContext().isNotNull(this);
		}
	}
}
