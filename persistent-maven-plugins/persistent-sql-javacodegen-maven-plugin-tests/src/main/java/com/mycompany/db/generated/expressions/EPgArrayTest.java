package com.mycompany.db.generated.expressions;

import com.mycompany.db.generated.values.PgArrayTest;
import com.persistentbit.sql.dsl.expressions.*;

public abstract class EPgArrayTest implements DExpr<PgArrayTest>{

	public final ELong                   id;
	public final EArray<EString, String> strings;
	public final EArray<EInt, Integer>   ints;


	public EPgArrayTest(ELong id, EArray<EString, String> strings, EArray<EInt, Integer> ints) {
		this.id = id;
		this.strings = strings;
		this.ints = ints;
	}
}
