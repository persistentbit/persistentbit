package com.persistentbit.sql.dsl.generic.expressions.impl.experiment;

import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprBoolean;
import com.persistentbit.sql.dsl.generic.expressions.impl.dboolean.DBooleanBinOp;
import com.persistentbit.sql.dsl.generic.expressions.impl.dboolean.DBooleanSingleOp;

/**
 * TODOC
 *
 * @author petermuys
 * @since 16/12/17
 */
public interface ExprContext{
	<E extends DExpr<J>,J> E singleOp(Class<E> resultTypeClass, DExpr expr, DBooleanSingleOp.Operator operator);
	<E extends DExpr<J>,J> E binOp(Class<E> resultTypeClass, DExpr left, DBooleanBinOp.Operator operator, DExpr right);
	<E extends DExpr<J>,J> E val(Class<E> typeClass, J value);


	default DExprBoolean booleanSingleOp(DExpr expr, DBooleanSingleOp.Operator operator){
		return singleOp(DExprBoolean.class, expr, operator);
	}
	//default DExprBoolean booleanAndOr(DExpr left, DBooleanAndOr.Operator operator, DExpr right){
	//	return binOp(DExprBoolean.class, left, operator, right);
	//}
	default DExprBoolean booleanBinOp(DExpr left, DBooleanBinOp.Operator operator, DExpr right){
		return binOp(DExprBoolean.class, left, operator, right);

	}

	default DExprBoolean eq(DExpr left, DExpr right){
		return booleanBinOp(left, DBooleanBinOp.Operator.opEq,right);
	}
	default DExprBoolean notEq(DExpr left, DExpr right){
		return booleanBinOp(left, DBooleanBinOp.Operator.opNotEq,right);
	}
	default DExprBoolean lt(DExpr left, DExpr right){
		return booleanBinOp(left, DBooleanBinOp.Operator.opLt,right);
	}
	default DExprBoolean ltEq(DExpr left, DExpr right){
		return booleanBinOp(left, DBooleanBinOp.Operator.opLtEq,right);
	}
	default DExprBoolean gt(DExpr left, DExpr right){
		return booleanBinOp(left, DBooleanBinOp.Operator.opGt,right);
	}
	default DExprBoolean gtEq(DExpr left, DExpr right){
		return booleanBinOp(left, DBooleanBinOp.Operator.opGtEq,right);
	}


}
