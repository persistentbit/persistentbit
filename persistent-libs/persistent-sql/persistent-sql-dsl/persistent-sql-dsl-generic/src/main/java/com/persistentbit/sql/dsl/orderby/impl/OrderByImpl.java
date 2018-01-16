package com.persistentbit.sql.dsl.orderby.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.Sql;
import com.persistentbit.sql.dsl.assql.SqlConvertibleImpl;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.orderby.OrderBy;
import com.persistentbit.sql.dsl.orderby.OrderByItem;

/**
 * TODOC
 *
 * @author petermuys
 * @since 7/01/18
 */
public class OrderByImpl implements OrderBy, SqlConvertibleImpl{

	private final PList<OrderByItem> items;

	public OrderByImpl(PList<OrderByItem> items) {
		this.items = items;
	}

	public OrderByImpl() {
		this(PList.empty());
	}

	@Override
	public OrderBy add(OrderByItem orderByItem) {
		return new OrderByImpl(items.plus(orderByItem));
	}

	public Sql toSql(ExprContext context) {
		if(items.isEmpty()) {
			return Sql.empty;
		}
		return Sql.sql("ORDER BY ").add(items.map(i -> orderByItemToSql(i, context)), ", ");
	}

	private Sql orderByItemToSql(OrderByItem item, ExprContext context) {
		return context
			.toSql(item.getExpr())
			.add(" " + item.getDir().name());
	}
}
