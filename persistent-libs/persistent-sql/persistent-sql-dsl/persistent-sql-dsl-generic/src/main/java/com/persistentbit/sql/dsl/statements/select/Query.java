package com.persistentbit.sql.dsl.statements.select;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.sql.dsl.expressions.ETuple2;
import com.persistentbit.sql.dsl.expressions.ETuple3;
import com.persistentbit.sql.dsl.tables.Table;
import com.persistentbit.tuples.Tuple2;
import com.persistentbit.tuples.Tuple3;

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

	<E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2>
	TypedSelection1<ETuple2<E1, J1, E2, J2>, Tuple2<J1, J2>>
	selection(E1 sel1, E2 sel2);

	<E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2, E3 extends DExpr<J3>, J3>
	TypedSelection1<ETuple3<E1, J1, E2, J2, E3, J3>, Tuple3<J1, J2, J3>>
	selection(E1 sel1, E2 sel2, E3 sel3);

}
