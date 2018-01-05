package com.mycompany.db.generated.impl.typefactories;

import com.mycompany.db.generated.expressions.EPgArrayTest;
import com.mycompany.db.generated.values.PgArrayTest;
import com.persistentbit.collections.ImmutableArray;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructTypeImplMixin;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.util.Iterator;

public class PgArrayTestTypeFactory extends AbstractStructureTypeFactory<EPgArrayTest, PgArrayTest>{

	private class EPgArrayTestImpl extends EPgArrayTest implements StructTypeImplMixin<EPgArrayTest, PgArrayTest>{


		public EPgArrayTestImpl(Iterator<DExpr> iter) {
			super(
				(ELong) iter.next()
				, (EArray<EString, String>) iter.next()
				, (EArray<EInt, Integer>) iter.next()
			);
		}

		@Override
		public AbstractStructureTypeFactory<EPgArrayTest, PgArrayTest> getTypeFactory() {
			return PgArrayTestTypeFactory.this;
		}

		@Override
		public String toString() {
			return "EPgArrayTest[" + id + strings + ints + "]";
		}

		@Override
		public EPgArrayTest getThis() {
			return this;
		}
	}

	public PgArrayTestTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected PList<StructureField<EPgArrayTest, PgArrayTest>> buildFields() {
		return PList.val(
			createField(ELong.class, "id", "id", v -> v.getId(), v -> v.id)
			, createArrayField(EString.class, "strings", "strings", v -> v.getStrings(), v -> v.strings)
			, createArrayField(EInt.class, "ints", "ints", v -> v.getInts(), v -> v.ints)
		);
	}

	@Override
	protected PgArrayTest buildValue(Object[] fieldValues) {
		return new PgArrayTest(
			(Long) fieldValues[0]
			, (ImmutableArray) fieldValues[1]
			, (ImmutableArray) fieldValues[2]
		);
	}

	@Override
	protected EPgArrayTestImpl createExpression(PStream<DExpr> fieldValues) {
		return new EPgArrayTestImpl(fieldValues.iterator());
	}

	@Override
	public Class<EPgArrayTest> getTypeClass() {
		return EPgArrayTest.class;
	}
}
