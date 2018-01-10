package com.pbtest.postgres.expressions;

import com.pbtest.postgres.values.TranslationsCountry;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EString;

public abstract class ETranslationsCountry implements DExpr<TranslationsCountry>{

	public final EString countryCode;
	public final EString languageCode;
	public final EString name;


	public ETranslationsCountry(EString countryCode, EString languageCode, EString name) {
		this.countryCode = countryCode;
		this.languageCode = languageCode;
		this.name = name;
	}
}
