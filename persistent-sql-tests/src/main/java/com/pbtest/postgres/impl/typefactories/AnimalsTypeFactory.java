package com.pbtest.postgres.impl.typefactories;

import com.pbtest.postgres.expressions.EAnimals;
import com.pbtest.postgres.values.Animals;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EInt;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructTypeImplMixin;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.util.Iterator;

public class AnimalsTypeFactory extends AbstractStructureTypeFactory<EAnimals, Animals>{

	private class EAnimalsImpl extends EAnimals implements StructTypeImplMixin<EAnimals, Animals>{


		public EAnimalsImpl(Iterator<DExpr> iter) {
			super(
				(EInt) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
			);
		}

		@Override
		public AbstractStructureTypeFactory<EAnimals, Animals> getTypeFactory() {
			return AnimalsTypeFactory.this;
		}

		@Override
		public String toString() {
			return "EAnimals[" + id + type + name + "]";
		}

		@Override
		public EAnimals getThis() {
			return this;
		}
	}

	public AnimalsTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected PList<StructureField<EAnimals, Animals>> buildFields() {
		return PList.val(
			createField(EInt.class, "id", "id", v -> v.getId(), v -> v.id)
			, createField(EString.class, "type", "type", v -> v.getType(), v -> v.type)
			, createField(EString.class, "name", "name", v -> v.getName(), v -> v.name)
		);
	}

	@Override
	protected Animals buildValue(Object[] fieldValues) {
		return new Animals(
			(Integer) fieldValues[0]
			, (String) fieldValues[1]
			, (String) fieldValues[2]
		);
	}

	@Override
	protected EAnimalsImpl createExpression(PStream<DExpr> fieldValues) {
		return new EAnimalsImpl(fieldValues.iterator());
	}

	@Override
	public Class<EAnimals> getTypeClass() {
		return EAnimals.class;
	}
}
