package com.persistentbit.sql.dsl.generic.expressions;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/11/17
 */
public interface ENumberMixin<BE,SE,IE,LE,FE,DE,BDE,E extends DExpr<N>,N extends Number>{
	EBool eq(DExpr<? extends Number> other);
	EBool eq(N other);

	EBool notEq(DExpr<? extends Number> other);
	EBool notEq(N other);

	EBool lt(DExpr<? extends Number> other);
	EBool lt(N other);

	EBool gt(DExpr<? extends Number> other);
	EBool gt(N other);

	EBool ltEq(DExpr<? extends Number> other);
	EBool ltEq(N other);

	EBool gtEq(DExpr<? extends Number> other);
	EBool gtEq(N other);

	E add(N other);
	E sub(N other);
	E div(N other);
	E mul(N other);

	BE add(EByte other);
	SE add(EShort other);
	IE add(EInt other);
	LE add(ELong other);
	FE add(EFloat other);
	BDE add(EBigDecimal other);

	BE sub(EByte other);
	SE sub(EShort other);
	IE sub(EInt other);
	LE sub(ELong other);
	FE sub(EFloat other);
	BDE sub(EBigDecimal other);


	BE mul(EByte other);
	SE mul(EShort other);
	IE mul(EInt other);
	LE mul(ELong other);
	FE mul(EFloat other);
	BDE mul(EBigDecimal other);

	BE div(EByte other);
	SE div(EShort other);
	IE div(EInt other);
	LE div(ELong other);
	FE div(EFloat other);
	BDE div(EBigDecimal other);


}
