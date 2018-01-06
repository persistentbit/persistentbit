package com.mycompany.db.generated.impl.typefactories;

import com.mycompany.db.generated.expressions.EGroupByTest;
import com.mycompany.db.generated.values.GroupByTest;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.sql.dsl.expressions.EInt;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructTypeImplMixin;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.util.Iterator;

public class GroupByTestTypeFactory extends AbstractStructureTypeFactory<EGroupByTest, GroupByTest>{

	private class EGroupByTestImpl extends EGroupByTest implements StructTypeImplMixin<EGroupByTest, GroupByTest>{


		public EGroupByTestImpl(Iterator<DExpr> iter) {
			super(
				(ELong) iter.next()
				, (EInt) iter.next()
				, (EInt) iter.next()
				, (EBool) iter.next()
			);
		}

		@Override
		public AbstractStructureTypeFactory<EGroupByTest, GroupByTest> getTypeFactory() {
			return GroupByTestTypeFactory.this;
		}

		@Override
		public String toString() {
			return "EGroupByTest[" + empId + hiringYear + hiringQuarter + active + "]";
		}

		@Override
		public EGroupByTest getThis() {
			return this;
		}
	}

	public GroupByTestTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected PList<StructureField<EGroupByTest, GroupByTest>> buildFields() {
		return PList.val(
			createField(ELong.class, "emp_id", "empId", v -> v.getEmpId(), v -> v.empId)
			, createField(EInt.class, "hiring_year", "hiringYear", v -> v.getHiringYear(), v -> v.hiringYear)
			, createField(EInt.class, "hiring_quarter", "hiringQuarter", v -> v
				.getHiringQuarter(), v -> v.hiringQuarter)
			, createField(EBool.class, "active", "active", v -> v.getActive(), v -> v.active)
		);
	}

	@Override
	protected GroupByTest buildValue(Object[] fieldValues) {
		return new GroupByTest(
			(Long) fieldValues[0]
			, (Integer) fieldValues[1]
			, (Integer) fieldValues[2]
			, (Boolean) fieldValues[3]
		);
	}

	@Override
	protected EGroupByTestImpl createExpression(PStream<DExpr> fieldValues) {
		return new EGroupByTestImpl(fieldValues.iterator());
	}

	@Override
	public Class<EGroupByTest> getTypeClass() {
		return EGroupByTest.class;
	}
}
