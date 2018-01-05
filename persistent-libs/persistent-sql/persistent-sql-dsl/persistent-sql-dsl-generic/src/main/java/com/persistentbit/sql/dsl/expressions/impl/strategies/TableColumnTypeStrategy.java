package com.persistentbit.sql.dsl.expressions.impl.strategies;

import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractTypeImpl;

/**
 * TODOC
 *
 * @author petermuys
 * @since 17/12/17
 */
public class TableColumnTypeStrategy<J> extends AbstractTypeStrategy<J>{
	private final String fieldSelectionName;
	private final String fieldName;
	private final String columnName;

	public TableColumnTypeStrategy(
		String fieldSelectionName,
		String fieldName,
		String columnName
	) {
		this.fieldSelectionName = fieldSelectionName;
		this.fieldName = fieldName;
		this.columnName = columnName;
	}

	@Override
	public TypeStrategy<J> onlyColumnName(AbstractTypeImpl impl) {
		return new TableColumnTypeStrategy<>(
			columnName, fieldName, columnName
		);
	}

	@Override
	public SqlWithParams _toSql(AbstractTypeImpl impl) {
		return SqlWithParams.sql(fieldSelectionName);
	}



	@Override
	public String _createAliasName(AbstractTypeImpl impl, String aliasPrefix) {
		return aliasPrefix == null ? null : " AS " + aliasPrefix +  columnName;
	}


}
