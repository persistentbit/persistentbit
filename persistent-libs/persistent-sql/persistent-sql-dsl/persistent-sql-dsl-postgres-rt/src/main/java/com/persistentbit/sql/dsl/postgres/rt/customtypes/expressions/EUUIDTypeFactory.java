package com.persistentbit.sql.dsl.postgres.rt.customtypes.expressions;

import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.expressions.impl.strategies.TypeStrategy;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeImpl;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.TypeImplComparableMixin;

import java.util.UUID;

/**
 * TODOC
 *
 * @author petermuys
 * @since 14/01/18
 */
public class EUUIDTypeFactory extends AbstractTypeFactory<EUUID, UUID>{

	public EUUIDTypeFactory(ExprContext context) {
		super(context);
	}


	@Override
	public Class getValueClass() {
		return UUID.class;
	}

	@Override
	public Class<EUUID> getTypeClass() {
		return EUUID.class;
	}


	@Override
	public EUUID buildWithStrategy(TypeStrategy<UUID> strategy) {
		return new EUUIDTypeFactory.EUUIDImpl(strategy);
	}

	private class EUUIDImpl extends AbstractTypeImpl<EUUID, UUID> implements EUUID,
		TypeImplComparableMixin<EUUID, UUID>{

		public EUUIDImpl(TypeStrategy<UUID> typeStrategy) {
			super(typeStrategy);
		}


		@Override
		public ExprTypeJdbcConvert<UUID> getJdbcConverter() {
			return context.getJavaJdbcConverter(UUID.class);
		}

		@Override
		public AbstractTypeFactory<EUUID, UUID> getTypeFactory() {
			return EUUIDTypeFactory.this;
		}

		public EBool eq(EUUID other) {
			return getExprContext().eq(this, other);
		}

		public EBool notEq(EUUID other) {
			return getExprContext().notEq(this, other);
		}


		@Override
		public EUUID getThis() {
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
