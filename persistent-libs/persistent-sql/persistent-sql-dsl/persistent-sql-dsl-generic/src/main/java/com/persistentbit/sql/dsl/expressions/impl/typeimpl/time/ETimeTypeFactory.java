package com.persistentbit.sql.dsl.expressions.impl.typeimpl.time;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ETime;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.strategies.TypeStrategy;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeImpl;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.TypeImplComparableMixin;
import com.persistentbit.sql.dsl.genericdb.GenericExprTypeJdbcConverters;

import java.time.LocalTime;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class ETimeTypeFactory extends AbstractTypeFactory<ETime, LocalTime>{

	public ETimeTypeFactory(ExprContext context) {
		super(context, GenericExprTypeJdbcConverters.forLocalTime);
	}


	@Override
	public Class<? extends DExpr<LocalTime>> getTypeClass() {
		return ETime.class;
	}


	@Override
	protected ETime buildWithStrategy(TypeStrategy<LocalTime> strategy) {
		return new ETimeImpl(context, strategy);
	}

	private class ETimeImpl extends AbstractTypeImpl<ETime, LocalTime>
		implements ETime, TypeImplComparableMixin<ETime, LocalTime>{

		public ETimeImpl(ExprContext context, TypeStrategy<LocalTime> typeStrategy
		) {
			super(typeStrategy);
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
