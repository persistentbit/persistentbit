package com.persistentbit.sql.dsl.expressions.impl.typeimpl.numbers;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EBigDecimal;
import com.persistentbit.sql.dsl.expressions.EDouble;
import com.persistentbit.sql.dsl.expressions.EFloat;
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
public class EFloatTypeFactory extends AbstractTypeFactory<EFloat, Float>{

	public EFloatTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	public Class<EFloat> getTypeClass() {
		return EFloat.class;
	}

	@Override
	public Class getValueClass() {
		return Float.class;
	}

	@Override
	public EFloat buildWithStrategy(TypeStrategy<Float> strategy) {
		return new EFloatImpl(strategy);
	}

	private class EFloatImpl extends
							 AbstractNumberTypeImpl<EFloat, EFloat, EFloat, EFloat, EFloat, EDouble, EBigDecimal, EFloat, EFloat, Float>
		implements EFloat{


		public EFloatImpl(TypeStrategy<Float> strategy) {
			super(strategy);
		}



		@Override
		public ExprTypeJdbcConvert<Float> getJdbcConverter() {
			return context.getJavaJdbcConverter(Float.class);
		}

		@Override
		public AbstractTypeFactory<EFloat, Float> getTypeFactory() {
			return EFloatTypeFactory.this;
		}

		@Override
		protected EFloat beBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EFloat.class).buildBinOp(this, op, other);
		}

		@Override
		protected EFloat seBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EFloat.class).buildBinOp(this, op, other);
		}

		@Override
		protected EFloat ieBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EFloat.class).buildBinOp(this, op, other);
		}

		@Override
		protected EFloat leBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EFloat.class).buildBinOp(this, op, other);
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
		protected EFloat nBinOp(BinOpOperator op, DExpr other) {
			return context.getTypeFactory(EFloat.class).buildBinOp(this, op, other);
		}

		@Override
		protected EFloat val(Float value) {
			return buildVal(value);
		}

	}
}
