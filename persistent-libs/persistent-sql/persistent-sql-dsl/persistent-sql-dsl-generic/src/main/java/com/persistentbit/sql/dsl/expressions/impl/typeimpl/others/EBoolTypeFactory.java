package com.persistentbit.sql.dsl.expressions.impl.typeimpl.others;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.sql.dsl.expressions.impl.BinOpOperator;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.SingleOpOperator;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.expressions.impl.strategies.TypeStrategy;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeImpl;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class EBoolTypeFactory extends AbstractTypeFactory<EBool, Boolean>{

	public EBoolTypeFactory(ExprContext context) {
		super(context);
	}


	@Override
	public Class<EBool> getTypeClass() {
		return EBool.class;
	}

	@Override
	public Class getValueClass() {
		return Boolean.class;
	}

	@Override
	public EBool buildWithStrategy(TypeStrategy<Boolean> strategy) {
		return new EBoolImpl(strategy);
	}


	private class EBoolImpl extends AbstractTypeImpl<EBool, Boolean> implements EBool{

		public EBoolImpl(TypeStrategy<Boolean> typeStrategy) {
			super(typeStrategy);
		}


		@Override
		public ExprTypeJdbcConvert<Boolean> getJdbcConverter() {
			return context.getJavaJdbcConverter(Boolean.class);
		}

		@Override
		public AbstractTypeFactory<EBool, Boolean> getTypeFactory() {
			return EBoolTypeFactory.this;
		}


		@Override
		public EBool not() {
			return getExprContext().booleanSingleOp(this, SingleOpOperator.opNot);
		}

		@Override
		public EBool and(DExpr<Boolean> other) {
			return getExprContext().booleanBinOp(this, BinOpOperator.opAnd, other);
		}

		@Override
		public EBool or(DExpr<Boolean> other) {
			return getExprContext().booleanBinOp(this, BinOpOperator.opOr, other);
		}

		@Override
		public EBool eq(DExpr<Boolean> other) {
			return getExprContext().eq(this, other);
		}

		@Override
		public EBool notEq(DExpr<Boolean> other) {
			return getExprContext().notEq(this, other);
		}

		@Override
		public EBool isNull() {
			return getExprContext().isNull(this);
		}

		@Override
		public EBool isNotNull() {
			return getExprContext().isNotNull(this);
		}
	}
}
