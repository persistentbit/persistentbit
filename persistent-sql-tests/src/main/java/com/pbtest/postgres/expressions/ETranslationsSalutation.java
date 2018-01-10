package com.pbtest.postgres.expressions;

import com.pbtest.postgres.values.TranslationsSalutation;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EString;

public abstract class ETranslationsSalutation implements DExpr<TranslationsSalutation>{

	public final EString salutationCode;
	public final EString languageCode;
	public final EString description;


	public ETranslationsSalutation(EString salutationCode, EString languageCode, EString description) {
		this.salutationCode = salutationCode;
		this.languageCode = languageCode;
		this.description = description;
	}
}
