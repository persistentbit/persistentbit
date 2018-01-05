package com.persistentbit.sql.dsl.expressions.impl.typeimpl.numbers;

import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.dsl.expressions.impl.BinOpOperator;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.expressions.impl.strategies.TypeStrategy;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeFactory;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class EByteTypeFactory extends AbstractTypeFactory<EByte, Byte>{

	public EByteTypeFactory(ExprContext context) {
		super(context);
	}


	@Override
	public Class<EByte> getTypeClass() {
		return EByte.class;
	}

	@Override
	public EByte buildWithStrategy(TypeStrategy<Byte> strategy
	) {
		return new EByteImpl(strategy);
	}

	@Override
	public Class getValueClass() {
		return Byte.class;
	}

	private class EByteImpl
		extends AbstractNumberTypeImpl<EByte, EShort, EInt, ELong, EFloat, EDouble, EBigDecimal, EByte, EByte, Byte>
		implements EByte{


		private EByteImpl(TypeStrategy typeStrategy) {
			super(typeStrategy);
		}


		@Override
		public ExprTypeJdbcConvert<Byte> getJdbcConverter() {
			return context.getJavaJdbcConverter(Byte.class);
		}

		@Override
		public AbstractTypeFactory<EByte, Byte> getTypeFactory() {
			return EByteTypeFactory.this;
		}

		@Override
		protected EByte beBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EByte.class).buildBinOp(this, op, other);
		}

		@Override
		protected EShort seBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EShort.class).buildBinOp(this, op, other);
		}

		@Override
		protected EInt ieBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EInt.class).buildBinOp(this, op, other);
		}

		@Override
		protected ELong leBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(ELong.class).buildBinOp(this, op, other);
		}

		@Override
		protected EFloat feBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EFloat.class).buildBinOp(this, op, other);
		}

		@Override
		protected EDouble deBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EDouble.class).buildBinOp(this, op, other);
		}

		@Override
		protected EBigDecimal bdeBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EBigDecimal.class).buildBinOp(this, op, other);
		}

		@Override
		protected EByte nBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EByte.class).buildBinOp(this, op, other);
		}

		@Override
		protected EByte val(Byte value) {
			return EByteTypeFactory.this.buildVal(value);
		}

	}
}
