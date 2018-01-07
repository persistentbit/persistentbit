package com.pbtest.postgres.inserts;

import com.pbtest.postgres.tables.TProductGroups;
import com.pbtest.postgres.values.ProductGroups;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

public class InsertProductGroups extends Insert<TProductGroups, Integer>{


	public InsertProductGroups(ExprContext context, TProductGroups into, PList<String> columnNames,
							   PSet<String> withDefaults, String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertProductGroups(ExprContext context, TProductGroups into) {
		this(context, into, columnNames, PSet.empty(), "group_id", PList.empty());
	}

	private static final PList<String> columnNames = PList.val("group_id", "group_name");

	public InsertProductGroups add(@Nullable Integer groupId, @Nullable String groupName) {
		Object[] row = new Object[]{
			groupId
			, groupName
		};
		return new InsertProductGroups(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertProductGroups add(ProductGroups value) {
		return add(
			value.getGroupId(), value.getGroupName()
		);
	}
}
