package com.persistentbit.sql.dsl.expressions.impl.typeimpl.others;

import com.persistentbit.collections.ImmutableArray;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PMap;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EArray;
import com.persistentbit.sql.dsl.expressions.EList;
import com.persistentbit.sql.dsl.expressions.impl.*;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.expressions.impl.strategies.TypeStrategy;
import com.persistentbit.utils.exceptions.ToDo;

import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class EListTypeFactory implements ExprTypeFactory<EList, PList<DExpr>>{

	private final ExprContext context;

	public EListTypeFactory(ExprContext context) {
		this.context = context;
	}

	@Override
	public Class<EList> getTypeClass() {
		return EList.class;
	}

	@Override
	public <V extends PList<DExpr>> EList buildVal(V value) {
		throw new ToDo();
	}

	@Override
	public EList buildParam(Function<PMap<String, Object>, Object> paramGetter) {
		throw new ToDo();
	}

	@Override
	public EList buildCall(String callName, Object... params) {
		throw new ToDo();
	}


	@Override
	public EList buildBinOp(DExpr left, BinOpOperator op, DExpr right) {
		throw new ToDo();
	}

	@Override
	public EList buildSingleOp(DExpr expr, SingleOpOperator op
	) {
		throw new ToDo();
	}

	@Override
	public EList buildTableField(String fieldSelectionName, String fieldName, String columnName) {
		throw new ToDo();
	}

	@Override
	public EArray<EList, PList<DExpr>> buildArrayTableField(String fieldSelectionName, String fieldName,
															String columnName) {
		throw new ToDo();
	}

	@Override
	public <V extends PList<DExpr>> EArray<EList, PList<DExpr>> buildArrayVal(ImmutableArray<V> values) {
		throw new ToDo();
	}

	@Override
	public ExprContext getExprContext() {
		return context;
	}

	private class EListImpl implements ExprTypeImpl<EList, PList<DExpr>>{

		private final PList<DExpr> expressions;

		public EListImpl(TypeStrategy<PList> typeStrategy,
						 PList<DExpr> expressions
		) {
			throw new ToDo();
		}

		@Override
		public ExprTypeFactory<EList, PList<DExpr>> getTypeFactory() {
			return EListTypeFactory.this;
		}

		@Override
		public EList buildAlias(String alias) {
			throw new UnsupportedOperationException();
		}

		@Override
		public EList buildSelection(String prefixAlias) {
			throw new UnsupportedOperationException();
		}

		@Override
		public EList onlyTableColumn() {
			throw new UnsupportedOperationException();
		}

		@Override
		public PList<DExpr> expand() {
			throw new ToDo();
		}

		@Override
		public SqlWithParams toSql() {
			throw new ToDo();
		}

		@Override
		public ExprTypeJdbcConvert<PList<DExpr>> getJdbcConverter() {
			throw new ToDo();
		}
	}
}
