package com.persistentbit.sql.dsl.generic.expressions.impl.experiment.typeimpl.numbers;

import com.persistentbit.sql.dsl.generic.expressions.*;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.AbstractTypeFactory;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.BinOpOperator;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.ExprContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.strategies.TypeStrategy;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class ELongTypeFactory extends AbstractTypeFactory<ELong,Long>{

	public ELongTypeFactory(ExprContext context) {
		super(context, ELong.class, context.getJavaJdbcConverter(Long.class));
	}

	@Override
	protected ELong buildWithStrategy(TypeStrategy<Long> strategy
	) {
		return new ELongImpl(context, EInt.class, strategy);
	}
	private class ELongImpl extends AbstractNumberTypeImpl<ELong,ELong,ELong,ELong,EFloat,EDouble,EBigDecimal,ELong,ELong,Long> implements ELong{


		public ELongImpl(ExprContext context, Class typeClass,
						TypeStrategy typeStrategy
		) {
			super(context, typeClass, typeStrategy);
		}


		@Override
		protected ELong beBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(ELong.class).buildBinOp(this,op,other);
		}

		@Override
		protected ELong seBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(ELong.class).buildBinOp(this,op,other);
		}

		@Override
		protected ELong ieBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(ELong.class).buildBinOp(this,op,other);
		}

		@Override
		protected ELong leBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(ELong.class).buildBinOp(this,op,other);
		}

		@Override
		protected EFloat feBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EFloat.class).buildBinOp(this,op,other);
		}

		@Override
		protected EDouble deBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EDouble.class).buildBinOp(this,op,other);
		}

		@Override
		protected EBigDecimal bdeBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EBigDecimal.class).buildBinOp(this,op,other);
		}

		@Override
		protected ELong nBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(ELong.class).buildBinOp(this,op,other);
		}

		@Override
		protected ELong val(Long value) {
			return buildVal(value);
		}

	}
}
