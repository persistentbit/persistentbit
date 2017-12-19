package com.persistentbit.sql.dsl.statements.select;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ESelection;
import com.persistentbit.sql.dsl.statements.select.impl.SubQuery1;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public interface TypedSelection1
	<
		E1 extends DExpr<J1>,J1
	> {


	SubQuery1<E1,J1> asSubQuery(String name);
	ESelection<J1> asExpr();

}
