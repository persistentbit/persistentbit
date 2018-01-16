package com.persistentbit.sql.dsl.expressions.impl.typeimpl.others;

import com.persistentbit.collections.ImmutableArray;
import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EArray;
import com.persistentbit.sql.dsl.expressions.EInt;
import com.persistentbit.sql.dsl.expressions.impl.BinOpOperator;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.expressions.impl.strategies.BinOpTypeStrategy;
import com.persistentbit.sql.dsl.expressions.impl.strategies.TableColumnTypeStrategy;
import com.persistentbit.sql.dsl.expressions.impl.strategies.TypeStrategy;
import com.persistentbit.sql.dsl.expressions.impl.strategies.ValTypeStrategy;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeImpl;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.ArrayExprTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.TypeImplComparableMixin;
import com.persistentbit.utils.exceptions.ToDo;

import java.sql.*;

/**
 * TODOC
 *
 * @author petermuys
 * @since 4/01/18
 */
public class EArrayTypeFactory<I1 extends DExpr<JI1>, JI1>
	extends AbstractTypeFactory<EArray<I1, JI1>, ImmutableArray<JI1>> implements
	ArrayExprTypeFactory{

	public EArrayTypeFactory(ExprContext context) {
		super(context);
	}


	@Override
	public Class<EArray> getTypeClass() {
		return EArray.class;
	}

	@Override
	public Class getValueClass() {
		return ImmutableArray.class;
	}

	@Override
	public EArray buildWithStrategy(TypeStrategy<ImmutableArray<JI1>> strategy) {
		throw new UnsupportedOperationException();
	}

	@Override
	public EArray createArrayVal(Class itemClass, ExprTypeJdbcConvert itemJdbcConverter, ImmutableArray values) {
		return new EArrayImpl(new ValTypeStrategy(values), itemJdbcConverter, itemClass);
	}

	@Override
	public EArray createArrayTableColumn(Class itemClass, ExprTypeJdbcConvert itemJdbcConverter,
										 String fieldSelectionName, String fieldName, String columnName) {
		return new EArrayImpl(new TableColumnTypeStrategy<>(fieldSelectionName, fieldName, columnName), itemJdbcConverter, itemClass);
	}


	private class EArrayImpl<I1 extends DExpr<JI1>, JI1> extends AbstractTypeImpl<EArray<I1, JI1>, ImmutableArray<JI1>>
		implements EArray<I1, JI1>, TypeImplComparableMixin<EArray<I1, JI1>, ImmutableArray<JI1>>{

		private final ExprTypeJdbcConvert<JI1> itemJdbcConverter;
		private final Class<I1>                itemTypeClass;

		public EArrayImpl(TypeStrategy<ImmutableArray<JI1>> typeStrategy, ExprTypeJdbcConvert<JI1> itemJdbcConverter,
						  Class<I1> itemTypeClass) {
			super(typeStrategy);
			this.itemJdbcConverter = itemJdbcConverter;
			this.itemTypeClass = itemTypeClass;
		}

		@Override
		public String toString() {
			return "EArrayImpl<" + itemTypeClass.getSimpleName() + ">[" + typeStrategy + "]";
		}

		@Override
		public AbstractTypeFactory<EArray<I1, JI1>, ImmutableArray<JI1>> getTypeFactory() {
			return (AbstractTypeFactory) EArrayTypeFactory.this;
		}

		@Override
		public ExprTypeJdbcConvert<ImmutableArray<JI1>> getJdbcConverter() {

			return new ExprTypeJdbcConvert<>(){
				@Override
				public void setParam(int index, PreparedStatement stat, ImmutableArray<JI1> value) throws SQLException {
					if(value == null) {
						stat.setNull(index, Types.ARRAY);
					}
					else {
						Array arr = itemJdbcConverter.createJdbcArray(stat.getConnection(), value);
						stat.setArray(index, arr);
					}
				}

				@Override
				public ImmutableArray<JI1> read(int index, ResultSet resultSet) throws SQLException {
					Array arr = resultSet.getArray(index);
					if(arr == null) {
						return null;
					}
					ImmutableArray<JI1> res = itemJdbcConverter.createJavaArray(arr);
					arr.free();
					return res;
				}

				@Override
				public Array createJdbcArray(Connection con,
											 ImmutableArray<ImmutableArray<JI1>> values) throws SQLException {
					throw new ToDo();
				}

				@Override
				public ImmutableArray<ImmutableArray<JI1>> createJavaArray(Array jdbcArray) throws SQLException {
					throw new ToDo();
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

		@Override
		public EArray slice(EInt start, EInt end) {
			return buildWithStrategy(new BinOpTypeStrategy(this, BinOpOperator.opArraySlice, context
				.tuple(start, end)));
		}

		@Override
		public EArray<I1, JI1> slice(int start, int end) {
			return slice(context.val(start), context.val(end));
		}

		@Override
		public I1 get(int index) {
			return get(context.val(index));
		}

		@Override
		public I1 get(EInt index) {
			return context.binOp(itemTypeClass, this, BinOpOperator.opArrayIndex, index);
		}

		@Override
		public EArray getThis() {
			return this;
		}

		@Override
		public EArray<I1, JI1> buildWithStrategy(TypeStrategy typeStrategy) {
			return new EArrayImpl(typeStrategy, itemJdbcConverter, itemTypeClass);
		}

		@Override
		public EArray<I1, JI1> buildVal(ImmutableArray<JI1> value) {
			return new EArrayImpl<>(new ValTypeStrategy<>(value), itemJdbcConverter, itemTypeClass);
		}

		@Override
		public EArray<I1, JI1> buildBinOp(DExpr left, BinOpOperator op, DExpr right) {
			return new EArrayImpl<>(new BinOpTypeStrategy<>(left, op, right), itemJdbcConverter, itemTypeClass);
		}
	}
}
