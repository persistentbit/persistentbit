package com.pbtest.postgres.expressions;

import com.pbtest.postgres.values.Animals;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EInt;
import com.persistentbit.sql.dsl.expressions.EString;

public abstract class EAnimals implements DExpr<Animals>{

	public final EInt    id;
	public final EString type;
	public final EString name;


	public EAnimals(EInt id, EString type, EString name) {
		this.id = id;
		this.type = type;
		this.name = name;
	}
}
