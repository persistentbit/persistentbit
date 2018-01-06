package com.mycompany.db.generated.impl.typefactories;

import com.mycompany.db.generated.expressions.ELimitOffsetTest;
import com.mycompany.db.generated.values.LimitOffsetTest;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EInt;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructTypeImplMixin;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.util.Iterator;

public class LimitOffsetTestTypeFactory extends AbstractStructureTypeFactory<ELimitOffsetTest, LimitOffsetTest>{

	private class ELimitOffsetTestImpl extends ELimitOffsetTest
		implements StructTypeImplMixin<ELimitOffsetTest, LimitOffsetTest>{


		public ELimitOffsetTestImpl(Iterator<DExpr> iter) {
			super(
				(EInt) iter.next()
				, (ELong) iter.next()
			);
		}
		@Override
		public AbstractStructureTypeFactory<ELimitOffsetTest, LimitOffsetTest> getTypeFactory() {
			return LimitOffsetTestTypeFactory.this;
		}
		@Override
		public String toString() {
			return "ELimitOffsetTest[" + id + value + "]";
		}
		@Override
		public ELimitOffsetTest getThis() {
			return this;
		}
	}

	public LimitOffsetTestTypeFactory(ExprContext context) {
		super(context);
	}
	@Override
	protected PList<StructureField<ELimitOffsetTest, LimitOffsetTest>> buildFields() {
		return PList.val(
			createField(EInt.class, "id", "id", v -> v.getId(), v -> v.id)
			, createField(ELong.class, "value", "value", v -> v.getValue(), v -> v.value)
		);
	}
	@Override
	protected LimitOffsetTest buildValue(Object[] fieldValues) {
		return new LimitOffsetTest(
			(Integer) fieldValues[0]
			, (Long) fieldValues[1]
		);
	}
	@Override
	protected ELimitOffsetTestImpl createExpression(PStream<DExpr> fieldValues) {
		return new ELimitOffsetTestImpl(fieldValues.iterator());
	}
	@Override
	public Class<ELimitOffsetTest> getTypeClass() {
		return ELimitOffsetTest.class;
	}
}
