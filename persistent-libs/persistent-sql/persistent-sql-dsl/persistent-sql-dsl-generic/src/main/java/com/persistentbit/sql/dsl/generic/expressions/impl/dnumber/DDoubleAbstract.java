package com.persistentbit.sql.dsl.generic.expressions.impl.dnumber;

import com.persistentbit.sql.dsl.generic.expressions.*;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/11/17
 */
public abstract class DDoubleAbstract extends DNumberAbstract<Double> implements DExprDouble{

	@Override
	DExprDouble _value(Double value) {
		return new DDoubleValue(value);
	}

	@Override
	public DExprDouble add(DExprByte other) {
		return new DDoubleBinOp(this,NumberBinOperator.add,other);
	}

	@Override
	public DExprDouble add(DExprInt other) {
		return new DDoubleBinOp(this,NumberBinOperator.add,other);
	}

	@Override
	public DExprDouble add(DExprShort other) {
		return new DDoubleBinOp(this,NumberBinOperator.add,other);
	}

	@Override
	public DExprDouble add(DExprLong other) {
		return new DDoubleBinOp(this,NumberBinOperator.add,other);
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
	public DExprDouble sub(DExprByte other) {
		return new DDoubleBinOp(this,NumberBinOperator.sub,other);
	}

	@Override
	public DExprDouble sub(DExprInt other) {
		return new DDoubleBinOp(this,NumberBinOperator.sub,other);
	}

	@Override
	public DExprDouble sub(DExprShort other) {
		return new DDoubleBinOp(this,NumberBinOperator.sub,other);
	}

	@Override
	public DExprDouble sub(DExprLong other) {
		return new DDoubleBinOp(this,NumberBinOperator.sub,other);
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
	public DExprDouble div(DExprByte other) {
		return new DDoubleBinOp(this,NumberBinOperator.div,other);
	}

	@Override
	public DExprDouble div(DExprInt other) {
		return new DDoubleBinOp(this,NumberBinOperator.div,other);
	}

	@Override
	public DExprDouble div(DExprShort other) {
		return new DDoubleBinOp(this,NumberBinOperator.div,other);
	}

	@Override
	public DExprDouble div(DExprLong other) {
		return new DDoubleBinOp(this,NumberBinOperator.div,other);
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
	public DExprDouble mul(DExprByte other) {
		return new DDoubleBinOp(this,NumberBinOperator.mul,other);
	}

	@Override
	public DExprDouble mul(DExprInt other) {
		return new DDoubleBinOp(this,NumberBinOperator.mul,other);
	}

	@Override
	public DExprDouble mul(DExprShort other) {
		return new DDoubleBinOp(this,NumberBinOperator.mul,other);
	}

	@Override
	public DExprDouble mul(DExprLong other) {
		return new DDoubleBinOp(this,NumberBinOperator.mul,other);
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
	public DExprDouble add(double value) {
		return add(_value(value));
	}

	@Override
	public DExprDouble sub(double value) {
		return add(_value(value));
	}

	@Override
	public DExprDouble div(double value) {
		return add(_value(value));
	}

	@Override
	public DExprDouble mul(double value) {
		return add(_value(value));
	}
}
