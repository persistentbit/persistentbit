package com.persistentbit.sql.dsl.statements.select.impl;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
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
	private final Type      type;
	private final Table     selectable;
	@Nullable
	private final EBool     joinExpr;

	private final PList<DExpr> using;

	public JoinImpl(QueryImpl query, Type type, Table selectable, @Nullable EBool joinExpr, PList<DExpr> using) {
		this.query = query;
		this.type = type;
		this.selectable = selectable;
		this.joinExpr = joinExpr;
		this.using = using;
	}

	public JoinImpl(QueryImpl query, Type type, Table selectable) {
		this(query, type, selectable, null, PList.empty());
	}

	@Override
	public Query on(EBool joinExpr) {
		return new JoinImpl(query, type, selectable, joinExpr, PList.empty()).query();
	}

	@Override
	public Query using(DExpr... columnExpr) {
		PList<DExpr> using = PList.val(columnExpr).map(query.qc.context::onlyTableColumn);
		return new JoinImpl(query, type, selectable, null, using).query();
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
			.add(query.qc.context.getFromTableName(selectable));
		if(joinExpr != null) {
			result = result.add(" ON ").add(query.qc.context.toSql(joinExpr));
		}
		if(using.isEmpty() == false) {
			result = result.add(" USING(").add(using.map(e -> query.qc.context.toSql(e)), ", ").add(") ");
		}
		return result;
	}
}
