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
public class EShortTypeFactory extends AbstractTypeFactory<EShort,Short>{

	public EShortTypeFactory(ExprContext context) {
		super(context);
	}


	@Override
	public Class<EShort> getTypeClass() {
		return EShort.class;
	}

	@Override
	public Class getValueClass() {
		return Short.class;
	}

	@Override
	public EShort buildWithStrategy(TypeStrategy<Short> strategy) {
		return new EShortImpl(strategy);
	}
	private class EShortImpl extends AbstractNumberTypeImpl<EShort,EShort,EInt,ELong,EFloat,EDouble,EBigDecimal,EShort,EShort,Short> implements EShort{


		public EShortImpl(TypeStrategy<Short> typeStrategy) {
			super(typeStrategy);
		}



		@Override
		public ExprTypeJdbcConvert<Short> getJdbcConverter() {
			return context.getJavaJdbcConverter(Short.class);
		}

		@Override
		public AbstractTypeFactory<EShort, Short> getTypeFactory() {
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