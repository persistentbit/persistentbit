package com.pbtest.postgres.expressions;

import com.pbtest.postgres.values.Salutations;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EObject;
import com.persistentbit.sql.dsl.expressions.EString;

public abstract class ESalutations implements DExpr<Salutations>{

	public final EObject salutationCode;
	public final EString description;


	public ESalutations(EObject salutationCode, EString description) {
		this.salutationCode = salutationCode;
		this.description = description;
	}
}
