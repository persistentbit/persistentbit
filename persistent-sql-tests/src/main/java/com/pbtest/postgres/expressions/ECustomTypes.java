package com.pbtest.postgres.expressions;

import com.pbtest.postgres.values.CustomTypes;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EObject;

public abstract class ECustomTypes implements DExpr<CustomTypes>{

	public final EObject tUuid;
	public final EObject tInterval;


	public ECustomTypes(EObject tUuid, EObject tInterval) {
		this.tUuid = tUuid;
		this.tInterval = tInterval;
	}
}
