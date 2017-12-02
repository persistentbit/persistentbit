package com.persistentbit.sql.dsl.generic.expressions;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/11/17
 */
public interface DExprLong extends DExprNumber<Long>{
	DExprLong	add(DExprByte other);
	DExprLong	add(DExprInt other);
	DExprLong	add(DExprShort other);
	DExprLong	add(DExprLong other);
	DExprBigDecimal add(DExprBigDecimal other);
	DExprDouble	add(DExprDouble other);

	DExprLong	sub(DExprByte other);
	DExprLong	sub(DExprInt other);
	DExprLong	sub(DExprShort other);
	DExprLong	sub(DExprLong other);
	DExprBigDecimal sub(DExprBigDecimal other);
	DExprDouble	sub(DExprDouble other);

	DExprLong	div(DExprByte other);
	DExprLong	div(DExprInt other);
	DExprLong	div(DExprShort other);
	DExprLong	div(DExprLong other);
	DExprBigDecimal div(DExprBigDecimal other);
	DExprDouble	div(DExprDouble other);

	DExprLong	mul(DExprByte other);
	DExprLong	mul(DExprInt other);
	DExprLong	mul(DExprShort other);
	DExprLong	mul(DExprLong other);
	DExprBigDecimal mul(DExprBigDecimal other);
	DExprDouble	mul(DExprDouble other);

	DExprLong	add(long value);
	DExprLong	sub(long value);
	DExprLong	div(long value);
	DExprLong	mul(long value);

	static DExprLong cast(DExpr<Long> expr){
		return (DExprLong)expr;
	}

}
