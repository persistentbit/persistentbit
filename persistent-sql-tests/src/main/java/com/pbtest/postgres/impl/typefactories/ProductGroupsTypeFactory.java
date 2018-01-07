package com.pbtest.postgres.impl.typefactories;

import com.pbtest.postgres.expressions.EProductGroups;
import com.pbtest.postgres.values.ProductGroups;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EInt;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructTypeImplMixin;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.util.Iterator;

public class ProductGroupsTypeFactory extends AbstractStructureTypeFactory<EProductGroups, ProductGroups>{

	private class EProductGroupsImpl extends EProductGroups
		implements StructTypeImplMixin<EProductGroups, ProductGroups>{


		public EProductGroupsImpl(Iterator<DExpr> iter) {
			super(
				(EInt) iter.next()
				, (EString) iter.next()
			);
		}

		@Override
		public AbstractStructureTypeFactory<EProductGroups, ProductGroups> getTypeFactory() {
			return ProductGroupsTypeFactory.this;
		}

		@Override
		public String toString() {
			return "EProductGroups[" + groupId + groupName + "]";
		}

		@Override
		public EProductGroups getThis() {
			return this;
		}
	}

	public ProductGroupsTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected PList<StructureField<EProductGroups, ProductGroups>> buildFields() {
		return PList.val(
			createField(EInt.class, "group_id", "groupId", v -> v.getGroupId(), v -> v.groupId)
			, createField(EString.class, "group_name", "groupName", v -> v.getGroupName(), v -> v.groupName)
		);
	}

	@Override
	protected ProductGroups buildValue(Object[] fieldValues) {
		return new ProductGroups(
			(Integer) fieldValues[0]
			, (String) fieldValues[1]
		);
	}

	@Override
	protected EProductGroupsImpl createExpression(PStream<DExpr> fieldValues) {
		return new EProductGroupsImpl(fieldValues.iterator());
	}

	@Override
	public Class<EProductGroups> getTypeClass() {
		return EProductGroups.class;
	}
}
