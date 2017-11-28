package com.persistentbit.sql.dsl.generic.expressions.impl.dnumber;

import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExprBigDecimal;
import com.persistentbit.sql.dsl.generic.expressions.DExprNumber;
import com.persistentbit.sql.utils.rowreader.RowReader;

import java.math.BigDecimal;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/11/17
 */
public abstract class DBigDecimalAbstract extends DNumberAbstract<BigDecimal> implements DExprBigDecimal{

	@Override
	DExprBigDecimal _value(BigDecimal value) {
		return new DBigDecimalValue(value);
	}

	@Override
	public DExprBigDecimal add(DExprNumber<?> other) {
		return new DBigDecimalBinOp(this,NumberBinOperator.add,other);
	}

	@Override
	public DExprBigDecimal sub(DExprNumber<?> other) {
		return new DBigDecimalBinOp(this,NumberBinOperator.sub,other);
	}

	@Override
	public DExprBigDecimal div(DExprNumber<?> other) {
		return new DBigDecimalBinOp(this,NumberBinOperator.div,other);
	}

	@Override
	public DExprBigDecimal mul(DExprNumber<?> other) {
		return new DBigDecimalBinOp(this,NumberBinOperator.mul,other);
	}

	@Override
	public DExprBigDecimal add(BigDecimal value) {
		return add(_value(value));
	}

	@Override
	public DExprBigDecimal sub(BigDecimal value) {
		return sub(_value(value));
	}

	@Override
	public DExprBigDecimal div(BigDecimal value) {
		return div(_value(value));
	}

	@Override
	public DExprBigDecimal mul(BigDecimal value) {
		return mul(_value(value));
	}

	@Override
	public BigDecimal read(DbSqlContext context, RowReader rowReader
	) {
		return rowReader.readNext(BigDecimal.class);
	}
}
