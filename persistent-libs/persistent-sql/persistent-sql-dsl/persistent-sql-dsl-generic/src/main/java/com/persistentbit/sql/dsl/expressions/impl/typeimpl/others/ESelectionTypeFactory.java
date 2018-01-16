package com.persistentbit.sql.dsl.expressions.impl.typeimpl.others;

import com.persistentbit.collections.ImmutableArray;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PMap;
import com.persistentbit.sql.dsl.Sql;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EArray;
import com.persistentbit.sql.dsl.expressions.ESelection;
import com.persistentbit.sql.dsl.expressions.impl.*;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.statements.select.impl.SelectionImpl;
import com.persistentbit.utils.exceptions.ToDo;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 19/12/17
 */

public class ESelectionTypeFactory<J> implements ExprTypeFactory<ESelection<J>, J>{
	private final ExprContext context;
	public ESelectionTypeFactory(ExprContext context) {
		this.context = context;
	}


	@Override
	public ESelection<J> buildVal(Object value) {
		throw new UnsupportedOperationException("buildVal for a ESubQuery");
	}

	@Override
	public ESelection<J> buildParam(Function<PMap<String, Object>, Object> paramGetter) {
		throw new ToDo();
	}

	@Override
	public ESelection<J> buildCall(String callName, Object... params) {
		throw new ToDo();
	}



	@Override
	public ESelection<J> buildBinOp(DExpr left, BinOpOperator op, DExpr right) {
		throw new UnsupportedOperationException("buildVal for a ESubQuery");
	}

	@Override
	public ESelection<J> buildSingleOp(DExpr expr, SingleOpOperator op) {
		throw new UnsupportedOperationException("buildVal for a ESubQuery");
	}

	@Override
	public ESelection<J> buildTableField(String fieldSelectionName, String fieldName, String columnName) {
		throw new UnsupportedOperationException("buildVal for a ESubQuery");
	}

	@Override
	public ESelection<J> buildCustomSql(Supplier<Sql> sqlSupplier) {
		throw new UnsupportedOperationException("ESubQuery");
	}

	@Override
	public EArray<ESelection<J>, J> buildArrayTableField(String fieldSelectionName, String fieldName,
														 String columnName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <V extends J> EArray<ESelection<J>, J> buildArrayVal(ImmutableArray<V> values) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ExprContext getExprContext() {
		return context;
	}

	@Override
	public Class<ESelection<J>> getTypeClass() {
		Class cls = ESelection.class;
		return cls;
	}


	public ESelection<J> create(SelectionImpl<?, J> selection) {
		Sql sql = Sql.sql("(").add(selection.toSql()).add(")");
		return new ESelectionImpl<>(selection.getJdbcConverter(),this,sql);
	}

	static public class ESelectionImpl<J1> implements ExprTypeImpl<DExpr<J1>,J1>, ESelection<J1>{

		private final ExprTypeFactory<?, J1>  typeFactory;
		private final Sql                     sql;
		private final ExprTypeJdbcConvert<J1> jdbcConverter;

		public ESelectionImpl(ExprTypeJdbcConvert<J1> jdbcConverter, ExprTypeFactory<?, J1> typeFactory, Sql sql) {
			this.jdbcConverter = jdbcConverter;
			this.typeFactory = typeFactory;
			this.sql = sql;
		}
		public ExprTypeJdbcConvert<J1>	getJdbcConverter() {
			return jdbcConverter;
		}

		public Sql toSql() {
			return sql;
		}

		@Override
		public ExprTypeFactory getTypeFactory() {
			return typeFactory;
		}

		@Override
		public String toString() {
			return toSql().toString();
		}

		@Override
		public DExpr<J1> buildAlias(String alias) {
			throw new UnsupportedOperationException(alias);
		}

		@Override
		public DExpr<J1> buildSelection(String prefixAlias) {
			if(prefixAlias == null) {
				return this;
			}

			return new ESelectionImpl<>(getJdbcConverter(), getTypeFactory(), toSql().add(" AS " + prefixAlias));
		}

		@Override
		public DExpr<J1> onlyTableColumn() {
			throw new UnsupportedOperationException();
		}

		@Override
		public PList<DExpr> expand() {
			return PList.val(this);
		}

	}
}
