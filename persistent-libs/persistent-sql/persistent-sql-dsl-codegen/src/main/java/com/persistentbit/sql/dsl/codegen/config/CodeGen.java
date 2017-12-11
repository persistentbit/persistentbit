package com.persistentbit.sql.dsl.codegen.config;

import java.lang.SuppressWarnings;
import com.persistentbit.javacodegen.annotations.NoGet;
import com.persistentbit.javacodegen.annotations.NOT;
import java.util.Objects;
import com.persistentbit.javacodegen.annotations.Generated;
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
public class CodeGen {
	@DefaultValue("\"src/main/java\"")
	private  final	String	outputDir;
	@DefaultValue("true")
	private  final	boolean	generic;
	@DefaultValue("\"com.persistentbit.db\"")
	private  final	String	rootPackage;
	
	
	@Generated
	public CodeGen(@Nullable String outputDir, @Nullable Boolean generic, @Nullable String rootPackage){
			this.outputDir = outputDir == null ? "src/main/java" : outputDir;
			this.generic = generic == null ? true : generic;
			this.rootPackage = rootPackage == null ? "com.persistentbit.db" : rootPackage;
	}
	@Generated
	public CodeGen(){
			this(null, null, null);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder {
		private	String	outputDir;
		private	boolean	generic;
		private	String	rootPackage;
		
		
		public  Builder	setOutputDir(@Nullable String outputDir){
			this.outputDir	=	outputDir;
			return this;
		}
		public  Builder	setGeneric(@Nullable Boolean generic){
			this.generic	=	generic;
			return this;
		}
		public  Builder	setRootPackage(@Nullable String rootPackage){
			this.rootPackage	=	rootPackage;
			return this;
		}
	}
	/**
	 * Get the value of field {@link #outputDir}.<br>
	 * @return {@link #outputDir}
	 */
	@Generated
	public  String	getOutputDir(){
		return this.outputDir;
	}
	/**
	 * Create a copy of this CodeGen object with a new value for field {@link #outputDir}.<br>
	 * @param outputDir The new value for field {@link #outputDir}
	 * @return A new instance of {@link CodeGen}
	 */
	@Generated
	public  CodeGen	withOutputDir(@Nullable String outputDir){
		return new CodeGen(outputDir, generic, rootPackage);
	}
	/**
	 * Get the value of field {@link #generic}.<br>
	 * @return {@link #generic}
	 */
	@Generated
	public  boolean	getGeneric(){
		return this.generic;
	}
	/**
	 * Create a copy of this CodeGen object with a new value for field {@link #generic}.<br>
	 * @param generic The new value for field {@link #generic}
	 * @return A new instance of {@link CodeGen}
	 */
	@Generated
	public  CodeGen	withGeneric(@Nullable Boolean generic){
		return new CodeGen(outputDir, generic, rootPackage);
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
	 * Create a copy of this CodeGen object with a new value for field {@link #rootPackage}.<br>
	 * @param rootPackage The new value for field {@link #rootPackage}
	 * @return A new instance of {@link CodeGen}
	 */
	@Generated
	public  CodeGen	withRootPackage(@Nullable String rootPackage){
		return new CodeGen(outputDir, generic, rootPackage);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof CodeGen == false) return false;
		CodeGen obj = (CodeGen)o;
		if(!outputDir.equals(obj.outputDir)) return false;
		if(generic!= obj.generic) return false;
		if(!rootPackage.equals(obj.rootPackage)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.outputDir != null ? this.outputDir.hashCode() : 0);
		result = 31 * result + (this.generic ? 1 : 0);
		result = 31 * result + (this.rootPackage != null ? this.rootPackage.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "CodeGen[" + 
			"outputDir=" + (outputDir == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(outputDir),32,"...") + '\"') +
			", generic=" + generic + 
			", rootPackage=" + (rootPackage == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(rootPackage),32,"...") + '\"') +
			']';
	}
	@Generated
	public  CodeGen	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setOutputDir(this.outputDir);
		b.setGeneric(this.generic);
		b.setRootPackage(this.rootPackage);
		b = updater.apply(b);
		return new CodeGen(b.outputDir, b.generic, b.rootPackage);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static CodeGen	build(ThrowingFunction<Builder, Builder, Exception> setter){
		Builder b = setter.toNonChecked().apply(new Builder());
		return new CodeGen(b.outputDir, b.generic, b.rootPackage);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Result<CodeGen>	buildExc(ThrowingFunction<Builder, Builder,Exception> setter){
		return Result.noExceptions(() -> setter.apply(new Builder())).mapExc(b -> new CodeGen(b.outputDir, b.generic, b.rootPackage));
	}
}
