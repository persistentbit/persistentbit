package com.persistentbit.glasgolia.db.dbdef;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.CaseClass;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;
import com.persistentbit.string.UString;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 3/06/17
 */
@CaseClass
public class DbMetaSchema {
	public  final	DbMetaCatalog	catalog;
	@Nullable
	public  final	String	name;
	@Nullable
	public  final	String	comment;
	
	
	@Generated
	public DbMetaSchema(DbMetaCatalog catalog, @Nullable String name, @Nullable String comment){
			this.catalog = Objects.requireNonNull(catalog, "catalog can not be null");
			this.name = name;
			this.comment = comment;
	}
	@Generated
	public DbMetaSchema(DbMetaCatalog catalog){
			this(catalog, null, null);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1> {
		private	DbMetaCatalog	catalog;
		private	String	name;
		private	String	comment;
		
		
		public  Builder<SET>	setCatalog(DbMetaCatalog catalog){
			this.catalog	=	catalog;
			return (Builder<SET>)this;
		}
		public  Builder<_T1>	setName(@Nullable String name){
			this.name	=	name;
			return this;
		}
		public  Builder<_T1>	setComment(@Nullable String comment){
			this.comment	=	comment;
			return this;
		}
	}
	public  String	getFullName(){
	    String res = catalog.getName().map(name -> name + ".").orElse("");
	    return res + (name == null ? "" : name);
	}
	/**
	 * Get the value of field {@link #catalog}.<br>
	 * @return {@link #catalog}
	 */
	@Generated
	public  DbMetaCatalog	getCatalog(){
		return this.catalog;
	}
	/**
	 * Create a copy of this DbMetaSchema object with a new value for field {@link #catalog}.<br>
	 * @param catalog The new value for field {@link #catalog}
	 * @return A new instance of {@link DbMetaSchema}
	 */
	@Generated
	public  DbMetaSchema	withCatalog(DbMetaCatalog catalog){
		return new DbMetaSchema(catalog, name, comment);
	}
	/**
	 * Get the value of field {@link #name}.<br>
	 * @return {@link #name}
	 */
	@Generated
	public  Optional<String>	getName(){
		return Optional.ofNullable(this.name);
	}
	/**
	 * Create a copy of this DbMetaSchema object with a new value for field {@link #name}.<br>
	 * @param name The new value for field {@link #name}
	 * @return A new instance of {@link DbMetaSchema}
	 */
	@Generated
	public  DbMetaSchema	withName(@Nullable String name){
		return new DbMetaSchema(catalog, name, comment);
	}
	/**
	 * Get the value of field {@link #comment}.<br>
	 * @return {@link #comment}
	 */
	@Generated
	public  Optional<String>	getComment(){
		return Optional.ofNullable(this.comment);
	}
	/**
	 * Create a copy of this DbMetaSchema object with a new value for field {@link #comment}.<br>
	 * @param comment The new value for field {@link #comment}
	 * @return A new instance of {@link DbMetaSchema}
	 */
	@Generated
	public  DbMetaSchema	withComment(@Nullable String comment){
		return new DbMetaSchema(catalog, name, comment);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof DbMetaSchema == false) return false;
		DbMetaSchema obj = (DbMetaSchema)o;
		if(!catalog.equals(obj.catalog)) return false;
		if(name != null ? !name.equals(obj.name) : obj.name!= null) return false;
		if(comment != null ? !comment.equals(obj.comment) : obj.comment!= null) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.catalog != null ? this.catalog.hashCode() : 0);
		result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
		result = 31 * result + (this.comment != null ? this.comment.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "DbMetaSchema[" + 
			"catalog=" + catalog + 
			", name=" + (name == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(name),32,"...") + '\"') +
			", comment=" + (comment == null ? "null" : '\"' + UString.present(UString.escapeToJavaString(comment),32,"...") + '\"') +
			']';
	}
	@Generated
	public  DbMetaSchema	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setCatalog(this.catalog);
		b.setName(this.name);
		b.setComment(this.comment);
		b = updater.apply(b);
		return new DbMetaSchema(b.catalog, b.name, b.comment);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static DbMetaSchema	build(ThrowingFunction<Builder<NOT>, Builder<SET>, Exception> setter){
		Builder b = setter.toNonChecked().apply(new Builder());
		return new DbMetaSchema(b.catalog, b.name, b.comment);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Result<DbMetaSchema>	buildExc(ThrowingFunction<Builder<NOT>, Builder<SET>,Exception> setter){
		return Result.noExceptions(() -> setter.apply(new Builder<>())).mapExc(b -> new DbMetaSchema(b.catalog, b.name, b.comment));
	}
}
