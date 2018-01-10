package com.pbtest.postgres.inserts;

import com.pbtest.postgres.tables.TPeople;
import com.pbtest.postgres.values.People;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

public class InsertPeople extends Insert<TPeople, Long>{


	public InsertPeople(ExprContext context, TPeople into, PList<String> columnNames, PSet<String> withDefaults,
						String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertPeople(ExprContext context, TPeople into) {
		this(context, into, columnNames, PSet.empty(), "person_id", PList.empty());
	}

	private static final PList<String> columnNames = PList.val("person_id");

	public InsertPeople add(@Nullable Long personId) {
		Object[] row = new Object[]{
			personId
		};
		return new InsertPeople(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertPeople add(People value) {
		return add(
			value.getPersonId()
		);
	}
}
