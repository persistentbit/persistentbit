package com.persistentbit.sql.dsl.codegen;

import java.lang.SuppressWarnings;
import com.persistentbit.sql.dsl.codegen.dbjavafields.DbJavaTable;
import com.persistentbit.javacodegen.annotations.NoGet;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.sql.dsl.codegen.generic.DbEnumType;
import com.persistentbit.collections.PSet;
import com.persistentbit.collections.PList;
import java.util.function.Function;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.DefaultValue;
import com.persistentbit.javacodegen.annotations.CaseClass;
import com.persistentbit.result.Result;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.javacodegen.annotations.NoWith;
import com.persistentbit.sql.meta.data.DbMetaUDT;
import com.persistentbit.sql.dsl.codegen.generic.DbCustomType;

/**
 * TODOC
 *
 * @author petermuys
 * @since 9/12/17
 */
@CaseClass
public class DbDefinition {
	@DefaultValue("PList.empty()")
	private  final	PList<DbJavaTable>	tables;
	@DefaultValue("PSet.empty()")
	private  final	PSet<DbCustomType>	customTypes;
	@DefaultValue("PSet.empty()")
	private  final	PSet<DbEnumType>	enumTypes;
	@DefaultValue("PSet.empty()")
	private  final	PSet<DbMetaUDT>	domainObjects;
	
	
	@Generated
	public DbDefinition(@Nullable PList<DbJavaTable> tables, @Nullable PSet<DbCustomType> customTypes, @Nullable PSet<DbEnumType> enumTypes, @Nullable PSet<DbMetaUDT> domainObjects){
			this.tables = tables == null ? PList.empty() : tables;
			this.customTypes = customTypes == null ? PSet.empty() : customTypes;
			this.enumTypes = enumTypes == null ? PSet.empty() : enumTypes;
			this.domainObjects = domainObjects == null ? PSet.empty() : domainObjects;
	}
	@Generated
	public DbDefinition(){
			this(null, null, null, null);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder {
		private	PList<DbJavaTable>	tables;
		private	PSet<DbCustomType>	customTypes;
		private	PSet<DbEnumType>	enumTypes;
		private	PSet<DbMetaUDT>	domainObjects;
		
		
		public  Builder	setTables(@Nullable PList<DbJavaTable> tables){
			this.tables	=	tables;
			return this;
		}
		public  Builder	setCustomTypes(@Nullable PSet<DbCustomType> customTypes){
			this.customTypes	=	customTypes;
			return this;
		}
		public  Builder	setEnumTypes(@Nullable PSet<DbEnumType> enumTypes){
			this.enumTypes	=	enumTypes;
			return this;
		}
		public  Builder	setDomainObjects(@Nullable PSet<DbMetaUDT> domainObjects){
			this.domainObjects	=	domainObjects;
			return this;
		}
	}
	/**
	 * Get the value of field {@link #tables}.<br>
	 * @return {@link #tables}
	 */
	@Generated
	public  PList<DbJavaTable>	getTables(){
		return this.tables;
	}
	/**
	 * Create a copy of this DbDefinition object with a new value for field {@link #tables}.<br>
	 * @param tables The new value for field {@link #tables}
	 * @return A new instance of {@link DbDefinition}
	 */
	@Generated
	public  DbDefinition	withTables(@Nullable PList<DbJavaTable> tables){
		return new DbDefinition(tables, customTypes, enumTypes, domainObjects);
	}
	/**
	 * Get the value of field {@link #customTypes}.<br>
	 * @return {@link #customTypes}
	 */
	@Generated
	public  PSet<DbCustomType>	getCustomTypes(){
		return this.customTypes;
	}
	/**
	 * Create a copy of this DbDefinition object with a new value for field {@link #customTypes}.<br>
	 * @param customTypes The new value for field {@link #customTypes}
	 * @return A new instance of {@link DbDefinition}
	 */
	@Generated
	public  DbDefinition	withCustomTypes(@Nullable PSet<DbCustomType> customTypes){
		return new DbDefinition(tables, customTypes, enumTypes, domainObjects);
	}
	/**
	 * Get the value of field {@link #enumTypes}.<br>
	 * @return {@link #enumTypes}
	 */
	@Generated
	public  PSet<DbEnumType>	getEnumTypes(){
		return this.enumTypes;
	}
	/**
	 * Create a copy of this DbDefinition object with a new value for field {@link #enumTypes}.<br>
	 * @param enumTypes The new value for field {@link #enumTypes}
	 * @return A new instance of {@link DbDefinition}
	 */
	@Generated
	public  DbDefinition	withEnumTypes(@Nullable PSet<DbEnumType> enumTypes){
		return new DbDefinition(tables, customTypes, enumTypes, domainObjects);
	}
	/**
	 * Get the value of field {@link #domainObjects}.<br>
	 * @return {@link #domainObjects}
	 */
	@Generated
	public  PSet<DbMetaUDT>	getDomainObjects(){
		return this.domainObjects;
	}
	/**
	 * Create a copy of this DbDefinition object with a new value for field {@link #domainObjects}.<br>
	 * @param domainObjects The new value for field {@link #domainObjects}
	 * @return A new instance of {@link DbDefinition}
	 */
	@Generated
	public  DbDefinition	withDomainObjects(@Nullable PSet<DbMetaUDT> domainObjects){
		return new DbDefinition(tables, customTypes, enumTypes, domainObjects);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbDefinition == false) return false;
		DbDefinition obj = (DbDefinition)o;
		if(!tables.equals(obj.tables)) return false;
		if(!customTypes.equals(obj.customTypes)) return false;
		if(!enumTypes.equals(obj.enumTypes)) return false;
		if(!domainObjects.equals(obj.domainObjects)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.tables != null ? this.tables.hashCode() : 0);
		result = 31 * result + (this.customTypes != null ? this.customTypes.hashCode() : 0);
		result = 31 * result + (this.enumTypes != null ? this.enumTypes.hashCode() : 0);
		result = 31 * result + (this.domainObjects != null ? this.domainObjects.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbDefinition[" + 
			"tables=" + tables + 
			", customTypes=" + customTypes + 
			", enumTypes=" + enumTypes + 
			", domainObjects=" + domainObjects + 
			']';
	}
	@Generated
	public  DbDefinition	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setTables(this.tables);
		b.setCustomTypes(this.customTypes);
		b.setEnumTypes(this.enumTypes);
		b.setDomainObjects(this.domainObjects);
		b = updater.apply(b);
		return new DbDefinition(b.tables, b.customTypes, b.enumTypes, b.domainObjects);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static DbDefinition	build(ThrowingFunction<Builder, Builder, Exception> setter){
		Builder b = setter.toNonChecked().apply(new Builder());
		return new DbDefinition(b.tables, b.customTypes, b.enumTypes, b.domainObjects);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Result<DbDefinition>	buildExc(ThrowingFunction<Builder, Builder,Exception> setter){
		return Result.noExceptions(() -> setter.apply(new Builder())).mapExc(b -> new DbDefinition(b.tables, b.customTypes, b.enumTypes, b.domainObjects));
	}
}
