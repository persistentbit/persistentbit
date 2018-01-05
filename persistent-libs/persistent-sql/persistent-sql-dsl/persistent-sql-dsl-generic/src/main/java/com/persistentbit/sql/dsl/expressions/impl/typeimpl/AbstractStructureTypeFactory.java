package com.persistentbit.sql.dsl.expressions.impl.typeimpl;

import com.persistentbit.collections.ImmutableArray;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PMap;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EArray;
import com.persistentbit.sql.dsl.expressions.impl.BinOpOperator;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.SingleOpOperator;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;

import java.util.function.Function;

/**
 * Abstract Base class for {@link ExprTypeFactory}s for Structures and Tables
 *
 * @author petermuys
 * @since 23/12/17
 */
public abstract class AbstractStructureTypeFactory<E extends DExpr<J>, J> implements ExprTypeFactory<E, J>{

	protected final ExprContext                   context;
	private final   PStream<StructureField<E, J>> fields;

	protected AbstractStructureTypeFactory(ExprContext context) {
		this.context = context;
		this.fields = buildFields().lazy();
	}


	protected abstract PList<StructureField<E, J>> buildFields();

	protected abstract E createExpression(PStream<DExpr> fieldValues);

	protected abstract J buildValue(Object[] fieldValues);


	protected <EF extends DExpr<JF>, JF> StructureField<EF, JF> createField(Class<? extends DExpr> typeClass,
																			String columnName,
																			String fieldName,
																			Function<JF, Object> valueGetter,
																			Function<EF, DExpr> expressionGetter
	) {
		return new StructureField<>(context, typeClass, columnName, fieldName, valueGetter, expressionGetter, null);
	}


	@Override
	public <V extends J> E buildVal(V value) {
		return createExpression(
			fields.map(sf ->
						   sf.getTypeFactory()
							   .buildVal(sf.valueGetter.apply(value))
			));
	}

	@Override
	public EArray<E, J> buildArrayTableField(String fieldSelectionName, String fieldName, String columnName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <V extends J> EArray<E, J> buildArrayVal(ImmutableArray<V> values) {
		throw new UnsupportedOperationException();
	}

	@Override
	@SuppressWarnings("unchecked")
	public E buildParam(Function<PMap<String, Object>, Object> paramGetter) {
		return createExpression(fields.map(sf ->
											   sf.getTypeFactory().buildParam(map -> sf.valueGetter
												   .apply((J) paramGetter.apply((PMap<String, Object>) map)))
		));
	}

	//	@Override
	//	@SuppressWarnings("unchecked")
	public ExprTypeJdbcConvert<J> getJdbcConverter(E expr) {
		PList<ExprTypeJdbcConvert> jdbcList =
			fields.map(sf -> context.getJdbcConverter(sf.expressionGetter.apply(expr))).plist();

		return ExprTypeJdbcConvert.createMultiColumn(jdbcList, v -> isNull(v) ? null : buildValue(v));
	}

	private boolean isNull(Object[] list) {
		if(list == null) {
			return true;
		}
		for(Object o : list) {
			if(o != null) {
				return false;
			}
		}
		return true;
	}


	@Override
	public E buildCall(String callName, Object... params) {
		throw new UnsupportedOperationException("call " + callName + " on " + getTypeClass().getSimpleName());
	}

	@SuppressWarnings("unchecked")
	private Function<PMap<String, Object>, Object> createGetter(Function<PMap<String, Object>, Object> pg,
																Function<J, Object> fieldGetter
	) {
		return map -> {
			J v = (J) pg.apply(map);
			if(v == null) {
				return null;
			}
			return fieldGetter.apply(v);
		};
	}


	public E buildAlias(E expr, String alias) {
		return createExpression(fields.map(sf -> (DExpr) context
			.buildAlias(sf.expressionGetter.apply(expr), alias + sf.fieldName)
		));
	}

	@Override
	public E buildBinOp(DExpr left, BinOpOperator op, DExpr right) {
		switch(op) {
			case opAssign:
				return assign((E) left, (E) right);
		}
		throw new UnsupportedOperationException(op.name());
	}

	private E assign(E left, E right) {
		return createExpression(
			fields.map(sf -> sf.getTypeFactory()
				.buildBinOp(
					sf.expressionGetter.apply(left),
					BinOpOperator.opAssign,
					sf.expressionGetter.apply(right)
				)
			)
		);
	}

	@Override
	public E buildSingleOp(DExpr expr, SingleOpOperator op) {
		throw new UnsupportedOperationException(op.name());
	}

	@Override
	public E buildTableField(String fieldSelectionName, String fieldName, String columnName) {
		return createExpression(fields.map(sf ->
											   sf.getTypeFactory()
												   .buildTableField(fieldSelectionName + sf.columnName, fieldName + sf.fieldName, columnName + sf.columnName)
		));
	}

	public E onlyTableColumn(E expr) {
		return createExpression(
			fields.map(sf ->
						   context.onlyTableColumn(sf.expressionGetter.apply(expr))
			)
		);
	}


	@SuppressWarnings("unchecked")
	public E buildSelection(E expr, String prefixAlias) {
		return createExpression(fields.map(sf -> (DExpr) context
			.buildSelection(sf.expressionGetter.apply(expr), prefixAlias)
		));
	}

	@SuppressWarnings("unchecked")
	public PList<DExpr> expand(E expr) {
		return fields
			.map(sf -> context.expand(sf.expressionGetter.apply(expr)))
			.<DExpr>flatten().plist();

	}


	@SuppressWarnings("unchecked")
	public SqlWithParams toSql(E expr) {
		PStream<SqlWithParams> sql =
			fields.map(sf -> context.toSql(sf.expressionGetter.apply(expr)));
		if(sql.isEmpty()) {
			return SqlWithParams.empty;
		}
		return sql.tail().fold(sql.head(), (a, b) -> a.add(", ").add(b));

	}

	@Override
	public ExprContext getExprContext() {
		return context;
	}


}

