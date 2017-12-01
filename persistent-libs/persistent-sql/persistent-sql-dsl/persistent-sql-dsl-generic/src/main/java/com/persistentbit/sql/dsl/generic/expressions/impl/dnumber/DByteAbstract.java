package com.persistentbit.sql.dsl.generic.expressions.impl.dnumber;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.*;
import com.persistentbit.sql.utils.rowreader.RowReader;
import com.persistentbit.utils.exceptions.ToDo;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/11/17
 */
public abstract class DByteAbstract extends DNumberAbstract<Byte> implements DExprByte{

	@Override
	DExprByte _value(Byte value) {
		return new DByteValue(value);
	}



	//------ ADD
	@Override
	public DExprInt add(DExprByte other) {
		return new DIntBinOp(this, NumberBinOperator.add, other);
	}

	@Override
	public DExprInt add(DExprInt other) {
		return new DIntBinOp(this, NumberBinOperator.add, other);
	}

	@Override
	public DExprInt add(DExprShort other) {
		return new DIntBinOp(this, NumberBinOperator.add, other);
	}

	@Override
	public DExprLong add(DExprLong other) {
		return new DLongBinOp(this, NumberBinOperator.add, other);
	}


	@Override
	public DExprBigDecimal add(DExprBigDecimal other) {
		return new DBigDecimalBinOp(this, NumberBinOperator.add, other);
	}

	@Override
	public DExprDouble add(DExprDouble other) {
		return new DDoubleBinOp(this, NumberBinOperator.add, other);
	}


	//------ SUB
	@Override
	public DExprInt sub(DExprByte other) {
		return new DIntBinOp(this, NumberBinOperator.sub, other);
	}

	@Override
	public DExprInt sub(DExprInt other) {
		return new DIntBinOp(this, NumberBinOperator.sub, other);
	}

	@Override
	public DExprInt sub(DExprShort other) {
		return new DIntBinOp(this, NumberBinOperator.sub, other);
	}

	@Override
	public DExprLong sub(DExprLong other) {
		return new DLongBinOp(this, NumberBinOperator.sub, other);
	}


	@Override
	public DExprBigDecimal sub(DExprBigDecimal other) {
		return new DBigDecimalBinOp(this, NumberBinOperator.sub, other);
	}

	@Override
	public DExprDouble sub(DExprDouble other) {
		return new DDoubleBinOp(this, NumberBinOperator.sub, other);
	}


	//------ DIV
	@Override
	public DExprInt div(DExprByte other) {
		return new DIntBinOp(this, NumberBinOperator.div, other);
	}

	@Override
	public DExprInt div(DExprInt other) {
		return new DIntBinOp(this, NumberBinOperator.div, other);
	}

	@Override
	public DExprInt div(DExprShort other) {
		return new DIntBinOp(this, NumberBinOperator.div, other);
	}

	@Override
	public DExprLong div(DExprLong other) {
		return new DLongBinOp(this, NumberBinOperator.div, other);
	}


	@Override
	public DExprBigDecimal div(DExprBigDecimal other) {
		return new DBigDecimalBinOp(this, NumberBinOperator.div, other);
	}

	@Override
	public DExprDouble div(DExprDouble other) {
		return new DDoubleBinOp(this, NumberBinOperator.div, other);
	}

	//------ MUL
	@Override
	public DExprInt mul(DExprByte other) {
		return new DIntBinOp(this, NumberBinOperator.mul, other);
	}

	@Override
	public DExprInt mul(DExprInt other) {
		return new DIntBinOp(this, NumberBinOperator.mul, other);
	}

	@Override
	public DExprInt mul(DExprShort other) {
		return new DIntBinOp(this, NumberBinOperator.mul, other);
	}

	@Override
	public DExprLong mul(DExprLong other) {
		return new DLongBinOp(this, NumberBinOperator.mul, other);
	}

	@Override
	public DExprBigDecimal mul(DExprBigDecimal other) {
		return new DBigDecimalBinOp(this, NumberBinOperator.mul, other);
	}

	@Override
	public DExprDouble mul(DExprDouble other) {
		return new DDoubleBinOp(this, NumberBinOperator.mul, other);
	}


	@Override
	public DExprInt add(int value) {
		return add(new DIntValue(value));
	}

	@Override
	public DExprInt sub(int value) {
		return sub(new DIntValue(value));
	}

	@Override
	public DExprInt div(int value) {
		return div(new DIntValue(value));
	}

	@Override
	public DExprInt mul(int value) {
		return mul(new DIntValue(value));
	}

	@Override
	public Byte _read(DbSqlContext context, RowReader rowReader
	) {
		return rowReader.readNext(Byte.class);
	}

	@Override
	public DExprByte _withAlias(String alias) {
		throw new ToDo();
	}
}
