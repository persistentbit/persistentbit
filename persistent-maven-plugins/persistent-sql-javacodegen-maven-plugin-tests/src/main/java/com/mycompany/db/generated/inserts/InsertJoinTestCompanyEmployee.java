package com.mycompany.db.generated.inserts;

import com.mycompany.db.generated.tables.TJoinTestCompanyEmployee;
import com.mycompany.db.generated.values.JoinTestCompanyEmployee;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

public class InsertJoinTestCompanyEmployee extends Insert<TJoinTestCompanyEmployee, Void>{


	public InsertJoinTestCompanyEmployee(ExprContext context, TJoinTestCompanyEmployee into, PList<String> columnNames,
										 PSet<String> withDefaults, String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertJoinTestCompanyEmployee(ExprContext context, TJoinTestCompanyEmployee into) {
		this(context, into, columnNames, PSet.empty(), null, PList.empty());
	}

	private static final PList<String> columnNames = PList.val("cmp_id", "emp_id", "function");

	public InsertJoinTestCompanyEmployee add(@Nullable Long cmpId, @Nullable Long empId, @Nullable String function) {
		Object[] row = new Object[]{
			cmpId
			, empId
			, function
		};
		return new InsertJoinTestCompanyEmployee(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertJoinTestCompanyEmployee add(JoinTestCompanyEmployee value) {
		return add(
			value.getCmpId(), value.getEmpId(), value.getFunction()
		);
	}
}
