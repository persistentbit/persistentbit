package com.persistentbit.sql.dsl.generic.expressions.impl.typeimpl.numbers;

import com.persistentbit.sql.dsl.generic.expressions.*;
import com.persistentbit.sql.dsl.generic.expressions.impl.typeimpl.AbstractTypeFactory;
import com.persistentbit.sql.dsl.generic.expressions.impl.BinOpOperator;
import com.persistentbit.sql.dsl.generic.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.ExprTypeFactory;
import com.persistentbit.sql.dsl.generic.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.generic.expressions.impl.strategies.TypeStrategy;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class ELongTypeFactory extends AbstractTypeFactory<ELong, Long>{

	public ELongTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected ExprTypeJdbcConvert doGetJdbcConverter() {
		return context.getJavaJdbcConverter(Long.class);
	}

	@Override
	public Class<? extends DExpr<Long>> getTypeClass() {
		return ELong.class;
	}

	@Override
	protected ELong buildWithStrategy(TypeStrategy<Long> strategy) {
		return new ELongImpl(strategy);
	}

	private class ELongImpl
		extends AbstractNumberTypeImpl<ELong, ELong, ELong, ELong, EFloat, EDouble, EBigDecimal, ELong, ELong, Long>
		implements ELong{


		public ELongImpl(TypeStrategy<Long> typeStrategy) {
			super(typeStrategy);
		}

		@Override
		public ExprTypeFactory<ELong, Long> getTypeFactory() {
			return ELongTypeFactory.this;
		}

		@Override
		protected ELong beBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(ELong.class).buildBinOp(this, op, other);
		}

		@Override
		protected ELong seBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(ELong.class).buildBinOp(this, op, other);
		}

		@Override
		protected ELong ieBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(ELong.class).buildBinOp(this, op, other);
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
		protected ELong nBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(ELong.class).buildBinOp(this, op, other);
		}

		@Override
		protected ELong val(Long value) {
			return buildVal(value);
		}

	}
}
