package com.persistentbit.sql.dsl.generic;

import com.persistentbit.sql.dsl.exprcontext.DbContext;
import com.persistentbit.sql.dsl.generic.expressions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * TODOC
 *
 * @author petermuys
 * @since 2/12/17
 */
public abstract class DbGeneric{
	protected final DbContext context;
	public DbGeneric(DbContext context){
		this.context = context;
	}

	public DExprInt	val(Integer value){
		return context.val(value);
	}
	public DExprLong val(Long value){
		return context.val(value);
	}
	public DExprBoolean val(Boolean value){
		return context.val(value);
	}
	public DExprString val(String value){
		return context.val(value);
	}
	public DExprShort val(Short value){
		return context.val(value);
	}
	public DExprDouble val(Double value){
		return context.val(value);
	}
	public DExprBigDecimal val(BigDecimal value){
		return context.val(value);
	}
	public DExprDateTime val(LocalDateTime value){
		return context.val(value);
	}
}
