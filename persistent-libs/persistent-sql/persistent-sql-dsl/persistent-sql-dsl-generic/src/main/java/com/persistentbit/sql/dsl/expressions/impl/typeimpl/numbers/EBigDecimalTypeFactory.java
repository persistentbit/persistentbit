package com.persistentbit.sql.dsl.expressions.impl.typeimpl.numbers;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EBigDecimal;
import com.persistentbit.sql.dsl.expressions.impl.BinOpOperator;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.expressions.impl.strategies.TypeStrategy;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeFactory;

import java.math.BigDecimal;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */


public class EBigDecimalTypeFactory extends AbstractTypeFactory<EBigDecimal, BigDecimal>{

	public EBigDecimalTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	public Class<EBigDecimal> getTypeClass() {
		return EBigDecimal.class;
	}

	@Override
	public EBigDecimal buildWithStrategy(TypeStrategy<BigDecimal> strategy) {
		return new EBigDecimalImpl(strategy);
	}

	@Override
	public Class getValueClass() {
		return BigDecimal.class;
	}

	private class EBigDecimalImpl
		extends
		AbstractNumberTypeImpl<EBigDecimal, EBigDecimal, EBigDecimal, EBigDecimal, EBigDecimal, EBigDecimal, EBigDecimal, EBigDecimal, EBigDecimal, BigDecimal>
		implements EBigDecimal{


		public EBigDecimalImpl(TypeStrategy<BigDecimal> strategy) {
			super(strategy);
		}



		@Override
		public ExprTypeJdbcConvert<BigDecimal> getJdbcConverter() {
			return context.getJavaJdbcConverter(BigDecimal.class);
		}

		@Override
		public AbstractTypeFactory<EBigDecimal, BigDecimal> getTypeFactory() {
			return EBigDecimalTypeFactory.this;
		}

		@Override
		protected EBigDecimal beBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EBigDecimal.class).buildBinOp(this, op, other);
		}

		@Override
		protected EBigDecimal seBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EBigDecimal.class).buildBinOp(this, op, other);
		}

		@Override
		protected EBigDecimal ieBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EBigDecimal.class).buildBinOp(this, op, other);
		}

		@Override
		protected EBigDecimal leBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EBigDecimal.class).buildBinOp(this, op, other);
		}

		@Override
		protected EBigDecimal feBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EBigDecimal.class).buildBinOp(this, op, other);
		}

		@Override
		protected EBigDecimal deBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EBigDecimal.class).buildBinOp(this, op, other);
		}

		@Override
		protected EBigDecimal bdeBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EBigDecimal.class).buildBinOp(this, op, other);
		}

		@Override
		protected EBigDecimal nBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EBigDecimal.class).buildBinOp(this, op, other);
		}

		@Override
		protected EBigDecimal val(BigDecimal value) {
			return buildVal(value);
		}

	}
}
