package com.persistentbit.sql.dsl.statements.select;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.sql.dsl.tables.Table;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public interface Query{




	Query orderByDesc(DExpr<?> expr);

	Query orderByAsc(DExpr<?> expr);

	Join leftJoin(Table table);

	Join rightJoin(Table table);

	Join innerJoin(Table table);

	Join fullJoin(Table table);

	Query where(EBool whereExpr);


	Query limit(long limit);
	Query limitAndOffset(long limit, long offset);



	<E1 extends DExpr<J1>,J1> TypedSelection1<E1,J1> selection(E1 selection);



}
