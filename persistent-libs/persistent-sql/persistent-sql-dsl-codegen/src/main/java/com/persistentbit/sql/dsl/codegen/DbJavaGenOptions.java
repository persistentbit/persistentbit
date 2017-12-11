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

import java.io.File;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 23/07/17
 */
@CaseClass
public class DbJavaGenOptions {
	private  final	Supplier<DbTransaction>	transSupplier;
	private  final	JavaGenTableSelection	selection;
	private  final	DbNameTransformer	nameTransformer;
	private  final	String	rootPackage;
	private  final	boolean	fullDbSupport;
	private  final	File	outputDirectory;
	private  final	String	dbJavaName;
	
	
	@Generated
	public DbJavaGenOptions(Supplier<DbTransaction> transSupplier, JavaGenTableSelection selection, DbNameTransformer nameTransformer, String rootPackage, boolean fullDbSupport, File outputDirectory, String dbJavaName){
			this.transSupplier = Objects.requireNonNull(transSupplier, "transSupplier can not be null");
			this.selection = Objects.requireNonNull(selection, "selection can not be null");
			this.nameTransformer = Objects.requireNonNull(nameTransformer, "nameTransformer can not be null");
			this.rootPackage = Objects.requireNonNull(rootPackage, "rootPackage can not be null");
			this.fullDbSupport = Objects.requireNonNull(fullDbSupport, "fullDbSupport can not be null");
			this.outputDirectory = Objects.requireNonNull(outputDirectory, "outputDirectory can not be null");
			this.dbJavaName = Objects.requireNonNull(dbJavaName, "dbJavaName can not be null");
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7> {
		private	Supplier<DbTransaction>	transSupplier;
		private	JavaGenTableSelection	selection;
		private	DbNameTransformer	nameTransformer;
		private	String	rootPackage;
		private	boolean	fullDbSupport;
		private	File	outputDirectory;
		private	String	dbJavaName;
		
		
		public  Builder<SET, _T2, _T3, _T4, _T5, _T6, _T7>	setTransSupplier(Supplier<DbTransaction> transSupplier){
			this.transSupplier	=	transSupplier;
			return (Builder<SET, _T2, _T3, _T4, _T5, _T6, _T7>)this;
		}
		public  Builder<_T1, SET, _T3, _T4, _T5, _T6, _T7>	setSelection(JavaGenTableSelection selection){
			this.selection	=	selection;
			return (Builder<_T1, SET, _T3, _T4, _T5, _T6, _T7>)this;
		}
		public  Builder<_T1, _T2, SET, _T4, _T5, _T6, _T7>	setNameTransformer(DbNameTransformer nameTransformer){
			this.nameTransformer	=	nameTransformer;
			return (Builder<_T1, _T2, SET, _T4, _T5, _T6, _T7>)this;
		}
		public  Builder<_T1, _T2, _T3, SET, _T5, _T6, _T7>	setRootPackage(String rootPackage){
			this.rootPackage	=	rootPackage;
			return (Builder<_T1, _T2, _T3, SET, _T5, _T6, _T7>)this;
		}
		public  Builder<_T1, _T2, _T3, _T4, SET, _T6, _T7>	setFullDbSupport(boolean fullDbSupport){
			this.fullDbSupport	=	fullDbSupport;
			return (Builder<_T1, _T2, _T3, _T4, SET, _T6, _T7>)this;
		}
		public  Builder<_T1, _T2, _T3, _T4, _T5, SET, _T7>	setOutputDirectory(File outputDirectory){
			this.outputDirectory	=	outputDirectory;
			return (Builder<_T1, _T2, _T3, _T4, _T5, SET, _T7>)this;
		}
		public  Builder<_T1, _T2, _T3, _T4, _T5, _T6, SET>	setDbJavaName(String dbJavaName){
			this.dbJavaName	=	dbJavaName;
			return (Builder<_T1, _T2, _T3, _T4, _T5, _T6, SET>)this;
		}
	}
	/**
	 * Get the value of field {@link #transSupplier}.<br>
	 * @return {@link #transSupplier}
	 */
	@Generated
	public  Supplier<DbTransaction>	getTransSupplier(){
		return this.transSupplier;
	}
	/**
	 * Create a copy of this DbJavaGenOptions object with a new value for field {@link #transSupplier}.<br>
	 * @param transSupplier The new value for field {@link #transSupplier}
	 * @return A new instance of {@link DbJavaGenOptions}
	 */
	@Generated
	public  DbJavaGenOptions	withTransSupplier(Supplier<DbTransaction> transSupplier){
		return new DbJavaGenOptions(transSupplier, selection, nameTransformer, rootPackage, fullDbSupport, outputDirectory, dbJavaName);
	}
	/**
	 * Get the value of field {@link #selection}.<br>
	 * @return {@link #selection}
	 */
	@Generated
	public  JavaGenTableSelection	getSelection(){
		return this.selection;
	}
	/**
	 * Create a copy of this DbJavaGenOptions object with a new value for field {@link #selection}.<br>
	 * @param selection The new value for field {@link #selection}
	 * @return A new instance of {@link DbJavaGenOptions}
	 */
	@Generated
	public  DbJavaGenOptions	withSelection(JavaGenTableSelection selection){
		return new DbJavaGenOptions(transSupplier, selection, nameTransformer, rootPackage, fullDbSupport, outputDirectory, dbJavaName);
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
	 * Create a copy of this DbJavaGenOptions object with a new value for field {@link #nameTransformer}.<br>
	 * @param nameTransformer The new value for field {@link #nameTransformer}
	 * @return A new instance of {@link DbJavaGenOptions}
	 */
	@Generated
	public  DbJavaGenOptions	withNameTransformer(DbNameTransformer nameTransformer){
		return new DbJavaGenOptions(transSupplier, selection, nameTransformer, rootPackage, fullDbSupport, outputDirectory, dbJavaName);
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
	 * Create a copy of this DbJavaGenOptions object with a new value for field {@link #rootPackage}.<br>
	 * @param rootPackage The new value for field {@link #rootPackage}
	 * @return A new instance of {@link DbJavaGenOptions}
	 */
	@Generated
	public  DbJavaGenOptions	withRootPackage(String rootPackage){
		return new DbJavaGenOptions(transSupplier, selection, nameTransformer, rootPackage, fullDbSupport, outputDirectory, dbJavaName);
	}
	/**
	 * Get the value of field {@link #fullDbSupport}.<br>
	 * @return {@link #fullDbSupport}
	 */
	@Generated
	public  boolean	getFullDbSupport(){
		return this.fullDbSupport;
	}
	/**
	 * Create a copy of this DbJavaGenOptions object with a new value for field {@link #fullDbSupport}.<br>
	 * @param fullDbSupport The new value for field {@link #fullDbSupport}
	 * @return A new instance of {@link DbJavaGenOptions}
	 */
	@Generated
	public  DbJavaGenOptions	withFullDbSupport(boolean fullDbSupport){
		return new DbJavaGenOptions(transSupplier, selection, nameTransformer, rootPackage, fullDbSupport, outputDirectory, dbJavaName);
	}
	/**
	 * Get the value of field {@link #outputDirectory}.<br>
	 * @return {@link #outputDirectory}
	 */
	@Generated
	public  File	getOutputDirectory(){
		return this.outputDirectory;
	}
	/**
	 * Create a copy of this DbJavaGenOptions object with a new value for field {@link #outputDirectory}.<br>
	 * @param outputDirectory The new value for field {@link #outputDirectory}
	 * @return A new instance of {@link DbJavaGenOptions}
	 */
	@Generated
	public  DbJavaGenOptions	withOutputDirectory(File outputDirectory){
		return new DbJavaGenOptions(transSupplier, selection, nameTransformer, rootPackage, fullDbSupport, outputDirectory, dbJavaName);
	}
	/**
	 * Get the value of field {@link #dbJavaName}.<br>
	 * @return {@link #dbJavaName}
	 */
	@Generated
	public  String	getDbJavaName(){
		return this.dbJavaName;
	}
	/**
	 * Create a copy of this DbJavaGenOptions object with a new value for field {@link #dbJavaName}.<br>
	 * @param dbJavaName The new value for field {@link #dbJavaName}
	 * @return A new instance of {@link DbJavaGenOptions}
	 */
	@Generated
	public  DbJavaGenOptions	withDbJavaName(String dbJavaName){
		return new DbJavaGenOptions(transSupplier, selection, nameTransformer, rootPackage, fullDbSupport, outputDirectory, dbJavaName);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbJavaGenOptions == false) return false;
		DbJavaGenOptions obj = (DbJavaGenOptions)o;
		if(!transSupplier.equals(obj.transSupplier)) return false;
		if(!selection.equals(obj.selection)) return false;
		if(!nameTransformer.equals(obj.nameTransformer)) return false;
		if(!rootPackage.equals(obj.rootPackage)) return false;
		if(fullDbSupport!= obj.fullDbSupport) return false;
		if(!outputDirectory.equals(obj.outputDirectory)) return false;
		if(!dbJavaName.equals(obj.dbJavaName)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.transSupplier != null ? this.transSupplier.hashCode() : 0);
		result = 31 * result + (this.selection != null ? this.selection.hashCode() : 0);
		result = 31 * result + (this.nameTransformer != null ? this.nameTransformer.hashCode() : 0);
		result = 31 * result + (this.rootPackage != null ? this.rootPackage.hashCode() : 0);
		result = 31 * result + (this.fullDbSupport ? 1 : 0);
		result = 31 * result + (this.outputDirectory != null ? this.outputDirectory.hashCode() : 0);
		result = 31 * result + (this.dbJavaName != null ? this.dbJavaName.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbJavaGenOptions[" + 
			"transSupplier=" + transSupplier + 
			", selection=" + selection + 
			", nameTransformer=" + nameTransformer + 
			", rootPackage=" + (rootPackage == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(rootPackage),32,"...") + '\"') +
			", fullDbSupport=" + fullDbSupport + 
			", outputDirectory=" + outputDirectory + 
			", dbJavaName=" + (dbJavaName == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(dbJavaName),32,"...") + '\"') +
			']';
	}
	@Generated
	public  DbJavaGenOptions	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setTransSupplier(this.transSupplier);
		b.setSelection(this.selection);
		b.setNameTransformer(this.nameTransformer);
		b.setRootPackage(this.rootPackage);
		b.setFullDbSupport(this.fullDbSupport);
		b.setOutputDirectory(this.outputDirectory);
		b.setDbJavaName(this.dbJavaName);
		b = updater.apply(b);
		return new DbJavaGenOptions(b.transSupplier, b.selection, b.nameTransformer, b.rootPackage, b.fullDbSupport, b.outputDirectory, b.dbJavaName);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static DbJavaGenOptions	build(ThrowingFunction<Builder<NOT,NOT,NOT,NOT,NOT,NOT,NOT>, Builder<SET,SET,SET,SET,SET,SET,SET>, Exception> setter){
		Builder b = setter.toNonChecked().apply(new Builder());
		return new DbJavaGenOptions(b.transSupplier, b.selection, b.nameTransformer, b.rootPackage, b.fullDbSupport, b.outputDirectory, b.dbJavaName);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Result<DbJavaGenOptions>	buildExc(ThrowingFunction<Builder<NOT,NOT,NOT,NOT,NOT,NOT,NOT>, Builder<SET,SET,SET,SET,SET,SET,SET>,Exception> setter){
		return Result.noExceptions(() -> setter.apply(new Builder<>())).mapExc(b -> new DbJavaGenOptions(b.transSupplier, b.selection, b.nameTransformer, b.rootPackage, b.fullDbSupport, b.outputDirectory, b.dbJavaName));
	}
}
