package com.pbtest.mysql.expressions;

import com.pbtest.mysql.values.MysqlAllTypes;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.sql.dsl.expressions.EByte;

public abstract class EMysqlAllTypes implements DExpr<MysqlAllTypes>{

	public final EBool aBit;
	public final EByte aTinyint;
	public final EByte aTinyintUnsinged;
	public final EBool aBool;


	public EMysqlAllTypes(EBool aBit, EByte aTinyint, EByte aTinyintUnsinged, EBool aBool) {
		this.aBit = aBit;
		this.aTinyint = aTinyint;
		this.aTinyintUnsinged = aTinyintUnsinged;
		this.aBool = aBool;
	}
}
