package com.persistentbit.sql.dsl.generic.expressions.impl.experiment.typeimpl.numbers;

import com.persistentbit.sql.dsl.generic.expressions.*;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.AbstractTypeFactory;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.BinOpOperator;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.ExprContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.strategies.TypeStrategy;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class EFloatTypeFactory extends AbstractTypeFactory<EFloat,Float>{

	public EFloatTypeFactory(ExprContext context,
							Class<EFloat> typeClass,
							ExprTypeJdbcConvert<Float> jdbcConvert
	) {
		super(context, typeClass, jdbcConvert);
	}

	@Override
	protected EFloat buildWithStrategy(TypeStrategy<Float> strategy
	) {
		return new EFloatImpl(context, EInt.class, strategy);
	}
	private class EFloatImpl extends AbstractNumberTypeImpl<EFloat,EFloat,EFloat,EFloat,EFloat,EDouble,EBigDecimal,EFloat,EFloat,Float> implements EFloat{


		public EFloatImpl(ExprContext context, Class typeClass,
						 TypeStrategy typeStrategy
		) {
			super(context, typeClass, typeStrategy);
		}


		@Override
		protected EFloat beBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EFloat.class).buildBinOp(this,op,other);
		}

		@Override
		protected EFloat seBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EFloat.class).buildBinOp(this,op,other);
		}

		@Override
		protected EFloat ieBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EFloat.class).buildBinOp(this,op,other);
		}

		@Override
		protected EFloat leBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EFloat.class).buildBinOp(this,op,other);
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
		protected EFloat nBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EFloat.class).buildBinOp(this,op,other);
		}

		@Override
		protected EFloat val(Float value) {
			return buildVal(value);
		}

	}
}
