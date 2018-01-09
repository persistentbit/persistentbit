package com.pbtest.postgres.expressions;

import com.pbtest.postgres.values.Genders;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EObject;
import com.persistentbit.sql.dsl.expressions.EString;

public abstract class EGenders implements DExpr<Genders>{

	public final EObject genderCode;
	public final EString description;


	public EGenders(EObject genderCode, EString description) {
		this.genderCode = genderCode;
		this.description = description;
	}
}
