package com.persistentbit.sql.dsl.codegen.config;

import java.lang.SuppressWarnings;
import java.util.Optional;
import com.persistentbit.javacodegen.annotations.NoGet;
import com.persistentbit.javacodegen.annotations.NOT;
import java.util.Objects;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.collections.PStream;
import com.persistentbit.collections.PList;
import java.util.function.Function;
import com.persistentbit.string.UString;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.CaseClass;
import com.persistentbit.result.Result;
import com.persistentbit.javacodegen.annotations.DefaultEmpty;
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
public class SchemaDef {
	@Nullable
	private  final	String	catalogName;
	@Nullable
	private  final	String	schemaName;
	@Nullable
	private  final	String	javaName;
	@DefaultEmpty
	private  final	PList<String>	tableNamePrefixes;
	@DefaultEmpty
	private  final	PList<String>	tableNamePostfixes;
	@DefaultEmpty
	private  final	PList<TableDef>	tables;
	@DefaultEmpty
	private  final	PList<String>	excludeTables;
	
	
	@Generated
	public SchemaDef(@Nullable String catalogName, @Nullable String schemaName, @Nullable String javaName, @Nullable PList<String> tableNamePrefixes, @Nullable PList<String> tableNamePostfixes, @Nullable PList<TableDef> tables, @Nullable PList<String> excludeTables){
			this.catalogName = catalogName;
			this.schemaName = schemaName;
			this.javaName = javaName;
			this.tableNamePrefixes = tableNamePrefixes == null ? PList.empty() : tableNamePrefixes;
			this.tableNamePostfixes = tableNamePostfixes == null ? PList.empty() : tableNamePostfixes;
			this.tables = tables == null ? PList.empty() : tables;
			this.excludeTables = excludeTables == null ? PList.empty() : excludeTables;
	}
	@Generated
	public SchemaDef(){
			this(null, null, null, null, null, null, null);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder {
		private	String	catalogName;
		private	String	schemaName;
		private	String	javaName;
		private	PList<String>	tableNamePrefixes;
		private	PList<String>	tableNamePostfixes;
		private	PList<TableDef>	tables;
		private	PList<String>	excludeTables;
		
		
		public  Builder	setCatalogName(@Nullable String catalogName){
			this.catalogName	=	catalogName;
			return this;
		}
		public  Builder	setSchemaName(@Nullable String schemaName){
			this.schemaName	=	schemaName;
			return this;
		}
		public  Builder	setJavaName(@Nullable String javaName){
			this.javaName	=	javaName;
			return this;
		}
		public  Builder	setTableNamePrefixes(@Nullable PList<String> tableNamePrefixes){
			this.tableNamePrefixes	=	tableNamePrefixes;
			return this;
		}
		public  Builder	setTableNamePostfixes(@Nullable PList<String> tableNamePostfixes){
			this.tableNamePostfixes	=	tableNamePostfixes;
			return this;
		}
		public  Builder	setTables(@Nullable PList<TableDef> tables){
			this.tables	=	tables;
			return this;
		}
		public  Builder	setExcludeTables(@Nullable PList<String> excludeTables){
			this.excludeTables	=	excludeTables;
			return this;
		}
	}
	public  SchemaDef	addTable(TableDef tableDef){
	    return withTables(tables.plus(Objects.requireNonNull(tableDef)));
	}
	public  SchemaDef	addExcludeTables(String... excludeTablePatterns){
	    return withExcludeTables(excludeTables.plusAll(PStream.from(excludeTablePatterns)));
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
	 * Create a copy of this SchemaDef object with a new value for field {@link #catalogName}.<br>
	 * @param catalogName The new value for field {@link #catalogName}
	 * @return A new instance of {@link SchemaDef}
	 */
	@Generated
	public  SchemaDef	withCatalogName(@Nullable String catalogName){
		return new SchemaDef(catalogName, schemaName, javaName, tableNamePrefixes, tableNamePostfixes, tables, excludeTables);
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
	 * Create a copy of this SchemaDef object with a new value for field {@link #schemaName}.<br>
	 * @param schemaName The new value for field {@link #schemaName}
	 * @return A new instance of {@link SchemaDef}
	 */
	@Generated
	public  SchemaDef	withSchemaName(@Nullable String schemaName){
		return new SchemaDef(catalogName, schemaName, javaName, tableNamePrefixes, tableNamePostfixes, tables, excludeTables);
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
	 * Create a copy of this SchemaDef object with a new value for field {@link #javaName}.<br>
	 * @param javaName The new value for field {@link #javaName}
	 * @return A new instance of {@link SchemaDef}
	 */
	@Generated
	public  SchemaDef	withJavaName(@Nullable String javaName){
		return new SchemaDef(catalogName, schemaName, javaName, tableNamePrefixes, tableNamePostfixes, tables, excludeTables);
	}
	/**
	 * Get the value of field {@link #tableNamePrefixes}.<br>
	 * @return {@link #tableNamePrefixes}
	 */
	@Generated
	public  PList<String>	getTableNamePrefixes(){
		return this.tableNamePrefixes;
	}
	/**
	 * Create a copy of this SchemaDef object with a new value for field {@link #tableNamePrefixes}.<br>
	 * @param tableNamePrefixes The new value for field {@link #tableNamePrefixes}
	 * @return A new instance of {@link SchemaDef}
	 */
	@Generated
	public  SchemaDef	withTableNamePrefixes(@Nullable PList<String> tableNamePrefixes){
		return new SchemaDef(catalogName, schemaName, javaName, tableNamePrefixes, tableNamePostfixes, tables, excludeTables);
	}
	/**
	 * Get the value of field {@link #tableNamePostfixes}.<br>
	 * @return {@link #tableNamePostfixes}
	 */
	@Generated
	public  PList<String>	getTableNamePostfixes(){
		return this.tableNamePostfixes;
	}
	/**
	 * Create a copy of this SchemaDef object with a new value for field {@link #tableNamePostfixes}.<br>
	 * @param tableNamePostfixes The new value for field {@link #tableNamePostfixes}
	 * @return A new instance of {@link SchemaDef}
	 */
	@Generated
	public  SchemaDef	withTableNamePostfixes(@Nullable PList<String> tableNamePostfixes){
		return new SchemaDef(catalogName, schemaName, javaName, tableNamePrefixes, tableNamePostfixes, tables, excludeTables);
	}
	/**
	 * Get the value of field {@link #tables}.<br>
	 * @return {@link #tables}
	 */
	@Generated
	public  PList<TableDef>	getTables(){
		return this.tables;
	}
	/**
	 * Create a copy of this SchemaDef object with a new value for field {@link #tables}.<br>
	 * @param tables The new value for field {@link #tables}
	 * @return A new instance of {@link SchemaDef}
	 */
	@Generated
	public  SchemaDef	withTables(@Nullable PList<TableDef> tables){
		return new SchemaDef(catalogName, schemaName, javaName, tableNamePrefixes, tableNamePostfixes, tables, excludeTables);
	}
	/**
	 * Get the value of field {@link #excludeTables}.<br>
	 * @return {@link #excludeTables}
	 */
	@Generated
	public  PList<String>	getExcludeTables(){
		return this.excludeTables;
	}
	/**
	 * Create a copy of this SchemaDef object with a new value for field {@link #excludeTables}.<br>
	 * @param excludeTables The new value for field {@link #excludeTables}
	 * @return A new instance of {@link SchemaDef}
	 */
	@Generated
	public  SchemaDef	withExcludeTables(@Nullable PList<String> excludeTables){
		return new SchemaDef(catalogName, schemaName, javaName, tableNamePrefixes, tableNamePostfixes, tables, excludeTables);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof SchemaDef == false) return false;
		SchemaDef obj = (SchemaDef)o;
		if(catalogName != null ? !catalogName.equals(obj.catalogName) : obj.catalogName!= null) return false;
		if(schemaName != null ? !schemaName.equals(obj.schemaName) : obj.schemaName!= null) return false;
		if(javaName != null ? !javaName.equals(obj.javaName) : obj.javaName!= null) return false;
		if(!tableNamePrefixes.equals(obj.tableNamePrefixes)) return false;
		if(!tableNamePostfixes.equals(obj.tableNamePostfixes)) return false;
		if(!tables.equals(obj.tables)) return false;
		if(!excludeTables.equals(obj.excludeTables)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.catalogName != null ? this.catalogName.hashCode() : 0);
		result = 31 * result + (this.schemaName != null ? this.schemaName.hashCode() : 0);
		result = 31 * result + (this.javaName != null ? this.javaName.hashCode() : 0);
		result = 31 * result + (this.tableNamePrefixes != null ? this.tableNamePrefixes.hashCode() : 0);
		result = 31 * result + (this.tableNamePostfixes != null ? this.tableNamePostfixes.hashCode() : 0);
		result = 31 * result + (this.tables != null ? this.tables.hashCode() : 0);
		result = 31 * result + (this.excludeTables != null ? this.excludeTables.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "SchemaDef[" + 
			"catalogName=" + (catalogName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(catalogName),32,"...") + '\"') +
			", schemaName=" + (schemaName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(schemaName),32,"...") + '\"') +
			", javaName=" + (javaName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(javaName),32,"...") + '\"') +
			", tableNamePrefixes=" + tableNamePrefixes + 
			", tableNamePostfixes=" + tableNamePostfixes + 
			", tables=" + tables + 
			", excludeTables=" + excludeTables + 
			']';
	}
	@Generated
	public  SchemaDef	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setCatalogName(this.catalogName);
		b.setSchemaName(this.schemaName);
		b.setJavaName(this.javaName);
		b.setTableNamePrefixes(this.tableNamePrefixes);
		b.setTableNamePostfixes(this.tableNamePostfixes);
		b.setTables(this.tables);
		b.setExcludeTables(this.excludeTables);
		b = updater.apply(b);
		return new SchemaDef(b.catalogName, b.schemaName, b.javaName, b.tableNamePrefixes, b.tableNamePostfixes, b.tables, b.excludeTables);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static SchemaDef	build(ThrowingFunction<Builder, Builder, Exception> setter){
		Builder b = setter.toNonChecked().apply(new Builder());
		return new SchemaDef(b.catalogName, b.schemaName, b.javaName, b.tableNamePrefixes, b.tableNamePostfixes, b.tables, b.excludeTables);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Result<SchemaDef>	buildExc(ThrowingFunction<Builder, Builder,Exception> setter){
		return Result.noExceptions(() -> setter.apply(new Builder())).mapExc(b -> new SchemaDef(b.catalogName, b.schemaName, b.javaName, b.tableNamePrefixes, b.tableNamePostfixes, b.tables, b.excludeTables));
	}
}
