package com.persistentbit.sql.dsl.expressions.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;

import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public interface ExprTypeImpl<E extends DExpr<J>,J> extends DExpr<J>{

	ExprTypeFactory<E,J> getTypeFactory();

	E buildAlias(String alias);

	E buildSelection(String prefixAlias);

	E onlyTableColumn();

	PList<DExpr> expand();

	SqlWithParams toSql();

	ExprTypeJdbcConvert<J> getJdbcConverter();

	default E buildVal(J value) {
		return getTypeFactory().buildVal(value);
	}

	default E buildBinOp(DExpr left, BinOpOperator op, DExpr right) {
		return getTypeFactory().buildBinOp(left, op, right);
	}

	default E buildCustomSql(Supplier<SqlWithParams> sqlSupplier) {
		return getTypeFactory().buildCustomSql(sqlSupplier);
	}
}
