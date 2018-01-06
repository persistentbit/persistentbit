package com.mycompany.db.generated.expressions;

import com.mycompany.db.generated.values.LimitOffsetTest;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EInt;
import com.persistentbit.sql.dsl.expressions.ELong;

public abstract class ELimitOffsetTest implements DExpr<LimitOffsetTest>{

	public final EInt  id;
	public final ELong value;


	public ELimitOffsetTest(EInt id, ELong value) {
		this.id = id;
		this.value = value;
	}
}
