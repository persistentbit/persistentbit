package com.pbtest.postgres.expressions;

import com.pbtest.postgres.values.People;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ELong;

public abstract class EPeople implements DExpr<People>{

	public final ELong personId;


	public EPeople(ELong personId) {
		this.personId = personId;
	}
}
