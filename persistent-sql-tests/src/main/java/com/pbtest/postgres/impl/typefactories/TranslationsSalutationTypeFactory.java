package com.pbtest.postgres.impl.typefactories;

import com.pbtest.postgres.expressions.ETranslationsSalutation;
import com.pbtest.postgres.values.TranslationsSalutation;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructTypeImplMixin;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.util.Iterator;

public class TranslationsSalutationTypeFactory
	extends AbstractStructureTypeFactory<ETranslationsSalutation, TranslationsSalutation>{

	private class ETranslationsSalutationImpl extends ETranslationsSalutation
		implements StructTypeImplMixin<ETranslationsSalutation, TranslationsSalutation>{


		public ETranslationsSalutationImpl(Iterator<DExpr> iter) {
			super(
				(EString) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
			);
		}
		@Override
		public AbstractStructureTypeFactory<ETranslationsSalutation, TranslationsSalutation> getTypeFactory() {
			return TranslationsSalutationTypeFactory.this;
		}
		@Override
		public String toString() {
			return "ETranslationsSalutation[" + salutationCode + languageCode + description + "]";
		}
		@Override
		public ETranslationsSalutation getThis() {
			return this;
		}
	}

	public TranslationsSalutationTypeFactory(ExprContext context) {
		super(context);
	}
	@Override
	protected PList<StructureField<ETranslationsSalutation, TranslationsSalutation>> buildFields() {
		return PList.val(
			createField(EString.class, "salutation_code", "salutationCode", v -> v
				.getSalutationCode(), v -> v.salutationCode)
			, createField(EString.class, "language_code", "languageCode", v -> v.getLanguageCode(), v -> v.languageCode)
			, createField(EString.class, "description", "description", v -> v.getDescription(), v -> v.description)
		);
	}
	@Override
	protected TranslationsSalutation buildValue(Object[] fieldValues) {
		return new TranslationsSalutation(
			(String) fieldValues[0]
			, (String) fieldValues[1]
			, (String) fieldValues[2]
		);
	}
	@Override
	protected ETranslationsSalutationImpl createExpression(PStream<DExpr> fieldValues) {
		return new ETranslationsSalutationImpl(fieldValues.iterator());
	}
	@Override
	public Class<ETranslationsSalutation> getTypeClass() {
		return ETranslationsSalutation.class;
	}
}
