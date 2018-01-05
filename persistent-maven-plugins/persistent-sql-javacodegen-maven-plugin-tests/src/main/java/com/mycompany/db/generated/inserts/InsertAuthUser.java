package com.mycompany.db.generated.inserts;

import com.mycompany.db.generated.tables.TAuthUser;
import com.mycompany.db.generated.values.AuthUser;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

import java.time.LocalDateTime;

public class InsertAuthUser extends Insert<TAuthUser, Long>{


	public InsertAuthUser(ExprContext context, TAuthUser into, PList<String> columnNames, PSet<String> withDefaults,
						  String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertAuthUser(ExprContext context, TAuthUser into) {
		this(context, into, columnNames, PSet.empty(), "id", PList.empty());
	}

	private static final PList<String> columnNames = PList
		.val("id", "auth_app_id", "user_name", "password", "wrong_password_count", "created", "last_login", "verified", "reset_password_code", "reset_password_valid_until", "verify_code", "verify_code_valid_until");

	public InsertAuthUser add(@Nullable Long id, @Nullable Long authAppId, @Nullable String userName,
							  @Nullable String password, @Nullable Integer wrongPasswordCount,
							  @Nullable LocalDateTime created, @Nullable LocalDateTime lastLogin,
							  @Nullable LocalDateTime verified, @Nullable String resetPasswordCode,
							  @Nullable LocalDateTime resetPasswordValidUntil, @Nullable String verifyCode,
							  @Nullable LocalDateTime verifyCodeValidUntil) {
		Object[] row = new Object[]{
			id
			, authAppId
			, userName
			, password
			, wrongPasswordCount
			, created
			, lastLogin
			, verified
			, resetPasswordCode
			, resetPasswordValidUntil
			, verifyCode
			, verifyCodeValidUntil
		};
		return new InsertAuthUser(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertAuthUser add(AuthUser value) {
		return add(
			value.getId(), value.getAuthAppId(), value.getUserName(), value.getPassword(), value
				.getWrongPasswordCount(), value.getCreated().orElse(null), value.getLastLogin().orElse(null), value
				.getVerified().orElse(null), value.getResetPasswordCode().orElse(null), value
				.getResetPasswordValidUntil().orElse(null), value.getVerifyCode().orElse(null), value
				.getVerifyCodeValidUntil().orElse(null)
		);
	}
}
