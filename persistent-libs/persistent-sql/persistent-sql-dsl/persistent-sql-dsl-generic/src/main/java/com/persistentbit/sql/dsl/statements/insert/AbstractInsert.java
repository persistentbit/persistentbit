package com.persistentbit.sql.dsl.statements.insert;

import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.tables.Table;

/**
 * TODOC
 *
 * @author petermuys
 * @since 19/12/17
 */
public class AbstractInsert<EALL extends DExpr<J>, J>{

	protected final ExprContext     context;
	private final   Table<EALL, J>  into;
	private final   PList<String>   columnNames;
	private final   PSet<String>    withDefaults;
	private final   PList<Object[]> rows;

	public AbstractInsert(ExprContext context, Table<EALL, J> into,
						  PList<String> columnNames,
						  PSet<String> withDefaults,
						  PList<Object[]> rows
	) {
		this.context = context;
		this.into = into;
		this.columnNames = columnNames;
		this.withDefaults = withDefaults;
		this.rows = rows;
	}


}
