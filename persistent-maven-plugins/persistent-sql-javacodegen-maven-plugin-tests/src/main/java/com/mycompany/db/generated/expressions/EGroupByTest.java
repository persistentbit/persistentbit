package com.mycompany.db.generated.expressions;

import com.mycompany.db.generated.values.GroupByTest;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.sql.dsl.expressions.EInt;
import com.persistentbit.sql.dsl.expressions.ELong;

public abstract class EGroupByTest implements DExpr<GroupByTest>{

	public final ELong empId;
	public final EInt  hiringYear;
	public final EInt  hiringQuarter;
	public final EBool active;


	public EGroupByTest(ELong empId, EInt hiringYear, EInt hiringQuarter, EBool active) {
		this.empId = empId;
		this.hiringYear = hiringYear;
		this.hiringQuarter = hiringQuarter;
		this.active = active;
	}
}
