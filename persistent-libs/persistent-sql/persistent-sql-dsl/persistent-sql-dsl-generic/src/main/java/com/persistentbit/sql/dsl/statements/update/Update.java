package com.persistentbit.sql.dsl.statements.update;

import com.persistentbit.collections.PList;
import com.persistentbit.collections.PMap;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.sql.dsl.expressions.impl.BinOpOperator;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;
import com.persistentbit.sql.dsl.tables.Table;
import com.persistentbit.sql.transactions.DbTransaction;
import com.persistentbit.sql.work.DbWork;

import java.sql.PreparedStatement;

/**
 * TODOC
 *
 * @author petermuys
 * @since 19/12/17
 */
public class Update implements DbWork<Integer>{

	private final ExprContext  context;
	private final Table        table;
	private final EBool        where;
	private final PList<DExpr> setExpr;

	public Update(ExprContext context, Table table, EBool where,
				  PList<DExpr> setExpr
	) {
		this.context = context;
		this.table = table;
		this.where = where;
		this.setExpr = setExpr;
	}

	public Update(ExprContext context, Table table) {
		this(context, table, context.val(true), PList.empty());
	}

	public Update where(EBool where) {
		return new Update(context, table, where, setExpr);
	}

	public <E extends DExpr<J>, J> Update set(E orgLeft, E value) {
		ExprTypeFactory<E, J> tf   = context.getTypeFactory(orgLeft);
		E                     left = (E) context.onlyTableColumn(orgLeft);
		return new Update(context, table, where,
						  setExpr.plus(context.binOp(orgLeft, left, BinOpOperator.opAssign, value))
		);
	}

	public <E extends DExpr<J>, J> Update set(E left, J value) {
		ExprTypeFactory<E, J> tf = context.getTypeFactory(left);
		return set(left, context.buildVal(left, value));
	}

	@Override
	public Result<Integer> run(DbTransaction transaction) {
		return Result.function().code(log -> {
			SqlWithParams sql   = SqlWithParams.sql("UPDATE " + context.getFromTableName(table) + " SET ");
			boolean       first = true;
			for(DExpr expr : setExpr) {
				if(first == false) {
					sql = sql.add(", ");
				}
				first = false;
				sql = sql.add(context.toSql(expr));
			}
			SqlWithParams finalSql = sql.add(" WHERE ").add(context.toSql(where));
			return transaction.run(con -> {

				try(PreparedStatement stat = con.prepareStatement(finalSql.getSql())) {
					finalSql.setParams(PMap.empty(), stat);
					return Result.success(stat.executeUpdate());
				}
			});
		});
	}
}
