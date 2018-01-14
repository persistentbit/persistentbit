package com.pbtest.postgres.expressions;

import com.pbtest.postgres.values.CustomTypes;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.postgres.rt.customtypes.expressions.EInterval;
import com.persistentbit.sql.dsl.postgres.rt.customtypes.expressions.EUUID;

public abstract class ECustomTypes implements DExpr<CustomTypes>{

	public final EUUID     tUuid;
	public final EInterval tInterval;


	public ECustomTypes(EUUID tUuid, EInterval tInterval) {
		this.tUuid = tUuid;
		this.tInterval = tInterval;
	}
}
