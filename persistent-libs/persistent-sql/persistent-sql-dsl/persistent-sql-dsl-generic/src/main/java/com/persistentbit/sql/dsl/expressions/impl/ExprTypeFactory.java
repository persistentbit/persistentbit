package com.persistentbit.sql.dsl.expressions.impl;

import com.persistentbit.collections.ImmutableArray;
import com.persistentbit.collections.PMap;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EArray;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public interface ExprTypeFactory<E extends DExpr<J>,J> {
	<V extends J> E buildVal(V value);
	E buildParam(Function<PMap<String, Object>, Object> paramGetter);

	E buildCall(String callName, Object... exprOrStringParams);

	E buildTableField(String fieldSelectionName, String fieldName, String columnName);

	EArray<E, J> buildArrayTableField(String fieldSelectionName, String fieldName, String columnName);

	<V extends J> EArray<E, J> buildArrayVal(ImmutableArray<V> values);

	E buildBinOp(DExpr left, BinOpOperator op, DExpr right);

	E buildSingleOp(DExpr expr, SingleOpOperator op);

	E buildCustomSql(Supplier<SqlWithParams> sqlSupplier);



	ExprContext	getExprContext();

	Class<? extends DExpr> getTypeClass();
}
