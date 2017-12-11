package com.persistentbit.sql.dsl.codegen.config;

import java.lang.SuppressWarnings;
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
public class Instance {
	@DefaultValue("\"Db\"")
	private  final	String	javaDbName;
	private  final	Connector	connector;
	private  final	PList<SchemaDef>	schemas;
	private  final	CodeGen	codeGen;
	@DefaultValue("DbNameToJavaNameType.snakeToMixedCase")
	private  final	DbNameToJavaNameType	nameConversionType;
	
	
	@Generated
	public Instance(@Nullable String javaDbName, Connector connector, PList<SchemaDef> schemas, CodeGen codeGen, @Nullable DbNameToJavaNameType nameConversionType){
			this.javaDbName = javaDbName == null ? "Db" : javaDbName;
			this.connector = Objects.requireNonNull(connector, "connector can not be null");
			this.schemas = Objects.requireNonNull(schemas, "schemas can not be null");
			this.codeGen = Objects.requireNonNull(codeGen, "codeGen can not be null");
			this.nameConversionType = nameConversionType == null ? DbNameToJavaNameType.snakeToMixedCase : nameConversionType;
	}
	@Generated
	public Instance(Connector connector, PList<SchemaDef> schemas, CodeGen codeGen){
			this(null, connector, schemas, codeGen, null);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3> {
		private	String	javaDbName;
		private	Connector	connector;
		private	PList<SchemaDef>	schemas;
		private	CodeGen	codeGen;
		private	DbNameToJavaNameType	nameConversionType;
		
		
		public  Builder<_T1, _T2, _T3>	setJavaDbName(@Nullable String javaDbName){
			this.javaDbName	=	javaDbName;
			return this;
		}
		public  Builder<SET, _T2, _T3>	setConnector(Connector connector){
			this.connector	=	connector;
			return (Builder<SET, _T2, _T3>)this;
		}
		public  Builder<_T1, SET, _T3>	setSchemas(PList<SchemaDef> schemas){
			this.schemas	=	schemas;
			return (Builder<_T1, SET, _T3>)this;
		}
		public  Builder<_T1, _T2, SET>	setCodeGen(CodeGen codeGen){
			this.codeGen	=	codeGen;
			return (Builder<_T1, _T2, SET>)this;
		}
		public  Builder<_T1, _T2, _T3>	setNameConversionType(@Nullable DbNameToJavaNameType nameConversionType){
			this.nameConversionType	=	nameConversionType;
			return this;
		}
	}
	/**
	 * Get the value of field {@link #javaDbName}.<br>
	 * @return {@link #javaDbName}
	 */
	@Generated
	public  String	getJavaDbName(){
		return this.javaDbName;
	}
	/**
	 * Create a copy of this Instance object with a new value for field {@link #javaDbName}.<br>
	 * @param javaDbName The new value for field {@link #javaDbName}
	 * @return A new instance of {@link Instance}
	 */
	@Generated
	public  Instance	withJavaDbName(@Nullable String javaDbName){
		return new Instance(javaDbName, connector, schemas, codeGen, nameConversionType);
	}
	/**
	 * Get the value of field {@link #connector}.<br>
	 * @return {@link #connector}
	 */
	@Generated
	public  Connector	getConnector(){
		return this.connector;
	}
	/**
	 * Create a copy of this Instance object with a new value for field {@link #connector}.<br>
	 * @param connector The new value for field {@link #connector}
	 * @return A new instance of {@link Instance}
	 */
	@Generated
	public  Instance	withConnector(Connector connector){
		return new Instance(javaDbName, connector, schemas, codeGen, nameConversionType);
	}
	/**
	 * Get the value of field {@link #schemas}.<br>
	 * @return {@link #schemas}
	 */
	@Generated
	public  PList<SchemaDef>	getSchemas(){
		return this.schemas;
	}
	/**
	 * Create a copy of this Instance object with a new value for field {@link #schemas}.<br>
	 * @param schemas The new value for field {@link #schemas}
	 * @return A new instance of {@link Instance}
	 */
	@Generated
	public  Instance	withSchemas(PList<SchemaDef> schemas){
		return new Instance(javaDbName, connector, schemas, codeGen, nameConversionType);
	}
	/**
	 * Get the value of field {@link #codeGen}.<br>
	 * @return {@link #codeGen}
	 */
	@Generated
	public  CodeGen	getCodeGen(){
		return this.codeGen;
	}
	/**
	 * Create a copy of this Instance object with a new value for field {@link #codeGen}.<br>
	 * @param codeGen The new value for field {@link #codeGen}
	 * @return A new instance of {@link Instance}
	 */
	@Generated
	public  Instance	withCodeGen(CodeGen codeGen){
		return new Instance(javaDbName, connector, schemas, codeGen, nameConversionType);
	}
	/**
	 * Get the value of field {@link #nameConversionType}.<br>
	 * @return {@link #nameConversionType}
	 */
	@Generated
	public  DbNameToJavaNameType	getNameConversionType(){
		return this.nameConversionType;
	}
	/**
	 * Create a copy of this Instance object with a new value for field {@link #nameConversionType}.<br>
	 * @param nameConversionType The new value for field {@link #nameConversionType}
	 * @return A new instance of {@link Instance}
	 */
	@Generated
	public  Instance	withNameConversionType(@Nullable DbNameToJavaNameType nameConversionType){
		return new Instance(javaDbName, connector, schemas, codeGen, nameConversionType);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof Instance == false) return false;
		Instance obj = (Instance)o;
		if(!javaDbName.equals(obj.javaDbName)) return false;
		if(!connector.equals(obj.connector)) return false;
		if(!schemas.equals(obj.schemas)) return false;
		if(!codeGen.equals(obj.codeGen)) return false;
		if(!nameConversionType.equals(obj.nameConversionType)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.javaDbName != null ? this.javaDbName.hashCode() : 0);
		result = 31 * result + (this.connector != null ? this.connector.hashCode() : 0);
		result = 31 * result + (this.schemas != null ? this.schemas.hashCode() : 0);
		result = 31 * result + (this.codeGen != null ? this.codeGen.hashCode() : 0);
		result = 31 * result + (this.nameConversionType != null ? this.nameConversionType.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "Instance[" + 
			"javaDbName=" + (javaDbName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(javaDbName),32,"...") + '\"') +
			", connector=" + connector + 
			", schemas=" + schemas + 
			", codeGen=" + codeGen + 
			", nameConversionType=" + nameConversionType + 
			']';
	}
	@Generated
	public  Instance	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setJavaDbName(this.javaDbName);
		b.setConnector(this.connector);
		b.setSchemas(this.schemas);
		b.setCodeGen(this.codeGen);
		b.setNameConversionType(this.nameConversionType);
		b = updater.apply(b);
		return new Instance(b.javaDbName, b.connector, b.schemas, b.codeGen, b.nameConversionType);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Instance	build(ThrowingFunction<Builder<NOT,NOT,NOT>, Builder<SET,SET,SET>, Exception> setter){
		Builder b = setter.toNonChecked().apply(new Builder());
		return new Instance(b.javaDbName, b.connector, b.schemas, b.codeGen, b.nameConversionType);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Result<Instance>	buildExc(ThrowingFunction<Builder<NOT,NOT,NOT>, Builder<SET,SET,SET>,Exception> setter){
		return Result.noExceptions(() -> setter.apply(new Builder<>())).mapExc(b -> new Instance(b.javaDbName, b.connector, b.schemas, b.codeGen, b.nameConversionType));
	}
}
