package com.persistentbit.sql.dsl.generic_old.query;

import com.persistentbit.sql.dsl.expressions.EBool;

/**
 * Created by petermuys on 1/10/16.
 */

public interface Join{

	enum Type{
		inner, left, right, full
	}

	Query on(EBool joinExpr);

	Query query();


}
