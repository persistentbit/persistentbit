package com.pbtest.postgres.inserts;

import com.pbtest.postgres.tables.TAnimals;
import com.pbtest.postgres.values.Animals;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

public class InsertAnimals extends Insert<TAnimals, Integer>{


	public InsertAnimals(ExprContext context, TAnimals into, PList<String> columnNames, PSet<String> withDefaults,
						 String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertAnimals(ExprContext context, TAnimals into) {
		this(context, into, columnNames, PSet.empty(), "id", PList.empty());
	}

	private static final PList<String> columnNames = PList.val("id", "type", "name");

	public InsertAnimals add(@Nullable Integer id, @Nullable String type, @Nullable String name) {
		Object[] row = new Object[]{
			id
			, type
			, name
		};
		return new InsertAnimals(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertAnimals add(Animals value) {
		return add(
			value.getId(), value.getType(), value.getName()
		);
	}
}
