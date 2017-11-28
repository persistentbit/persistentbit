package com.persistentbit.db.generated.c_persistenttest.s_persistenttest;

import java.lang.SuppressWarnings;
import com.persistentbit.sql.dsl.annotations.DbColumnName;
import com.persistentbit.javacodegen.annotations.NoGet;
import com.persistentbit.javacodegen.annotations.NOT;
import java.util.Objects;
import com.persistentbit.javacodegen.annotations.Generated;
import java.time.LocalDateTime;
import java.util.function.Function;
import com.persistentbit.string.UString;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.result.Result;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.javacodegen.annotations.SET;
import java.lang.String;
import com.persistentbit.javacodegen.annotations.NoWith;

public class SchemaHistory {
	@DbColumnName("createddate")
	private  final	LocalDateTime	createddate;
	@DbColumnName("package_name")
	private  final	String	packageName;
	@DbColumnName("update_name")
	private  final	String	updateName;
	
	
	@Generated
	public SchemaHistory(LocalDateTime createddate, String packageName, String updateName){
			this.createddate = Objects.requireNonNull(createddate, "createddate can not be null");
			this.packageName = Objects.requireNonNull(packageName, "packageName can not be null");
			this.updateName = Objects.requireNonNull(updateName, "updateName can not be null");
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3> {
		private	LocalDateTime	createddate;
		private	String	packageName;
		private	String	updateName;
		
		
		public  Builder<SET, _T2, _T3>	setCreateddate(LocalDateTime createddate){
			this.createddate	=	createddate;
			return (Builder<SET, _T2, _T3>)this;
		}
		public  Builder<_T1, SET, _T3>	setPackageName(String packageName){
			this.packageName	=	packageName;
			return (Builder<_T1, SET, _T3>)this;
		}
		public  Builder<_T1, _T2, SET>	setUpdateName(String updateName){
			this.updateName	=	updateName;
			return (Builder<_T1, _T2, SET>)this;
		}
	}
	/**
	 * Get the value of field {@link #createddate}.<br>
	 * @return {@link #createddate}
	 */
	@Generated
	public  LocalDateTime	getCreateddate(){
		return this.createddate;
	}
	/**
	 * Create a copy of this SchemaHistory object with a new value for field {@link #createddate}.<br>
	 * @param createddate The new value for field {@link #createddate}
	 * @return A new instance of {@link SchemaHistory}
	 */
	@Generated
	public  SchemaHistory	withCreateddate(LocalDateTime createddate){
		return new SchemaHistory(createddate, packageName, updateName);
	}
	/**
	 * Get the value of field {@link #packageName}.<br>
	 * @return {@link #packageName}
	 */
	@Generated
	public  String	getPackageName(){
		return this.packageName;
	}
	/**
	 * Create a copy of this SchemaHistory object with a new value for field {@link #packageName}.<br>
	 * @param packageName The new value for field {@link #packageName}
	 * @return A new instance of {@link SchemaHistory}
	 */
	@Generated
	public  SchemaHistory	withPackageName(String packageName){
		return new SchemaHistory(createddate, packageName, updateName);
	}
	/**
	 * Get the value of field {@link #updateName}.<br>
	 * @return {@link #updateName}
	 */
	@Generated
	public  String	getUpdateName(){
		return this.updateName;
	}
	/**
	 * Create a copy of this SchemaHistory object with a new value for field {@link #updateName}.<br>
	 * @param updateName The new value for field {@link #updateName}
	 * @return A new instance of {@link SchemaHistory}
	 */
	@Generated
	public  SchemaHistory	withUpdateName(String updateName){
		return new SchemaHistory(createddate, packageName, updateName);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof SchemaHistory == false) return false;
		SchemaHistory obj = (SchemaHistory)o;
		if(!createddate.equals(obj.createddate)) return false;
		if(!packageName.equals(obj.packageName)) return false;
		if(!updateName.equals(obj.updateName)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.createddate != null ? this.createddate.hashCode() : 0);
		result = 31 * result + (this.packageName != null ? this.packageName.hashCode() : 0);
		result = 31 * result + (this.updateName != null ? this.updateName.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "SchemaHistory[" + 
			"createddate=" + createddate + 
			", packageName=" + (packageName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(packageName),32,"...") + '\"') +
			", updateName=" + (updateName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(updateName),32,"...") + '\"') +
			']';
	}
	@Generated
	public  SchemaHistory	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setCreateddate(this.createddate);
		b.setPackageName(this.packageName);
		b.setUpdateName(this.updateName);
		b = updater.apply(b);
		return new SchemaHistory(b.createddate, b.packageName, b.updateName);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static SchemaHistory	build(ThrowingFunction<Builder<NOT,NOT,NOT>, Builder<SET,SET,SET>, Exception> setter){
		Builder b = setter.toNonChecked().apply(new Builder());
		return new SchemaHistory(b.createddate, b.packageName, b.updateName);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Result<SchemaHistory>	buildExc(ThrowingFunction<Builder<NOT,NOT,NOT>, Builder<SET,SET,SET>,Exception> setter){
		return Result.noExceptions(() -> setter.apply(new Builder<>())).mapExc(b -> new SchemaHistory(b.createddate, b.packageName, b.updateName));
	}
}
