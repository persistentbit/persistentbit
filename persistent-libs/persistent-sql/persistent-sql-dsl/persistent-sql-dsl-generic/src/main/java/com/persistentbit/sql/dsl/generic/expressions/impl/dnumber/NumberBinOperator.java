package com.persistentbit.sql.dsl.generic.expressions.impl.dnumber;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.utils.exceptions.ToDo;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/11/17
 */
public enum NumberBinOperator{
	add,sub,div,mul;

	SqlWithParams asSql(DbSqlContext context, DExpr left, DExpr right){
		SqlWithParams sqlLeft = DImpl._get(left)._toSql(context);
		SqlWithParams sqlRight = DImpl._get(right)._toSql(context);
		switch(this){
			case add: return sqlLeft.add("+").add(sqlRight);
			case sub: return sqlLeft.add("-").add(sqlRight);
			case div: return sqlLeft.add("/").add(sqlRight);
			case mul: return sqlLeft.add("*").add(sqlRight);
			default: throw new ToDo();
		}
	}
}
