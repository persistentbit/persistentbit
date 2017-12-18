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
public class EShortTypeFactory extends AbstractTypeFactory<EShort,Short>{

	public EShortTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected ExprTypeJdbcConvert doGetJdbcConverter() {
		return context.getJavaJdbcConverter(Short.class);
	}

	@Override
	public Class<? extends DExpr<Short>> getTypeClass() {
		return EShort.class;
	}

	@Override
	protected EShort buildWithStrategy(TypeStrategy<Short> strategy) {
		return new EShortImpl(strategy);
	}
	private class EShortImpl extends AbstractNumberTypeImpl<EShort,EShort,EInt,ELong,EFloat,EDouble,EBigDecimal,EShort,EShort,Short> implements EShort{


		public EShortImpl(TypeStrategy<Short> typeStrategy) {
			super(typeStrategy);
		}

		@Override
		public ExprTypeFactory<EShort, Short> getTypeFactory() {
			return EShortTypeFactory.this;
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