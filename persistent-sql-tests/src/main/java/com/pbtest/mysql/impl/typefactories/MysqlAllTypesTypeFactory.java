package com.pbtest.mysql.impl.typefactories;

import com.pbtest.mysql.expressions.EMysqlAllTypes;
import com.pbtest.mysql.values.MysqlAllTypes;
import com.persistentbit.collections.PByteList;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.sql.dsl.expressions.EByteList;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructTypeImplMixin;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.util.Iterator;

public class MysqlAllTypesTypeFactory extends AbstractStructureTypeFactory<EMysqlAllTypes, MysqlAllTypes>{

	private class EMysqlAllTypesImpl extends EMysqlAllTypes
		implements StructTypeImplMixin<EMysqlAllTypes, MysqlAllTypes>{


		public EMysqlAllTypesImpl(Iterator<DExpr> iter) {
			super(
				(EBool) iter.next()
				, (EByteList) iter.next()
				, (EByteList) iter.next()
				, (EBool) iter.next()
			);
		}
		@Override
		public AbstractStructureTypeFactory<EMysqlAllTypes, MysqlAllTypes> getTypeFactory() {
			return MysqlAllTypesTypeFactory.this;
		}
		@Override
		public String toString() {
			return "EMysqlAllTypes[" + aBit + aTinyint + aTinyintUnsinged + aBool + "]";
		}
		@Override
		public EMysqlAllTypes getThis() {
			return this;
		}
	}

	public MysqlAllTypesTypeFactory(ExprContext context) {
		super(context);
	}
	@Override
	protected PList<StructureField<EMysqlAllTypes, MysqlAllTypes>> buildFields() {
		return PList.val(
			createField(EBool.class, "a_bit", "aBit", v -> v.getABit(), v -> v.aBit)
			, createField(EByteList.class, "a_tinyint", "aTinyint", v -> v.getATinyint(), v -> v.aTinyint)
			, createField(EByteList.class, "a_tinyint_unsinged", "aTinyintUnsinged", v -> v
				.getATinyintUnsinged(), v -> v.aTinyintUnsinged)
			, createField(EBool.class, "a_bool", "aBool", v -> v.getABool(), v -> v.aBool)
		);
	}
	@Override
	protected MysqlAllTypes buildValue(Object[] fieldValues) {
		return new MysqlAllTypes(
			(Boolean) fieldValues[0]
			, (PByteList) fieldValues[1]
			, (PByteList) fieldValues[2]
			, (Boolean) fieldValues[3]
		);
	}
	@Override
	protected EMysqlAllTypesImpl createExpression(PStream<DExpr> fieldValues) {
		return new EMysqlAllTypesImpl(fieldValues.iterator());
	}
	@Override
	public Class<EMysqlAllTypes> getTypeClass() {
		return EMysqlAllTypes.class;
	}
}
