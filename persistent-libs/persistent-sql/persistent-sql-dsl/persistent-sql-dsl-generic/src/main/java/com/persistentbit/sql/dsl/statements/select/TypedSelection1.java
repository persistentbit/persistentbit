package com.persistentbit.sql.dsl.statements.select;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ESelection;
import com.persistentbit.sql.dsl.expressions.Param;
import com.persistentbit.sql.dsl.statements.select.impl.SubQuery1;
import com.persistentbit.sql.dsl.statements.work.DbWorkP1;
import com.persistentbit.sql.dsl.statements.work.DbWorkP2;
import com.persistentbit.sql.dsl.statements.work.DbWorkP3;
import com.persistentbit.sql.dsl.statements.work.DbWorkP4;
import com.persistentbit.sql.work.DbWork;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public interface TypedSelection1
	<
		E1 extends DExpr<J1>, J1
		>{


	SubQuery1<E1, J1> asSubQuery(String name);

	ESelection<J1> asExpr();

	DbWork<PList<J1>> list();

	<P1 extends DExpr<PJ1>, PJ1> DbWorkP1<PJ1, PList<J1>> list(Param<P1> p1);

	<
		P1 extends DExpr<PJ1>, PJ1,
		P2 extends DExpr<PJ2>, PJ2
		> DbWorkP2<PJ1, PJ2, PList<J1>> list(Param<P1> p1, Param<P2> p2);

	<
		P1 extends DExpr<PJ1>, PJ1,
		P2 extends DExpr<PJ2>, PJ2,
		P3 extends DExpr<PJ3>, PJ3
		> DbWorkP3<PJ1, PJ2, PJ3, PList<J1>> list(Param<P1> p1, Param<P2> p2, Param<P3> p3);

	<
		P1 extends DExpr<PJ1>, PJ1,
		P2 extends DExpr<PJ2>, PJ2,
		P3 extends DExpr<PJ3>, PJ3,
		P4 extends DExpr<PJ4>, PJ4
		> DbWorkP4<PJ1, PJ2, PJ3, PJ4, PList<J1>> list(Param<P1> p1, Param<P2> p2, Param<P3> p3, Param<P4> p4);

	DbWork<J1> one();


	<P1 extends DExpr<PJ1>, PJ1> DbWorkP1<PJ1, J1> one(Param<P1> p1);

	<P1 extends DExpr<PJ1>, PJ1,
		P2 extends DExpr<PJ2>, PJ2
		> DbWorkP2<PJ1, PJ2, J1> one(Param<P1> p1, Param<P2> p2);

	<
		P1 extends DExpr<PJ1>, PJ1,
		P2 extends DExpr<PJ2>, PJ2,
		P3 extends DExpr<PJ3>, PJ3
		> DbWorkP3<PJ1, PJ2, PJ3, J1> one(Param<P1> p1, Param<P2> p2, Param<P3> p3);


	<
		P1 extends DExpr<PJ1>, PJ1,
		P2 extends DExpr<PJ2>, PJ2,
		P3 extends DExpr<PJ3>, PJ3,
		P4 extends DExpr<PJ4>, PJ4
		> DbWorkP4<PJ1, PJ2, PJ3, PJ4, J1> one(Param<P1> p1, Param<P2> p2, Param<P3> p3, Param<P4> p4);


}
