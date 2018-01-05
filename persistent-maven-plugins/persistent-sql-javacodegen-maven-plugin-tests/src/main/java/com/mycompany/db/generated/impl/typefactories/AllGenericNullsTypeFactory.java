package com.mycompany.db.generated.impl.typefactories;

import com.mycompany.db.generated.expressions.EAllGenericNulls;
import com.mycompany.db.generated.values.AllGenericNulls;
import com.persistentbit.collections.PByteList;
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

public class AllGenericNullsTypeFactory extends AbstractStructureTypeFactory<EAllGenericNulls, AllGenericNulls>{

	private class EAllGenericNullsImpl extends EAllGenericNulls
		implements StructTypeImplMixin<EAllGenericNulls, AllGenericNulls>{


		public EAllGenericNullsImpl(Iterator<DExpr> iter) {
			super(
				(EString) iter.next()
				, (ELong) iter.next()
				, (EShort) iter.next()
				, (EInt) iter.next()
				, (ELong) iter.next()
				, (EInt) iter.next()
				, (ELong) iter.next()
				, (EBigDecimal) iter.next()
				, (EBigDecimal) iter.next()
				, (EBigDecimal) iter.next()
				, (EFloat) iter.next()
				, (EDouble) iter.next()
				, (EShort) iter.next()
				, (EInt) iter.next()
				, (ELong) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EString) iter.next()
				, (EByteList) iter.next()
				, (EDateTime) iter.next()
				, (EDateTime) iter.next()
				, (EDateTime) iter.next()
				, (EDate) iter.next()
				, (ETime) iter.next()
				, (ETime) iter.next()
				, (EBool) iter.next()
			);
		}
		@Override
		public AbstractStructureTypeFactory<EAllGenericNulls, AllGenericNulls> getTypeFactory() {
			return AllGenericNullsTypeFactory.this;
		}
		@Override
		public String toString() {
			return "EAllGenericNulls[" + idPart1 + idPart2 + serSmall + ser + serBig + anInteger + aBigint + aDecimal72 + aNumeric6 + aNumeric + aReal + aDouble + anInt2 + anInt4 + anInt8 + aVarchar + aVarchar10 + aText + aChar + aChar10 + aBytea + aTimestamp3 + aTimestamp + aTimestampWithZone + aDate + aTime + aTimeWithZone + aBoolean + "]";
		}
		@Override
		public EAllGenericNulls getThis() {
			return this;
		}
	}

	public AllGenericNullsTypeFactory(ExprContext context) {
		super(context);
	}
	@Override
	protected PList<StructureField<EAllGenericNulls, AllGenericNulls>> buildFields() {
		return PList.val(
			createField(EString.class, "id_part1", "idPart1", v -> v.getIdPart1(), v -> v.idPart1)
			, createField(ELong.class, "id_part2", "idPart2", v -> v.getIdPart2(), v -> v.idPart2)
			, createField(EShort.class, "ser_small", "serSmall", v -> v.getSerSmall(), v -> v.serSmall)
			, createField(EInt.class, "ser", "ser", v -> v.getSer(), v -> v.ser)
			, createField(ELong.class, "ser_big", "serBig", v -> v.getSerBig(), v -> v.serBig)
			, createField(EInt.class, "an_integer", "anInteger", v -> v.getAnInteger().orElse(null), v -> v.anInteger)
			, createField(ELong.class, "a_bigint", "aBigint", v -> v.getABigint().orElse(null), v -> v.aBigint)
			, createField(EBigDecimal.class, "a_decimal_7_2", "aDecimal72", v -> v.getADecimal72()
				.orElse(null), v -> v.aDecimal72)
			, createField(EBigDecimal.class, "a_numeric_6", "aNumeric6", v -> v.getANumeric6()
				.orElse(null), v -> v.aNumeric6)
			, createField(EBigDecimal.class, "a_numeric", "aNumeric", v -> v.getANumeric()
				.orElse(null), v -> v.aNumeric)
			, createField(EFloat.class, "a_real", "aReal", v -> v.getAReal().orElse(null), v -> v.aReal)
			, createField(EDouble.class, "a_double", "aDouble", v -> v.getADouble().orElse(null), v -> v.aDouble)
			, createField(EShort.class, "an_int2", "anInt2", v -> v.getAnInt2().orElse(null), v -> v.anInt2)
			, createField(EInt.class, "an_int4", "anInt4", v -> v.getAnInt4().orElse(null), v -> v.anInt4)
			, createField(ELong.class, "an_int8", "anInt8", v -> v.getAnInt8().orElse(null), v -> v.anInt8)
			, createField(EString.class, "a_varchar", "aVarchar", v -> v.getAVarchar().orElse(null), v -> v.aVarchar)
			, createField(EString.class, "a_varchar_10", "aVarchar10", v -> v.getAVarchar10()
				.orElse(null), v -> v.aVarchar10)
			, createField(EString.class, "a_text", "aText", v -> v.getAText().orElse(null), v -> v.aText)
			, createField(EString.class, "a_char", "aChar", v -> v.getAChar().orElse(null), v -> v.aChar)
			, createField(EString.class, "a_char_10", "aChar10", v -> v.getAChar10().orElse(null), v -> v.aChar10)
			, createField(EByteList.class, "a_bytea", "aBytea", v -> v.getABytea().orElse(null), v -> v.aBytea)
			, createField(EDateTime.class, "a_timestamp_3", "aTimestamp3", v -> v.getATimestamp3()
				.orElse(null), v -> v.aTimestamp3)
			, createField(EDateTime.class, "a_timestamp", "aTimestamp", v -> v.getATimestamp()
				.orElse(null), v -> v.aTimestamp)
			, createField(EDateTime.class, "a_timestamp_with_zone", "aTimestampWithZone", v -> v.getATimestampWithZone()
				.orElse(null), v -> v.aTimestampWithZone)
			, createField(EDate.class, "a_date", "aDate", v -> v.getADate().orElse(null), v -> v.aDate)
			, createField(ETime.class, "a_time", "aTime", v -> v.getATime().orElse(null), v -> v.aTime)
			, createField(ETime.class, "a_time_with_zone", "aTimeWithZone", v -> v.getATimeWithZone()
				.orElse(null), v -> v.aTimeWithZone)
			, createField(EBool.class, "a_boolean", "aBoolean", v -> v.getABoolean().orElse(null), v -> v.aBoolean)
		);
	}
	@Override
	protected AllGenericNulls buildValue(Object[] fieldValues) {
		return new AllGenericNulls(
			(String) fieldValues[0]
			, (Long) fieldValues[1]
			, (Short) fieldValues[2]
			, (Integer) fieldValues[3]
			, (Long) fieldValues[4]
			, (Integer) fieldValues[5]
			, (Long) fieldValues[6]
			, (BigDecimal) fieldValues[7]
			, (BigDecimal) fieldValues[8]
			, (BigDecimal) fieldValues[9]
			, (Float) fieldValues[10]
			, (Double) fieldValues[11]
			, (Short) fieldValues[12]
			, (Integer) fieldValues[13]
			, (Long) fieldValues[14]
			, (String) fieldValues[15]
			, (String) fieldValues[16]
			, (String) fieldValues[17]
			, (String) fieldValues[18]
			, (String) fieldValues[19]
			, (PByteList) fieldValues[20]
			, (LocalDateTime) fieldValues[21]
			, (LocalDateTime) fieldValues[22]
			, (LocalDateTime) fieldValues[23]
			, (LocalDate) fieldValues[24]
			, (LocalTime) fieldValues[25]
			, (LocalTime) fieldValues[26]
			, (Boolean) fieldValues[27]
		);
	}
	@Override
	protected EAllGenericNullsImpl createExpression(PStream<DExpr> fieldValues) {
		return new EAllGenericNullsImpl(fieldValues.iterator());
	}
	@Override
	public Class<EAllGenericNulls> getTypeClass() {
		return EAllGenericNulls.class;
	}
}
