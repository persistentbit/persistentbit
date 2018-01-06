package com.persistentbit.sql.dsl.statements.select;

import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.dsl.tables.Table;
import com.persistentbit.tuples.*;

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

	Query limit(ELong limit);

	Query limitAndOffset(ELong limit, ELong offset);

	Query limit(long limit);
	Query limitAndOffset(long limit, long offset);

	Query distinct();



	<E1 extends DExpr<J1>,J1> TypedSelection1<E1,J1> selection(E1 selection);

	<E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2>
	TypedSelection1<ETuple2<E1, J1, E2, J2>, Tuple2<J1, J2>>
	selection(E1 sel1, E2 sel2);

	<E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2, E3 extends DExpr<J3>, J3>
	TypedSelection1<ETuple3<E1, J1, E2, J2, E3, J3>, Tuple3<J1, J2, J3>>
	selection(E1 sel1, E2 sel2, E3 sel3);


	<E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2, E3 extends DExpr<J3>, J3, E4 extends DExpr<J4>, J4>
	TypedSelection1<ETuple4<E1, J1, E2, J2, E3, J3, E4, J4>, Tuple4<J1, J2, J3, J4>>
	selection(E1 sel1, E2 sel2, E3 sel3, E4 sel4);

	<E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2, E3 extends DExpr<J3>, J3, E4 extends DExpr<J4>, J4, E5 extends DExpr<J5>, J5>
	TypedSelection1<ETuple5<E1, J1, E2, J2, E3, J3, E4, J4, E5, J5>, Tuple5<J1, J2, J3, J4, J5>>
	selection(E1 sel1, E2 sel2, E3 sel3, E4 sel4, E5 sel5);


	<E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2, E3 extends DExpr<J3>, J3, E4 extends DExpr<J4>, J4, E5 extends DExpr<J5>, J5, E6 extends DExpr<J6>, J6>
	TypedSelection1<ETuple6<E1, J1, E2, J2, E3, J3, E4, J4, E5, J5, E6, J6>, Tuple6<J1, J2, J3, J4, J5, J6>>
	selection(E1 sel1, E2 sel2, E3 sel3, E4 sel4, E5 sel5, E6 sel6);


	<E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2, E3 extends DExpr<J3>, J3, E4 extends DExpr<J4>, J4, E5 extends DExpr<J5>, J5, E6 extends DExpr<J6>, J6, E7 extends DExpr<J7>, J7>
	TypedSelection1<ETuple7<E1, J1, E2, J2, E3, J3, E4, J4, E5, J5, E6, J6, E7, J7>, Tuple7<J1, J2, J3, J4, J5, J6, J7>>
	selection(E1 sel1, E2 sel2, E3 sel3, E4 sel4, E5 sel5, E6 sel6, E7 sel7);

}
