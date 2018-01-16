package com.persistentbit.sql.dsl.expressions.impl.typeimpl;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.Sql;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeImpl;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;

/**
 * TODOC
 *
 * @author petermuys
 * @since 5/01/18
 */
public interface StructTypeImplMixin<E extends DExpr<J>, J> extends ExprTypeImpl<E, J>{

	@Override
	AbstractStructureTypeFactory<E, J> getTypeFactory();

	E getThis();

	@Override
	default E buildAlias(String alias) {
		return getTypeFactory().buildAlias(getThis(), alias);
	}

	@Override
	default E buildSelection(String prefixAlias) {
		return getTypeFactory().buildSelection(getThis(), prefixAlias);
	}

	@Override
	default E onlyTableColumn() {
		return getTypeFactory().onlyTableColumn(getThis());
	}

	@Override
	default PList<DExpr> expand() {
		return getTypeFactory().expand(getThis());
	}

	@Override
	default Sql toSql() {
		return getTypeFactory().toSql(getThis());
	}

	@Override
	default ExprTypeJdbcConvert<J> getJdbcConverter() {
		return getTypeFactory().getJdbcConverter(getThis());
	}
}
