package com.persistentbit.sql.dsl.statements.select.impl;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.DExprTable;
import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.select.Join;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.statements.select.TypedSelection1;
import com.persistentbit.sql.dsl.tables.Table;
import com.persistentbit.tuples.Tuple2;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class QueryImpl implements Query{

	final ExprContext	context;
	final PList<Table> from;
	final PList<JoinImpl>   joins;
	@Nullable
	final EBool             where;
	final PList<OrderBy>    orderBy;
	@Nullable
	final Tuple2<Long,Long> limitAndOffset;


	public QueryImpl(ExprContext	context,
					 PList<Table> from,
					 PList<JoinImpl> joins,
					 @Nullable EBool where,
					 PList<OrderBy> orderBy,
					 @Nullable Tuple2<Long, Long> limitAndOffset
	) {
		this.context = context;
		this.from = from;
		this.joins = joins;
		this.where = where;
		this.orderBy = orderBy;
		this.limitAndOffset = limitAndOffset;
	}

	public QueryImpl(ExprContext	context, PList<Table> from){
		this(context, from, PList.empty(),null,PList.empty(),null);
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
		return new QueryImpl(context,from,joins,where,this.orderBy.plus(orderBy),limitAndOffset);
	}

	@Override
	public Join leftJoin(Table table
	) {
		return new JoinImpl(this, JoinImpl.Type.left,table);
	}

	@Override
	public Join rightJoin(Table table
	) {
		return new JoinImpl(this, JoinImpl.Type.right,table);
	}

	@Override
	public Join innerJoin(Table table
	) {
		return new JoinImpl(this, JoinImpl.Type.inner,table);
	}

	@Override
	public Join fullJoin(Table table
	) {
		return new JoinImpl(this, JoinImpl.Type.full,table);
	}

	Query addJoin(JoinImpl join){
		return new QueryImpl(context,from,joins.plus(join),where,orderBy,limitAndOffset);
	}

	@Override
	public Query where(EBool whereExpr) {
		return new QueryImpl(context,from,joins,whereExpr,orderBy,limitAndOffset);
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
	public <E1 extends DExpr<J1>, J1> TypedSelection1<E1, J1> selection(E1 col1) {
		return new TypedSelection1Impl<>(this,col1);
	}


}
