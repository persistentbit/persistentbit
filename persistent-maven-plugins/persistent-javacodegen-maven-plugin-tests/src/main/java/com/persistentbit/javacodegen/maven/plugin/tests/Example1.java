package com.persistentbit.javacodegen.maven.plugin.tests;

import com.persistentbit.javacodegen.annotations.CaseClass;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.result.Result;
import com.persistentbit.core.utils.builders.NOT;

import java.lang.SuppressWarnings;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.string.UString;

import java.util.Objects;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.core.utils.builders.SET;
import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 11/11/17
 */
@CaseClass
public class Example1 {
	private  final	int	id;
	private  final	String	name;
	
	
	@Generated
	public Example1(int id, String name){
			this.id = Objects.requireNonNull(id, "id can not be null");
			this.name = Objects.requireNonNull(name, "name can not be null");
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2> {
		private	int	id;
		private	String	name;
		
		
		public  Builder<SET, _T2>	setId(int id){
			this.id	=	id;
			return (Builder<SET, _T2>)this;
		}
		public  Builder<_T1, SET>	setName(String name){
			this.name	=	name;
			return (Builder<_T1, SET>)this;
		}
	}
	/**
	 * Get the value of field {@link #id}.<br>
	 * @return {@link #id}
	 */
	@Generated
	public  int	getId(){
		return this.id;
	}
	/**
	 * Create a copy of this Example1 object with a new value for field {@link #id}.<br>
	 * @param id The new value for field {@link #id}
	 * @return A new instance of {@link Example1}
	 */
	@Generated
	public  Example1	withId(int id){
		return new Example1(id, name);
	}
	/**
	 * Get the value of field {@link #name}.<br>
	 * @return {@link #name}
	 */
	@Generated
	public  String	getName(){
		return this.name;
	}
	/**
	 * Create a copy of this Example1 object with a new value for field {@link #name}.<br>
	 * @param name The new value for field {@link #name}
	 * @return A new instance of {@link Example1}
	 */
	@Generated
	public  Example1	withName(String name){
		return new Example1(id, name);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof Example1 == false) return false;
		Example1 obj = (Example1)o;
		if(id!= obj.id) return false;
		if(!name.equals(obj.name)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = this.id;
		result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "Example1[" + 
			"id=" + id + 
			", name=" + (name == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(name),32,"...") + '\"') +
			']';
	}
	@Generated
	public  Example1	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setId(this.id);
		b.setName(this.name);
		b = updater.apply(b);
		return new Example1(b.id, b.name);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Example1	build(ThrowingFunction<Builder<NOT,NOT>, Builder<SET,SET>, Exception> setter){
		Builder b = setter.toNonChecked().apply(new Builder());
		return new Example1(b.id, b.name);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Result<Example1>	buildExc(ThrowingFunction<Builder<NOT,NOT>, Builder<SET,SET>,Exception> setter){
		return setter.applyResult(new Builder<>()).mapExc(b -> new Example1(b.id, b.name));
	}
}
