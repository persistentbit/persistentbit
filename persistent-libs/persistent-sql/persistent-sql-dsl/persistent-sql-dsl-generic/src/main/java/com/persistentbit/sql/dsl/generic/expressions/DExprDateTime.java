package com.persistentbit.sql.dsl.generic.expressions;

import com.persistentbit.collections.PList;

import java.time.LocalDateTime;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/11/17
 */
public interface DExprDateTime extends DExpr<LocalDateTime>{

	DExprBoolean eq(DExpr<LocalDateTime> other);
	DExprBoolean eq(LocalDateTime other);

	DExprBoolean notEq(DExpr<LocalDateTime> other);
	DExprBoolean notEq(LocalDateTime other);

	DExprBoolean lt(DExpr<LocalDateTime> other);
	DExprBoolean lt(LocalDateTime other);

	DExprBoolean gt(DExpr<LocalDateTime> other);
	DExprBoolean gt(LocalDateTime other);

	DExprBoolean ltEq(DExpr<LocalDateTime> other);
	DExprBoolean ltEq(LocalDateTime other);

	DExprBoolean gtEq(DExpr<LocalDateTime> other);
	DExprBoolean gtEq(LocalDateTime other);

	DExprBoolean isNull();
	DExprBoolean isNotNull();

	DExprBoolean in(PList<DExpr<LocalDateTime>> values);
	DExprBoolean in(DExpr<LocalDateTime>...values);

}
