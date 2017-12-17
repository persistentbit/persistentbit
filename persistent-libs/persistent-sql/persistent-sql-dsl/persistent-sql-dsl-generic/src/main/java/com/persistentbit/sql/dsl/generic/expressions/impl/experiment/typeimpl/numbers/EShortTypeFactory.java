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
public class EShortTypeFactory extends AbstractTypeFactory<EShort,Short>{

	public EShortTypeFactory(ExprContext context) {
		super(context, EShort.class, context.getJavaJdbcConverter(Short.class));
	}

	@Override
	protected EShort buildWithStrategy(TypeStrategy<Short> strategy
	) {
		return new EShortImpl(context, EShort.class, strategy);
	}
	private class EShortImpl extends AbstractNumberTypeImpl<EShort,EShort,EInt,ELong,EFloat,EDouble,EBigDecimal,EShort,EShort,Short> implements EShort{


		public EShortImpl(ExprContext context, Class typeClass,
						 TypeStrategy typeStrategy
		) {
			super(context, typeClass, typeStrategy);
		}


		@Override
		protected EShort beBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EShort.class).buildBinOp(this,op,other);
		}

		@Override
		protected EShort seBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EShort.class).buildBinOp(this,op,other);
		}

		@Override
		protected EInt ieBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EInt.class).buildBinOp(this,op,other);
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
		protected EShort nBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EShort.class).buildBinOp(this,op,other);
		}

		@Override
		protected EShort val(Short value) {
			return EShortTypeFactory.this.buildVal(value);
		}

	}
}