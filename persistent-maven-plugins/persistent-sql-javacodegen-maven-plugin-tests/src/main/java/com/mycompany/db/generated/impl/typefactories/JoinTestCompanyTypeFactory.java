package com.mycompany.db.generated.impl.typefactories;

import com.mycompany.db.generated.expressions.EJoinTestCompany;
import com.mycompany.db.generated.values.JoinTestCompany;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructTypeImplMixin;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.util.Iterator;

public class JoinTestCompanyTypeFactory extends AbstractStructureTypeFactory<EJoinTestCompany, JoinTestCompany>{

	private class EJoinTestCompanyImpl extends EJoinTestCompany
		implements StructTypeImplMixin<EJoinTestCompany, JoinTestCompany>{


		public EJoinTestCompanyImpl(Iterator<DExpr> iter) {
			super(
				(ELong) iter.next()
				, (EString) iter.next()
				, (ELong) iter.next()
			);
		}

		@Override
		public AbstractStructureTypeFactory<EJoinTestCompany, JoinTestCompany> getTypeFactory() {
			return JoinTestCompanyTypeFactory.this;
		}

		@Override
		public String toString() {
			return "EJoinTestCompany[" + cmpId + name + ownerEmpId + "]";
		}

		@Override
		public EJoinTestCompany getThis() {
			return this;
		}
	}

	public JoinTestCompanyTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected PList<StructureField<EJoinTestCompany, JoinTestCompany>> buildFields() {
		return PList.val(
			createField(ELong.class, "cmp_id", "cmpId", v -> v.getCmpId(), v -> v.cmpId)
			, createField(EString.class, "name", "name", v -> v.getName(), v -> v.name)
			, createField(ELong.class, "owner_emp_id", "ownerEmpId", v -> v.getOwnerEmpId()
				.orElse(null), v -> v.ownerEmpId)
		);
	}

	@Override
	protected JoinTestCompany buildValue(Object[] fieldValues) {
		return new JoinTestCompany(
			(Long) fieldValues[0]
			, (String) fieldValues[1]
			, (Long) fieldValues[2]
		);
	}

	@Override
	protected EJoinTestCompanyImpl createExpression(PStream<DExpr> fieldValues) {
		return new EJoinTestCompanyImpl(fieldValues.iterator());
	}

	@Override
	public Class<EJoinTestCompany> getTypeClass() {
		return EJoinTestCompany.class;
	}
}
