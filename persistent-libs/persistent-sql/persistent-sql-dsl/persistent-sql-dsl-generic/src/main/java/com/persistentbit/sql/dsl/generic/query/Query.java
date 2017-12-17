package com.persistentbit.sql.dsl.generic.query;

import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprTable;
import com.persistentbit.sql.dsl.generic.expressions.EBool;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public interface Query{




	Query orderByDesc(DExpr<?> expr);

	Query orderByAsc(DExpr<?> expr);

	Join leftJoin(DExprTable table);

	Join rightJoin(DExprTable table);

	Join innerJoin(DExprTable table);

	Join fullJoin(DExprTable table);

	Query where(EBool whereExpr);


	Query	limit(long limit);
	Query	limitAndOffset(long limit, long offset);



	<T> Selection<T> selection(DExpr<T> selection);

	/*default <T1, T2> Selection<Tuple2<T1, T2>> selection(DExpr<T1> col1, DExpr<T2> col2){
		return selection(col1.tuple2(col2));
	}

	default <T1, T2, T3> Selection<Tuple3<T1, T2, T3>> selection(DExpr<T1> col1, DExpr<T2> col2, DExpr<T3> col3){
		return selection(col1.tuple2(col2).tuple3(col3));
	}

	default <T1, T2, T3, T4> Selection<Tuple4<T1, T2, T3, T4>> selection(
		DExpr<T1> col1, DExpr<T2> col2, DExpr<T3> col3, DExpr<T4> col4){
		return selection(col1.tuple2(col2).tuple3(col3).tuple4(col4));
	}

	default <T1, T2, T3, T4, T5> Selection<Tuple5<T1, T2, T3, T4, T5>> selection(
		DExpr<T1> col1, DExpr<T2> col2, DExpr<T3> col3, DExpr<T4> col4, DExpr<T5> col5){
		return selection(col1.tuple2(col2).tuple3(col3).tuple4(col4).tuple5(col5));
	}

	default <T1, T2, T3, T4, T5, T6> Selection<Tuple6<T1, T2, T3, T4, T5, T6>> selection(
		DExpr<T1> col1, DExpr<T2> col2, DExpr<T3> col3, DExpr<T4> col4, DExpr<T5> col5, DExpr<T6> col6){
		return selection(col1.tuple2(col2).tuple3(col3).tuple4(col4).tuple5(col5).tuple6(col6));

	}

	default <T1, T2, T3, T4, T5, T6, T7> Selection<Tuple7<T1, T2, T3, T4, T5, T6, T7>> selection(
		DExpr<T1> col1, DExpr<T2> col2, DExpr<T3> col3, DExpr<T4> col4, DExpr<T5> col5, DExpr<T6> col6, DExpr<T7> col7){
		return selection(col1.tuple2(col2).tuple3(col3).tuple4(col4).tuple5(col5).tuple6(col6).tuple7(col7));
	}*/

}
