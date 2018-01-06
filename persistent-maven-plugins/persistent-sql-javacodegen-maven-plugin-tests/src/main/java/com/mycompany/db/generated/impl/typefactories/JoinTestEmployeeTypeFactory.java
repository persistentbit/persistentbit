package com.mycompany.db.generated.impl.typefactories;

import com.mycompany.db.generated.expressions.EJoinTestEmployee;
import com.mycompany.db.generated.values.JoinTestEmployee;
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

public class JoinTestEmployeeTypeFactory extends AbstractStructureTypeFactory<EJoinTestEmployee, JoinTestEmployee>{

	private class EJoinTestEmployeeImpl extends EJoinTestEmployee
		implements StructTypeImplMixin<EJoinTestEmployee, JoinTestEmployee>{


		public EJoinTestEmployeeImpl(Iterator<DExpr> iter) {
			super(
				(ELong) iter.next()
				, (EString) iter.next()
			);
		}

		@Override
		public AbstractStructureTypeFactory<EJoinTestEmployee, JoinTestEmployee> getTypeFactory() {
			return JoinTestEmployeeTypeFactory.this;
		}

		@Override
		public String toString() {
			return "EJoinTestEmployee[" + empId + name + "]";
		}

		@Override
		public EJoinTestEmployee getThis() {
			return this;
		}
	}

	public JoinTestEmployeeTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected PList<StructureField<EJoinTestEmployee, JoinTestEmployee>> buildFields() {
		return PList.val(
			createField(ELong.class, "emp_id", "empId", v -> v.getEmpId(), v -> v.empId)
			, createField(EString.class, "name", "name", v -> v.getName(), v -> v.name)
		);
	}

	@Override
	protected JoinTestEmployee buildValue(Object[] fieldValues) {
		return new JoinTestEmployee(
			(Long) fieldValues[0]
			, (String) fieldValues[1]
		);
	}

	@Override
	protected EJoinTestEmployeeImpl createExpression(PStream<DExpr> fieldValues) {
		return new EJoinTestEmployeeImpl(fieldValues.iterator());
	}

	@Override
	public Class<EJoinTestEmployee> getTypeClass() {
		return EJoinTestEmployee.class;
	}
}
