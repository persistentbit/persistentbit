package com.persistentbit.sql.dsl.generic.expressions;

import java.math.BigDecimal;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/11/17
 */
public interface DExprBigDecimal extends DExprNumber<BigDecimal>{
	DExprBigDecimal	add(DExprNumber<?> other);
	DExprBigDecimal sub(DExprNumber<?> other);
	DExprBigDecimal	div(DExprNumber<?> other);
	DExprBigDecimal	mul(DExprNumber<?> other);

	DExprBigDecimal	add(BigDecimal value);
	DExprBigDecimal	sub(BigDecimal value);
	DExprBigDecimal	div(BigDecimal value);
	DExprBigDecimal	mul(BigDecimal value);
}
