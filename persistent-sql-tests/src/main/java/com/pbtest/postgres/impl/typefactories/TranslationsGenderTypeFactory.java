package com.pbtest.postgres.impl.typefactories;

import com.pbtest.postgres.expressions.ETranslationsGender;
import com.pbtest.postgres.values.TranslationsGender;
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

public class TranslationsGenderTypeFactory
	extends AbstractStructureTypeFactory<ETranslationsGender, TranslationsGender>{

	private class ETranslationsGenderImpl extends ETranslationsGender
		implements StructTypeImplMixin<ETranslationsGender, TranslationsGender>{


		public ETranslationsGenderImpl(Iterator<DExpr> iter) {
			super(
				(EObject) iter.next()
				, (EObject) iter.next()
				, (EString) iter.next()
			);
		}

		@Override
		public AbstractStructureTypeFactory<ETranslationsGender, TranslationsGender> getTypeFactory() {
			return TranslationsGenderTypeFactory.this;
		}

		@Override
		public String toString() {
			return "ETranslationsGender[" + genderCode + languageCode + description + "]";
		}

		@Override
		public ETranslationsGender getThis() {
			return this;
		}
	}

	public TranslationsGenderTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected PList<StructureField<ETranslationsGender, TranslationsGender>> buildFields() {
		return PList.val(
			createField(EObject.class, "gender_code", "genderCode", v -> v.getGenderCode(), v -> v.genderCode)
			, createField(EObject.class, "language_code", "languageCode", v -> v.getLanguageCode(), v -> v.languageCode)
			, createField(EString.class, "description", "description", v -> v.getDescription(), v -> v.description)
		);
	}

	@Override
	protected TranslationsGender buildValue(Object[] fieldValues) {
		return new TranslationsGender(
			(Object) fieldValues[0]
			, (Object) fieldValues[1]
			, (String) fieldValues[2]
		);
	}

	@Override
	protected ETranslationsGenderImpl createExpression(PStream<DExpr> fieldValues) {
		return new ETranslationsGenderImpl(fieldValues.iterator());
	}

	@Override
	public Class<ETranslationsGender> getTypeClass() {
		return ETranslationsGender.class;
	}
}
