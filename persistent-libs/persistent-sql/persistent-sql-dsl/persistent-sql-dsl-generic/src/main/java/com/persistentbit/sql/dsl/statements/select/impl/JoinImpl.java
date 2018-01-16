package com.persistentbit.sql.dsl.statements.select.impl;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.Sql;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.select.Join;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.tables.Table;

import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class JoinImpl<QIMPL extends Query> implements Join{

	public enum Type{
		inner, left, right, full
	}

	private final Function<JoinImpl, QIMPL> queryAddJoin;
	private final QueryCtx                  queryCtx;
	private final Type                      type;
	private final Table                     selectable;
	@Nullable
	private final EBool                     joinExpr;

	private final PList<DExpr> using;

	public JoinImpl(Function<JoinImpl, QIMPL> queryAddJoin, QueryCtx queryCtx, Type type, Table selectable,
					@Nullable EBool joinExpr, PList<DExpr> using) {
		this.queryAddJoin = queryAddJoin;
		this.type = type;
		this.selectable = selectable;
		this.joinExpr = joinExpr;
		this.using = using;
		this.queryCtx = queryCtx;
	}

	protected QueryCtx getCtx() {
		return queryCtx;
	}

	protected ExprContext getExprContext() {
		return getCtx().context;
	}

	public JoinImpl(Function<JoinImpl, QIMPL> queryAddJoin, QueryCtx queryCtx, Type type, Table selectable) {
		this(queryAddJoin, queryCtx, type, selectable, null, PList.empty());
	}

	@Override
	public QIMPL on(EBool joinExpr) {
		return new JoinImpl<>(queryAddJoin, queryCtx, type, selectable, joinExpr, PList.empty()).query();
	}

	@Override
	public QIMPL using(DExpr... columnExpr) {
		PList<DExpr> using = PList.val(columnExpr).map(e -> getExprContext().onlyTableColumn(e));
		return new JoinImpl<>(queryAddJoin, queryCtx, type, selectable, null, using).query();
	}

	@Override
	public QIMPL query() {
		return queryAddJoin.apply(this);
	}

	public Sql toSql() {
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
		Sql result = Sql
			.nl
			.add(res)
			.add(getExprContext().getFromTableName(selectable));
		if(joinExpr != null) {
			result = result.add(" ON ").add(getExprContext().toSql(joinExpr));
		}
		if(using.isEmpty() == false) {
			result = result.add(" USING(").add(using.map(e -> getExprContext().toSql(e)), ", ").add(") ");
		}
		return result;
	}
}
