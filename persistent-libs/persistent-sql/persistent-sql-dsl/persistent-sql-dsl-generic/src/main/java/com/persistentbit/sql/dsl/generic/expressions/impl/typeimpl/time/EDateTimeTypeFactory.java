package com.persistentbit.sql.dsl.generic.expressions.impl.typeimpl.time;

import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.EDateTime;
import com.persistentbit.sql.dsl.generic.expressions.impl.typeimpl.AbstractTypeFactory;
import com.persistentbit.sql.dsl.generic.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.ExprTypeFactory;
import com.persistentbit.sql.dsl.generic.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.generic.expressions.impl.strategies.TypeStrategy;
import com.persistentbit.sql.dsl.generic.expressions.impl.typeimpl.AbstractTypeImpl;
import com.persistentbit.sql.dsl.generic.expressions.impl.typeimpl.TypeImplComparableMixin;

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
	protected ExprTypeJdbcConvert doGetJdbcConverter() {
		return context.getJavaJdbcConverter(LocalDateTime.class);
	}

	@Override
	public Class<? extends DExpr<LocalDateTime>> getTypeClass() {
		return EDateTime.class;
	}

	@Override
	protected EDateTime buildWithStrategy(TypeStrategy<LocalDateTime> strategy
	) {
		return new EDateTimeImpl(strategy);
	}
	private class EDateTimeImpl extends AbstractTypeImpl<EDateTime,LocalDateTime> implements EDateTime,TypeImplComparableMixin<EDateTime,LocalDateTime>{

		EDateTimeImpl(TypeStrategy<LocalDateTime> typeStrategy) {
			super(typeStrategy);
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
