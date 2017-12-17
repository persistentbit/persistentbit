package com.persistentbit.sql.dsl.generic.expressions.impl.experiment;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.strategies.*;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public abstract class AbstractTypeFactory<E extends DExpr<J>,J> implements ExprTypeFactory<E,J>{
	protected final ExprContext            context;
	protected final Class<E>               typeClass;
	private final   ExprTypeJdbcConvert<J> jdbcConvert;

	public AbstractTypeFactory(ExprContext context,
							   Class<E> typeClass,
							   ExprTypeJdbcConvert<J> jdbcConvert
	) {
		this.context = context;
		this.typeClass = typeClass;
		this.jdbcConvert = jdbcConvert;
	}

	protected abstract E buildWithStrategy(TypeStrategy<J> strategy);


	@Override
	public ExprContext getExprContext() {
		return context;
	}

	@Override
	public PList<DExpr> expand(DExpr<J> expr) {
		return PList.val(expr);
	}

	@Override
	public TypeStrategy<J> getTypeStrategy(DExpr<J> expr) {
		return ((ExprTypeImpl<?,J>)expr).getStrategy();
	}

	@Override
	public E buildVal(Object value) {
		return buildWithStrategy(new ValTypeStrategy<>(typeClass,this,(J)value,jdbcConvert));
	}

	@Override
	public E buildAlias(String alias) {
		return buildWithStrategy(new AliasTypeStrategie<>(typeClass,this,alias));
	}

	@Override
	public E buildBinOp(DExpr left, BinOpOperator op, DExpr right) {
		return buildWithStrategy(new BinOpTypeStrategy<>(typeClass,this,left,op,right));
	}

	@Override
	public E buildSingleOp(DExpr expr, SingleOpOperator op) {
		return buildWithStrategy(new SingleOpTypeStrategy<>(typeClass,this,expr,op));
	}

	@Override
	public E buildTableField(String fieldSelectionName, String fieldName) {
		return buildWithStrategy(new TableColumnTypeStrategy<>(typeClass,this,fieldSelectionName,fieldName));
	}
}
