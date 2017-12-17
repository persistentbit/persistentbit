package com.persistentbit.sql.dsl.generic.expressions.impl.experiment.strategies;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.impl.experiment.ExprTypeFactory;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class TableColumnTypeStrategy<J> extends AbstractTypeStrategy<J>{
	private final String fieldSelectionName;
	private final String columnName;

	public TableColumnTypeStrategy(
		Class<? extends DExpr<J>> typeClass,
		ExprTypeFactory exprTypeFactory,
		String fieldSelectionName,
		String fieldName
	) {
		super(typeClass, exprTypeFactory);
		this.fieldSelectionName = fieldSelectionName;
		this.columnName = fieldName;
	}

	@Override
	public SqlWithParams _toSql() {
		return SqlWithParams.sql(fieldSelectionName);
	}

	@Override
	public SqlWithParams _toSqlSelection(String alias) {
		return _toSql().add(alias == null ? "" : " AS " + alias + "_" + columnName);
	}

	@Override
	public PList<String> _getColumnNames() {
		return PList.val(columnName);
	}
}
