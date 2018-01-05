package com.mycompany.db.generated.inserts;

import com.mycompany.db.generated.tables.TPgArrayTest;
import com.mycompany.db.generated.values.PgArrayTest;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.ImmutableArray;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

public class InsertPgArrayTest extends Insert<TPgArrayTest, Long>{


	public InsertPgArrayTest(ExprContext context, TPgArrayTest into, PList<String> columnNames,
							 PSet<String> withDefaults, String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertPgArrayTest(ExprContext context, TPgArrayTest into) {
		this(context, into, columnNames, PSet.empty(), "id", PList.empty());
	}

	private static final PList<String> columnNames = PList.val("id", "strings", "ints");

	public InsertPgArrayTest add(@Nullable Long id, @Nullable ImmutableArray<String> strings,
								 @Nullable ImmutableArray<Integer> ints) {
		Object[] row = new Object[]{
			id
			, strings
			, ints
		};
		return new InsertPgArrayTest(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertPgArrayTest add(PgArrayTest value) {
		return add(
			value.getId(), value.getStrings(), value.getInts()
		);
	}
}
