package com.persistentbit.sql.dsl.exprcontext;

import com.persistentbit.sql.dsl.generic.expressions.*;
import com.persistentbit.sql.dsl.generic.expressions.impl.date.DDateValue;
import com.persistentbit.sql.dsl.generic.expressions.impl.datetime.DDateTimeValue;
import com.persistentbit.sql.dsl.generic.expressions.impl.dboolean.DBooleanValue;
import com.persistentbit.sql.dsl.generic.expressions.impl.dnumber.*;
import com.persistentbit.sql.dsl.generic.expressions.impl.dstring.DStringValue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * TODOC
 *
 * @author petermuys
 * @since 26/11/17
 */
public interface DbContext{
	DbTableContext forTable(String schemaName, String tableName);
	DbSqlContext	createSqlContext();


	default DExprBoolean val(Boolean v) { return new DBooleanValue(v);}
	default DExprByte val(Byte v) { return new DByteValue(v); }
	default DExprShort val(Short v) { return new DShortValue(v); }
	default DExprInt val(Integer v) { return new DIntValue(v); }
	default DExprLong val(Long v) { return new DLongValue(v); }
	default DExprDouble val(Double v) { return new DDoubleValue(v); }
	default DExprFloat val(Float v) { return new DFloatValue(v);}
	default DExprBigDecimal val(BigDecimal v) { return new DBigDecimalValue(v); }
	default DExprString val(String v) { return new DStringValue(v); }
	default DExprDateTime val(LocalDateTime v) { return new DDateTimeValue(v); }
	default DExprDate val(LocalDate v) { return new DDateValue(v); }

}
