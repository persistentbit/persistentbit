package com.persistentbit.sql.dsl.postgres.rt.windowover;

import com.persistentbit.sql.dsl.assql.SqlConvertible;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.orderby.OrderBy;
import com.persistentbit.sql.dsl.postgres.rt.windowover.impl.WindowOverImpl;

/**
 * TODOC
 *
 * @author petermuys
 * @since 7/01/18
 */
public interface WindowOver extends SqlConvertible{

	WindowOver partitionBy(DExpr<?>... exprList);

	WindowOver orderBy(OrderBy orderBy);

	WindowOver frame(Frame frame);

	static WindowOver empty() {
		return new WindowOverImpl();
	}
}
