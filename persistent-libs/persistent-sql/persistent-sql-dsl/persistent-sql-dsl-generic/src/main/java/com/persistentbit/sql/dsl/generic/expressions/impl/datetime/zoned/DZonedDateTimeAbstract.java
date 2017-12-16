package com.persistentbit.sql.dsl.generic.expressions.impl.datetime.zoned;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprBoolean;
import com.persistentbit.sql.dsl.generic.expressions.DExprDateTime;
import com.persistentbit.sql.dsl.generic.expressions.DExprZonedDateTime;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.utils.rowreader.RowReader;
import com.persistentbit.utils.exceptions.ToDo;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/11/17
 */
public abstract class DZonedDateTimeAbstract implements DImpl<ZonedDateTime>, DExprZonedDateTime{



	@Override
	public DExprBoolean eq(DExpr<ZonedDateTime> other) {
		throw new ToDo();
	}

	@Override
	public DExprBoolean eq(ZonedDateTime other) {
		return eq(new DZonedDateTimeValue(other));
	}

	@Override
	public DExprBoolean notEq(DExpr<ZonedDateTime> other) {
		throw new ToDo();
	}

	@Override
	public DExprBoolean notEq(ZonedDateTime other) {
		return notEq(new DZonedDateTimeValue(other));
	}

	@Override
	public DExprBoolean lt(DExpr<ZonedDateTime> other) {
		throw new ToDo();
	}

	@Override
	public DExprBoolean lt(ZonedDateTime other) {
		return lt(new DZonedDateTimeValue(other));
	}

	@Override
	public DExprBoolean gt(DExpr<ZonedDateTime> other) {
		throw new ToDo();
	}

	@Override
	public DExprBoolean gt(ZonedDateTime other) {
		return gt(new DZonedDateTimeValue(other));
	}

	@Override
	public DExprBoolean ltEq(DExpr<ZonedDateTime> other) {
		throw new ToDo();
	}

	@Override
	public DExprBoolean ltEq(ZonedDateTime other) {
		return ltEq(new DZonedDateTimeValue(other));
	}

	@Override
	public DExprBoolean gtEq(DExpr<ZonedDateTime> other) {
		throw new ToDo();
	}

	@Override
	public DExprBoolean gtEq(ZonedDateTime other) {
		return gtEq(new DZonedDateTimeValue(other));
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
	public DExprBoolean in(PList<DExpr<ZonedDateTime>> values
	) {
		throw new ToDo();
	}

	@Override
	public DExprBoolean in(DExpr<ZonedDateTime>... values) {
		throw new ToDo();
	}

	@Override
	public ZonedDateTime _read(DbSqlContext context, RowReader rowReader
	) {
		return rowReader.readNext(ZonedDateTime.class);
	}

	@Override
	public DExprZonedDateTime _withAlias(String alias) {
		return new DZonedDateTimeAlias(alias, this);
	}

	@Override
	public PList<DExpr> _expand() {
		return PList.val(this);
	}
}
