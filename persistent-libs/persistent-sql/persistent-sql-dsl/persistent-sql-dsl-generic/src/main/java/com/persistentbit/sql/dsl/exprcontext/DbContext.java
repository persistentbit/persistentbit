package com.persistentbit.sql.dsl.exprcontext;

/**
 * TODOC
 *
 * @author petermuys
 * @since 26/11/17
 */
public interface DbContext{
	DbTableContext forTable(String schemaName, String tableName);
	DbSqlContext	createSqlContext();

/*
	default EBool val(Boolean v) { return new DBooleanValue(v);}
	default EByte val(Byte v) { return new DByteValue(v); }
	default EShort val(Short v) { return new DShortValue(v); }
	default EInt val(Integer v) { return new DIntValue(v); }
	default ELong val(Long v) { return new DLongValue(v); }
	default EDouble val(Double v) { return new DDoubleValue(v); }
	default EFloat val(Float v) { return new DFloatValue(v);}
	default EBigDecimal val(BigDecimal v) { return new DBigDecimalValue(v); }
	default EString val(String v) { return new DStringValue(v); }
	default EDateTime val(LocalDateTime v) { return new DDateTimeValue(v); }
	default EDate val(LocalDate v) { return new DDateValue(v); }
	default EZonedDateTime val(ZonedDateTime v) { return new DZonedDateTimeValue(v);}
	default EByteList val(PByteList v) { return new DByteListValue(v);}
	default EBitList val(PBitList v) { return new DBitListValue(v);}
	default ETime val(LocalTime v) { return new DLocalTimeValue(v); }
	*/
}
