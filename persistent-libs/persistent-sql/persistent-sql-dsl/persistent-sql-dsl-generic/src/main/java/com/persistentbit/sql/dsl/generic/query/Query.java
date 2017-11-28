package com.persistentbit.sql.dsl.generic.query;

import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprBoolean;
import com.persistentbit.sql.dsl.generic.expressions.DExprSelectable;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public interface Query{




	Query orderByDesc(DExpr<?> expr);

	Query orderByAsc(DExpr<?> expr);

	Join leftJoin(DExprSelectable table);

	Join rightJoin(DExprSelectable table);

	Join innerJoin(DExprSelectable table);

	Join fullJoin(DExprSelectable table);

	Query where(DExprBoolean whereExpr);


	Query	limit(long limit);
	Query	limitAndOffset(long limit, long offset);



	<T> DSelection1<T> selection(DExpr<T> selection);

	<T1, T2> DSelection2<T1, T2> selection(DExpr<T1> col1, DExpr<T2> col2);

	<T1, T2, T3> DSelection3<T1, T2, T3> selection(DExpr<T1> col1, DExpr<T2> col2, DExpr<T3> col3);

	<T1, T2, T3, T4> DSelection4<T1, T2, T3, T4> selection(
		DExpr<T1> col1, DExpr<T2> col2, DExpr<T3> col3, DExpr<T4> col4);

	<T1, T2, T3, T4, T5> DSelection5<T1, T2, T3, T4, T5> selection(
		DExpr<T1> col1, DExpr<T2> col2, DExpr<T3> col3, DExpr<T4> col4, DExpr<T5> col5);

	<T1, T2, T3, T4, T5, T6> DSelection6<T1, T2, T3, T4, T5, T6> selection(
		DExpr<T1> col1, DExpr<T2> col2, DExpr<T3> col3, DExpr<T4> col4, DExpr<T5> col5, DExpr<T6> col6);

	<T1, T2, T3, T4, T5, T6, T7> DSelection7<T1, T2, T3, T4, T5, T6, T7> selection(
		DExpr<T1> col1, DExpr<T2> col2, DExpr<T3> col3, DExpr<T4> col4, DExpr<T5> col5, DExpr<T6> col6, DExpr<T7> col7);

}
