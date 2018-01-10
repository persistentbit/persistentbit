package com.pbtest.postgres.expressions;

import com.pbtest.postgres.values.TranslationsGender;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EString;

public abstract class ETranslationsGender implements DExpr<TranslationsGender>{

	public final EString genderCode;
	public final EString languageCode;
	public final EString description;


	public ETranslationsGender(EString genderCode, EString languageCode, EString description) {
		this.genderCode = genderCode;
		this.languageCode = languageCode;
		this.description = description;
	}
}
