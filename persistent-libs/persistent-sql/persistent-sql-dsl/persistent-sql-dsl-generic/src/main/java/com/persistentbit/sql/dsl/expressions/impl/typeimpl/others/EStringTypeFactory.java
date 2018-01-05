package com.persistentbit.sql.dsl.expressions.impl.typeimpl.others;

import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.impl.BinOpOperator;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.expressions.impl.strategies.TypeStrategy;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeImpl;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.TypeImplComparableMixin;

/**
 * TODOC
 *
 * @author petermuys
 * @since 18/12/17
 */
public class EStringTypeFactory extends AbstractTypeFactory<EString,String>{

	public EStringTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected ExprTypeJdbcConvert<String> getJdbcConverter() {
		return context.getJavaJdbcConverter(String.class);
	}

	@Override
	public Class<EString> getTypeClass() {
		return EString.class;
	}


	@Override
	protected EString buildWithStrategy(TypeStrategy<String> strategy) {
		return new EStringImpl(strategy);
	}
	private class EStringImpl extends AbstractTypeImpl<EString,String> implements EString, TypeImplComparableMixin<EString,String>{

		public EStringImpl(TypeStrategy<String> typeStrategy) {
			super(typeStrategy);
		}

		@Override
		public Class<EString> getTypeClass() {
			return getTypeFactory().getTypeClass();
		}

		@Override
		public EString buildWithStrategy(TypeStrategy<String> typeStrategy) {
			return EStringTypeFactory.this.buildWithStrategy(typeStrategy);
		}

		@Override
		public ExprContext getContext() {
			return context;
		}

		@Override
		public ExprTypeJdbcConvert<String> getJdbcConverter() {
			return EStringTypeFactory.this.getJdbcConverter();
		}

		@Override
		public ExprTypeFactory<EString, String> getTypeFactory() {
			return EStringTypeFactory.this;
		}

		@Override
		public EString concat(DExpr<String> other) {
			return getTypeFactory().buildBinOp(this,BinOpOperator.opConcat,other);
		}

		@Override
		public EString concat(String other) {
			return concat(buildVal(other));
		}

		@Override
		public EBool like(DExpr<String> likeOther) {
			return context.booleanBinOp(this,BinOpOperator.opLike,likeOther);
		}

		@Override
		public EBool like(String likeOther) {
			return like(buildVal(likeOther));
		}

		@Override
		public EString getThis() {
			return this;
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
