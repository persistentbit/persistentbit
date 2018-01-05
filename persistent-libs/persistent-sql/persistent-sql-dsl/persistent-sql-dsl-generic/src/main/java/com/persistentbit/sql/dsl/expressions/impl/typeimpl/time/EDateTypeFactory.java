package com.persistentbit.sql.dsl.expressions.impl.typeimpl.time;

import com.persistentbit.sql.dsl.expressions.EDate;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.expressions.impl.strategies.TypeStrategy;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeImpl;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.TypeImplComparableMixin;

import java.time.LocalDate;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class EDateTypeFactory extends AbstractTypeFactory<EDate, LocalDate>{

	public EDateTypeFactory(ExprContext context) {
		super(context);
	}


	@Override
	public Class<EDate> getTypeClass() {
		return EDate.class;
	}

	@Override
	public Class getValueClass() {
		return LocalDate.class;
	}

	@Override
	public EDate buildWithStrategy(TypeStrategy<LocalDate> strategy) {
		return new EDateImpl(strategy);
	}



	private final class EDateImpl extends AbstractTypeImpl<EDate, LocalDate>
		implements EDate, TypeImplComparableMixin<EDate, LocalDate>{

		private EDateImpl(TypeStrategy<LocalDate> typeStrategy) {
			super(typeStrategy);
		}




		@Override
		public ExprTypeJdbcConvert<LocalDate> getJdbcConverter() {
			return context.getJavaJdbcConverter(LocalDate.class);
		}

		@Override
		public AbstractTypeFactory<EDate, LocalDate> getTypeFactory() {
			return EDateTypeFactory.this;
		}

		@Override
		public EDate getThis() {
			return this;
		}
	}
}
