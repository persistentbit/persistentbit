package com.persistentbit.sql.dsl.generic.expressions.impl.experiment.strategies;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.ExprContext;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.ExprTypeFactory;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.ExprTypeImpl;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.sql.utils.rowreader.RowReader;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class AbstractTypeStrategy<J> implements TypeStrategy<J>{

	protected final Class<? extends DExpr<J>> typeClass;


	protected final ExprTypeFactory exprTypeFactory;

	public AbstractTypeStrategy(
		Class<? extends DExpr<J>> typeClass,
		ExprTypeFactory exprTypeFactory
	) {
		this.typeClass = typeClass;
		this.exprTypeFactory = exprTypeFactory;
	}

	protected ExprContext getExprContext() {
		return exprTypeFactory.getExprContext();
	}
	protected ExprTypeFactory getExprTypeFactory(DExpr expr){
		return getExprContext().getTypeFactory(expr);
	}
	protected TypeStrategy<J> getTypeStrategy(DExpr expr){
		ExprTypeImpl impl = (ExprTypeImpl)expr;
		return impl.getStrategy();
	}


	protected UnsupportedOperationException exception(String message) {
		return new UnsupportedOperationException(typeClass.getSimpleName() + ": " + message);
	}

	protected UnsupportedOperationException exception() {
		return new UnsupportedOperationException(typeClass.getSimpleName());
	}

	@Override
	public SqlWithParams _toSqlSelection(String alias
	) {
		throw exception();
	}

	@Override
	public SqlWithParams _toSql(
	) {
		throw exception();
	}


	@Override
	public J _read(RowReader rowReader
	) {
		throw exception();
	}

	/*@Override
	public DExpr<J> _withAlias(String alias) {
		return exprTypeFactory.buildAlias(alias);
	}

	@Override
	public PList<DExpr> _expand(DExpr<J> expr) {
		return exprTypeFactory.expand(expr);
	}*/

	@Override
	public PList<String> _getColumnNames() {
		return PList.val(null);
	}
}
