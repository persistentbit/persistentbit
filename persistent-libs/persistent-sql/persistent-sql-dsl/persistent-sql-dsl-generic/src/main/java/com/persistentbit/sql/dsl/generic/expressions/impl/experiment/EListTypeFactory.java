package com.persistentbit.sql.dsl.generic.expressions.impl.experiment;

import com.persistentbit.collections.IPList;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.EList;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class EListTypeFactory implements ExprTypeFactory<EList,IPList>{

	@Override
	public EList buildVal(Object value) {
		return null;
	}

	@Override
	public EList buildAlias(String alias) {
		return null;
	}

	@Override
	public EList buildBinOp(DExpr left, BinOpOperator op, DExpr right) {
		return null;
	}

	@Override
	public EList buildSingleOp(DExpr expr, SingleOpOperator op
	) {
		return null;
	}

	@Override
	public EList buildTableField(String fieldSelectionName, String fieldName) {
		return null;
	}

	@Override
	public PList<DExpr> expand(DExpr<IPList> expr
	) {
		return null;
	}

	@Override
	public ExprContext getExprContext() {
		return null;
	}

	private class EListImpl extends ExprTypeImpl<EList,PList>{
		private final IPList<DExpr> expressions;

	}
}
