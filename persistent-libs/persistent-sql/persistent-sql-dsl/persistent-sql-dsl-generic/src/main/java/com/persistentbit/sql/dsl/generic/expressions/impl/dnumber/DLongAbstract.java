package com.persistentbit.sql.dsl.generic.expressions.impl.dnumber;

import com.persistentbit.sql.dsl.generic.expressions.*;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/11/17
 */
public abstract class DLongAbstract extends DNumberAbstract<Long> implements DExprLong{

	@Override
	DExprLong _value(Long value) {
		return new DLongValue(value);
	}

	//--------- ADD

	@Override
	public DExprLong add(DExprInt other) {
		return new DLongBinOp(this,NumberBinOperator.add, other);
	}

	@Override
	public DExprLong add(DExprByte other) {
		return new DLongBinOp(this,NumberBinOperator.add, other);
	}

	@Override
	public DExprLong add(DExprShort other) {
		return new DLongBinOp(this,NumberBinOperator.add, other);
	}

	@Override
	public DExprLong add(DExprLong other) {
		return new DLongBinOp(this,NumberBinOperator.add, other);
	}

	@Override
	public DExprBigDecimal add(DExprBigDecimal other) {
		return new DBigDecimalBinOp(this,NumberBinOperator.add, other);
	}

	@Override
	public DExprDouble add(DExprDouble other) {
		return new DDoubleBinOp(this,NumberBinOperator.add, other);
	}

	//--------- SUB

	@Override
	public DExprLong sub(DExprByte other) {
		return new DLongBinOp(this,NumberBinOperator.sub, other);
	}
	@Override
	public DExprLong sub(DExprInt other) {
		return new DLongBinOp(this,NumberBinOperator.sub, other);
	}

	@Override
	public DExprLong sub(DExprShort other) {
		return new DLongBinOp(this,NumberBinOperator.sub, other);
	}

	@Override
	public DExprLong sub(DExprLong other) {
		return new DLongBinOp(this,NumberBinOperator.sub, other);
	}

	@Override
	public DExprBigDecimal sub(DExprBigDecimal other) {
		return new DBigDecimalBinOp(this,NumberBinOperator.sub, other);
	}

	@Override
	public DExprDouble sub(DExprDouble other) {
		return new DDoubleBinOp(this,NumberBinOperator.sub, other);
	}

	//--------- DIV

	@Override
	public DExprLong div(DExprByte other) {
		return new DLongBinOp(this,NumberBinOperator.div, other);
	}

	@Override
	public DExprLong div(DExprInt other) {
		return new DLongBinOp(this,NumberBinOperator.div, other);
	}

	@Override
	public DExprLong div(DExprShort other) {
		return new DLongBinOp(this,NumberBinOperator.div, other);
	}

	@Override
	public DExprLong div(DExprLong other) {
		return new DLongBinOp(this,NumberBinOperator.div, other);
	}

	@Override
	public DExprBigDecimal div(DExprBigDecimal other) {
		return new DBigDecimalBinOp(this,NumberBinOperator.div, other);
	}

	@Override
	public DExprDouble div(DExprDouble other) {
		return new DDoubleBinOp(this,NumberBinOperator.div, other);
	}

	//--------- MUL

	@Override
	public DExprLong mul(DExprByte other) {
		return new DLongBinOp(this,NumberBinOperator.mul, other);
	}

	@Override
	public DExprLong mul(DExprInt other) {
		return new DLongBinOp(this,NumberBinOperator.mul, other);
	}

	@Override
	public DExprLong mul(DExprShort other) {
		return new DLongBinOp(this,NumberBinOperator.mul, other);
	}

	@Override
	public DExprLong mul(DExprLong other) {
		return new DLongBinOp(this,NumberBinOperator.mul, other);
	}

	@Override
	public DExprBigDecimal mul(DExprBigDecimal other) {
		return new DBigDecimalBinOp(this,NumberBinOperator.mul, other);
	}

	@Override
	public DExprDouble mul(DExprDouble other) {
		return new DDoubleBinOp(this,NumberBinOperator.mul, other);
	}



	@Override
	public DExprLong add(long value) {
		return add(_value(value));
	}

	@Override
	public DExprLong sub(long value) {
		return sub(_value(value));
	}

	@Override
	public DExprLong div(long value) {
		return div(_value(value));
	}

	@Override
	public DExprLong mul(long value) {
		return mul(_value(value));
	}
}
