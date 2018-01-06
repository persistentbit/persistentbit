package com.mycompany.db.generated.inserts;

import com.mycompany.db.generated.tables.TGroupByTest;
import com.mycompany.db.generated.values.GroupByTest;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

public class InsertGroupByTest extends Insert<TGroupByTest, Long>{


	public InsertGroupByTest(ExprContext context, TGroupByTest into, PList<String> columnNames,
							 PSet<String> withDefaults, String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertGroupByTest(ExprContext context, TGroupByTest into) {
		this(context, into, columnNames, PSet.empty(), "emp_id", PList.empty());
	}

	private static final PList<String> columnNames = PList.val("emp_id", "hiring_year", "hiring_quarter", "active");

	public InsertGroupByTest add(@Nullable Long empId, @Nullable Integer hiringYear, @Nullable Integer hiringQuarter,
								 @Nullable Boolean active) {
		Object[] row = new Object[]{
			empId
			, hiringYear
			, hiringQuarter
			, active
		};
		return new InsertGroupByTest(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertGroupByTest add(GroupByTest value) {
		return add(
			value.getEmpId(), value.getHiringYear(), value.getHiringQuarter(), value.getActive()
		);
	}
}
