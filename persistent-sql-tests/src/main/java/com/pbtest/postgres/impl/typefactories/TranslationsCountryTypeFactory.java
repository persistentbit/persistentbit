package com.pbtest.postgres.impl.typefactories;

import com.pbtest.postgres.expressions.ETranslationsCountry;
import com.pbtest.postgres.values.TranslationsCountry;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructTypeImplMixin;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.util.Iterator;

public class TranslationsCountryTypeFactory
	extends AbstractStructureTypeFactory<ETranslationsCountry, TranslationsCountry>{

	private class ETranslationsCountryImpl extends ETranslationsCountry
		implements StructTypeImplMixin<ETranslationsCountry, TranslationsCountry>{


		public ETranslationsCountryImpl(Iterator<DExpr> iter) {
			super(
				(EString) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
			);
		}
		@Override
		public AbstractStructureTypeFactory<ETranslationsCountry, TranslationsCountry> getTypeFactory() {
			return TranslationsCountryTypeFactory.this;
		}
		@Override
		public String toString() {
			return "ETranslationsCountry[" + countryCode + languageCode + name + "]";
		}
		@Override
		public ETranslationsCountry getThis() {
			return this;
		}
	}

	public TranslationsCountryTypeFactory(ExprContext context) {
		super(context);
	}
	@Override
	protected PList<StructureField<ETranslationsCountry, TranslationsCountry>> buildFields() {
		return PList.val(
			createField(EString.class, "country_code", "countryCode", v -> v.getCountryCode(), v -> v.countryCode)
			, createField(EString.class, "language_code", "languageCode", v -> v.getLanguageCode(), v -> v.languageCode)
			, createField(EString.class, "name", "name", v -> v.getName(), v -> v.name)
		);
	}
	@Override
	protected TranslationsCountry buildValue(Object[] fieldValues) {
		return new TranslationsCountry(
			(String) fieldValues[0]
			, (String) fieldValues[1]
			, (String) fieldValues[2]
		);
	}
	@Override
	protected ETranslationsCountryImpl createExpression(PStream<DExpr> fieldValues) {
		return new ETranslationsCountryImpl(fieldValues.iterator());
	}
	@Override
	public Class<ETranslationsCountry> getTypeClass() {
		return ETranslationsCountry.class;
	}
}
