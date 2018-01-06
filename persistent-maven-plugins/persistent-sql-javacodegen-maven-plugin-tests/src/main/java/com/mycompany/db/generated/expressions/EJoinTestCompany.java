package com.mycompany.db.generated.expressions;

import com.mycompany.db.generated.values.JoinTestCompany;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;

public abstract class EJoinTestCompany implements DExpr<JoinTestCompany>{

	public final ELong   cmpId;
	public final EString name;
	public final ELong   ownerEmpId;


	public EJoinTestCompany(ELong cmpId, EString name, ELong ownerEmpId) {
		this.cmpId = cmpId;
		this.name = name;
		this.ownerEmpId = ownerEmpId;
	}
}
