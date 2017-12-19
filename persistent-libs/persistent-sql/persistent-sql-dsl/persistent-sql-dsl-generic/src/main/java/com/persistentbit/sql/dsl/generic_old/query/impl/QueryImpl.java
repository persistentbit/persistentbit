package com.persistentbit.sql.dsl.generic_old.query.impl;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbContext;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.sql.dsl.expressions.DExprTable;
import com.persistentbit.sql.dsl.generic_old.query.Join;
import com.persistentbit.sql.dsl.generic_old.query.Query;
import com.persistentbit.sql.dsl.generic_old.query.Selection;
import com.persistentbit.tuples.Tuple2;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class QueryImpl implements Query{

	//final DbSqlContext	sqlContext;
	final DbContext         dbContext;
	final PList<DExprTable> from;
	final PList<JoinImpl>   joins;
	@Nullable
	final EBool             where;
	final PList<OrderBy>    orderBy;
	@Nullable
	final Tuple2<Long,Long> limitAndOffset;


	public QueryImpl(DbContext dbContext,
					 PList<DExprTable> from,
					 PList<JoinImpl> joins,
					 @Nullable EBool where,
					 PList<OrderBy> orderBy,
					 @Nullable Tuple2<Long, Long> limitAndOffset
	) {
		this.dbContext = dbContext;
		this.from = from;
		this.joins = joins;
		this.where = where;
		this.orderBy = orderBy;
		this.limitAndOffset = limitAndOffset;
	}

	public QueryImpl(DbContext dbContext, PList<DExprTable> from){
		this(dbContext, from, PList.empty(),null,PList.empty(),null);
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
		return new QueryImpl(dbContext,from,joins,where,this.orderBy.plus(orderBy),limitAndOffset);
	}

	@Override
	public Join leftJoin(DExprTable table
	) {
		return new JoinImpl(this, JoinImpl.Type.left,table);
	}

	@Override
	public Join rightJoin(DExprTable table
	) {
		return new JoinImpl(this, JoinImpl.Type.right,table);
	}

	@Override
	public Join innerJoin(DExprTable table
	) {
		return new JoinImpl(this, JoinImpl.Type.inner,table);
	}

	@Override
	public Join fullJoin(DExprTable table
	) {
		return new JoinImpl(this, JoinImpl.Type.full,table);
	}

	Query addJoin(JoinImpl join){
		return new QueryImpl(dbContext,from,joins.plus(join),where,orderBy,limitAndOffset);
	}

	@Override
	public Query where(EBool whereExpr) {
		return new QueryImpl(dbContext,from,joins,whereExpr,orderBy,limitAndOffset);
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
	public <T> Selection<T> selection(DExpr<T> selection
	) {
		return new SelectionImpl<>(this,selection);
	}

}
