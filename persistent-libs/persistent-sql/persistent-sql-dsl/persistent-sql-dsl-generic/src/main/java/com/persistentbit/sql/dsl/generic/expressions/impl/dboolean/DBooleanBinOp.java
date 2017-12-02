package com.persistentbit.sql.dsl.generic.expressions.impl.dboolean;

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
public class DBooleanBinOp extends DBooleanAbstract{
	public enum Operator {
		opLt, opLtEq, opGt, opGtEq, opEq, opNotEq, like
	}
	private final DExpr left;
	private final Operator operator;
	private final DExpr right;

	public DBooleanBinOp(DExpr left, Operator operator, DExpr right) {
		this.left = left;
		this.operator = operator;
		this.right = right;
	}

	@Override
	public SqlWithParams _toSqlSelection(DbSqlContext context, String alias) {
		return DImpl._get(left)._toSqlSelection(context,null)
					.add(" "+ opString() + " ")
		    .add(DImpl._get(right)._toSqlSelection(context,null))
			.add(alias == null ? "" : " AS " + alias);
	}



	@Override
	public SqlWithParams _toSql(DbSqlContext context
	) {
		return DImpl._get(left)._toSql(context)
					.add(" "+ opString() + " ")
					.add(DImpl._get(right)._toSql(context));
	}
	private String opString(){
		switch(operator){
			case opEq: return "=";
			case opNotEq: return "<>";
			case opGt: return ">";
			case opLt: return "<";
			case opGtEq: return ">=";
			case opLtEq: return "<=";
			case like: return "like ";
			default: throw new ToDo(operator.name());
		}
	}
}
