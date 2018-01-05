package com.persistentbit.sql.dsl.expressions.impl.typeimpl.time;

import com.persistentbit.sql.dsl.expressions.EDate;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;
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
	protected EDate buildWithStrategy(TypeStrategy<LocalDate> strategy) {
		return new EDateImpl(strategy);
	}

	@Override
	protected ExprTypeJdbcConvert<LocalDate> getJdbcConverter() {
		return context.getJavaJdbcConverter(LocalDate.class);
	}

	private final class EDateImpl extends AbstractTypeImpl<EDate, LocalDate>
		implements EDate, TypeImplComparableMixin<EDate, LocalDate>{

		private EDateImpl(TypeStrategy<LocalDate> typeStrategy) {
			super(typeStrategy);
		}


		@Override
		public Class<EDate> getTypeClass() {
			return EDate.class;
		}

		@Override
		public EDate buildWithStrategy(TypeStrategy<LocalDate> typeStrategy
		) {
			return EDateTypeFactory.this.buildWithStrategy(typeStrategy);
		}

		@Override
		public ExprContext getContext() {
			return EDateTypeFactory.this.context;
		}

		@Override
		public ExprTypeJdbcConvert<LocalDate> getJdbcConverter() {
			return EDateTypeFactory.this.getJdbcConverter();
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
