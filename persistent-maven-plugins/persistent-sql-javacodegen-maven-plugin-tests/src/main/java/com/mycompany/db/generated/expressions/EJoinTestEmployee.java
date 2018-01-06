package com.mycompany.db.generated.expressions;

import com.mycompany.db.generated.values.JoinTestEmployee;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;

public abstract class EJoinTestEmployee implements DExpr<JoinTestEmployee>{

	public final ELong   empId;
	public final EString name;


	public EJoinTestEmployee(ELong empId, EString name) {
		this.empId = empId;
		this.name = name;
	}
}
