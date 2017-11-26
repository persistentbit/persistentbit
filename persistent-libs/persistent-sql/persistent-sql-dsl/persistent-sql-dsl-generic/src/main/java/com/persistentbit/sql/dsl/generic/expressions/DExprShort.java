package com.persistentbit.sql.dsl.generic.expressions;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/11/17
 */
public interface DExprShort extends DExprNumber<Short>{
	DExprInt    add(DExprByte other);
	DExprInt	add(DExprInt other);
	DExprInt	add(DExprShort other);
	DExprLong	add(DExprLong other);
	DExprBigDecimal add(DExprBigDecimal other);
	DExprDouble	add(DExprDouble other);

	DExprInt    sub(DExprByte other);
	DExprInt	sub(DExprInt other);
	DExprInt	sub(DExprShort other);
	DExprLong	sub(DExprLong other);
	DExprBigDecimal sub(DExprBigDecimal other);
	DExprDouble	sub(DExprDouble other);

	DExprInt    div(DExprByte other);
	DExprInt	div(DExprInt other);
	DExprInt	div(DExprShort other);
	DExprLong	div(DExprLong other);
	DExprBigDecimal div(DExprBigDecimal other);
	DExprDouble	div(DExprDouble other);

	DExprInt    mul(DExprByte other);
	DExprInt	mul(DExprInt other);
	DExprInt	mul(DExprShort other);
	DExprLong	mul(DExprLong other);
	DExprBigDecimal mul(DExprBigDecimal other);
	DExprDouble	mul(DExprDouble other);

	DExprInt	add(int value);
	DExprInt	sub(int value);
	DExprInt	div(int value);
	DExprInt	mul(int value);
}
