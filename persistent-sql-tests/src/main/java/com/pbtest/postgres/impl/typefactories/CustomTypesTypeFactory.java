package com.pbtest.postgres.impl.typefactories;

import com.pbtest.postgres.expressions.ECustomTypes;
import com.pbtest.postgres.values.CustomTypes;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructTypeImplMixin;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;
import com.persistentbit.sql.dsl.postgres.rt.customtypes.Interval;
import com.persistentbit.sql.dsl.postgres.rt.customtypes.expressions.EInterval;
import com.persistentbit.sql.dsl.postgres.rt.customtypes.expressions.EUUID;

import java.util.Iterator;
import java.util.UUID;

public class CustomTypesTypeFactory extends AbstractStructureTypeFactory<ECustomTypes, CustomTypes>{

	private class ECustomTypesImpl extends ECustomTypes implements StructTypeImplMixin<ECustomTypes, CustomTypes>{


		public ECustomTypesImpl(Iterator<DExpr> iter) {
			super(
				(EUUID) iter.next()
				, (EInterval) iter.next()
			);
		}
		@Override
		public AbstractStructureTypeFactory<ECustomTypes, CustomTypes> getTypeFactory() {
			return CustomTypesTypeFactory.this;
		}
		@Override
		public String toString() {
			return "ECustomTypes[" + tUuid + tInterval + "]";
		}
		@Override
		public ECustomTypes getThis() {
			return this;
		}
	}

	public CustomTypesTypeFactory(ExprContext context) {
		super(context);
	}
	@Override
	protected PList<StructureField<ECustomTypes, CustomTypes>> buildFields() {
		return PList.val(
			createField(EUUID.class, "t_uuid", "tUuid", v -> v.getTUuid(), v -> v.tUuid)
			, createField(EInterval.class, "t_interval", "tInterval", v -> v.getTInterval(), v -> v.tInterval)
		);
	}
	@Override
	protected CustomTypes buildValue(Object[] fieldValues) {
		return new CustomTypes(
			(UUID) fieldValues[0]
			, (Interval) fieldValues[1]
		);
	}
	@Override
	protected ECustomTypesImpl createExpression(PStream<DExpr> fieldValues) {
		return new ECustomTypesImpl(fieldValues.iterator());
	}
	@Override
	public Class<ECustomTypes> getTypeClass() {
		return ECustomTypes.class;
	}
}
