package com.mycompany.db.generated.inserts;

import com.mycompany.db.generated.tables.TJoinTestEmployee;
import com.mycompany.db.generated.values.JoinTestEmployee;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

public class InsertJoinTestEmployee extends Insert<TJoinTestEmployee, Void>{


	public InsertJoinTestEmployee(ExprContext context, TJoinTestEmployee into, PList<String> columnNames,
								  PSet<String> withDefaults, String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertJoinTestEmployee(ExprContext context, TJoinTestEmployee into) {
		this(context, into, columnNames, PSet.empty(), null, PList.empty());
	}

	private static final PList<String> columnNames = PList.val("emp_id", "name");

	public InsertJoinTestEmployee add(@Nullable Long empId, @Nullable String name) {
		Object[] row = new Object[]{
			empId
			, name
		};
		return new InsertJoinTestEmployee(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertJoinTestEmployee add(JoinTestEmployee value) {
		return add(
			value.getEmpId(), value.getName()
		);
	}
}
