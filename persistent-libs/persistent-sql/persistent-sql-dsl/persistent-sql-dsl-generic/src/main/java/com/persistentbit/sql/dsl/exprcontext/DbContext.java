package com.persistentbit.sql.dsl.exprcontext;

import com.persistentbit.collections.PBitList;
import com.persistentbit.collections.PByteList;
import com.persistentbit.sql.dsl.generic.expressions.*;
import com.persistentbit.sql.dsl.generic.expressions.impl.date.DDateValue;
import com.persistentbit.sql.dsl.generic.expressions.impl.datetime.local.DDateTimeValue;
import com.persistentbit.sql.dsl.generic.expressions.impl.datetime.zoned.DZonedDateTimeValue;
import com.persistentbit.sql.dsl.generic.expressions.impl.dbitlist.DBitListValue;
import com.persistentbit.sql.dsl.generic.expressions.impl.dboolean.DBooleanValue;
import com.persistentbit.sql.dsl.generic.expressions.impl.dbytelist.DByteListValue;
import com.persistentbit.sql.dsl.generic.expressions.impl.dnumber.*;
import com.persistentbit.sql.dsl.generic.expressions.impl.dstring.DStringValue;
import com.persistentbit.sql.dsl.generic.expressions.impl.time.DLocalTimeValue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

/**
 * TODOC
 *
 * @author petermuys
 * @since 26/11/17
 */
public interface DbContext{
	DbTableContext forTable(String schemaName, String tableName);
	DbSqlContext	createSqlContext();


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
}
