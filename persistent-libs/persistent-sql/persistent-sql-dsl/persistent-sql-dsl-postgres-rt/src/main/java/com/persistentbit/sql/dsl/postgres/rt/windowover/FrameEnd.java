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
public interface FrameEnd extends SqlConvertibleImpl{

	static FrameEnd unboundedPreceding() {
		return context -> SqlWithParams.sql("UNBOUNDED PRECEDING");
	}

	static FrameEnd preceding(DExpr<?> expr) {
		return context -> context.toSql(expr).add(" PRECEDING");
	}

	static FrameEnd currentRow() {
		return context -> SqlWithParams.sql("CURRENT ROW");
	}

	static FrameEnd following(DExpr<?> expr) {
		return context -> context.toSql(expr).add(" FOLLOWING");
	}

	static FrameEnd unboundedFollowing() {
		return context -> SqlWithParams.sql("UNBOUNDED FOLLOWING");
	}
}
