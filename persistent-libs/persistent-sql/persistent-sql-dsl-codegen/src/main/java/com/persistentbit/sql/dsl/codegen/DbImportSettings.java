package com.persistentbit.sql.dsl.codegen;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.CaseClass;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;
import com.persistentbit.sql.transactions.DbTransaction;
import com.persistentbit.string.UString;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 9/12/17
 */
@CaseClass
public class DbImportSettings {
	private  final	JavaGenTableSelection	tableSelection;
	private  final	DbNameTransformer	nameTransformer;
	private  final	Supplier<DbTransaction>	transactionSupplier;
	private  final	String	rootPackage;
	
	
	@Generated
	public DbImportSettings(JavaGenTableSelection tableSelection, DbNameTransformer nameTransformer, Supplier<DbTransaction> transactionSupplier, String rootPackage){
			this.tableSelection = Objects.requireNonNull(tableSelection, "tableSelection can not be null");
			this.nameTransformer = Objects.requireNonNull(nameTransformer, "nameTransformer can not be null");
			this.transactionSupplier = Objects.requireNonNull(transactionSupplier, "transactionSupplier can not be null");
			this.rootPackage = Objects.requireNonNull(rootPackage, "rootPackage can not be null");
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3, _T4> {
		private	JavaGenTableSelection	tableSelection;
		private	DbNameTransformer	nameTransformer;
		private	Supplier<DbTransaction>	transactionSupplier;
		private	String	rootPackage;
		
		
		public  Builder<SET, _T2, _T3, _T4>	setTableSelection(JavaGenTableSelection tableSelection){
			this.tableSelection	=	tableSelection;
			return (Builder<SET, _T2, _T3, _T4>)this;
		}
		public  Builder<_T1, SET, _T3, _T4>	setNameTransformer(DbNameTransformer nameTransformer){
			this.nameTransformer	=	nameTransformer;
			return (Builder<_T1, SET, _T3, _T4>)this;
		}
		public  Builder<_T1, _T2, SET, _T4>	setTransactionSupplier(Supplier<DbTransaction> transactionSupplier){
			this.transactionSupplier	=	transactionSupplier;
			return (Builder<_T1, _T2, SET, _T4>)this;
		}
		public  Builder<_T1, _T2, _T3, SET>	setRootPackage(String rootPackage){
			this.rootPackage	=	rootPackage;
			return (Builder<_T1, _T2, _T3, SET>)this;
		}
	}
	/**
	 * Get the value of field {@link #tableSelection}.<br>
	 * @return {@link #tableSelection}
	 */
	@Generated
	public  JavaGenTableSelection	getTableSelection(){
		return this.tableSelection;
	}
	/**
	 * Create a copy of this DbImportSettings object with a new value for field {@link #tableSelection}.<br>
	 * @param tableSelection The new value for field {@link #tableSelection}
	 * @return A new instance of {@link DbImportSettings}
	 */
	@Generated
	public  DbImportSettings	withTableSelection(JavaGenTableSelection tableSelection){
		return new DbImportSettings(tableSelection, nameTransformer, transactionSupplier, rootPackage);
	}
	/**
	 * Get the value of field {@link #nameTransformer}.<br>
	 * @return {@link #nameTransformer}
	 */
	@Generated
	public  DbNameTransformer	getNameTransformer(){
		return this.nameTransformer;
	}
	/**
	 * Create a copy of this DbImportSettings object with a new value for field {@link #nameTransformer}.<br>
	 * @param nameTransformer The new value for field {@link #nameTransformer}
	 * @return A new instance of {@link DbImportSettings}
	 */
	@Generated
	public  DbImportSettings	withNameTransformer(DbNameTransformer nameTransformer){
		return new DbImportSettings(tableSelection, nameTransformer, transactionSupplier, rootPackage);
	}
	/**
	 * Get the value of field {@link #transactionSupplier}.<br>
	 * @return {@link #transactionSupplier}
	 */
	@Generated
	public  Supplier<DbTransaction>	getTransactionSupplier(){
		return this.transactionSupplier;
	}
	/**
	 * Create a copy of this DbImportSettings object with a new value for field {@link #transactionSupplier}.<br>
	 * @param transactionSupplier The new value for field {@link #transactionSupplier}
	 * @return A new instance of {@link DbImportSettings}
	 */
	@Generated
	public  DbImportSettings	withTransactionSupplier(Supplier<DbTransaction> transactionSupplier){
		return new DbImportSettings(tableSelection, nameTransformer, transactionSupplier, rootPackage);
	}
	/**
	 * Get the value of field {@link #rootPackage}.<br>
	 * @return {@link #rootPackage}
	 */
	@Generated
	public  String	getRootPackage(){
		return this.rootPackage;
	}
	/**
	 * Create a copy of this DbImportSettings object with a new value for field {@link #rootPackage}.<br>
	 * @param rootPackage The new value for field {@link #rootPackage}
	 * @return A new instance of {@link DbImportSettings}
	 */
	@Generated
	public  DbImportSettings	withRootPackage(String rootPackage){
		return new DbImportSettings(tableSelection, nameTransformer, transactionSupplier, rootPackage);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbImportSettings == false) return false;
		DbImportSettings obj = (DbImportSettings)o;
		if(!tableSelection.equals(obj.tableSelection)) return false;
		if(!nameTransformer.equals(obj.nameTransformer)) return false;
		if(!transactionSupplier.equals(obj.transactionSupplier)) return false;
		if(!rootPackage.equals(obj.rootPackage)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.tableSelection != null ? this.tableSelection.hashCode() : 0);
		result = 31 * result + (this.nameTransformer != null ? this.nameTransformer.hashCode() : 0);
		result = 31 * result + (this.transactionSupplier != null ? this.transactionSupplier.hashCode() : 0);
		result = 31 * result + (this.rootPackage != null ? this.rootPackage.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbImportSettings[" + 
			"tableSelection=" + tableSelection + 
			", nameTransformer=" + nameTransformer + 
			", transactionSupplier=" + transactionSupplier + 
			", rootPackage=" + (rootPackage == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(rootPackage),32,"...") + '\"') +
			']';
	}
	@Generated
	public  DbImportSettings	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setTableSelection(this.tableSelection);
		b.setNameTransformer(this.nameTransformer);
		b.setTransactionSupplier(this.transactionSupplier);
		b.setRootPackage(this.rootPackage);
		b = updater.apply(b);
		return new DbImportSettings(b.tableSelection, b.nameTransformer, b.transactionSupplier, b.rootPackage);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static DbImportSettings	build(ThrowingFunction<Builder<NOT,NOT,NOT,NOT>, Builder<SET,SET,SET,SET>, Exception> setter){
		Builder b = setter.toNonChecked().apply(new Builder());
		return new DbImportSettings(b.tableSelection, b.nameTransformer, b.transactionSupplier, b.rootPackage);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Result<DbImportSettings>	buildExc(ThrowingFunction<Builder<NOT,NOT,NOT,NOT>, Builder<SET,SET,SET,SET>,Exception> setter){
		return Result.noExceptions(() -> setter.apply(new Builder<>())).mapExc(b -> new DbImportSettings(b.tableSelection, b.nameTransformer, b.transactionSupplier, b.rootPackage));
	}
}
