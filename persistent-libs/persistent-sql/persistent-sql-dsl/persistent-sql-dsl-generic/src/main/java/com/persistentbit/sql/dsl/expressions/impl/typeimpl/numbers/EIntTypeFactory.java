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
public class EIntTypeFactory extends AbstractTypeFactory<EInt, Integer>{

	public EIntTypeFactory(ExprContext context) {
		super(context);
	}


	@Override
	public Class<EInt> getTypeClass() {
		return EInt.class;
	}

	@Override
	public EInt buildWithStrategy(TypeStrategy<Integer> strategy
	) {
		return new EIntImpl(strategy);
	}

	@Override
	public Class getValueClass() {
		return Integer.class;
	}

	private class EIntImpl
		extends AbstractNumberTypeImpl<EInt, EInt, EInt, ELong, EFloat, EDouble, EBigDecimal, EInt, EInt, Integer>
		implements EInt{


		public EIntImpl(TypeStrategy<Integer> typeStrategy) {
			super(typeStrategy);
		}

		@Override
		public ExprTypeJdbcConvert<Integer> getJdbcConverter() {
			return context.getJavaJdbcConverter(Integer.class);
		}

		@Override
		public AbstractTypeFactory<EInt, Integer> getTypeFactory() {
			return EIntTypeFactory.this;
		}

		@Override
		protected EInt beBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EInt.class).buildBinOp(this, op, other);
		}

		@Override
		protected EInt seBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EInt.class).buildBinOp(this, op, other);
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
		protected EInt nBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EInt.class).buildBinOp(this, op, other);
		}

		@Override
		protected EInt val(Integer value) {
			return EIntTypeFactory.this.buildVal(value);
		}

	}
}
