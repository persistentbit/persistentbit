package com.persistentbit.sql.dsl.postgres.rt.customtypes;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.javacodegen.annotations.CaseClass;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NoBuilder;
import com.persistentbit.javacodegen.annotations.NoWith;
import com.persistentbit.string.UString;

import java.util.Objects;

/**
 * TODOC
 *
 * @author petermuys
 * @since 14/07/17
 */
@CaseClass
@NoBuilder
@NoWith
public class Inet {
	private  final	String	value;
	
	
	@Generated
	public Inet(String value){
			this.value = Objects.requireNonNull(value, "value can not be null");
	}
	/**
	 * Get the value of field {@link #value}.<br>
	 * @return {@link #value}
	 */
	@Generated
	public  String	getValue(){
		return this.value;
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof Inet == false) return false;
		Inet obj = (Inet)o;
		if(!value.equals(obj.value)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.value != null ? this.value.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "Inet[" + 
			"value=" + (value == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(value),32,"...") + '\"') +
			']';
	}
}
