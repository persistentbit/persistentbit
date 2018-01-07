package com.persistentbit.sql.dsl.postgres.rt.windowover;

import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.assql.SqlConvertibleImpl;
import com.persistentbit.sql.dsl.expressions.DExpr;

/**
 * TODOC
 *
 * @author petermuys
 * @since 7/01/18
 */
public interface FrameStart extends SqlConvertibleImpl{

	static FrameStart unboundedPreceding() {
		return context -> SqlWithParams.sql("UNBOUNDED PRECEDING");
	}

	static FrameStart preceding(DExpr<?> expr) {
		return context -> context.toSql(expr).add(" PRECEDING");
	}

	static FrameStart currentRow() {
		return context -> SqlWithParams.sql("CURRENT ROW");
	}

	static FrameStart following(DExpr<?> expr) {
		return context -> context.toSql(expr).add(" FOLLOWING");
	}

	static FrameStart unboundedFollowing() {
		return context -> SqlWithParams.sql("UNBOUNDED FOLLOWING");
	}
}
