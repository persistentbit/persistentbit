package com.persistentbit.sql.dsl.generic.expressions.impl.experiment;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public interface ExprTypeFactory<E extends DExpr<J>,J> {
	<V extends J> E buildVal(V value);
	E buildAlias(String alias);
	E buildBinOp(DExpr left, BinOpOperator op, DExpr right);
	E buildSingleOp(DExpr expr, SingleOpOperator op);
	E buildTableField(String fieldSelectionName, String fieldName);
	E buildSelection(E expr, String prefixAlias);

	PList<DExpr> expand(E expr);

	SqlWithParams	toSql(E expr);

	ExprContext	getExprContext();
	Class<? extends DExpr<J>> getTypeClass();
}
