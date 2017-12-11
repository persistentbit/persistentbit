package com.persistentbit.sql.dsl.codegen.config;

import java.lang.SuppressWarnings;
import com.persistentbit.javacodegen.annotations.NoGet;
import com.persistentbit.javacodegen.annotations.NOT;
import java.util.Objects;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.collections.PList;
import java.util.function.Function;
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
 * @since 11/12/17
 */
@CaseClass
public class DbCodeGenConfig {
	private  final	PList<Instance>	instances;
	
	
	@Generated
	public DbCodeGenConfig(PList<Instance> instances){
			this.instances = Objects.requireNonNull(instances, "instances can not be null");
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1> {
		private	PList<Instance>	instances;
		
		
		public  Builder<SET>	setInstances(PList<Instance> instances){
			this.instances	=	instances;
			return (Builder<SET>)this;
		}
	}
	/**
	 * Get the value of field {@link #instances}.<br>
	 * @return {@link #instances}
	 */
	@Generated
	public  PList<Instance>	getInstances(){
		return this.instances;
	}
	/**
	 * Create a copy of this DbCodeGenConfig object with a new value for field {@link #instances}.<br>
	 * @param instances The new value for field {@link #instances}
	 * @return A new instance of {@link DbCodeGenConfig}
	 */
	@Generated
	public  DbCodeGenConfig	withInstances(PList<Instance> instances){
		return new DbCodeGenConfig(instances);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbCodeGenConfig == false) return false;
		DbCodeGenConfig obj = (DbCodeGenConfig)o;
		if(!instances.equals(obj.instances)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.instances != null ? this.instances.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbCodeGenConfig[" + 
			"instances=" + instances + 
			']';
	}
	@Generated
	public  DbCodeGenConfig	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setInstances(this.instances);
		b = updater.apply(b);
		return new DbCodeGenConfig(b.instances);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static DbCodeGenConfig	build(ThrowingFunction<Builder<NOT>, Builder<SET>, Exception> setter){
		Builder b = setter.toNonChecked().apply(new Builder());
		return new DbCodeGenConfig(b.instances);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Result<DbCodeGenConfig>	buildExc(ThrowingFunction<Builder<NOT>, Builder<SET>,Exception> setter){
		return Result.noExceptions(() -> setter.apply(new Builder<>())).mapExc(b -> new DbCodeGenConfig(b.instances));
	}
}
