package com.mycompany.db.generated.impl.typefactories;

import com.mycompany.db.generated.expressions.ECaseWhenTest;
import com.mycompany.db.generated.values.CaseWhenTest;
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

public class CaseWhenTestTypeFactory extends AbstractStructureTypeFactory<ECaseWhenTest, CaseWhenTest>{

	private class ECaseWhenTestImpl extends ECaseWhenTest implements StructTypeImplMixin<ECaseWhenTest, CaseWhenTest>{


		public ECaseWhenTestImpl(Iterator<DExpr> iter) {
			super(
				(EInt) iter.next()
				, (ELong) iter.next()
			);
		}

		@Override
		public AbstractStructureTypeFactory<ECaseWhenTest, CaseWhenTest> getTypeFactory() {
			return CaseWhenTestTypeFactory.this;
		}

		@Override
		public String toString() {
			return "ECaseWhenTest[" + id + value + "]";
		}

		@Override
		public ECaseWhenTest getThis() {
			return this;
		}
	}

	public CaseWhenTestTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected PList<StructureField<ECaseWhenTest, CaseWhenTest>> buildFields() {
		return PList.val(
			createField(EInt.class, "id", "id", v -> v.getId(), v -> v.id)
			, createField(ELong.class, "value", "value", v -> v.getValue(), v -> v.value)
		);
	}

	@Override
	protected CaseWhenTest buildValue(Object[] fieldValues) {
		return new CaseWhenTest(
			(Integer) fieldValues[0]
			, (Long) fieldValues[1]
		);
	}

	@Override
	protected ECaseWhenTestImpl createExpression(PStream<DExpr> fieldValues) {
		return new ECaseWhenTestImpl(fieldValues.iterator());
	}

	@Override
	public Class<ECaseWhenTest> getTypeClass() {
		return ECaseWhenTest.class;
	}
}
