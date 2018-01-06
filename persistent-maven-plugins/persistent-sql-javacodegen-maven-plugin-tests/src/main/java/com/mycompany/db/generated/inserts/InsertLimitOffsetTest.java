package com.mycompany.db.generated.inserts;

import com.mycompany.db.generated.tables.TLimitOffsetTest;
import com.mycompany.db.generated.values.LimitOffsetTest;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

public class InsertLimitOffsetTest extends Insert<TLimitOffsetTest, Void>{


	public InsertLimitOffsetTest(ExprContext context, TLimitOffsetTest into, PList<String> columnNames,
								 PSet<String> withDefaults, String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertLimitOffsetTest(ExprContext context, TLimitOffsetTest into) {
		this(context, into, columnNames, PSet.empty(), null, PList.empty());
	}

	private static final PList<String> columnNames = PList.val("id", "value");

	public InsertLimitOffsetTest add(@Nullable Integer id, @Nullable Long value) {
		Object[] row = new Object[]{
			id
			, value
		};
		return new InsertLimitOffsetTest(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertLimitOffsetTest add(LimitOffsetTest value) {
		return add(
			value.getId(), value.getValue()
		);
	}
}
