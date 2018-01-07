package com.persistentbit.sql.dsl.orderby;

import com.persistentbit.sql.dsl.assql.SqlConvertible;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.orderby.impl.OrderByImpl;

/**
 * TODOC
 *
 * @author petermuys
 * @since 7/01/18
 */
public interface OrderBy extends SqlConvertible{

	default OrderBy addDesc(DExpr<?> expr) {
		return add(new OrderByItem(expr, OrderByItem.Direction.desc));
	}

	default OrderBy addAsc(DExpr<?> expr) {
		return add(new OrderByItem(expr, OrderByItem.Direction.asc));
	}

	OrderBy add(OrderByItem orderByItem);

	static OrderBy empty() {
		return new OrderByImpl();
	}

	static OrderBy createAsc(DExpr<?> expr) {
		return empty().addAsc(expr);
	}

	static OrderBy createDesc(DExpr<?> expr) {
		return empty().addDesc(expr);
	}
}
