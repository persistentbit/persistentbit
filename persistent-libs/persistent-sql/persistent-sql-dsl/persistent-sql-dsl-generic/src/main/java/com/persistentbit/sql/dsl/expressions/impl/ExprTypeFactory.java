package com.persistentbit.sql.dsl.expressions.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.collections.PMap;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;

import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public interface ExprTypeFactory<E extends DExpr<J>,J> {
	<V extends J> E buildVal(V value);
	E buildParam(Function<PMap<String, Object>, Object> paramGetter);
	E buildCall(String callName, DExpr[] params);
	E buildAlias(String alias);
	E buildBinOp(DExpr left, BinOpOperator op, DExpr right);
	E buildSingleOp(DExpr expr, SingleOpOperator op);
	E buildTableField(String fieldSelectionName, String fieldName);
	E buildSelection(E expr, String prefixAlias);

	PList<DExpr> expand(E expr);

	SqlWithParams	toSql(E expr);

	ExprTypeJdbcConvert<J>	getJdbcConverter(E expr);

	ExprContext	getExprContext();
	Class<? extends DExpr<J>> getTypeClass();
}