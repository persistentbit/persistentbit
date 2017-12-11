package com.persistentbit.sql.dsl.codegen.config;

import java.lang.SuppressWarnings;
import java.util.Optional;
import com.persistentbit.javacodegen.annotations.NoGet;
import com.persistentbit.javacodegen.annotations.NOT;
import java.util.Objects;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.collections.PList;
import java.util.function.Function;
import com.persistentbit.string.UString;
import com.persistentbit.javacodegen.annotations.*;
import com.persistentbit.functions.ThrowingFunction;
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
public class TableDef {
	private  final	String	tableName;
	@Nullable
	private  final	String	javaName;
	@DefaultEmpty
	private  final	PList<ColumnDef>	columns;
	@DefaultEmpty
	private  final	PList<String>	ignoreColumns;
	
	
	@Generated
	public TableDef(String tableName, @Nullable String javaName, @Nullable PList<ColumnDef> columns, @Nullable PList<String> ignoreColumns){
			this.tableName = Objects.requireNonNull(tableName, "tableName can not be null");
			this.javaName = javaName;
			this.columns = columns == null ? PList.empty() : columns;
			this.ignoreColumns = ignoreColumns == null ? PList.empty() : ignoreColumns;
	}
	@Generated
	public TableDef(String tableName){
			this(tableName, null, null, null);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1> {
		private	String	tableName;
		private	String	javaName;
		private	PList<ColumnDef>	columns;
		private	PList<String>	ignoreColumns;
		
		
		public  Builder<SET>	setTableName(String tableName){
			this.tableName	=	tableName;
			return (Builder<SET>)this;
		}
		public  Builder<_T1>	setJavaName(@Nullable String javaName){
			this.javaName	=	javaName;
			return this;
		}
		public  Builder<_T1>	setColumns(@Nullable PList<ColumnDef> columns){
			this.columns	=	columns;
			return this;
		}
		public  Builder<_T1>	setIgnoreColumns(@Nullable PList<String> ignoreColumns){
			this.ignoreColumns	=	ignoreColumns;
			return this;
		}
	}
	/**
	 * Get the value of field {@link #tableName}.<br>
	 * @return {@link #tableName}
	 */
	@Generated
	public  String	getTableName(){
		return this.tableName;
	}
	/**
	 * Create a copy of this TableDef object with a new value for field {@link #tableName}.<br>
	 * @param tableName The new value for field {@link #tableName}
	 * @return A new instance of {@link TableDef}
	 */
	@Generated
	public  TableDef	withTableName(String tableName){
		return new TableDef(tableName, javaName, columns, ignoreColumns);
	}
	/**
	 * Get the value of field {@link #javaName}.<br>
	 * @return {@link #javaName}
	 */
	@Generated
	public  Optional<String>	getJavaName(){
		return Optional.ofNullable(this.javaName);
	}
	/**
	 * Create a copy of this TableDef object with a new value for field {@link #javaName}.<br>
	 * @param javaName The new value for field {@link #javaName}
	 * @return A new instance of {@link TableDef}
	 */
	@Generated
	public  TableDef	withJavaName(@Nullable String javaName){
		return new TableDef(tableName, javaName, columns, ignoreColumns);
	}
	/**
	 * Get the value of field {@link #columns}.<br>
	 * @return {@link #columns}
	 */
	@Generated
	public  PList<ColumnDef>	getColumns(){
		return this.columns;
	}
	/**
	 * Create a copy of this TableDef object with a new value for field {@link #columns}.<br>
	 * @param columns The new value for field {@link #columns}
	 * @return A new instance of {@link TableDef}
	 */
	@Generated
	public  TableDef	withColumns(@Nullable PList<ColumnDef> columns){
		return new TableDef(tableName, javaName, columns, ignoreColumns);
	}
	/**
	 * Get the value of field {@link #ignoreColumns}.<br>
	 * @return {@link #ignoreColumns}
	 */
	@Generated
	public  PList<String>	getIgnoreColumns(){
		return this.ignoreColumns;
	}
	/**
	 * Create a copy of this TableDef object with a new value for field {@link #ignoreColumns}.<br>
	 * @param ignoreColumns The new value for field {@link #ignoreColumns}
	 * @return A new instance of {@link TableDef}
	 */
	@Generated
	public  TableDef	withIgnoreColumns(@Nullable PList<String> ignoreColumns){
		return new TableDef(tableName, javaName, columns, ignoreColumns);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof TableDef == false) return false;
		TableDef obj = (TableDef)o;
		if(!tableName.equals(obj.tableName)) return false;
		if(javaName != null ? !javaName.equals(obj.javaName) : obj.javaName!= null) return false;
		if(!columns.equals(obj.columns)) return false;
		if(!ignoreColumns.equals(obj.ignoreColumns)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.tableName != null ? this.tableName.hashCode() : 0);
		result = 31 * result + (this.javaName != null ? this.javaName.hashCode() : 0);
		result = 31 * result + (this.columns != null ? this.columns.hashCode() : 0);
		result = 31 * result + (this.ignoreColumns != null ? this.ignoreColumns.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "TableDef[" + 
			"tableName=" + (tableName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(tableName),32,"...") + '\"') +
			", javaName=" + (javaName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(javaName),32,"...") + '\"') +
			", columns=" + columns + 
			", ignoreColumns=" + ignoreColumns + 
			']';
	}
	@Generated
	public  TableDef	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setTableName(this.tableName);
		b.setJavaName(this.javaName);
		b.setColumns(this.columns);
		b.setIgnoreColumns(this.ignoreColumns);
		b = updater.apply(b);
		return new TableDef(b.tableName, b.javaName, b.columns, b.ignoreColumns);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static TableDef	build(ThrowingFunction<Builder<NOT>, Builder<SET>, Exception> setter){
		Builder b = setter.toNonChecked().apply(new Builder());
		return new TableDef(b.tableName, b.javaName, b.columns, b.ignoreColumns);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Result<TableDef>	buildExc(ThrowingFunction<Builder<NOT>, Builder<SET>,Exception> setter){
		return Result.noExceptions(() -> setter.apply(new Builder<>())).mapExc(b -> new TableDef(b.tableName, b.javaName, b.columns, b.ignoreColumns));
	}
}
