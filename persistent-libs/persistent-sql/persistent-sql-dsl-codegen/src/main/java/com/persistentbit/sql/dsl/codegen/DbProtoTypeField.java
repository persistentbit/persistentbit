package com.persistentbit.sql.dsl.codegen;

import com.persistentbit.javacodegen.annotations.NoBuilder;
import com.persistentbit.javacodegen.annotations.Generated;
import java.util.function.Function;
import com.persistentbit.javacodegen.annotations.CaseClass;
import com.persistentbit.code.annotations.Nullable;
import java.util.function.Predicate;

/**
 * TODOC
 *
 * @author petermuys
 * @since 22/06/17
 */
@CaseClass
@NoBuilder
public class DbProtoTypeField {
	private  final	Predicate<String>	fieldSelector;
	private  final	Function<String,String>	nameConverter;
	
	
	public DbProtoTypeField(Predicate<String> fieldSelector, Function<String,String> nameConverter){
	    this.fieldSelector = fieldSelector;
	    this.nameConverter = nameConverter;
	}
	public  static DbProtoTypeField	nameStartingWith(String prefix){
	    return new DbProtoTypeField(name -> name.startsWith(prefix), name -> name.substring(prefix.length()));
	}
	public  static DbProtoTypeField	nameEndingWith(String suffix){
	    return new DbProtoTypeField(name -> name.endsWith(suffix), name -> name.substring(0, name.length() - suffix.length()));
	}
	/**
	 * Get the value of field {@link #fieldSelector}.<br>
	 * @return {@link #fieldSelector}
	 */
	@Generated
	public  Predicate<String>	getFieldSelector(){
		return this.fieldSelector;
	}
	/**
	 * Create a copy of this DbProtoTypeField object with a new value for field {@link #fieldSelector}.<br>
	 * @param fieldSelector The new value for field {@link #fieldSelector}
	 * @return A new instance of {@link DbProtoTypeField}
	 */
	@Generated
	public  DbProtoTypeField	withFieldSelector(Predicate<String> fieldSelector){
		return new DbProtoTypeField(fieldSelector, nameConverter);
	}
	/**
	 * Get the value of field {@link #nameConverter}.<br>
	 * @return {@link #nameConverter}
	 */
	@Generated
	public  Function<String,String>	getNameConverter(){
		return this.nameConverter;
	}
	/**
	 * Create a copy of this DbProtoTypeField object with a new value for field {@link #nameConverter}.<br>
	 * @param nameConverter The new value for field {@link #nameConverter}
	 * @return A new instance of {@link DbProtoTypeField}
	 */
	@Generated
	public  DbProtoTypeField	withNameConverter(Function<String,String> nameConverter){
		return new DbProtoTypeField(fieldSelector, nameConverter);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbProtoTypeField == false) return false;
		DbProtoTypeField obj = (DbProtoTypeField)o;
		if(!fieldSelector.equals(obj.fieldSelector)) return false;
		if(!nameConverter.equals(obj.nameConverter)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.fieldSelector != null ? this.fieldSelector.hashCode() : 0);
		result = 31 * result + (this.nameConverter != null ? this.nameConverter.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbProtoTypeField[" + 
			"fieldSelector=" + fieldSelector + 
			", nameConverter=" + nameConverter + 
			']';
	}
}
