package com.mycompany.db.generated.inserts;

import com.mycompany.db.generated.tables.TCaseWhenTest;
import com.mycompany.db.generated.values.CaseWhenTest;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

public class InsertCaseWhenTest extends Insert<TCaseWhenTest, Void>{


	public InsertCaseWhenTest(ExprContext context, TCaseWhenTest into, PList<String> columnNames,
							  PSet<String> withDefaults, String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertCaseWhenTest(ExprContext context, TCaseWhenTest into) {
		this(context, into, columnNames, PSet.empty(), null, PList.empty());
	}

	private static final PList<String> columnNames = PList.val("id", "value");

	public InsertCaseWhenTest add(@Nullable Integer id, @Nullable Long value) {
		Object[] row = new Object[]{
			id
			, value
		};
		return new InsertCaseWhenTest(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertCaseWhenTest add(CaseWhenTest value) {
		return add(
			value.getId(), value.getValue()
		);
	}
}
