package com.persistentbit.sql.dsl.generic.query;

import com.persistentbit.sql.dsl.generic.expressions.DExprBoolean;

/**
 * Created by petermuys on 1/10/16.
 */

public interface Join{

	enum Type{
		inner, left, right, full
	}

	Query on(DExprBoolean joinExpr);

	Query query();


}
