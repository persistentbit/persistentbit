package com.pbtest.postgres.impl.typefactories;

import com.pbtest.postgres.expressions.EGenders;
import com.pbtest.postgres.values.Genders;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructTypeImplMixin;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.util.Iterator;

public class GendersTypeFactory extends AbstractStructureTypeFactory<EGenders, Genders>{

	private class EGendersImpl extends EGenders implements StructTypeImplMixin<EGenders, Genders>{


		public EGendersImpl(Iterator<DExpr> iter) {
			super(
				(EString) iter.next()
				, (EString) iter.next()
			);
		}
		@Override
		public AbstractStructureTypeFactory<EGenders, Genders> getTypeFactory() {
			return GendersTypeFactory.this;
		}
		@Override
		public String toString() {
			return "EGenders[" + genderCode + description + "]";
		}
		@Override
		public EGenders getThis() {
			return this;
		}
	}

	public GendersTypeFactory(ExprContext context) {
		super(context);
	}
	@Override
	protected PList<StructureField<EGenders, Genders>> buildFields() {
		return PList.val(
			createField(EString.class, "gender_code", "genderCode", v -> v.getGenderCode(), v -> v.genderCode)
			, createField(EString.class, "description", "description", v -> v.getDescription(), v -> v.description)
		);
	}
	@Override
	protected Genders buildValue(Object[] fieldValues) {
		return new Genders(
			(String) fieldValues[0]
			, (String) fieldValues[1]
		);
	}
	@Override
	protected EGendersImpl createExpression(PStream<DExpr> fieldValues) {
		return new EGendersImpl(fieldValues.iterator());
	}
	@Override
	public Class<EGenders> getTypeClass() {
		return EGenders.class;
	}
}
