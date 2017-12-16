package com.persistentbit.sql.dsl.generic.expressions.impl.time;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprBoolean;
import com.persistentbit.sql.dsl.generic.expressions.DExprTime;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.utils.rowreader.RowReader;
import com.persistentbit.utils.exceptions.ToDo;

import java.time.LocalTime;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public abstract class DLocalTimeAbstract implements DImpl<LocalTime>, DExprTime{



	@Override
	public DExprBoolean eq(DExpr<LocalTime> other) {
		throw new ToDo();
	}

	@Override
	public DExprBoolean eq(LocalTime other) {
		return eq(new DLocalTimeValue(other));
	}

	@Override
	public DExprBoolean notEq(DExpr<LocalTime> other) {
		throw new ToDo();
	}

	@Override
	public DExprBoolean notEq(LocalTime other) {
		return notEq(new DLocalTimeValue(other));
	}

	@Override
	public DExprBoolean lt(DExpr<LocalTime> other) {
		throw new ToDo();
	}

	@Override
	public DExprBoolean lt(LocalTime other) {
		return lt(new DLocalTimeValue(other));
	}

	@Override
	public DExprBoolean gt(DExpr<LocalTime> other) {
		throw new ToDo();
	}

	@Override
	public DExprBoolean gt(LocalTime other) {
		return gt(new DLocalTimeValue(other));
	}

	@Override
	public DExprBoolean ltEq(DExpr<LocalTime> other) {
		throw new ToDo();
	}

	@Override
	public DExprBoolean ltEq(LocalTime other) {
		return ltEq(new DLocalTimeValue(other));
	}

	@Override
	public DExprBoolean gtEq(DExpr<LocalTime> other) {
		throw new ToDo();
	}

	@Override
	public DExprBoolean gtEq(LocalTime other) {
		return gtEq(new DLocalTimeValue(other));
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
	public LocalTime _read(DbSqlContext context, RowReader rowReader
	) {
		return rowReader.readNext(LocalTime.class);
	}

	@Override
	public DExprTime _withAlias(String alias) {
		return new DLocalTimeAlias(alias, this);
	}

	@Override
	public PList<DExpr> _expand() {
		return PList.val(this);
	}
}
