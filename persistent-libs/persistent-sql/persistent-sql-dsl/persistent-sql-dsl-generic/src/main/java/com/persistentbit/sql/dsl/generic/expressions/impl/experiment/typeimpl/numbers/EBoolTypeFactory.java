package com.persistentbit.sql.dsl.generic.expressions.impl.experiment.typeimpl.numbers;

import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.EBool;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.AbstractTypeFactory;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.BinOpOperator;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.ExprContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.SingleOpOperator;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.strategies.TypeStrategy;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.typeimpl.AbstractTypeImpl;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class EBoolTypeFactory extends AbstractTypeFactory<EBool,Boolean>{

	public EBoolTypeFactory(ExprContext context) {
		super(context, EBool.class, context.getJavaJdbcConverter(Boolean.class));
	}

	@Override
	protected EBool buildWithStrategy(TypeStrategy<Boolean> strategy
	) {
		return new EBoolImpl(EBool.class, strategy);
	}
	private class EBoolImpl extends AbstractTypeImpl<EBool,Boolean> implements EBool{

		public EBoolImpl(Class<EBool> typeClass,
						 TypeStrategy<Boolean> typeStrategy
		) {
			super(typeClass, typeStrategy);
		}

		@Override
		public EBool not() {
			return getExprContext().booleanSingleOp(this, SingleOpOperator.opNot);
		}

		@Override
		public EBool and(DExpr<Boolean> other) {
			return getExprContext().booleanBinOp(this, BinOpOperator.opAnd,other);
		}

		@Override
		public EBool or(DExpr<Boolean> other) {
			return getExprContext().booleanBinOp(this,BinOpOperator.opOr,other);
		}

		@Override
		public EBool eq(DExpr<Boolean> other) {
			return getExprContext().eq(this,other);
		}

		@Override
		public EBool notEq(DExpr<Boolean> other) {
			return getExprContext().notEq(this,other);
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
