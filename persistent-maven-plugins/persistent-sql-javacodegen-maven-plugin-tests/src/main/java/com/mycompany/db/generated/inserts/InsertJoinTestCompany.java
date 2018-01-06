package com.mycompany.db.generated.inserts;

import com.mycompany.db.generated.tables.TJoinTestCompany;
import com.mycompany.db.generated.values.JoinTestCompany;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

public class InsertJoinTestCompany extends Insert<TJoinTestCompany, Void>{


	public InsertJoinTestCompany(ExprContext context, TJoinTestCompany into, PList<String> columnNames,
								 PSet<String> withDefaults, String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertJoinTestCompany(ExprContext context, TJoinTestCompany into) {
		this(context, into, columnNames, PSet.empty(), null, PList.empty());
	}

	private static final PList<String> columnNames = PList.val("cmp_id", "name", "owner_emp_id");

	public InsertJoinTestCompany add(@Nullable Long cmpId, @Nullable String name, @Nullable Long ownerEmpId) {
		Object[] row = new Object[]{
			cmpId
			, name
			, ownerEmpId
		};
		return new InsertJoinTestCompany(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertJoinTestCompany add(JoinTestCompany value) {
		return add(
			value.getCmpId(), value.getName(), value.getOwnerEmpId().orElse(null)
		);
	}
}
