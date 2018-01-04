package com.persistentbit.sql.dsl.expressions.impl.strategies;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public abstract class AbstractTypeStrategy<J> implements TypeStrategy<J>{

	@Override
	public String toString() {
		return _toSql().toString();
	}
/*
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


	protected TypeStrategy<J> getTypeStrategy(DExpr<J> expr){
		AbstractTypeImpl<?,J> impl = (AbstractTypeImpl<?,J>)expr;
		return impl.getTypeStrategy();
	}



	protected UnsupportedOperationException exception(String message) {
		return new UnsupportedOperationException(typeClass.getSimpleName() + ": " + message);
	}

	protected UnsupportedOperationException exception() {
		return new UnsupportedOperationException(typeClass.getSimpleName());
	}



	@Override
	public SqlWithParams _toSql(
	) {
		throw exception();
	}



	@Override
	public String _createAliasName(String aliasPrefix) {
		return aliasPrefix;
	}
*/

	/*@Override
	public DExpr<J> _withAlias(String alias) {
		return exprTypeFactory.buildAlias(alias);
	}

	@Override
	public PList<DExpr> _expand(DExpr<J> expr) {
		return exprTypeFactory.expand(expr);
	}*/


}
