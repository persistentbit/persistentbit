package com.persistentbit.sql.dsl.generic.expressions.impl.datetime;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprBoolean;
import com.persistentbit.sql.dsl.generic.expressions.DExprDateTime;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.utils.rowreader.RowReader;
import com.persistentbit.utils.exceptions.ToDo;

import java.time.LocalDateTime;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public class DDateTimeAbstract extends DImpl<LocalDateTime> implements DExprDateTime{



	@Override
	public DExprBoolean eq(DExpr<LocalDateTime> other) {
		throw new ToDo();
	}

	@Override
	public DExprBoolean eq(LocalDateTime other) {
		return eq(new DDateTimeValue(other));
	}

	@Override
	public DExprBoolean notEq(DExpr<LocalDateTime> other) {
		throw new ToDo();
	}

	@Override
	public DExprBoolean notEq(LocalDateTime other) {
		return notEq(new DDateTimeValue(other));
	}

	@Override
	public DExprBoolean lt(DExpr<LocalDateTime> other) {
		throw new ToDo();
	}

	@Override
	public DExprBoolean lt(LocalDateTime other) {
		return lt(new DDateTimeValue(other));
	}

	@Override
	public DExprBoolean gt(DExpr<LocalDateTime> other) {
		throw new ToDo();
	}

	@Override
	public DExprBoolean gt(LocalDateTime other) {
		return gt(new DDateTimeValue(other));
	}

	@Override
	public DExprBoolean ltEq(DExpr<LocalDateTime> other) {
		throw new ToDo();
	}

	@Override
	public DExprBoolean ltEq(LocalDateTime other) {
		return ltEq(new DDateTimeValue(other));
	}

	@Override
	public DExprBoolean gtEq(DExpr<LocalDateTime> other) {
		throw new ToDo();
	}

	@Override
	public DExprBoolean gtEq(LocalDateTime other) {
		return gtEq(new DDateTimeValue(other));
	}

	@Override
	public DExprBoolean isNull() {
		throw new ToDo();
	}

	@Override
	public DExprBoolean isNotNull() {
		throw new ToDo();
	}

	@Override
	public DExprBoolean in(PList<DExpr<LocalDateTime>> values
	) {
		throw new ToDo();
	}

	@Override
	public DExprBoolean in(DExpr<LocalDateTime>... values) {
		throw new ToDo();
	}

	@Override
	public LocalDateTime _read(DbSqlContext context, RowReader rowReader
	) {
		return rowReader.readNext(LocalDateTime.class);
	}

	@Override
	public DExprDateTime _withAlias(String alias) {
		return new DDateTimeAlias(alias,this);
	}
}
