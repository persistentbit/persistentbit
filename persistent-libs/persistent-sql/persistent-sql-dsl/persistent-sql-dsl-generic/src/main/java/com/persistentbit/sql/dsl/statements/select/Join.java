package com.persistentbit.sql.dsl.statements.select;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EBool;

/**
 * Created by petermuys on 1/10/16.
 */

public interface Join{

	enum Type{
		inner, left, right, full
	}

	Query on(EBool joinExpr);

	Query using(DExpr... columnExpr);

	Query query();


}
