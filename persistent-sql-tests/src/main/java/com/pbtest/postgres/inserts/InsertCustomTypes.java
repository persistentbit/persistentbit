package com.pbtest.postgres.inserts;

import com.pbtest.postgres.tables.TCustomTypes;
import com.pbtest.postgres.values.CustomTypes;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

public class InsertCustomTypes extends Insert<TCustomTypes, Void>{


	public InsertCustomTypes(ExprContext context, TCustomTypes into, PList<String> columnNames,
							 PSet<String> withDefaults, String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertCustomTypes(ExprContext context, TCustomTypes into) {
		this(context, into, columnNames, PSet.empty(), null, PList.empty());
	}

	private static final PList<String> columnNames = PList.val("t_uuid", "t_interval");

	public InsertCustomTypes add(@Nullable Object tUuid, @Nullable Object tInterval) {
		Object[] row = new Object[]{
			tUuid
			, tInterval
		};
		return new InsertCustomTypes(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertCustomTypes add(CustomTypes value) {
		return add(
			value.getTUuid(), value.getTInterval()
		);
	}
}
