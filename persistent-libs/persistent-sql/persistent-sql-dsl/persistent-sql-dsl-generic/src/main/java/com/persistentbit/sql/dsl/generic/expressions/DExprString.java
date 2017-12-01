package com.persistentbit.sql.dsl.generic.expressions;

import com.persistentbit.collections.PList;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/11/17
 */
public interface DExprString extends DExpr<String>{

	DExprString	concat(DExpr<String> other);
	DExprString	concat(String other);


	DExprBoolean like(DExpr<String> likeOther);
	DExprBoolean like(String likeOther);

	DExprBoolean eq(DExpr<String> other);
	DExprBoolean eq(String other);

	DExprBoolean notEq(DExpr<String> other);
	DExprBoolean notEq(String other);

	DExprBoolean lt(DExpr<String> other);
	DExprBoolean lt(String other);

	DExprBoolean gt(DExpr<String> other);
	DExprBoolean gt(String other);

	DExprBoolean ltEq(DExpr<String> other);
	DExprBoolean ltEq(String other);

	DExprBoolean gtEq(DExpr<String> other);
	DExprBoolean gtEq(String other);

	DExprBoolean isNull();
	DExprBoolean isNotNull();

	DExprBoolean in(PList<DExpr<String>> values);
	DExprBoolean in(DExpr<String>...values);



}
