package com.pbtest.postgres.inserts;

import com.pbtest.postgres.tables.TGenders;
import com.pbtest.postgres.values.Genders;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

public class InsertGenders extends Insert<TGenders, Void>{


	public InsertGenders(ExprContext context, TGenders into, PList<String> columnNames, PSet<String> withDefaults,
						 String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertGenders(ExprContext context, TGenders into) {
		this(context, into, columnNames, PSet.empty(), null, PList.empty());
	}

	private static final PList<String> columnNames = PList.val("gender_code", "description");

	public InsertGenders add(@Nullable Object genderCode, @Nullable String description) {
		Object[] row = new Object[]{
			genderCode
			, description
		};
		return new InsertGenders(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertGenders add(Genders value) {
		return add(
			value.getGenderCode(), value.getDescription()
		);
	}
}
