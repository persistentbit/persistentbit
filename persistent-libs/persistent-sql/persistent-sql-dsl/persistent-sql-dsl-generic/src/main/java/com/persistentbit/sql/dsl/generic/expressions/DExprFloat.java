package com.persistentbit.sql.dsl.generic.expressions;

/**
 * TODOC
 *
 * @author petermuys
 * @since 15/12/17
 */
public interface DExprFloat extends DExprNumber<Float>{
	DExprFloat	add(DExprByte other);
	DExprFloat	add(DExprInt other);
	DExprFloat	add(DExprShort other);
	DExprFloat	add(DExprLong other);
	DExprBigDecimal add(DExprBigDecimal other);
	DExprDouble	add(DExprDouble other);
	DExprFloat add(DExprFloat other);

	DExprFloat	sub(DExprByte other);
	DExprFloat	sub(DExprInt other);
	DExprFloat	sub(DExprShort other);
	DExprFloat	sub(DExprLong other);
	DExprBigDecimal sub(DExprBigDecimal other);
	DExprDouble	sub(DExprDouble other);
	DExprFloat  sub(DExprFloat other);

	DExprFloat	div(DExprByte other);
	DExprFloat	div(DExprInt other);
	DExprFloat	div(DExprShort other);
	DExprFloat	div(DExprLong other);
	DExprBigDecimal div(DExprBigDecimal other);
	DExprDouble	div(DExprDouble other);
	DExprFloat div(DExprFloat other);

	DExprFloat	mul(DExprByte other);
	DExprFloat	mul(DExprInt other);
	DExprFloat	mul(DExprShort other);
	DExprFloat	mul(DExprLong other);
	DExprBigDecimal mul(DExprBigDecimal other);
	DExprDouble	mul(DExprDouble other);
	DExprFloat mul(DExprFloat other);

	DExprFloat	add(float value);
	DExprFloat	sub(float value);
	DExprFloat	div(float value);
	DExprFloat	mul(float value);
}
