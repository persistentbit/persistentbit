package com.pbtest.postgres.inserts;

import com.pbtest.postgres.tables.TSalutations;
import com.pbtest.postgres.values.Salutations;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

public class InsertSalutations extends Insert<TSalutations, Void>{


	public InsertSalutations(ExprContext context, TSalutations into, PList<String> columnNames,
							 PSet<String> withDefaults, String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertSalutations(ExprContext context, TSalutations into) {
		this(context, into, columnNames, PSet.empty(), null, PList.empty());
	}

	private static final PList<String> columnNames = PList.val("salutation_code", "description");

	public InsertSalutations add(@Nullable String salutationCode, @Nullable String description) {
		Object[] row = new Object[]{
			salutationCode
			, description
		};
		return new InsertSalutations(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertSalutations add(Salutations value) {
		return add(
			value.getSalutationCode(), value.getDescription()
		);
	}
}
