package com.persistentbit.sql.dsl.generic.expressions.impl.dnumber;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.*;
import com.persistentbit.sql.utils.rowreader.RowReader;
import com.persistentbit.utils.exceptions.ToDo;

/**
 * TODOC
 *
 * @author petermuys
 * @since 15/12/17
 */
public abstract class DFloatAbstract extends DNumberAbstract<Float> implements DExprFloat{

	@Override
	DExprFloat _value(Float value) {
		return new DFloatValue(value);
	}

	@Override
	public DExprFloat add(DExprByte other) {
		return new DFloatBinOp(this,NumberBinOperator.add,other);
	}

	@Override
	public DExprFloat add(DExprInt other) {
		return new DFloatBinOp(this,NumberBinOperator.add,other);
	}

	@Override
	public DExprFloat add(DExprShort other) {
		return new DFloatBinOp(this,NumberBinOperator.add,other);
	}

	@Override
	public DExprFloat add(DExprLong other) {
		return new DFloatBinOp(this,NumberBinOperator.add,other);
	}

	@Override
	public DExprBigDecimal add(DExprBigDecimal other) {
		return new DBigDecimalBinOp(this,NumberBinOperator.add,other);
	}

	@Override
	public DExprDouble add(DExprDouble other) {
		return new DDoubleBinOp(this,NumberBinOperator.add,other);
	}

	@Override
	public DExprFloat add(DExprFloat other) {
		return new DFloatBinOp(this,NumberBinOperator.add,other);
	}

	@Override
	public DExprFloat sub(DExprByte other) {
		return new DFloatBinOp(this,NumberBinOperator.sub,other);
	}

	@Override
	public DExprFloat sub(DExprInt other) {
		return new DFloatBinOp(this,NumberBinOperator.sub,other);
	}

	@Override
	public DExprFloat sub(DExprShort other) {
		return new DFloatBinOp(this,NumberBinOperator.sub,other);
	}

	@Override
	public DExprFloat sub(DExprLong other) {
		return new DFloatBinOp(this,NumberBinOperator.sub,other);
	}
	@Override
	public DExprFloat sub(DExprFloat other) {
		return new DFloatBinOp(this,NumberBinOperator.sub,other);
	}


	@Override
	public DExprBigDecimal sub(DExprBigDecimal other) {
		return new DBigDecimalBinOp(this,NumberBinOperator.sub,other);
	}

	@Override
	public DExprDouble sub(DExprDouble other) {
		return new DDoubleBinOp(this,NumberBinOperator.sub,other);
	}

	@Override
	public DExprFloat div(DExprByte other) {
		return new DFloatBinOp(this,NumberBinOperator.div,other);
	}

	@Override
	public DExprFloat div(DExprInt other) {
		return new DFloatBinOp(this,NumberBinOperator.div,other);
	}

	@Override
	public DExprFloat div(DExprShort other) {
		return new DFloatBinOp(this,NumberBinOperator.div,other);
	}

	@Override
	public DExprFloat div(DExprLong other) {
		return new DFloatBinOp(this,NumberBinOperator.div,other);
	}

	@Override
	public DExprBigDecimal div(DExprBigDecimal other) {
		return new DBigDecimalBinOp(this,NumberBinOperator.div,other);
	}

	@Override
	public DExprDouble div(DExprDouble other) {
		return new DDoubleBinOp(this,NumberBinOperator.div,other);
	}
	@Override
	public DExprFloat div(DExprFloat other) {
		return new DFloatBinOp(this,NumberBinOperator.div,other);
	}

	@Override
	public DExprFloat mul(DExprByte other) {
		return new DFloatBinOp(this,NumberBinOperator.mul,other);
	}

	@Override
	public DExprFloat mul(DExprInt other) {
		return new DFloatBinOp(this,NumberBinOperator.mul,other);
	}

	@Override
	public DExprFloat mul(DExprShort other) {
		return new DFloatBinOp(this,NumberBinOperator.mul,other);
	}

	@Override
	public DExprFloat mul(DExprLong other) {
		return new DFloatBinOp(this,NumberBinOperator.mul,other);
	}

	@Override
	public DExprBigDecimal mul(DExprBigDecimal other) {
		return new DBigDecimalBinOp(this,NumberBinOperator.mul,other);
	}

	@Override
	public DExprDouble mul(DExprDouble other) {
		return new DDoubleBinOp(this,NumberBinOperator.mul,other);
	}
	@Override
	public DExprFloat mul(DExprFloat other) {
		return new DFloatBinOp(this,NumberBinOperator.mul,other);
	}
	@Override
	public DExprFloat add(float value) {
		return add(_value(value));
	}

	@Override
	public DExprFloat sub(float value) {
		return add(_value(value));
	}

	@Override
	public DExprFloat div(float value) {
		return add(_value(value));
	}

	@Override
	public DExprFloat mul(float value) {
		return add(_value(value));
	}

	@Override
	public Float _read(DbSqlContext context, RowReader rowReader
	) {
		return rowReader.readNext(Float.class);
	}
	@Override
	public DExprFloat _withAlias(String alias) {
		throw new ToDo();
	}
}
