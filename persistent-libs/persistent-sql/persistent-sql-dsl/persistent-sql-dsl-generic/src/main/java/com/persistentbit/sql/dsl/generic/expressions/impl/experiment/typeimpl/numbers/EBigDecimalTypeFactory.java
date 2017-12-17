package com.persistentbit.sql.dsl.generic.expressions.impl.experiment.typeimpl.numbers;

import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.EBigDecimal;
import com.persistentbit.sql.dsl.generic.expressions.EInt;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.AbstractTypeFactory;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.BinOpOperator;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.ExprContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.strategies.TypeStrategy;

import java.math.BigDecimal;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */


public class EBigDecimalTypeFactory extends AbstractTypeFactory<EBigDecimal, BigDecimal>{

	public EBigDecimalTypeFactory(ExprContext context) {
		super(context, EBigDecimal.class, context.getJavaJdbcConverter(BigDecimal.class));
	}

	@Override
	protected EBigDecimal buildWithStrategy(TypeStrategy<BigDecimal> strategy
	) {
		return new EBigDecimalImpl(context, EInt.class, strategy);
	}

	private class EBigDecimalImpl extends
		AbstractNumberTypeImpl<EBigDecimal, EBigDecimal, EBigDecimal, EBigDecimal, EBigDecimal, EBigDecimal, EBigDecimal, EBigDecimal, EBigDecimal, BigDecimal>
		implements EBigDecimal{


		public EBigDecimalImpl(ExprContext context, Class typeClass,
							   TypeStrategy typeStrategy
		) {
			super(context, typeClass, typeStrategy);
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
