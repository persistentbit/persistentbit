package com.persistentbit.sql.dsl.generic.expressions.impl.experiment.typeimpl.numbers;

import com.persistentbit.sql.dsl.generic.expressions.*;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.BinOpOperator;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.ExprContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.strategies.TypeStrategy;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.typeimpl.AbstractTypeImpl;

import java.util.function.BiFunction;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public abstract class AbstractNumberTypeImpl<BE,SE,IE,LE,FE,DE,BDE,NE,E extends DExpr<J>,J extends Number> extends
	AbstractTypeImpl<E,J> implements
	ENumberMixin<BE,SE,IE,LE,FE,DE,BDE,E,J>{
	private final ExprContext                                 context;
	private final BiFunction<BinOpOperator,DExpr,E> binOp;

	public AbstractNumberTypeImpl(ExprContext context,Class<E> typeClass,
								  TypeStrategy<J> typeStrategy

	) {
		super(typeClass, typeStrategy);
		this.context = context;
		this.binOp = (op, other) -> context.getTypeFactory(typeClass).buildBinOp(this,op,other);
	}

	protected abstract BE beBinOp(BinOpOperator op, DExpr other);
	protected abstract SE seBinOp(BinOpOperator op, DExpr other);
	protected abstract IE ieBinOp(BinOpOperator op, DExpr other);
	protected abstract LE leBinOp(BinOpOperator op, DExpr other);
	protected abstract FE feBinOp(BinOpOperator op, DExpr other);
	protected abstract DE deBinOp(BinOpOperator op, DExpr other);
	protected abstract BDE bdeBinOp(BinOpOperator op, DExpr other);
	protected abstract E nBinOp(BinOpOperator op, DExpr other);

	@Override
	public EBool eq(DExpr<? extends Number> other
	) {
		return context.eq(this,other);
	}

	@Override
	public EBool eq(J other) {
		return eq(val(other));
	}

	@Override
	public EBool notEq(DExpr<? extends Number> other
	) {
		return context.notEq(this,other);
	}

	@Override
	public EBool notEq(J other) {
		return notEq(val(other));
	}

	@Override
	public EBool lt(DExpr<? extends Number> other
	) {
		return context.lt(this,other);
	}

	@Override
	public EBool lt(J other) {
		return lt(val(other));
	}

	@Override
	public EBool gt(DExpr<? extends Number> other
	) {
		return context.gt(this,other);
	}

	@Override
	public EBool gt(J other) {
		return gt(val(other));
	}

	@Override
	public EBool ltEq(DExpr<? extends Number> other
	) {
		return context.ltEq(this,other);
	}

	@Override
	public EBool ltEq(J other) {
		return ltEq(val(other));
	}

	@Override
	public EBool gtEq(DExpr<? extends Number> other
	) {
		return context.gtEq(this,other);
	}

	@Override
	public EBool gtEq(J other) {
		return gtEq(val(other));
	}

	@Override
	public E add(J other) {
		return nBinOp(BinOpOperator.opAdd,val(other));
	}

	@Override
	public E sub(J other) {
		return nBinOp(BinOpOperator.opSub,val(other));
	}

	@Override
	public E div(J other) {
		return nBinOp(BinOpOperator.opDiv,val(other));
	}

	@Override
	public E mul(J other) {
		return nBinOp(BinOpOperator.opMul,val(other));
	}

	@Override
	public BE add(EByte other) {
		return beBinOp(BinOpOperator.opAdd,other);
	}

	@Override
	public SE add(EShort other) {
		return seBinOp(BinOpOperator.opAdd,other);
	}

	@Override
	public IE add(EInt other) {
		return ieBinOp(BinOpOperator.opAdd,other);
	}

	@Override
	public LE add(ELong other) {
		return leBinOp(BinOpOperator.opAdd,other);
	}

	@Override
	public FE add(EFloat other) {
		return feBinOp(BinOpOperator.opAdd,other);
	}

	@Override
	public BDE add(EBigDecimal other) {
		return bdeBinOp(BinOpOperator.opAdd,other);
	}

	@Override
	public BE sub(EByte other) {
		return beBinOp(BinOpOperator.opSub,other);
	}

	@Override
	public SE sub(EShort other) {
		return seBinOp(BinOpOperator.opSub,other);
	}

	@Override
	public IE sub(EInt other) {
		return ieBinOp(BinOpOperator.opSub,other);
	}

	@Override
	public LE sub(ELong other) {
		return leBinOp(BinOpOperator.opSub,other);
	}

	@Override
	public FE sub(EFloat other) {
		return feBinOp(BinOpOperator.opSub,other);
	}

	@Override
	public BDE sub(EBigDecimal other) {
		return bdeBinOp(BinOpOperator.opSub,other);
	}

	@Override
	public BE mul(EByte other) {
		return beBinOp(BinOpOperator.opMul,other);
	}

	@Override
	public SE mul(EShort other) {
		return seBinOp(BinOpOperator.opMul,other);
	}

	@Override
	public IE mul(EInt other) {
		return ieBinOp(BinOpOperator.opMul,other);
	}

	@Override
	public LE mul(ELong other) {
		return leBinOp(BinOpOperator.opMul,other);
	}

	@Override
	public FE mul(EFloat other) {
		return feBinOp(BinOpOperator.opMul,other);
	}

	@Override
	public BDE mul(EBigDecimal other) {
		return bdeBinOp(BinOpOperator.opMul,other);
	}

	@Override
	public BE div(EByte other) {
		return beBinOp(BinOpOperator.opDiv,other);
	}

	@Override
	public SE div(EShort other) {
		return seBinOp(BinOpOperator.opDiv,other);
	}

	@Override
	public IE div(EInt other) {
		return ieBinOp(BinOpOperator.opDiv,other);
	}

	@Override
	public LE div(ELong other) {
		return leBinOp(BinOpOperator.opDiv,other);
	}

	@Override
	public FE div(EFloat other) {
		return feBinOp(BinOpOperator.opDiv,other);
	}

	@Override
	public BDE div(EBigDecimal other) {
		return bdeBinOp(BinOpOperator.opDiv,other);
	}

	protected abstract E val(J value);

}
