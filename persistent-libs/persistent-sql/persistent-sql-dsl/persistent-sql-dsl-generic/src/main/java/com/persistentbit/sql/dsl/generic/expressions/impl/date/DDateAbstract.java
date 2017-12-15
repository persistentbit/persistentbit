package com.persistentbit.sql.dsl.generic.expressions.impl.date;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprBoolean;
import com.persistentbit.sql.dsl.generic.expressions.DExprDate;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.utils.rowreader.RowReader;
import com.persistentbit.utils.exceptions.ToDo;

import java.time.LocalDate;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public abstract class DDateAbstract implements DImpl<LocalDate>, DExprDate{



	@Override
	public DExprBoolean eq(DExpr<LocalDate> other) {
		throw new ToDo();
	}

	@Override
	public DExprBoolean eq(LocalDate other) {
		return eq(new DDateValue(other));
	}

	@Override
	public DExprBoolean notEq(DExpr<LocalDate> other) {
		throw new ToDo();
	}

	@Override
	public DExprBoolean notEq(LocalDate other) {
		return notEq(new DDateValue(other));
	}

	@Override
	public DExprBoolean lt(DExpr<LocalDate> other) {
		throw new ToDo();
	}

	@Override
	public DExprBoolean lt(LocalDate other) {
		return lt(new DDateValue(other));
	}

	@Override
	public DExprBoolean gt(DExpr<LocalDate> other) {
		throw new ToDo();
	}

	@Override
	public DExprBoolean gt(LocalDate other) {
		return gt(new DDateValue(other));
	}

	@Override
	public DExprBoolean ltEq(DExpr<LocalDate> other) {
		throw new ToDo();
	}

	@Override
	public DExprBoolean ltEq(LocalDate other) {
		return ltEq(new DDateValue(other));
	}

	@Override
	public DExprBoolean gtEq(DExpr<LocalDate> other) {
		throw new ToDo();
	}

	@Override
	public DExprBoolean gtEq(LocalDate other) {
		return gtEq(new DDateValue(other));
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
	public LocalDate _read(DbSqlContext context, RowReader rowReader
	) {
		return rowReader.readNext(LocalDate.class);
	}

	@Override
	public DExprDate _withAlias(String alias) {
		return new DDateAlias(alias,this);
	}

	@Override
	public PList<DExpr> _expand() {
		return PList.val(this);
	}
}
