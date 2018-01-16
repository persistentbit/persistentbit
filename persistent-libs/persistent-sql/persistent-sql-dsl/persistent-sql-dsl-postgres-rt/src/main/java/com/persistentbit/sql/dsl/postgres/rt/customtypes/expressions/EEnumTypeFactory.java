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
 * @since 16/01/18
 */
public class EEnumTypeFactory<E extends Enum> extends AbstractTypeFactory<EEnum<E>, E>{


	public EEnumTypeFactory(ExprContext context) {
		super(context);
	}


	@Override
	public Class getValueClass() {
		return Interval.class;
	}

	@Override
	public Class<EEnum> getTypeClass() {
		return EEnum.class;
	}


	@Override
	public EEnum buildWithStrategy(TypeStrategy<E> strategy) {
		//return new EEnumImpl<>(strategy);
		throw new UnsupportedOperationException("Enum");
	}

	private class EEnumImpl<E extends Enum> extends AbstractTypeImpl<EEnum<E>, E> implements EEnum<E>,
		TypeImplComparableMixin<EEnum<E>, E>{

		private final Class<E> enumClass;

		public EEnumImpl(Class<E> enumClass, TypeStrategy<E> typeStrategy) {
			super(typeStrategy);
			this.enumClass = enumClass;
		}


		@Override
		public ExprTypeJdbcConvert<E> getJdbcConverter() {
			return (ExprTypeJdbcConvert<E>) context.getJavaJdbcConverter(Enum.class);
		}

		@Override
		public AbstractTypeFactory<EEnum<E>, E> getTypeFactory() {
			return (AbstractTypeFactory) EEnumTypeFactory.this;
		}


		@Override
		public EEnum<E> getThis() {
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
