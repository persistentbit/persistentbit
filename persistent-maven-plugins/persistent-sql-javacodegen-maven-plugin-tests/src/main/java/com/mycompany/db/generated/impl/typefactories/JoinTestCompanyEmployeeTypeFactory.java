package com.mycompany.db.generated.impl.typefactories;

import com.mycompany.db.generated.expressions.EJoinTestCompanyEmployee;
import com.mycompany.db.generated.values.JoinTestCompanyEmployee;
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

public class JoinTestCompanyEmployeeTypeFactory
	extends AbstractStructureTypeFactory<EJoinTestCompanyEmployee, JoinTestCompanyEmployee>{

	private class EJoinTestCompanyEmployeeImpl extends EJoinTestCompanyEmployee
		implements StructTypeImplMixin<EJoinTestCompanyEmployee, JoinTestCompanyEmployee>{


		public EJoinTestCompanyEmployeeImpl(Iterator<DExpr> iter) {
			super(
				(ELong) iter.next()
				, (ELong) iter.next()
				, (EString) iter.next()
			);
		}

		@Override
		public AbstractStructureTypeFactory<EJoinTestCompanyEmployee, JoinTestCompanyEmployee> getTypeFactory() {
			return JoinTestCompanyEmployeeTypeFactory.this;
		}

		@Override
		public String toString() {
			return "EJoinTestCompanyEmployee[" + cmpId + empId + function + "]";
		}

		@Override
		public EJoinTestCompanyEmployee getThis() {
			return this;
		}
	}

	public JoinTestCompanyEmployeeTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected PList<StructureField<EJoinTestCompanyEmployee, JoinTestCompanyEmployee>> buildFields() {
		return PList.val(
			createField(ELong.class, "cmp_id", "cmpId", v -> v.getCmpId(), v -> v.cmpId)
			, createField(ELong.class, "emp_id", "empId", v -> v.getEmpId(), v -> v.empId)
			, createField(EString.class, "function", "function", v -> v.getFunction(), v -> v.function)
		);
	}

	@Override
	protected JoinTestCompanyEmployee buildValue(Object[] fieldValues) {
		return new JoinTestCompanyEmployee(
			(Long) fieldValues[0]
			, (Long) fieldValues[1]
			, (String) fieldValues[2]
		);
	}

	@Override
	protected EJoinTestCompanyEmployeeImpl createExpression(PStream<DExpr> fieldValues) {
		return new EJoinTestCompanyEmployeeImpl(fieldValues.iterator());
	}

	@Override
	public Class<EJoinTestCompanyEmployee> getTypeClass() {
		return EJoinTestCompanyEmployee.class;
	}
}
