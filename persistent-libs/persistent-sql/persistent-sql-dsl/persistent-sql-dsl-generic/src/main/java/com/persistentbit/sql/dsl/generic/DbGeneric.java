package com.persistentbit.sql.dsl.generic;

import com.persistentbit.sql.dsl.exprcontext.DbContext;
import com.persistentbit.sql.dsl.generic.expressions.*;
import com.persistentbit.sql.dsl.generic.expressions.impl.datetime.DDateTimeValue;
import com.persistentbit.sql.dsl.generic.expressions.impl.dboolean.DBooleanValue;
import com.persistentbit.sql.dsl.generic.expressions.impl.dnumber.*;
import com.persistentbit.sql.dsl.generic.expressions.impl.dstring.DStringValue;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * TODOC
 *
 * @author petermuys
 * @since 2/12/17
 */
public abstract class DbGeneric{

	public DbGeneric(DbContext context){

	}

	public DExprInt	val(Integer value){
		return new DIntValue(value);
	}
	public DExprLong val(Long value){
		return new DLongValue(value);
	}
	public DExprBoolean val(Boolean value){
		return new DBooleanValue(value);
	}
	public DExprString val(String value){
		return new DStringValue(value);
	}
	public DExprShort val(Short value){
		return new DShortValue(value);
	}
	public DExprDouble val(Double value){
		return new DDoubleValue(value);
	}
	public DExprBigDecimal val(BigDecimal value){
		return new DBigDecimalValue(value);
	}
	public DExprDateTime val(LocalDateTime value){
		return new DDateTimeValue(value);
	}
}
