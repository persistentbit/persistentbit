package com.pbtest.postgres.expressions;

import com.pbtest.postgres.values.TranslationsGender;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EObject;
import com.persistentbit.sql.dsl.expressions.EString;

public abstract class ETranslationsGender implements DExpr<TranslationsGender>{

	public final EObject genderCode;
	public final EObject languageCode;
	public final EString description;


	public ETranslationsGender(EObject genderCode, EObject languageCode, EString description) {
		this.genderCode = genderCode;
		this.languageCode = languageCode;
		this.description = description;
	}
}
