package com.persistentbit.sql.dsl.statements.select.impl;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.sql.dsl.statements.select.Join;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.tables.Table;

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

	private final QueryImpl query;
	private final Type type;
	private final Table selectable;
	@Nullable
	private final EBool joinExpr;

	public JoinImpl(QueryImpl query, Type type, Table selectable,
					@Nullable
						EBool joinExpr
	) {
		this.query = query;
		this.type = type;
		this.selectable = selectable;
		this.joinExpr = joinExpr;
	}

	public JoinImpl(QueryImpl query, Type type, Table selectable) {
		this(query, type, selectable, null);
	}

	@Override
	public Query on(EBool joinExpr
	) {
		return new JoinImpl(query, type, selectable, joinExpr).query();
	}

	@Override
	public Query query() {
		return query.addJoin(this);
	}

	public SqlWithParams toSql() {
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
		SqlWithParams result = SqlWithParams
								   .nl
								   .add(res)
								   .add(query.context.getFromTableName(selectable));
		if(joinExpr != null) {
			result = result.add(" ON ").add(query.context.toSql(joinExpr));
		}
		return result;
	}
}
