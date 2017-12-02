package com.persistentbit.sql.dsl.generic.expressions.impl.dboolean;

import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;

import java.util.Objects;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/11/17
 */
public class DBooleanAndOr extends DBooleanAbstract{
	public enum Operator{
		opAnd, opOr
	}
	private final DExpr<Boolean> left;
	private final Operator	operator;
	private final DExpr<Boolean> right;

	public DBooleanAndOr(DExpr<Boolean> left,
						 Operator operator,
						 DExpr<Boolean> right
	) {
		this.left = Objects.requireNonNull(left);
		this.operator = Objects.requireNonNull(operator);
		this.right = Objects.requireNonNull(right);
	}


	@Override
	public SqlWithParams _toSqlSelection(DbSqlContext context
	) {
		return DImpl._get(left)._toSqlSelection(context)
					.add(" "+ opString() + " ")
					.add(DImpl._get(right)._toSqlSelection(context));
	}



	@Override
	public SqlWithParams _toSql(DbSqlContext context
	) {
		return DImpl._get(left)._toSql(context)
					.add(" "+ opString() + " ")
					.add(DImpl._get(right)._toSql(context));
	}

	private String opString() {
		return operator.name().toUpperCase().substring(2);
	}
}
