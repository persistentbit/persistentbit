package com.mycompany.db.generated.impl.typefactories;

import com.mycompany.db.generated.expressions.EAuthUser;
import com.mycompany.db.generated.values.AuthUser;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructTypeImplMixin;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.time.LocalDateTime;
import java.util.Iterator;

public class AuthUserTypeFactory extends AbstractStructureTypeFactory<EAuthUser, AuthUser>{

	private class EAuthUserImpl extends EAuthUser implements StructTypeImplMixin<EAuthUser, AuthUser>{


		public EAuthUserImpl(Iterator<DExpr> iter) {
			super(
				(ELong) iter.next()
				, (ELong) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EInt) iter.next()
				, (EDateTime) iter.next()
				, (EDateTime) iter.next()
				, (EDateTime) iter.next()
				, (EString) iter.next()
				, (EDateTime) iter.next()
				, (EString) iter.next()
				, (EDateTime) iter.next()
			);
		}
		@Override
		public AbstractStructureTypeFactory<EAuthUser, AuthUser> getTypeFactory() {
			return AuthUserTypeFactory.this;
		}
		@Override
		public String toString() {
			return "EAuthUser[" + id + authAppId + userName + password + wrongPasswordCount + created + lastLogin + verified + resetPasswordCode + resetPasswordValidUntil + verifyCode + verifyCodeValidUntil + "]";
		}
		@Override
		public EAuthUser getThis() {
			return this;
		}
	}

	public AuthUserTypeFactory(ExprContext context) {
		super(context);
	}
	@Override
	protected PList<StructureField<EAuthUser, AuthUser>> buildFields() {
		return PList.val(
			createField(ELong.class, "id", "id", v -> v.getId(), v -> v.id)
			, createField(ELong.class, "auth_app_id", "authAppId", v -> v.getAuthAppId(), v -> v.authAppId)
			, createField(EString.class, "user_name", "userName", v -> v.getUserName(), v -> v.userName)
			, createField(EString.class, "password", "password", v -> v.getPassword(), v -> v.password)
			, createField(EInt.class, "wrong_password_count", "wrongPasswordCount", v -> v
				.getWrongPasswordCount(), v -> v.wrongPasswordCount)
			, createField(EDateTime.class, "created", "created", v -> v.getCreated().orElse(null), v -> v.created)
			, createField(EDateTime.class, "last_login", "lastLogin", v -> v.getLastLogin()
				.orElse(null), v -> v.lastLogin)
			, createField(EDateTime.class, "verified", "verified", v -> v.getVerified().orElse(null), v -> v.verified)
			, createField(EString.class, "reset_password_code", "resetPasswordCode", v -> v.getResetPasswordCode()
				.orElse(null), v -> v.resetPasswordCode)
			, createField(EDateTime.class, "reset_password_valid_until", "resetPasswordValidUntil", v -> v
				.getResetPasswordValidUntil().orElse(null), v -> v.resetPasswordValidUntil)
			, createField(EString.class, "verify_code", "verifyCode", v -> v.getVerifyCode()
				.orElse(null), v -> v.verifyCode)
			, createField(EDateTime.class, "verify_code_valid_until", "verifyCodeValidUntil", v -> v
				.getVerifyCodeValidUntil().orElse(null), v -> v.verifyCodeValidUntil)
		);
	}
	@Override
	protected AuthUser buildValue(Object[] fieldValues) {
		return new AuthUser(
			(Long) fieldValues[0]
			, (Long) fieldValues[1]
			, (String) fieldValues[2]
			, (String) fieldValues[3]
			, (Integer) fieldValues[4]
			, (LocalDateTime) fieldValues[5]
			, (LocalDateTime) fieldValues[6]
			, (LocalDateTime) fieldValues[7]
			, (String) fieldValues[8]
			, (LocalDateTime) fieldValues[9]
			, (String) fieldValues[10]
			, (LocalDateTime) fieldValues[11]
		);
	}
	@Override
	protected EAuthUserImpl createExpression(PStream<DExpr> fieldValues) {
		return new EAuthUserImpl(fieldValues.iterator());
	}
	@Override
	public Class<EAuthUser> getTypeClass() {
		return EAuthUser.class;
	}
}
