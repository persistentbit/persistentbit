package com.persistentbit.sql.dsl.expressions.impl.typeimpl.time;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EDate;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.strategies.TypeStrategy;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeImpl;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.TypeImplComparableMixin;
import com.persistentbit.sql.dsl.genericdb.GenericExprTypeJdbcConverters;

import java.time.LocalDate;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class EDateTypeFactory extends AbstractTypeFactory<EDate, LocalDate>{

	public EDateTypeFactory(ExprContext context) {
		super(context, GenericExprTypeJdbcConverters.forLocalDate);
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
