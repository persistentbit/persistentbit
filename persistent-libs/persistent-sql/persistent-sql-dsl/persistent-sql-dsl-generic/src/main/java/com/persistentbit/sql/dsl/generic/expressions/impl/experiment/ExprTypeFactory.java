package com.persistentbit.sql.dsl.generic.expressions.impl.experiment;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public interface ExprTypeFactory<E extends DExpr<J>,J> {
	E buildVal(Object value);
	E buildAlias(String alias);
	E buildBinOp(DExpr left, BinOpOperator op, DExpr right);
	E buildSingleOp(DExpr expr, SingleOpOperator op);
	E buildTableField(String fieldSelectionName, String fieldName);

	PList<DExpr> expand(DExpr<J> expr);

	ExprContext	getExprContext();
	//TypeStrategy<J> getTypeStrategy(DExpr<J> expr);
}
