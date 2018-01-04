package com.persistentbit.sql.dsl.expressions.impl.typeimpl.others;

import com.persistentbit.collections.ImmutableArray;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EArray;
import com.persistentbit.sql.dsl.expressions.EInt;
import com.persistentbit.sql.dsl.expressions.ETuple2;
import com.persistentbit.sql.dsl.expressions.impl.*;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.TypeImplComparableMixin;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.tuples.Tuple2TypeFactory;
import com.persistentbit.utils.exceptions.ToDo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 4/01/18
 */
public class EArrayTypeFactory implements ExprTypeFactory{

	private final ExprContext context;


	@Override
	public Class getTypeClass() {
		return EArray.class;
	}

	public EArrayTypeFactory(ExprContext context) {
		this.context = context;
	}


	@Override
	public DExpr buildAlias(String alias) {
		throw new UnsupportedOperationException("buildAlias on a ETuple2");
	}

	@Override
	public DExpr buildBinOp(DExpr left, BinOpOperator op, DExpr right
	) {
		throw new UnsupportedOperationException("BinOp " + op);
	}

	@Override
	public DExpr buildSingleOp(DExpr expr, SingleOpOperator op
	) {
		throw new UnsupportedOperationException("SingleOp " + op);
	}

	@Override
	public DExpr buildTableField(String fieldSelectionName, String fieldName, String columnName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public DExpr onlyTableColumn(DExpr genExpr) {
		ETuple2 expr = (ETuple2) genExpr;
		DExpr   e1   = context.getTypeFactory(expr.v1()).onlyTableColumn(expr);
		DExpr   e2   = context.getTypeFactory(expr.v2()).onlyTableColumn(expr);
		return new Tuple2TypeFactory.ETuple2Impl(e1, e2);
	}

	@Override
	public DExpr buildVal(Object value) {
		throw new UnsupportedOperationException("buildVal on ETuple2");
	}


	@Override
	public DExpr buildParam(Function paramGetter) {
		throw new ToDo();
	}

	@Override
	public DExpr buildCall(String callName, Object... params) {
		throw new ToDo();
	}

	@Override
	public DExpr buildSelection(DExpr genExpr, String prefixAlias) {
		ETuple2 expr = (ETuple2) genExpr;
		DExpr e1 = context.getTypeFactory(expr.v1()).buildSelection(expr.v1(),
																	prefixAlias == null ? null : prefixAlias + "t1_"
		);
		DExpr e2 = context.getTypeFactory(expr.v2()).buildSelection(expr.v2(),
																	prefixAlias == null ? null : prefixAlias + "t2_"
		);
		return new Tuple2TypeFactory.ETuple2Impl<>(e1, e2);
	}

	@Override
	public PList<DExpr> expand(DExpr genExpr) {
		return PList.val(genExpr);
	}

	@Override
	public ExprTypeJdbcConvert getJdbcConverter(DExpr expr) {
		EArrayImpl impl = (EArrayImpl) expr;
		return impl.getJdbcConverter();
	}

	@Override
	public SqlWithParams toSql(DExpr genExpr) {
		EArrayImpl impl = (EArrayImpl) genExpr;
		return impl.toSql();
	}


	@Override
	public ExprContext getExprContext() {
		return context;
	}

	private abstract class EArrayImpl<E1 extends DExpr<J1>, J1> implements EArray<E1, J1>,
		ExprTypeImpl<EArray<E1, J1>, ImmutableArray<J1>>, TypeImplComparableMixin<EArray<E1, J1>, ImmutableArray<J1>{

		protected final Class<E1> itemTypeClass;

		public EArrayImpl(Class<E1> itemTypeClass) {
			this.itemTypeClass = itemTypeClass;
		}

		@Override
		public ExprTypeFactory getTypeFactory() {
			return EArrayTypeFactory.this;
		}

		public abstract ExprTypeJdbcConvert getJdbcConverter();

		@Override
		public EArray<E1, J1> slice(EInt start, EInt end) {
			return null;
		}

		protected abstract SqlWithParams toSql();

		@Override
		public EArray<E1, J1> getThis() {
			return this;
		}
	}

	private class EArrayImplVal<E1 extends DExpr<J1>, J1> extends EArrayImpl<E1, J1>{

		private ImmutableArray<J1> values;

		private EArrayImplVal(Class<E1> itemClass, ImmutableArray<J1> values) {
			super(itemClass);
			this.values = values;
		}


		@Override
		public ExprTypeJdbcConvert getJdbcConverter() {
			ExprTypeFactory<E1, J1> itemTypeFactory = context.getTypeFactory(itemTypeClass);
			return new ExprTypeJdbcConvert<ImmutableArray>(){
				@Override
				public void setParam(int index, PreparedStatement stat, ImmutableArray value) throws SQLException {
					for(J1 item : values) {
						E1                      itemExpr = itemTypeFactory.buildVal(item);
						ExprTypeJdbcConvert<J1> jdbc     = itemTypeFactory.getJdbcConverter(itemExpr);
						jdbc.setParam(index, stat, item);
						index += jdbc.columnCount();
					}
				}

				@Override
				public ImmutableArray read(int index, ResultSet resultSet) throws SQLException {
					throw new UnsupportedOperationException("Not supported");
				}

				@Override
				public int columnCount() {
					int count = 0;
					for(J1 item : values) {
						E1                      itemExpr = itemTypeFactory.buildVal(item);
						ExprTypeJdbcConvert<J1> jdbc     = itemTypeFactory.getJdbcConverter(itemExpr);
						count += jdbc.columnCount();
					}
					return count;
				}

				@Override
				public PList<ExprTypeJdbcConvert> expand() {
					return PList.val(this);
				}
			};
		}
	}

	private class EArrayImplTableColumn<E1 extends DExpr<J1>, J1> extends EArrayImpl<E1, J1>{

		private final String fieldSelectionName;
		private final String fieldName;
		private final String columnName;

		public EArrayImplTableColumn(Class<E1> itemTypeClass, String fieldSelectionName, String fieldName,
									 String columnName
		) {
			super(itemTypeClass);
			this.fieldSelectionName = fieldSelectionName;
			this.fieldName = fieldName;
			this.columnName = columnName;
		}
	}

	private class EArrayImplSlice<E1 extends DExpr<J1>, J1> extends EArrayImpl<E1, J1>{

		private final EArrayImpl<E1, J1> array;
		private final EInt               start;
		private final EInt               end;

		public EArrayImplSlice(Class<E1> itemTypeClass, EArrayImpl<E1, J1> array, EInt start, EInt end) {
			super(itemTypeClass);
			this.array = array;
			this.start = start;
			this.end = end;
		}

		@Override
		protected SqlWithParams toSql() {
			return array.toSql().add("[").add(context.toSql(start)).add(":").add(context.toSql(end)).add("]");
		}


	}
}
