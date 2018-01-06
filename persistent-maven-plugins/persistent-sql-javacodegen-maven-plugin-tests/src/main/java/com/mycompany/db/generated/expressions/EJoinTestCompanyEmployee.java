package com.mycompany.db.generated.expressions;

import com.mycompany.db.generated.values.JoinTestCompanyEmployee;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;

public abstract class EJoinTestCompanyEmployee implements DExpr<JoinTestCompanyEmployee>{

	public final ELong   cmpId;
	public final ELong   empId;
	public final EString function;


	public EJoinTestCompanyEmployee(ELong cmpId, ELong empId, EString function) {
		this.cmpId = cmpId;
		this.empId = empId;
		this.function = function;
	}
}
