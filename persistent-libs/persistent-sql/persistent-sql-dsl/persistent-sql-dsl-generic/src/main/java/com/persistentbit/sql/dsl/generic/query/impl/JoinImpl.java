package com.persistentbit.sql.dsl.generic.query.impl;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.EBool;
import com.persistentbit.sql.dsl.generic.expressions.DExprTable;
import com.persistentbit.sql.dsl.generic.expressions.impl.old.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.impl.old.DImplTable;
import com.persistentbit.sql.dsl.generic.query.Join;
import com.persistentbit.sql.dsl.generic.query.Query;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class JoinImpl implements Join{
	public enum Type{
		inner, left, right, full
	}

	private final QueryImpl  query;
	private final Type       type;
	private final DExprTable selectable;
	@Nullable
	private final EBool      joinExpr;

	public JoinImpl(QueryImpl query, Type type, DExprTable selectable,
					@Nullable
						EBool joinExpr
	) {
		this.query = query;
		this.type = type;
		this.selectable = selectable;
		this.joinExpr = joinExpr;
	}
	public JoinImpl(QueryImpl query, Type type, DExprTable selectable){
		this(query,type,selectable,null);
	}

	@Override
	public Query on(EBool joinExpr
	) {
		return new JoinImpl(query,type,selectable,joinExpr).query();
	}

	@Override
	public Query query() {
		return query.addJoin(this);
	}

	public SqlWithParams toSql(DbSqlContext sqlContext){
		String res = "";
		switch(type) {
			case full:
				res += "FULL JOIN ";
				break;
			case inner:
				res += "INNER JOIN ";
				break;
			case left:
				res += "LEFT JOIN ";
				break;
			case right:
				res += "RIGHT JOIN ";
				break;
			default:
				throw new IllegalArgumentException(type.toString());

		}
		SqlWithParams result = SqlWithParams.nl.add(res)
			.add(DImplTable._get(selectable)._toSqlFrom(sqlContext));
		if(joinExpr != null){
			result = result.add(" ON ").add(DImpl._get(joinExpr)._toSql(sqlContext));
		}
		return result;
	}
}
