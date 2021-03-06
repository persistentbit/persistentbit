package com.persistentbit.sql.dsl.expressions.impl.typeimpl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeImpl;
import com.persistentbit.sql.dsl.expressions.impl.strategies.AliasTypeStrategy;
import com.persistentbit.sql.dsl.expressions.impl.strategies.SelectionStrategy;
import com.persistentbit.sql.dsl.expressions.impl.strategies.TypeStrategy;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public abstract class AbstractTypeImpl<E extends DExpr<J>, J> implements ExprTypeImpl<E, J>{


	protected final TypeStrategy<J> typeStrategy;

	public AbstractTypeImpl(TypeStrategy<J> typeStrategy) {
		this.typeStrategy = typeStrategy;
	}

	public TypeStrategy<J> getTypeStrategy() {
		return typeStrategy;
	}


	@Override
	public String toString() {
		return typeStrategy.toString();
	}

	@Override
	public E buildAlias(String alias) {
		return buildWithStrategy(
			new AliasTypeStrategy<>(alias));
	}

	@Override
	public E buildSelection(String prefixAlias) {
		if(prefixAlias == null) {
			return (E) this;
		}
		String newAlias = typeStrategy._createAliasName(this, prefixAlias);
		return buildWithStrategy(new SelectionStrategy(this, newAlias));

	}

	@Override
	public E onlyTableColumn() {
		return buildWithStrategy(getTypeStrategy().onlyColumnName(this));
	}

	@Override
	public PList<DExpr> expand() {
		return PList.val(this);
	}

	@Override
	public SqlWithParams toSql() {
		return getTypeStrategy()._toSql(this);
	}


	public final Class<? extends DExpr> getTypeClass() {
		return getTypeFactory().getTypeClass();
	}


	public E buildWithStrategy(TypeStrategy typeStrategy) {
		return (E) getTypeFactory().buildWithStrategy(typeStrategy);
	}

	public final ExprContext getContext() {
		return getTypeFactory().context;
	}

	public abstract AbstractTypeFactory<E, J> getTypeFactory();
}
