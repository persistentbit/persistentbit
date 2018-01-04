package com.persistentbit.sql.dsl.genericdb;

import com.persistentbit.sql.dsl.expressions.impl.BinOpOperator;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;

/**
 * TODOC
 *
 * @author petermuys
 * @since 3/01/18
 */
public class GenericBinOps{

	static public void setDefaultBinOpBuilders(ExprContext context) {
		context.addBinOpBuilder(BinOpOperator.opEq, (left, sqlLeft, right, sqlRight) ->
			sqlLeft.add("=").add(sqlRight)
		);
		context.addBinOpBuilder(BinOpOperator.opNotEq, (left, sqlLeft, right, sqlRight) ->
			sqlLeft.add("<>").add(sqlRight)
		);
		context.addBinOpBuilder(BinOpOperator.opGt, (left, sqlLeft, right, sqlRight) ->
			sqlLeft.add(">").add(sqlRight)
		);
		context.addBinOpBuilder(BinOpOperator.opGtEq, (left, sqlLeft, right, sqlRight) ->
			sqlLeft.add(">=").add(sqlRight)
		);
		context.addBinOpBuilder(BinOpOperator.opLt, (left, sqlLeft, right, sqlRight) ->
			sqlLeft.add("<").add(sqlRight)
		);
		context.addBinOpBuilder(BinOpOperator.opLtEq, (left, sqlLeft, right, sqlRight) ->
			sqlLeft.add("<=").add(sqlRight)
		);
		context.addBinOpBuilder(BinOpOperator.opAnd, (left, sqlLeft, right, sqlRight) ->
			sqlLeft.add(" AND ").add(sqlRight)
		);
		context.addBinOpBuilder(BinOpOperator.opOr, (left, sqlLeft, right, sqlRight) ->
			sqlLeft.add(" OR ").add(sqlRight)
		);
		context.addBinOpBuilder(BinOpOperator.opLike, (left, sqlLeft, right, sqlRight) ->
			sqlLeft.add(" LIKE ").add(sqlRight)
		);
		context.addBinOpBuilder(BinOpOperator.opConcat, (left, sqlLeft, right, sqlRight) ->
			sqlLeft.add(" || ").add(sqlRight)
		);
		context.addBinOpBuilder(BinOpOperator.opAssign, (left, sqlLeft, right, sqlRight) ->
			sqlLeft.add(" = ").add(sqlRight)
		);

	}
}
