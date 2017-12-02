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
public class DBooleanSingleOp extends DBooleanAbstract{
	public enum Operator {
		opIsNull, opIsNotNull, opNot
	}
	private final DExpr<?> expr;
	private final Operator operator;

	public DBooleanSingleOp(DExpr<?> expr, Operator operator) {
		this.expr = expr;
		this.operator = operator;
	}

	@Override
	public SqlWithParams _toSql(DbSqlContext context) {
		SqlWithParams er = DImpl._get(expr)._toSql(context);
		switch(operator){
			case opIsNull: return er.add(" IS NULL");
			case opIsNotNull: return er.add(" IS NOT NULL");
			case opNot: return SqlWithParams.sql("NOT ").add(er);
			default: throw new ToDo(operator.name());
		}
	}

	@Override
	public SqlWithParams _toSqlSelection(DbSqlContext context, String alias) {
		return _toSql(context)
			.add(alias == null ? "" : " AS " + alias);
	}
}
