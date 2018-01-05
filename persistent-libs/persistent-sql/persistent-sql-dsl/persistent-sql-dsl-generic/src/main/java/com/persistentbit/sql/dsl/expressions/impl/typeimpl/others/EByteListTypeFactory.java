package com.persistentbit.sql.dsl.expressions.impl.typeimpl.others;

import com.persistentbit.collections.PByteList;
import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.sql.dsl.expressions.EByteList;
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
 * @since 27/12/17
 */
public class EByteListTypeFactory extends AbstractTypeFactory<EByteList, PByteList>{

	public EByteListTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	public Class getValueClass() {
		return PByteList.class;
	}

	@Override
	public Class<EByteList> getTypeClass() {
		return EByteList.class;
	}

	@Override
	public EByteList buildWithStrategy(TypeStrategy<PByteList> strategy) {
		return new EByteListImpl(strategy);
	}

	private class EByteListImpl extends AbstractTypeImpl<EByteList, PByteList>
		implements EByteList, TypeImplComparableMixin<EByteList, PByteList>{

		public EByteListImpl(TypeStrategy<PByteList> typeStrategy) {
			super(typeStrategy);
		}


		@Override
		public ExprTypeJdbcConvert<PByteList> getJdbcConverter() {
			return context.getJavaJdbcConverter(PByteList.class);
		}

		@Override
		public AbstractTypeFactory<EByteList, PByteList> getTypeFactory() {
			return EByteListTypeFactory.this;
		}


		@Override
		public EByteList getThis() {
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
