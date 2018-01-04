package com.persistentbit.sql.dsl.expressions.impl.typeimpl.others;

import com.persistentbit.collections.ImmutableArray;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PMap;
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

import java.sql.*;
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
	public DExpr buildAlias(DExpr expr, String alias) {
		return new EArrayImplAlias((EArrayImpl) expr, alias);
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
		return ((EArrayImpl) genExpr).onlyTableColumn();
	}

	@Override
	public DExpr buildVal(Object value) {
		return new EArrayImplVal((ImmutableArray) value);
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



		@Override
		public EArray<E1, J1> slice(EInt start, EInt end) {
			return new EArrayImplSlice<>(itemTypeClass, this, start, end);
		}

		@Override
		public E1 get(EInt index) {
			return context.getTypeFactory(itemTypeClass).buildBinOp(this, BinOpOperator.opArrayIndex, index);
		}

		protected abstract SqlWithParams toSql();

		@Override
		public EArray<E1, J1> getThis() {
			return this;
		}

		public DExpr onlyTableColumn() {
			return this;
		}


		public ExprTypeJdbcConvert getJdbcConverter() {
			ExprTypeFactory<E1, J1> itemTypeFactory = context.getTypeFactory(itemTypeClass);
			ExprTypeJdbcConvert     jdbcConvert     = itemTypeFactory.getJdbcConverter(itemTypeFactory.buildVal(null));
			return new ExprTypeJdbcConvert<ImmutableArray>(){
				@Override
				public void setParam(int index, PreparedStatement stat, ImmutableArray value) throws SQLException {
					if(value == null) {
						stat.setNull(index, Types.ARRAY);
					}
					else {
						Connection con = stat.getConnection();
						Array      arr = jdbcConvert.createJdbcArray(stat.getConnection(), value);
						stat.setArray(index, arr);
					}

				}

				@Override
				public ImmutableArray read(int index, ResultSet resultSet) throws SQLException {
					Array array        = resultSet.getArray(index);
					ImmutableArray res = jdbcConvert.createJavaArray(array);
					array.free();
					return res;
				}

				@Override
				public Array createJdbcArray(Connection con, ImmutableArray<ImmutableArray> values
				) throws SQLException {
					throw new ToDo();
					//					if(values == null) {
					//						return null;
					//					}
					//					Object[] jdbcValues = values.map(v -> toJdbc.apply(v)).toArray(Object.class);
					//					return con.createArrayOf(arrayType, jdbcValues);
				}

				@Override
				public ImmutableArray<ImmutableArray> createJavaArray(Array jdbcArray) {
					throw new ToDo();
					//					if(jdbcArray == null) {
					//						return null;
					//					}
					//					ArrayList<J> res = new ArrayList<>();
					//					try(ResultSet rs = jdbcArray.getResultSet()) {
					//						while(rs.next()) {
					//							res.add(toJava.apply(rs.getObject(2)));
					//						}
					//					}
					//					return ImmutableArray.from(res);
				}

				@Override
				public int columnCount() {
					return 1;
				}

				@Override
				public PList<ExprTypeJdbcConvert> expand() {
					return PList.val(this);
				}
			};
		}
	}

	private class EArrayImplVal<E1 extends DExpr<J1>, J1> extends EArrayImpl<E1, J1>{

		private ImmutableArray<J1> values;

		private EArrayImplVal(Class<E1> itemClass, ImmutableArray<J1> values) {
			super(itemClass);
			this.values = values;
		}

		@Override
		protected SqlWithParams toSql() {
			return SqlWithParams.param(new PrepStatParam(){
				@Override
				public int _setPrepStatement(PMap<String, Object> extParams, PreparedStatement stat, int index
				) throws SQLException {
					ExprTypeJdbcConvert jdbc = getJdbcConverter();
					jdbc.setParam(index, stat, values);
					return jdbc.columnCount();
				}
			});
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

		@Override
		public DExpr onlyTableColumn() {
			return new EArrayImplTableColumn(itemTypeClass, columnName, fieldName, columnName);
		}

		@Override
		protected SqlWithParams toSql() {
			return SqlWithParams.sql(fieldSelectionName);
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

	private class EArrayImplAlias<E1 extends DExpr<J1>, J1> extends EArrayImpl<E1, J1>{

		private final EArrayImpl<E1, J1> array;
		private final String             alias;

		public EArrayImplAlias(EArrayImpl<E1, J1> array, String alias) {
			super(array.itemTypeClass);
			this.array = array;
			this.alias = alias;
		}

		@Override
		protected SqlWithParams toSql() {
			return SqlWithParams.sql(alias);
		}
	}

}
