package com.persistentbit.sql.dsl.expressions.impl.typeimpl;

import com.persistentbit.collections.ImmutableArray;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EArray;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;

/**
 * TODOC
 *
 * @author petermuys
 * @since 5/01/18
 */
public interface ArrayExprTypeFactory<E1 extends DExpr<J1>, J1>{

	EArray<E1, J1> createArrayVal(Class<E1> itemClass, ExprTypeJdbcConvert<J1> itemJdbcConverter,
								  ImmutableArray<? extends J1> values);

	EArray<E1, J1> createArrayTableColumn(Class<E1> itemClass, ExprTypeJdbcConvert<J1> itemJdbcConverter,
										  String fieldSelectionName, String fieldName,
										  String columnName);
}
