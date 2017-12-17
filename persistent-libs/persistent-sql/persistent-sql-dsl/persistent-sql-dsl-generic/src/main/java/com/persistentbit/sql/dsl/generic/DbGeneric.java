package com.persistentbit.sql.dsl.generic;

import com.persistentbit.sql.dsl.exprcontext.DbContext;

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
/*
	public EInt val(Integer value){
		return context.val(value);
	}
	public ELong val(Long value){
		return context.val(value);
	}
	public EBool val(Boolean value){
		return context.val(value);
	}
	public EString val(String value){
		return context.val(value);
	}
	public EShort val(Short value){
		return context.val(value);
	}
	public EDouble val(Double value){
		return context.val(value);
	}
	public EBigDecimal val(BigDecimal value){
		return context.val(value);
	}
	public EDateTime val(LocalDateTime value){
		return context.val(value);
	}*/
}
