package com.pbtest.postgres.expressions;

import com.pbtest.postgres.values.TranslationsSalutation;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EObject;
import com.persistentbit.sql.dsl.expressions.EString;

public abstract class ETranslationsSalutation implements DExpr<TranslationsSalutation>{

	public final EObject salutationCode;
	public final EObject languageCode;
	public final EString description;


	public ETranslationsSalutation(EObject salutationCode, EObject languageCode, EString description) {
		this.salutationCode = salutationCode;
		this.languageCode = languageCode;
		this.description = description;
	}
}
