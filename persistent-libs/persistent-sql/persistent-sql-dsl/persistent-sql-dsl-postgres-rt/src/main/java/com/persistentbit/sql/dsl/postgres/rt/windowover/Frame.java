package com.persistentbit.sql.dsl.postgres.rt.windowover;

import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.assql.SqlConvertibleImpl;

/**
 * TODOC
 *
 * @author petermuys
 * @since 7/01/18
 */
public interface Frame extends SqlConvertibleImpl{

	static Frame empty() {
		return context -> SqlWithParams.empty;
	}

	static Frame rows(FrameStart start) {
		return context -> SqlWithParams.sql("ROWS ").add(context.toSql(start));
	}

	static Frame rowsBetween(FrameStart start, FrameEnd end) {
		return context -> SqlWithParams.sql("ROWS BETWEEN ")
			.add(context.toSql(start))
			.add(" AND ")
			.add(context.toSql(end));
	}

	static Frame range(FrameStart start) {
		return context -> SqlWithParams.sql("RANGE ").add(context.toSql(start));
	}

	static Frame rangeBetween(FrameStart start, FrameEnd end) {
		return context -> SqlWithParams.sql("RANGE BETWEEN ")
			.add(context.toSql(start))
			.add(" AND ")
			.add(context.toSql(end));
	}
}
