package com.mycompany.db.generated.inserts;

import com.mycompany.db.generated.tables.TAuthUserRememberMe;
import com.mycompany.db.generated.values.AuthUserRememberMe;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

import java.time.LocalDateTime;

public class InsertAuthUserRememberMe extends Insert<TAuthUserRememberMe, Long>{


	public InsertAuthUserRememberMe(ExprContext context, TAuthUserRememberMe into, PList<String> columnNames,
									PSet<String> withDefaults, String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertAuthUserRememberMe(ExprContext context, TAuthUserRememberMe into) {
		this(context, into, columnNames, PSet.empty(), "id", PList.empty());
	}

	private static final PList<String> columnNames =
		PList.val("id", "auth_user_id", "code", "valid_until", "password_code");

	public InsertAuthUserRememberMe add(@Nullable Long id, @Nullable Long authUserId, @Nullable String code,
										@Nullable LocalDateTime validUntil, @Nullable String passwordCode) {
		Object[] row = new Object[]{
			id
			, authUserId
			, code
			, validUntil
			, passwordCode
		};
		return new InsertAuthUserRememberMe(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertAuthUserRememberMe add(AuthUserRememberMe value) {
		return add(
			value.getId(), value.getAuthUserId(), value.getCode(), value.getValidUntil(), value.getPasswordCode()
		);
	}
}
