package com.mycompany.db.generated.impl.typefactories;

import com.mycompany.db.generated.expressions.EAuthUserRememberMe;
import com.mycompany.db.generated.values.AuthUserRememberMe;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EDateTime;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructTypeImplMixin;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.time.LocalDateTime;
import java.util.Iterator;

public class AuthUserRememberMeTypeFactory
	extends AbstractStructureTypeFactory<EAuthUserRememberMe, AuthUserRememberMe>{

	private class EAuthUserRememberMeImpl extends EAuthUserRememberMe
		implements StructTypeImplMixin<EAuthUserRememberMe, AuthUserRememberMe>{


		public EAuthUserRememberMeImpl(Iterator<DExpr> iter) {
			super(
				(ELong) iter.next()
				, (ELong) iter.next()
				, (EString) iter.next()
				, (EDateTime) iter.next()
				, (EString) iter.next()
			);
		}

		@Override
		public AbstractStructureTypeFactory<EAuthUserRememberMe, AuthUserRememberMe> getTypeFactory() {
			return AuthUserRememberMeTypeFactory.this;
		}

		@Override
		public String toString() {
			return "EAuthUserRememberMe[" + id + authUserId + code + validUntil + passwordCode + "]";
		}

		@Override
		public EAuthUserRememberMe getThis() {
			return this;
		}
	}

	public AuthUserRememberMeTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected PList<StructureField<EAuthUserRememberMe, AuthUserRememberMe>> buildFields() {
		return PList.val(
			createField(ELong.class, "id", "id", v -> v.getId(), v -> v.id)
			, createField(ELong.class, "auth_user_id", "authUserId", v -> v.getAuthUserId(), v -> v.authUserId)
			, createField(EString.class, "code", "code", v -> v.getCode(), v -> v.code)
			, createField(EDateTime.class, "valid_until", "validUntil", v -> v.getValidUntil(), v -> v.validUntil)
			, createField(EString.class, "password_code", "passwordCode", v -> v.getPasswordCode(), v -> v.passwordCode)
		);
	}

	@Override
	protected AuthUserRememberMe buildValue(Object[] fieldValues) {
		return new AuthUserRememberMe(
			(Long) fieldValues[0]
			, (Long) fieldValues[1]
			, (String) fieldValues[2]
			, (LocalDateTime) fieldValues[3]
			, (String) fieldValues[4]
		);
	}

	@Override
	protected EAuthUserRememberMeImpl createExpression(PStream<DExpr> fieldValues) {
		return new EAuthUserRememberMeImpl(fieldValues.iterator());
	}

	@Override
	public Class<EAuthUserRememberMe> getTypeClass() {
		return EAuthUserRememberMe.class;
	}
}
