package com.pbtest.postgres.expressions;

import com.pbtest.postgres.values.TranslationsCountry;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EObject;
import com.persistentbit.sql.dsl.expressions.EString;

public abstract class ETranslationsCountry implements DExpr<TranslationsCountry>{

	public final EObject countryCode;
	public final EObject languageCode;
	public final EString name;


	public ETranslationsCountry(EObject countryCode, EObject languageCode, EString name) {
		this.countryCode = countryCode;
		this.languageCode = languageCode;
		this.name = name;
	}
}
