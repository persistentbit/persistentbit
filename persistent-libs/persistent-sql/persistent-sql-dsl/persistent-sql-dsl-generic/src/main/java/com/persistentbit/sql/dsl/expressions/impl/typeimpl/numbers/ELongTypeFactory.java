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
public class ELongTypeFactory extends AbstractTypeFactory<ELong, Long>{

	public ELongTypeFactory(ExprContext context) {
		super(context);
	}


	@Override
	public Class<ELong> getTypeClass() {
		return ELong.class;
	}

	@Override
	public ELong buildWithStrategy(TypeStrategy<Long> strategy) {
		return new ELongImpl(strategy);
	}

	@Override
	public Class getValueClass() {
		return Long.class;
	}

	private class ELongImpl
		extends AbstractNumberTypeImpl<ELong, ELong, ELong, ELong, EFloat, EDouble, EBigDecimal, ELong, ELong, Long>
		implements ELong{


		public ELongImpl(TypeStrategy<Long> typeStrategy) {
			super(typeStrategy);
		}


		@Override
		public ExprTypeJdbcConvert<Long> getJdbcConverter() {
			return context.getJavaJdbcConverter(Long.class);
		}

		@Override
		public AbstractTypeFactory<ELong, Long> getTypeFactory() {
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
