package com.mycompany.db.generated.impl.typefactories;

import com.mycompany.db.generated.expressions.EAuthApp;
import com.mycompany.db.generated.values.AuthApp;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructTypeImplMixin;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.util.Iterator;

public class AuthAppTypeFactory extends AbstractStructureTypeFactory<EAuthApp, AuthApp>{

	private class EAuthAppImpl extends EAuthApp implements StructTypeImplMixin<EAuthApp, AuthApp>{


		public EAuthAppImpl(Iterator<DExpr> iter) {
			super(
				(ELong) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EBool) iter.next()
				, (EBool) iter.next()
				, (EInt) iter.next()
			);
		}

		@Override
		public AbstractStructureTypeFactory<EAuthApp, AuthApp> getTypeFactory() {
			return AuthAppTypeFactory.this;
		}

		@Override
		public String toString() {
			return "EAuthApp[" + id + name + password + isRoot + isActive + maxWrongPasswordCount + "]";
		}

		@Override
		public EAuthApp getThis() {
			return this;
		}
	}

	public AuthAppTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected PList<StructureField<EAuthApp, AuthApp>> buildFields() {
		return PList.val(
			createField(ELong.class, "id", "id", v -> v.getId(), v -> v.id)
			, createField(EString.class, "name", "name", v -> v.getName(), v -> v.name)
			, createField(EString.class, "password", "password", v -> v.getPassword(), v -> v.password)
			, createField(EBool.class, "is_root", "isRoot", v -> v.getIsRoot(), v -> v.isRoot)
			, createField(EBool.class, "is_active", "isActive", v -> v.getIsActive(), v -> v.isActive)
			, createField(EInt.class, "max_wrong_password_count", "maxWrongPasswordCount", v -> v
				.getMaxWrongPasswordCount().orElse(null), v -> v.maxWrongPasswordCount)
		);
	}

	@Override
	protected AuthApp buildValue(Object[] fieldValues) {
		return new AuthApp(
			(Long) fieldValues[0]
			, (String) fieldValues[1]
			, (String) fieldValues[2]
			, (Boolean) fieldValues[3]
			, (Boolean) fieldValues[4]
			, (Integer) fieldValues[5]
		);
	}

	@Override
	protected EAuthAppImpl createExpression(PStream<DExpr> fieldValues) {
		return new EAuthAppImpl(fieldValues.iterator());
	}

	@Override
	public Class<EAuthApp> getTypeClass() {
		return EAuthApp.class;
	}
}
