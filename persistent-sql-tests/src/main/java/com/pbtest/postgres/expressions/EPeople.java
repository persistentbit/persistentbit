package com.pbtest.postgres.expressions;

import com.pbtest.postgres.values.People;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;

public abstract class EPeople implements DExpr<People>{

	public final ELong   personId;
	public final EString salutationCode;
	public final EString nameFirst;
	public final EString nameMiddle;
	public final EString nameLast;
	public final EString genderCode;


	public EPeople(ELong personId, EString salutationCode, EString nameFirst, EString nameMiddle, EString nameLast,
				   EString genderCode) {
		this.personId = personId;
		this.salutationCode = salutationCode;
		this.nameFirst = nameFirst;
		this.nameMiddle = nameMiddle;
		this.nameLast = nameLast;
		this.genderCode = genderCode;
	}
}
