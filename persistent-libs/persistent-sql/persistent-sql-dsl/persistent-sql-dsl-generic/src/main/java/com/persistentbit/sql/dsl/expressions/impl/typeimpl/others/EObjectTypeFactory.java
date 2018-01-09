package com.persistentbit.sql.dsl.expressions.impl.typeimpl.others;

import com.persistentbit.sql.dsl.expressions.EObject;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.expressions.impl.strategies.TypeStrategy;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeImpl;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.TypeImplComparableMixin;

/**
 * TODOC
 *
 * @author petermuys
 * @since 9/01/18
 */
public class EObjectTypeFactory extends AbstractTypeFactory<EObject, Object>{

	public EObjectTypeFactory(ExprContext context) {
		super(context);
	}


	@Override
	public Class getValueClass() {
		return Object.class;
	}

	@Override
	public Class<EObject> getTypeClass() {
		return EObject.class;
	}


	@Override
	public EObject buildWithStrategy(TypeStrategy<Object> strategy) {
		return new EObjectImpl(strategy);
	}

	private class EObjectImpl extends AbstractTypeImpl<EObject, Object> implements EObject,
		TypeImplComparableMixin<EObject, Object>{

		public EObjectImpl(TypeStrategy<Object> typeStrategy) {
			super(typeStrategy);
		}


		@Override
		public ExprTypeJdbcConvert<Object> getJdbcConverter() {
			return context.getJavaJdbcConverter(Object.class);
		}

		@Override
		public AbstractTypeFactory<EObject, Object> getTypeFactory() {
			return EObjectTypeFactory.this;
		}


		@Override
		public EObject getThis() {
			return this;
		}

	}
}
