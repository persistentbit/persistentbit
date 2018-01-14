package com.persistentbit.sql.dsl.postgres.rt.statements;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.postgres.rt.statements.impl.PgSelectFor;
import com.persistentbit.sql.dsl.statements.select.Join;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.tables.Table;

/**
 * TODOC
 *
 * @author petermuys
 * @since 14/01/18
 */
public interface PgQuery extends Query{

	@Override
	PgQuery groupBy(DExpr... expr);

	@Override
	PgQuery groupByRollup(DExpr... expr);

	@Override
	PgQuery groupByCube(DExpr... expr);

	@Override
	PgQuery having(EBool condition);

	@Override
	PgQuery orderByDesc(DExpr<?> expr);

	@Override
	PgQuery orderByAsc(DExpr<?> expr);

	@Override
	Join leftJoin(Table table);

	@Override
	Join rightJoin(Table table);

	@Override
	Join innerJoin(Table table);

	@Override
	Join fullJoin(Table table);

	@Override
	PgQuery where(EBool whereExpr);

	@Override
	PgQuery limit(ELong limit);

	@Override
	PgQuery limitAndOffset(ELong limit, ELong offset);

	@Override
	PgQuery limit(long limit);

	@Override
	PgQuery limitAndOffset(long limit, long offset);

	@Override
	PgQuery distinct();


	PgSelectFor forUpdate();

	PgSelectFor forNoKeyUpdate();

	PgSelectFor forShare();

	PgSelectFor forKeyShare();
}
