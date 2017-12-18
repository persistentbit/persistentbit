package com.persistentbit.sql.dsl.generic.expressions.impl.typeimpl.time;

import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.EDate;
import com.persistentbit.sql.dsl.generic.expressions.impl.typeimpl.AbstractTypeFactory;
import com.persistentbit.sql.dsl.generic.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.ExprTypeFactory;
import com.persistentbit.sql.dsl.generic.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.generic.expressions.impl.strategies.TypeStrategy;
import com.persistentbit.sql.dsl.generic.expressions.impl.typeimpl.AbstractTypeImpl;
import com.persistentbit.sql.dsl.generic.expressions.impl.typeimpl.TypeImplComparableMixin;

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
	protected ExprTypeJdbcConvert doGetJdbcConverter() {
		return context.getJavaJdbcConverter(LocalDate.class);
	}

	@Override
	public Class<? extends DExpr<LocalDate>> getTypeClass() {
		return EDate.class;
	}

	@Override
	protected EDate buildWithStrategy(TypeStrategy<LocalDate> strategy) {
		return new EDateImpl(strategy);
	}

	private final class EDateImpl extends AbstractTypeImpl<EDate, LocalDate>
		implements EDate, TypeImplComparableMixin<EDate, LocalDate>{

		private EDateImpl(TypeStrategy<LocalDate> typeStrategy) {
			super(typeStrategy);
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
