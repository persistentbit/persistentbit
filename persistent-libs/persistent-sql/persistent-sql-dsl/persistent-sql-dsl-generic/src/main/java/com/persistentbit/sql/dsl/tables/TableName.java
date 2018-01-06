package com.persistentbit.sql.dsl.tables;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.javacodegen.annotations.CaseClass;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NoBuilder;
import com.persistentbit.string.UString;

import java.util.Objects;
import java.util.Optional;

/**
 * TODOC
 *
 * @author petermuys
 * @since 19/12/17
 */
@CaseClass
@NoBuilder
public class TableName {
	@Nullable
	private  final	String	catalogName;
	@Nullable
	private  final	String	schemaName;
	private  final	String	tableName;
	
	
	@Generated
	public TableName(@Nullable String catalogName, @Nullable String schemaName, String tableName){
			this.catalogName = catalogName;
			this.schemaName = schemaName;
			this.tableName = Objects.requireNonNull(tableName, "tableName can not be null");
	}
	@Generated
	public TableName(String tableName){
			this(null, null, tableName);
	}
	/**
	 * Get the value of field {@link #catalogName}.<br>
	 * @return {@link #catalogName}
	 */
	@Generated
	public  Optional<String>	getCatalogName(){
		return Optional.ofNullable(this.catalogName);
	}
	/**
	 * Create a copy of this TableName object with a new value for field {@link #catalogName}.<br>
	 * @param catalogName The new value for field {@link #catalogName}
	 * @return A new instance of {@link TableName}
	 */
	@Generated
	public  TableName	withCatalogName(@Nullable String catalogName){
		return new TableName(catalogName, schemaName, tableName);
	}
	/**
	 * Get the value of field {@link #schemaName}.<br>
	 * @return {@link #schemaName}
	 */
	@Generated
	public  Optional<String>	getSchemaName(){
		return Optional.ofNullable(this.schemaName);
	}
	/**
	 * Create a copy of this TableName object with a new value for field {@link #schemaName}.<br>
	 * @param schemaName The new value for field {@link #schemaName}
	 * @return A new instance of {@link TableName}
	 */
	@Generated
	public  TableName	withSchemaName(@Nullable String schemaName){
		return new TableName(catalogName, schemaName, tableName);
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
	 * Create a copy of this TableName object with a new value for field {@link #tableName}.<br>
	 * @param tableName The new value for field {@link #tableName}
	 * @return A new instance of {@link TableName}
	 */
	@Generated
	public  TableName	withTableName(String tableName){
		return new TableName(catalogName, schemaName, tableName);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof TableName == false) return false;
		TableName obj = (TableName)o;
		if(catalogName != null ? !catalogName.equals(obj.catalogName) : obj.catalogName!= null) return false;
		if(schemaName != null ? !schemaName.equals(obj.schemaName) : obj.schemaName!= null) return false;
		if(!tableName.equals(obj.tableName)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.catalogName != null ? this.catalogName.hashCode() : 0);
		result = 31 * result + (this.schemaName != null ? this.schemaName.hashCode() : 0);
		result = 31 * result + (this.tableName != null ? this.tableName.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "TableName[" + 
			"catalogName=" + (catalogName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(catalogName),32,"...") + '\"') +
			", schemaName=" + (schemaName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(schemaName),32,"...") + '\"') +
			", tableName=" + (tableName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(tableName),32,"...") + '\"') +
			']';
	}
}
