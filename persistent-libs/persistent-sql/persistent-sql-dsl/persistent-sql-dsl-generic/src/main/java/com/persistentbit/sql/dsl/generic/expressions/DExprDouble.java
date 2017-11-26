package com.persistentbit.sql.dsl.generic.expressions;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/11/17
 */
public interface DExprDouble extends DExprNumber<Double>{
	DExprDouble	add(DExprByte other);
	DExprDouble	add(DExprInt other);
	DExprDouble	add(DExprShort other);
	DExprDouble	add(DExprLong other);
	DExprBigDecimal add(DExprBigDecimal other);
	DExprDouble	add(DExprDouble other);

	DExprDouble	sub(DExprByte other);
	DExprDouble	sub(DExprInt other);
	DExprDouble	sub(DExprShort other);
	DExprDouble	sub(DExprLong other);
	DExprBigDecimal sub(DExprBigDecimal other);
	DExprDouble	sub(DExprDouble other);

	DExprDouble	div(DExprByte other);
	DExprDouble	div(DExprInt other);
	DExprDouble	div(DExprShort other);
	DExprDouble	div(DExprLong other);
	DExprBigDecimal div(DExprBigDecimal other);
	DExprDouble	div(DExprDouble other);

	DExprDouble	mul(DExprByte other);
	DExprDouble	mul(DExprInt other);
	DExprDouble	mul(DExprShort other);
	DExprDouble	mul(DExprLong other);
	DExprBigDecimal mul(DExprBigDecimal other);
	DExprDouble	mul(DExprDouble other);

	DExprDouble	add(double value);
	DExprDouble	sub(double value);
	DExprDouble	div(double value);
	DExprDouble	mul(double value);
}
