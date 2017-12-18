package com.persistentbit.sql.dsl.generic.expressions.impl.typeimpl.numbers;

import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.EBigDecimal;
import com.persistentbit.sql.dsl.generic.expressions.EDouble;
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
public class EDoubleTypeFactory extends AbstractTypeFactory<EDouble, Double>{

	public EDoubleTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected ExprTypeJdbcConvert doGetJdbcConverter() {
		return context.getJavaJdbcConverter(Double.class);
	}

	@Override
	public Class<? extends DExpr<Double>> getTypeClass() {
		return EDouble.class;
	}

	@Override
	protected EDouble buildWithStrategy(TypeStrategy<Double> strategy) {
		return new EDoubleImpl(strategy);
	}

	private class EDoubleImpl extends
							  AbstractNumberTypeImpl<EDouble, EDouble, EDouble, EDouble, EDouble, EDouble, EBigDecimal, EDouble, EDouble, Double>
		implements EDouble{


		public EDoubleImpl(TypeStrategy<Double> typeStrategy) {
			super(typeStrategy);
		}

		@Override
		public ExprTypeFactory<EDouble, Double> getTypeFactory() {
			return EDoubleTypeFactory.this;
		}

		@Override
		protected EDouble beBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EDouble.class).buildBinOp(this, op, other);
		}

		@Override
		protected EDouble seBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EDouble.class).buildBinOp(this, op, other);
		}

		@Override
		protected EDouble ieBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EDouble.class).buildBinOp(this, op, other);
		}

		@Override
		protected EDouble leBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EDouble.class).buildBinOp(this, op, other);
		}

		@Override
		protected EDouble feBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EDouble.class).buildBinOp(this, op, other);
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
		protected EDouble nBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EDouble.class).buildBinOp(this, op, other);
		}

		@Override
		protected EDouble val(Double value) {
			return buildVal(value);
		}

	}
}
