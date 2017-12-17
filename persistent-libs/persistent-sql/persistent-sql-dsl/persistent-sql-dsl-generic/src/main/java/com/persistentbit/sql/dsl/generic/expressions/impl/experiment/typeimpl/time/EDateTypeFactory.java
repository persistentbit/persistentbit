package com.persistentbit.sql.dsl.generic.expressions.impl.experiment.typeimpl.time;

import com.persistentbit.sql.dsl.generic.expressions.EDate;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.AbstractTypeFactory;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.ExprContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.ExprTypeFactory;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.strategies.TypeStrategy;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.typeimpl.AbstractTypeImpl;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.typeimpl.TypeImplComparableMixin;

import java.time.LocalDate;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class EDateTypeFactory extends AbstractTypeFactory<EDate,LocalDate>{

	public EDateTypeFactory(ExprContext context,
							Class<EDate> typeClass,
							ExprTypeJdbcConvert<LocalDate> jdbcConvert
	) {
		super(context, typeClass, jdbcConvert);
	}

	@Override
	protected EDate buildWithStrategy(TypeStrategy<LocalDate> strategy
	) {
		return new EDateImpl(typeClass,strategy);
	}
	private class EDateImpl extends AbstractTypeImpl<EDate,LocalDate> implements EDate,TypeImplComparableMixin<EDate,LocalDate>{

		public EDateImpl(Class<EDate> typeClass,
						 TypeStrategy<LocalDate> typeStrategy
		) {
			super(typeClass, typeStrategy);
		}

		@Override
		public ExprContext getContext() {
			return context;
		}

		@Override
		public ExprTypeFactory<EDate, LocalDate> getTypeFactory() {
			return EDateTypeFactory.this;
		}

		@Override
		public EDate getThis() {
			return this;
		}
	}
}
