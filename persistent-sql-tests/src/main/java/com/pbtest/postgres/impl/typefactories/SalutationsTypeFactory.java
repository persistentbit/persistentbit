package com.pbtest.postgres.impl.typefactories;

import com.pbtest.postgres.expressions.ESalutations;
import com.pbtest.postgres.values.Salutations;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EObject;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructTypeImplMixin;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.util.Iterator;

public class SalutationsTypeFactory extends AbstractStructureTypeFactory<ESalutations, Salutations>{

	private class ESalutationsImpl extends ESalutations implements StructTypeImplMixin<ESalutations, Salutations>{


		public ESalutationsImpl(Iterator<DExpr> iter) {
			super(
				(EObject) iter.next()
				, (EString) iter.next()
			);
		}

		@Override
		public AbstractStructureTypeFactory<ESalutations, Salutations> getTypeFactory() {
			return SalutationsTypeFactory.this;
		}

		@Override
		public String toString() {
			return "ESalutations[" + salutationCode + description + "]";
		}

		@Override
		public ESalutations getThis() {
			return this;
		}
	}

	public SalutationsTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected PList<StructureField<ESalutations, Salutations>> buildFields() {
		return PList.val(
			createField(EObject.class, "salutation_code", "salutationCode", v -> v
				.getSalutationCode(), v -> v.salutationCode)
			, createField(EString.class, "description", "description", v -> v.getDescription(), v -> v.description)
		);
	}

	@Override
	protected Salutations buildValue(Object[] fieldValues) {
		return new Salutations(
			(Object) fieldValues[0]
			, (String) fieldValues[1]
		);
	}

	@Override
	protected ESalutationsImpl createExpression(PStream<DExpr> fieldValues) {
		return new ESalutationsImpl(fieldValues.iterator());
	}

	@Override
	public Class<ESalutations> getTypeClass() {
		return ESalutations.class;
	}
}
