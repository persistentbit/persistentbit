package com.persistentbit.sql.dsl.codegen.config;

import java.lang.SuppressWarnings;
import com.persistentbit.javacodegen.annotations.NoGet;
import com.persistentbit.javacodegen.annotations.NOT;
import java.util.Objects;
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
 * @since 11/12/17
 */
@CaseClass
public class ColumnDef {
	private  final	String	columnName;
	private  final	String	javaName;
	
	
	@Generated
	public ColumnDef(String columnName, String javaName){
			this.columnName = Objects.requireNonNull(columnName, "columnName can not be null");
			this.javaName = Objects.requireNonNull(javaName, "javaName can not be null");
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2> {
		private	String	columnName;
		private	String	javaName;
		
		
		public  Builder<SET, _T2>	setColumnName(String columnName){
			this.columnName	=	columnName;
			return (Builder<SET, _T2>)this;
		}
		public  Builder<_T1, SET>	setJavaName(String javaName){
			this.javaName	=	javaName;
			return (Builder<_T1, SET>)this;
		}
	}
	/**
	 * Get the value of field {@link #columnName}.<br>
	 * @return {@link #columnName}
	 */
	@Generated
	public  String	getColumnName(){
		return this.columnName;
	}
	/**
	 * Create a copy of this ColumnDef object with a new value for field {@link #columnName}.<br>
	 * @param columnName The new value for field {@link #columnName}
	 * @return A new instance of {@link ColumnDef}
	 */
	@Generated
	public  ColumnDef	withColumnName(String columnName){
		return new ColumnDef(columnName, javaName);
	}
	/**
	 * Get the value of field {@link #javaName}.<br>
	 * @return {@link #javaName}
	 */
	@Generated
	public  String	getJavaName(){
		return this.javaName;
	}
	/**
	 * Create a copy of this ColumnDef object with a new value for field {@link #javaName}.<br>
	 * @param javaName The new value for field {@link #javaName}
	 * @return A new instance of {@link ColumnDef}
	 */
	@Generated
	public  ColumnDef	withJavaName(String javaName){
		return new ColumnDef(columnName, javaName);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof ColumnDef == false) return false;
		ColumnDef obj = (ColumnDef)o;
		if(!columnName.equals(obj.columnName)) return false;
		if(!javaName.equals(obj.javaName)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.columnName != null ? this.columnName.hashCode() : 0);
		result = 31 * result + (this.javaName != null ? this.javaName.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "ColumnDef[" + 
			"columnName=" + (columnName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(columnName),32,"...") + '\"') +
			", javaName=" + (javaName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(javaName),32,"...") + '\"') +
			']';
	}
	@Generated
	public  ColumnDef	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setColumnName(this.columnName);
		b.setJavaName(this.javaName);
		b = updater.apply(b);
		return new ColumnDef(b.columnName, b.javaName);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static ColumnDef	build(ThrowingFunction<Builder<NOT,NOT>, Builder<SET,SET>, Exception> setter){
		Builder b = setter.toNonChecked().apply(new Builder());
		return new ColumnDef(b.columnName, b.javaName);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Result<ColumnDef>	buildExc(ThrowingFunction<Builder<NOT,NOT>, Builder<SET,SET>,Exception> setter){
		return Result.noExceptions(() -> setter.apply(new Builder<>())).mapExc(b -> new ColumnDef(b.columnName, b.javaName));
	}
}
