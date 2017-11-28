package com.persistentbit.sql.dsl.generic.query.impl;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprBoolean;
import com.persistentbit.sql.dsl.generic.expressions.DExprSelectable;
import com.persistentbit.sql.dsl.generic.query.*;
import com.persistentbit.tuples.Tuple2;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class QueryImpl implements Query{

	final DbSqlContext	sqlContext;
	final PList<DExprSelectable> from;
	final PList<JoinImpl> joins;
	@Nullable
	final DExprBoolean where;
	final PList<OrderBy> orderBy;
	@Nullable
	final Tuple2<Long,Long> limitAndOffset;


	public QueryImpl(DbSqlContext sqlContext,
					 PList<DExprSelectable> from,
					 PList<JoinImpl> joins,
					 @Nullable DExprBoolean where,
					 PList<OrderBy> orderBy,
					 @Nullable Tuple2<Long, Long> limitAndOffset
	) {
		this.sqlContext = sqlContext;
		this.from = from;
		this.joins = joins;
		this.where = where;
		this.orderBy = orderBy;
		this.limitAndOffset = limitAndOffset;
	}

	public QueryImpl(DbSqlContext sqlContext, PList<DExprSelectable> from){
		this(sqlContext, from, PList.empty(),null,PList.empty(),null);
	}

	@Override
	public Query orderByDesc(DExpr<?> expr) {
		return orderBy(new OrderBy(expr, OrderBy.Direction.desc));
	}

	@Override
	public Query orderByAsc(DExpr<?> expr) {
		return orderBy(new OrderBy(expr, OrderBy.Direction.asc));
	}

	private Query orderBy(OrderBy orderBy) {
		return new QueryImpl(sqlContext,from,joins,where,this.orderBy.plus(orderBy),limitAndOffset);
	}

	@Override
	public Join leftJoin(DExprSelectable table
	) {
		return new JoinImpl(this, JoinImpl.Type.left,table);
	}

	@Override
	public Join rightJoin(DExprSelectable table
	) {
		return new JoinImpl(this, JoinImpl.Type.right,table);
	}

	@Override
	public Join innerJoin(DExprSelectable table
	) {
		return new JoinImpl(this, JoinImpl.Type.inner,table);
	}

	@Override
	public Join fullJoin(DExprSelectable table
	) {
		return new JoinImpl(this, JoinImpl.Type.full,table);
	}

	Query addJoin(JoinImpl join){
		return new QueryImpl(sqlContext,from,joins.plus(join),where,orderBy,limitAndOffset);
	}

	@Override
	public Query where(DExprBoolean whereExpr) {
		return new QueryImpl(sqlContext,from,joins,whereExpr,orderBy,limitAndOffset);
	}

	@Override
	public Query limit(long limit) {
		return null;
	}

	@Override
	public Query limitAndOffset(long limit, long offset) {
		return null;
	}

	@Override
	public <T> DSelection1<T> selection(DExpr<T> selection
	) {
		return new DImplSelection1<>(this,PList.val(selection),null);
	}

	@Override
	public <T1, T2> DSelection2<T1, T2> selection(DExpr<T1> col1, DExpr<T2> col2
	) {
		return new DImplSelection2<>(this,PList.val(col1,col2),null);
	}

	@Override
	public <T1, T2, T3> DSelection3<T1, T2, T3> selection(DExpr<T1> col1, DExpr<T2> col2, DExpr<T3> col3
	) {
		return new DImplSelection3<>(this,PList.val(col1,col2,col3),null);
	}

	@Override
	public <T1, T2, T3, T4> DSelection4<T1, T2, T3, T4> selection(DExpr<T1> col1, DExpr<T2> col2, DExpr<T3> col3,
																  DExpr<T4> col4
	) {
		return new DImplSelection4<>(this,PList.val(col1,col2,col3,col4),null);
	}

	@Override
	public <T1, T2, T3, T4, T5> DSelection5<T1, T2, T3, T4, T5> selection(DExpr<T1> col1, DExpr<T2> col2,
																		  DExpr<T3> col3, DExpr<T4> col4, DExpr<T5> col5
	) {
		return new DImplSelection5<>(this,PList.val(col1,col2,col3,col4,col5),null);
	}

	@Override
	public <T1, T2, T3, T4, T5, T6> DSelection6<T1, T2, T3, T4, T5, T6> selection(DExpr<T1> col1, DExpr<T2> col2,
																				  DExpr<T3> col3, DExpr<T4> col4,
																				  DExpr<T5> col5, DExpr<T6> col6
	) {
		return new DImplSelection6<>(this,PList.val(col1,col2,col3,col4,col5,col6),null);
	}

	@Override
	public <T1, T2, T3, T4, T5, T6, T7> DSelection7<T1, T2, T3, T4, T5, T6, T7> selection(DExpr<T1> col1,
																						  DExpr<T2> col2,
																						  DExpr<T3> col3,
																						  DExpr<T4> col4,
																						  DExpr<T5> col5,
																						  DExpr<T6> col6, DExpr<T7> col7
	) {
		return new DImplSelection7<>(this,PList.val(col1,col2,col3,col4,col5,col6,col7),null);
	}
}
