package com.persistentbit.sql.dsl.generic.expressions.impl.typeimpl.others;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.EList;
import com.persistentbit.sql.dsl.generic.expressions.impl.*;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.*;
import com.persistentbit.sql.dsl.generic.expressions.impl.strategies.TypeStrategy;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.utils.exceptions.ToDo;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class EListTypeFactory implements ExprTypeFactory<EList,PList<DExpr>>{

	private final ExprContext context;

	public EListTypeFactory(ExprContext context) {
		this.context = context;
	}

	@Override
	public Class<? extends DExpr<PList<DExpr>>> getTypeClass() {
		return EList.class;
	}

	@Override
	public <V extends PList<DExpr>> EList buildVal(V value) {
		throw new ToDo();
	}

	@Override
	public EList buildSelection(EList expr, String prefixAlias) {
		throw new ToDo();
	}

	@Override
	public PList<DExpr> expand(EList expr) {
		throw new ToDo();
	}


	@Override
	public SqlWithParams toSql(EList expr) {
		throw new ToDo();
	}

	@Override
	public EList buildAlias(String alias) {
		throw new ToDo();
	}

	@Override
	public EList buildBinOp(DExpr left, BinOpOperator op, DExpr right) {
		throw new ToDo();
	}

	@Override
	public EList buildSingleOp(DExpr expr, SingleOpOperator op
	) {
		throw new ToDo();
	}

	@Override
	public EList buildTableField(String fieldSelectionName, String fieldName) {
		throw new ToDo();
	}



	@Override
	public ExprContext getExprContext() {
		return context;
	}

	private class EListImpl extends ExprTypeImpl<EList,PList<DExpr>>{
		private final PList<DExpr> expressions;

		public EListImpl(TypeStrategy<PList> typeStrategy,
						 PList<DExpr> expressions
		) {
			throw new ToDo();
		}

		@Override
		public ExprTypeFactory<EList, PList<DExpr>> getTypeFactory() {
			return EListTypeFactory.this;
		}
	}
}
