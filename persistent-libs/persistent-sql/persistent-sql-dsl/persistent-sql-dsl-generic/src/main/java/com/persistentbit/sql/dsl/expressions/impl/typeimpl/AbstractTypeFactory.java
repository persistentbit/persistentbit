package com.persistentbit.sql.dsl.expressions.impl.typeimpl;

import com.persistentbit.collections.ImmutableArray;
import com.persistentbit.collections.PMap;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EArray;
import com.persistentbit.sql.dsl.expressions.impl.BinOpOperator;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.SingleOpOperator;
import com.persistentbit.sql.dsl.expressions.impl.strategies.*;

import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public abstract class AbstractTypeFactory<E extends DExpr<J>, J> implements ExprTypeFactory<E, J>{

	protected final ExprContext            context;

	public AbstractTypeFactory(ExprContext context) {
		this.context = context;
	}


	public abstract E buildWithStrategy(TypeStrategy<J> strategy);



	@Override
	public ExprContext getExprContext() {
		return context;
	}


	public TypeStrategy<J> getTypeStrategy(DExpr<J> expr) {
		return ((AbstractTypeImpl<?, J>) expr).getTypeStrategy();
	}

	@Override
	public <V extends J> E buildVal(V value) {
		return buildWithStrategy(
			new ValTypeStrategy<>(value));
	}

	@Override
	public E buildParam(Function<PMap<String, Object>, Object> paramGetter) {
		return buildWithStrategy(
			new ParamTypeStrategy<>(getTypeClass(), paramGetter)
		);
	}

	@Override
	public E buildCall(String callName, Object... params) {
		return buildWithStrategy(
			new FunctionCallTypeStrategy<>(callName, params)
		);
	}

	@Override
	public E buildBinOp(DExpr left, BinOpOperator op, DExpr right) {
		return buildWithStrategy(
			new BinOpTypeStrategy<>(left, op, right));
	}

	@Override
	public E buildSingleOp(DExpr expr, SingleOpOperator op) {
		return buildWithStrategy(
			new SingleOpTypeStrategy<>(expr, op));
	}

	@Override
	public E buildTableField(String fieldSelectionName, String fieldName, String columnName) {
		return buildWithStrategy(
			new TableColumnTypeStrategy<>(fieldSelectionName, fieldName, columnName));
	}

	@Override
	public EArray<E, J> buildArrayTableField(String fieldSelectionName, String fieldName, String columnName) {
		return context.buildArrayTableColumn(getTypeClass(), context
			.getJavaJdbcConverter(getValueClass()), fieldSelectionName, fieldName, columnName);
	}

	@Override
	public <V extends J> EArray<E, J> buildArrayVal(ImmutableArray<V> values) {
		return context.buildArrayVal(getTypeClass(), context.getJavaJdbcConverter(getValueClass()), values);
	}


	public abstract Class getValueClass();


}
