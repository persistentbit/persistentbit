package com.persistentbit.sql.dsl.statements.select.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.tables.Table;
import com.persistentbit.tuples.Tuple2;

/**
 * TODOC
 *
 * @author petermuys
 * @since 21/12/17
 */
public class QueryImpl1<P1 extends DExpr<J1>,J1> extends QueryImpl{
	public QueryImpl1(ExprContext context,
					  PList<Table> from,
					  PList<JoinImpl> joins,
					  EBool where,
					  PList<OrderBy> orderBy,
					  Tuple2<Long, Long> limitAndOffset,
					  Class<P1> clsP1, String nameP1) {
		super(context, from, joins, where, orderBy, limitAndOffset);
	}

	public QueryImpl1(ExprContext context,
					  PList<Table> from) {
		super(context, from);
	}
}
