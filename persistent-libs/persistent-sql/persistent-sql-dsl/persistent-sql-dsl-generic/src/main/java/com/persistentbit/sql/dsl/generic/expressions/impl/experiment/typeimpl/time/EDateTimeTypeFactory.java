package com.persistentbit.sql.dsl.generic.expressions.impl.experiment.typeimpl.time;

import com.persistentbit.sql.dsl.generic.expressions.EDateTime;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.AbstractTypeFactory;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.ExprContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.ExprTypeFactory;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.strategies.TypeStrategy;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.typeimpl.AbstractTypeImpl;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.typeimpl.TypeImplComparableMixin;

import java.time.LocalDateTime;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class EDateTimeTypeFactory extends AbstractTypeFactory<EDateTime,LocalDateTime>{

	public EDateTimeTypeFactory(ExprContext context,
							Class<EDateTime> typeClass,
							ExprTypeJdbcConvert<LocalDateTime> jdbcConvert
	) {
		super(context, typeClass, jdbcConvert);
	}

	@Override
	protected EDateTime buildWithStrategy(TypeStrategy<LocalDateTime> strategy
	) {
		return new EDateTimeImpl(typeClass,strategy);
	}
	private class EDateTimeImpl extends AbstractTypeImpl<EDateTime,LocalDateTime> implements EDateTime,TypeImplComparableMixin<EDateTime,LocalDateTime>{

		EDateTimeImpl(Class<EDateTime> typeClass,
							 TypeStrategy<LocalDateTime> typeStrategy
		) {
			super(typeClass, typeStrategy);
		}

		@Override
		public ExprContext getContext() {
			return context;
		}

		@Override
		public ExprTypeFactory<EDateTime, LocalDateTime> getTypeFactory() {
			return EDateTimeTypeFactory.this;
		}

		@Override
		public EDateTime getThis() {
			return this;
		}
	}
}
