package com.mycompany.db.generated.inserts;

import com.mycompany.db.generated.tables.TAuthApp;
import com.mycompany.db.generated.values.AuthApp;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

public class InsertAuthApp extends Insert<TAuthApp, Long>{


	public InsertAuthApp(ExprContext context, TAuthApp into, PList<String> columnNames, PSet<String> withDefaults,
						 String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertAuthApp(ExprContext context, TAuthApp into) {
		this(context, into, columnNames, PSet.empty(), "id", PList.empty());
	}

	private static final PList<String> columnNames =
		PList.val("id", "name", "password", "is_root", "is_active", "max_wrong_password_count");

	public InsertAuthApp add(@Nullable Long id, @Nullable String name, @Nullable String password,
							 @Nullable Boolean isRoot, @Nullable Boolean isActive,
							 @Nullable Integer maxWrongPasswordCount) {
		Object[] row = new Object[]{
			id
			, name
			, password
			, isRoot
			, isActive
			, maxWrongPasswordCount
		};
		return new InsertAuthApp(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertAuthApp add(AuthApp value) {
		return add(
			value.getId(), value.getName(), value.getPassword(), value.getIsRoot(), value.getIsActive(), value
				.getMaxWrongPasswordCount().orElse(null)
		);
	}
}
