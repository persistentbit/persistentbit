package com.persistentbit.sql.dsl.expressions.impl.typeimpl.others;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ESelection;
import com.persistentbit.sql.dsl.expressions.impl.*;
import com.persistentbit.sql.dsl.statements.select.impl.TypedSelection1Impl;

/**
 * TODOC
 *
 * @author petermuys
 * @since 19/12/17
 */
public class ESelectionTypeFactory<J> implements ExprTypeFactory<ESelection<J>, J>{
	private final ExprContext context;
	public ESelectionTypeFactory(ExprContext context) {
		this.context = context;
	}



	@Override
	public ESelection<J> buildVal(Object value) {
		throw new UnsupportedOperationException("buildVal for a ESubQuery");
	}

	@Override
	public ESelection<J> buildAlias(String alias) {
		throw new UnsupportedOperationException("buildVal for a ESubQuery");
	}

	@Override
	public ESelection<J> buildBinOp(DExpr left, BinOpOperator op, DExpr right) {
		throw new UnsupportedOperationException("buildVal for a ESubQuery");
	}

	@Override
	public ESelection<J> buildSingleOp(DExpr expr, SingleOpOperator op) {
		throw new UnsupportedOperationException("buildVal for a ESubQuery");
	}

	@Override
	public ESelection<J> buildTableField(String fieldSelectionName, String fieldName) {
		throw new UnsupportedOperationException("buildVal for a ESubQuery");
	}

	@Override
	public ESelection<J> buildSelection(ESelection expr, String prefixAlias) {
		if(prefixAlias == null){
			return expr;
		}
		ESelectionImpl se = (ESelectionImpl)expr;
		return new ESelectionImpl<>(this,se.toSql().add(" AS " + prefixAlias));
	}

	@Override
	public PList<DExpr> expand(ESelection expr) {
		return PList.val(expr);
	}

	@Override
	public SqlWithParams toSql(ESelection expr) {
		ESelectionImpl impl = (ESelectionImpl)expr;
		return impl.toSql();
	}

	@Override
	public ExprContext getExprContext() {
		return context;
	}

	@Override
	public Class<ESelection<J>> getTypeClass() {
		Class cls = ESelection.class;
		return cls;
	}

	public ESelection<J>	create(TypedSelection1Impl<?,J> selection){
		SqlWithParams sql = SqlWithParams.sql("(").add(selection.toSql()).add(")");
		return new ESelectionImpl<>(this,sql);
	}

	static public class ESelectionImpl<J1> implements ExprTypeImpl<DExpr<J1>,J1>, ESelection<J1>{
		private final ExprTypeFactory<?,J1> typeFactory;
		private final SqlWithParams sql;

		public ESelectionImpl(ExprTypeFactory<?, J1> typeFactory, SqlWithParams sql) {
			this.typeFactory = typeFactory;
			this.sql = sql;
		}

		public SqlWithParams toSql() {
			return sql;
		}

		@Override
		public ExprTypeFactory getTypeFactory() {
			return typeFactory;
		}

		@Override
		public String toString() {
			return toSql().toString();
		}
	}
}
