package com.persistentbit.sql.meta.data;

import java.lang.SuppressWarnings;
import java.util.Optional;
import com.persistentbit.javacodegen.annotations.NoGet;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.Generated;
import java.util.function.Function;
import com.persistentbit.string.UString;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.CaseClass;
import com.persistentbit.result.Result;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.javacodegen.annotations.NoWith;

/**
 * TODOC
 *
 * @author petermuys
 * @since 5/06/17
 */
@CaseClass
public class DbMetaCatalog {
	@Nullable
	public  final	String	name;
	
	
	@Generated
	public DbMetaCatalog(@Nullable String name){
			this.name = name;
	}
	@Generated
	public DbMetaCatalog(){
			this(null);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder {
		private	String	name;
		
		
		public  Builder	setName(@Nullable String name){
			this.name	=	name;
			return this;
		}
	}
	/**
	 * Get the value of field {@link #name}.<br>
	 * @return {@link #name}
	 */
	@Generated
	public  Optional<String>	getName(){
		return Optional.ofNullable(this.name);
	}
	/**
	 * Create a copy of this DbMetaCatalog object with a new value for field {@link #name}.<br>
	 * @param name The new value for field {@link #name}
	 * @return A new instance of {@link DbMetaCatalog}
	 */
	@Generated
	public  DbMetaCatalog	withName(@Nullable String name){
		return new DbMetaCatalog(name);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbMetaCatalog == false) return false;
		DbMetaCatalog obj = (DbMetaCatalog)o;
		if(name != null ? !name.equals(obj.name) : obj.name!= null) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.name != null ? this.name.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbMetaCatalog[" + 
			"name=" + (name == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(name),32,"...") + '\"') +
			']';
	}
	@Generated
	public  DbMetaCatalog	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setName(this.name);
		b = updater.apply(b);
		return new DbMetaCatalog(b.name);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static DbMetaCatalog	build(ThrowingFunction<Builder, Builder, Exception> setter){
		Builder b = setter.toNonChecked().apply(new Builder());
		return new DbMetaCatalog(b.name);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Result<DbMetaCatalog>	buildExc(ThrowingFunction<Builder, Builder,Exception> setter){
		return Result.noExceptions(() -> setter.apply(new Builder())).mapExc(b -> new DbMetaCatalog(b.name));
	}
}
