package com.persistentbit.sql.dsl.generic.expressions;

import com.persistentbit.collections.PList;

import java.time.ZonedDateTime;

/**
 * TODOC
 *
 * @author petermuys
 * @since 16/12/17
 */
public interface DExprZonedDateTime extends DExpr<ZonedDateTime>{

	DExprBoolean eq(DExpr<ZonedDateTime> other);

	DExprBoolean eq(ZonedDateTime other);

	DExprBoolean notEq(DExpr<ZonedDateTime> other);

	DExprBoolean notEq(ZonedDateTime other);

	DExprBoolean lt(DExpr<ZonedDateTime> other);

	DExprBoolean lt(ZonedDateTime other);

	DExprBoolean gt(DExpr<ZonedDateTime> other);

	DExprBoolean gt(ZonedDateTime other);

	DExprBoolean ltEq(DExpr<ZonedDateTime> other);

	DExprBoolean ltEq(ZonedDateTime other);

	DExprBoolean gtEq(DExpr<ZonedDateTime> other);

	DExprBoolean gtEq(ZonedDateTime other);

	DExprBoolean isNull();

	DExprBoolean isNotNull();

	DExprBoolean in(PList<DExpr<ZonedDateTime>> values);

	DExprBoolean in(DExpr<ZonedDateTime>... values);


}
