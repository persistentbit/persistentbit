package com.persistentbit.sql.dsl.postgres.rt.windowover.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.assql.SqlConvertibleImpl;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.orderby.OrderBy;
import com.persistentbit.sql.dsl.postgres.rt.windowover.Frame;
import com.persistentbit.sql.dsl.postgres.rt.windowover.WindowOver;

/**
 * TODOC
 *
 * @author petermuys
 * @since 7/01/18
 */
public class WindowOverImpl implements WindowOver, SqlConvertibleImpl{

	private PList<DExpr<?>> partitions;
	private OrderBy         orderBy;
	private Frame           frame;

	public WindowOverImpl(PList<DExpr<?>> partitions, OrderBy orderBy,
						  Frame frame) {
		this.partitions = partitions;
		this.orderBy = orderBy;
		this.frame = frame;
	}

	public WindowOverImpl() {
		this(PList.empty(), OrderBy.empty(), Frame.empty());
	}

	@Override
	public WindowOver partitionBy(DExpr<?>... exprList) {
		return new WindowOverImpl(partitions.plusAll(PStream.val(exprList)), orderBy, frame);
	}

	@Override
	public WindowOver orderBy(OrderBy orderBy) {
		return new WindowOverImpl(partitions, orderBy, frame);
	}

	@Override
	public WindowOver frame(Frame frame) {
		return new WindowOverImpl(partitions, orderBy, frame);
	}

	@Override
	public SqlWithParams toSql(ExprContext context) {
		return SqlWithParams
			.sql(partitions.isEmpty() ? "" : "PARTITION BY ")
			.add(partitions.map(p -> context.toSql(p)), ", ")
			.add(" ").add(context.toSql(orderBy))
			.add(" ").add(context.toSql(frame));
	}
}
