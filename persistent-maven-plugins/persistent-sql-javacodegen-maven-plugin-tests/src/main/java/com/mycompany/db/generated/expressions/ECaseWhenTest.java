package com.mycompany.db.generated.expressions;

import com.mycompany.db.generated.values.CaseWhenTest;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EInt;
import com.persistentbit.sql.dsl.expressions.ELong;

public abstract class ECaseWhenTest implements DExpr<CaseWhenTest>{

	public final EInt  id;
	public final ELong value;


	public ECaseWhenTest(EInt id, ELong value) {
		this.id = id;
		this.value = value;
	}
}
