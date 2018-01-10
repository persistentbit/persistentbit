package com.pbtest.postgres.impl.typefactories;

import com.pbtest.postgres.expressions.EPeople;
import com.pbtest.postgres.values.People;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructTypeImplMixin;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.util.Iterator;

public class PeopleTypeFactory extends AbstractStructureTypeFactory<EPeople, People>{

	private class EPeopleImpl extends EPeople implements StructTypeImplMixin<EPeople, People>{


		public EPeopleImpl(Iterator<DExpr> iter) {
			super(
				(ELong) iter.next()
			);
		}
		@Override
		public AbstractStructureTypeFactory<EPeople, People> getTypeFactory() {
			return PeopleTypeFactory.this;
		}
		@Override
		public String toString() {
			return "EPeople[" + personId + "]";
		}
		@Override
		public EPeople getThis() {
			return this;
		}
	}

	public PeopleTypeFactory(ExprContext context) {
		super(context);
	}
	@Override
	protected PList<StructureField<EPeople, People>> buildFields() {
		return PList.val(
			createField(ELong.class, "person_id", "personId", v -> v.getPersonId(), v -> v.personId)
		);
	}
	@Override
	protected People buildValue(Object[] fieldValues) {
		return new People(
			(Long) fieldValues[0]
		);
	}
	@Override
	protected EPeopleImpl createExpression(PStream<DExpr> fieldValues) {
		return new EPeopleImpl(fieldValues.iterator());
	}
	@Override
	public Class<EPeople> getTypeClass() {
		return EPeople.class;
	}
}
