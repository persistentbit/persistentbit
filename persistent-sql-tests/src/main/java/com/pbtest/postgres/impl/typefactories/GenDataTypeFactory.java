package com.pbtest.postgres.impl.typefactories;

import com.pbtest.postgres.expressions.EGenData;
import com.pbtest.postgres.values.GenData;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructTypeImplMixin;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Iterator;

public class GenDataTypeFactory extends AbstractStructureTypeFactory<EGenData, GenData>{

	private class EGenDataImpl extends EGenData implements StructTypeImplMixin<EGenData, GenData>{


		public EGenDataImpl(Iterator<DExpr> iter) {
			super(
				(EInt) iter.next()
				, (EInt) iter.next()
				, (EShort) iter.next()
				, (ELong) iter.next()
				, (EBigDecimal) iter.next()
				, (EDouble) iter.next()
				, (EFloat) iter.next()
				, (EBool) iter.next()
				, (EDate) iter.next()
				, (ETime) iter.next()
				, (EDateTime) iter.next()
				, (EString) iter.next()
			);
		}
		@Override
		public AbstractStructureTypeFactory<EGenData, GenData> getTypeFactory() {
			return GenDataTypeFactory.this;
		}
		@Override
		public String toString() {
			return "EGenData[" + genDataId + aInt + aShort + aLong + aNum + aDouble + aReal + aBool + aDate + aTime + aTimestamp + aString + "]";
		}
		@Override
		public EGenData getThis() {
			return this;
		}
	}

	public GenDataTypeFactory(ExprContext context) {
		super(context);
	}
	@Override
	protected PList<StructureField<EGenData, GenData>> buildFields() {
		return PList.val(
			createField(EInt.class, "gen_data_id", "genDataId", v -> v.getGenDataId(), v -> v.genDataId)
			, createField(EInt.class, "a_int", "aInt", v -> v.getAInt(), v -> v.aInt)
			, createField(EShort.class, "a_short", "aShort", v -> v.getAShort(), v -> v.aShort)
			, createField(ELong.class, "a_long", "aLong", v -> v.getALong(), v -> v.aLong)
			, createField(EBigDecimal.class, "a_num", "aNum", v -> v.getANum(), v -> v.aNum)
			, createField(EDouble.class, "a_double", "aDouble", v -> v.getADouble(), v -> v.aDouble)
			, createField(EFloat.class, "a_real", "aReal", v -> v.getAReal(), v -> v.aReal)
			, createField(EBool.class, "a_bool", "aBool", v -> v.getABool(), v -> v.aBool)
			, createField(EDate.class, "a_date", "aDate", v -> v.getADate(), v -> v.aDate)
			, createField(ETime.class, "a_time", "aTime", v -> v.getATime(), v -> v.aTime)
			, createField(EDateTime.class, "a_timestamp", "aTimestamp", v -> v.getATimestamp(), v -> v.aTimestamp)
			, createField(EString.class, "a_string", "aString", v -> v.getAString(), v -> v.aString)
		);
	}
	@Override
	protected GenData buildValue(Object[] fieldValues) {
		return new GenData(
			(Integer) fieldValues[0]
			, (Integer) fieldValues[1]
			, (Short) fieldValues[2]
			, (Long) fieldValues[3]
			, (BigDecimal) fieldValues[4]
			, (Double) fieldValues[5]
			, (Float) fieldValues[6]
			, (Boolean) fieldValues[7]
			, (LocalDate) fieldValues[8]
			, (LocalTime) fieldValues[9]
			, (LocalDateTime) fieldValues[10]
			, (String) fieldValues[11]
		);
	}
	@Override
	protected EGenDataImpl createExpression(PStream<DExpr> fieldValues) {
		return new EGenDataImpl(fieldValues.iterator());
	}
	@Override
	public Class<EGenData> getTypeClass() {
		return EGenData.class;
	}
}
