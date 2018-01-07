package com.pbtest.postgres.expressions;

import com.pbtest.postgres.values.ProductGroups;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EInt;
import com.persistentbit.sql.dsl.expressions.EString;

public abstract class EProductGroups implements DExpr<ProductGroups>{

	public final EInt    groupId;
	public final EString groupName;


	public EProductGroups(EInt groupId, EString groupName) {
		this.groupId = groupId;
		this.groupName = groupName;
	}
}
