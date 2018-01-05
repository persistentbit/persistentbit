package com.persistentbit.sql.dsl.expressions.impl.typeimpl.time;

import com.persistentbit.sql.dsl.expressions.EDateTime;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.expressions.impl.strategies.TypeStrategy;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeImpl;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.TypeImplComparableMixin;

import java.time.LocalDateTime;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class EDateTimeTypeFactory extends AbstractTypeFactory<EDateTime,LocalDateTime>{

	public EDateTimeTypeFactory(ExprContext context) {
		super(context);
	}


	@Override
	public Class<EDateTime> getTypeClass() {
		return EDateTime.class;
	}

	@Override
	public Class getValueClass() {
		return LocalDateTime.class;
	}

	@Override
	public EDateTime buildWithStrategy(TypeStrategy<LocalDateTime> strategy) {
		return new EDateTimeImpl(strategy);
	}


	private class EDateTimeImpl extends AbstractTypeImpl<EDateTime,LocalDateTime> implements EDateTime,TypeImplComparableMixin<EDateTime,LocalDateTime>{

		EDateTimeImpl(TypeStrategy<LocalDateTime> typeStrategy) {
			super(typeStrategy);
		}


		@Override
		public AbstractTypeFactory<EDateTime, LocalDateTime> getTypeFactory() {
			return EDateTimeTypeFactory.this;
		}

		@Override
		public EDateTime getThis() {
			return this;
		}

		@Override
		public ExprTypeJdbcConvert<LocalDateTime> getJdbcConverter() {
			return context.getJavaJdbcConverter(LocalDateTime.class);
		}
	}
}
