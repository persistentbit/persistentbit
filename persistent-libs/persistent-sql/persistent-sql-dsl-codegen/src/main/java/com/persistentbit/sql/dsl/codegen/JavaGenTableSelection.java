package com.persistentbit.sql.dsl.codegen;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.UPStreams;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.*;
import com.persistentbit.printable.PrintableText;
import com.persistentbit.result.Result;
import com.persistentbit.sql.meta.DbMetaDataImporter;
import com.persistentbit.sql.meta.data.DbMetaCatalog;
import com.persistentbit.sql.meta.data.DbMetaSchema;
import com.persistentbit.sql.meta.data.DbMetaTable;
import com.persistentbit.sql.transactions.DbTransaction;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 11/07/17
 */
@CaseClass
@NoWith
public class JavaGenTableSelection {
	private  final	Supplier<DbTransaction>	transactionSupplier;
	@DefaultValue("PList.empty()")
	private  final	PList<DbMetaCatalog>	catalogs;
	@DefaultValue("PList.empty()")
	private  final	PList<DbMetaSchema>	schemas;
	@DefaultValue("PList.empty()")
	private  final	PList<DbMetaTable>	tablesAndViews;
	
	
	@Generated
	public JavaGenTableSelection(Supplier<DbTransaction> transactionSupplier, @Nullable PList<DbMetaCatalog> catalogs, @Nullable PList<DbMetaSchema> schemas, @Nullable PList<DbMetaTable> tablesAndViews){
			this.transactionSupplier = Objects.requireNonNull(transactionSupplier, "transactionSupplier can not be null");
			this.catalogs = catalogs == null ? PList.empty() : catalogs;
			this.schemas = schemas == null ? PList.empty() : schemas;
			this.tablesAndViews = tablesAndViews == null ? PList.empty() : tablesAndViews;
	}
	@Generated
	public JavaGenTableSelection(Supplier<DbTransaction> transactionSupplier){
			this(transactionSupplier, null, null, null);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1> {
		private	Supplier<DbTransaction>	transactionSupplier;
		private	PList<DbMetaCatalog>	catalogs;
		private	PList<DbMetaSchema>	schemas;
		private	PList<DbMetaTable>	tablesAndViews;
		
		
		public  Builder<SET>	setTransactionSupplier(Supplier<DbTransaction> transactionSupplier){
			this.transactionSupplier	=	transactionSupplier;
			return (Builder<SET>)this;
		}
		public  Builder<_T1>	setCatalogs(@Nullable PList<DbMetaCatalog> catalogs){
			this.catalogs	=	catalogs;
			return this;
		}
		public  Builder<_T1>	setSchemas(@Nullable PList<DbMetaSchema> schemas){
			this.schemas	=	schemas;
			return this;
		}
		public  Builder<_T1>	setTablesAndViews(@Nullable PList<DbMetaTable> tablesAndViews){
			this.tablesAndViews	=	tablesAndViews;
			return this;
		}
	}
	public  PrintableText	show(){
	    return out -> {
	        catalogs.forEach(cat -> {
	            out.println("Catalog " + cat.getName().orElse("") + ": " + cat);
	            out.indent(sout -> {
	                schemas.filter(schema -> schema.getCatalog().equals(cat)).forEach(schema -> {
	                    sout.println("Schema " + schema.getName().orElse("") + ": " + schema);
	                    sout.indent(tout -> {
	                        tablesAndViews.filter(t -> t.getSchema().equals(schema)).forEach(table -> {
	                            tout.println("Table " + table.getName() + ": " + table);
	                            tout.indent(cout -> {
	                                table.getColumns().forEach(col -> {
	                                    cout.println(col.getName() + ": " + col);
	                                });
	                            });
	                        });
	                    });
	                });
	            });
	        });
	    };
	}
	public  Result<JavaGenTableSelection>	addCatalogs(Predicate<DbMetaCatalog> catalogFilter){
	    return DbMetaDataImporter.getCatalogs().run(transactionSupplier.get()).map(catList -> catList.filter(catalogFilter)).map(catList -> updated(b -> b.setCatalogs(catalogs.plusAll(catList))));
	}
	public  Result<JavaGenTableSelection>	addSchemas(Predicate<DbMetaSchema> schemaFilter){
	    Predicate<DbMetaSchema> catFilter = schema -> catalogs.contains(schema.getCatalog());
	    return DbMetaDataImporter.getAllSchemas().run(transactionSupplier.get()).map(schemaList -> schemaList.filter(catFilter.and(schemaFilter))).map(schemaList -> updated(b -> b.setSchemas(schemas.plusAll(schemaList))));
	}
	public  Result<JavaGenTableSelection>	addTablesAndViews(Predicate<DbMetaTable> tableFilter){
	    return UPStreams.fromSequence(schemas.map(schema -> DbMetaDataImporter.getTablesAndViews(schema).run(transactionSupplier.get()).map(tableList -> tableList.filter(tableFilter)))).map(listStream -> listStream.<DbMetaTable>flatten().plist()).map(tableList -> updated(b -> b.setTablesAndViews(tablesAndViews.plusAll(tableList))));
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
	 * Get the value of field {@link #catalogs}.<br>
	 * @return {@link #catalogs}
	 */
	@Generated
	public  PList<DbMetaCatalog>	getCatalogs(){
		return this.catalogs;
	}
	/**
	 * Get the value of field {@link #schemas}.<br>
	 * @return {@link #schemas}
	 */
	@Generated
	public  PList<DbMetaSchema>	getSchemas(){
		return this.schemas;
	}
	/**
	 * Get the value of field {@link #tablesAndViews}.<br>
	 * @return {@link #tablesAndViews}
	 */
	@Generated
	public  PList<DbMetaTable>	getTablesAndViews(){
		return this.tablesAndViews;
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof JavaGenTableSelection == false) return false;
		JavaGenTableSelection obj = (JavaGenTableSelection)o;
		if(!transactionSupplier.equals(obj.transactionSupplier)) return false;
		if(!catalogs.equals(obj.catalogs)) return false;
		if(!schemas.equals(obj.schemas)) return false;
		if(!tablesAndViews.equals(obj.tablesAndViews)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.transactionSupplier != null ? this.transactionSupplier.hashCode() : 0);
		result = 31 * result + (this.catalogs != null ? this.catalogs.hashCode() : 0);
		result = 31 * result + (this.schemas != null ? this.schemas.hashCode() : 0);
		result = 31 * result + (this.tablesAndViews != null ? this.tablesAndViews.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "JavaGenTableSelection[" + 
			"transactionSupplier=" + transactionSupplier + 
			", catalogs=" + catalogs + 
			", schemas=" + schemas + 
			", tablesAndViews=" + tablesAndViews + 
			']';
	}
	@Generated
	public  JavaGenTableSelection	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setTransactionSupplier(this.transactionSupplier);
		b.setCatalogs(this.catalogs);
		b.setSchemas(this.schemas);
		b.setTablesAndViews(this.tablesAndViews);
		b = updater.apply(b);
		return new JavaGenTableSelection(b.transactionSupplier, b.catalogs, b.schemas, b.tablesAndViews);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static JavaGenTableSelection	build(ThrowingFunction<Builder<NOT>, Builder<SET>, Exception> setter){
		Builder b = setter.toNonChecked().apply(new Builder());
		return new JavaGenTableSelection(b.transactionSupplier, b.catalogs, b.schemas, b.tablesAndViews);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Result<JavaGenTableSelection>	buildExc(ThrowingFunction<Builder<NOT>, Builder<SET>,Exception> setter){
		return Result.noExceptions(() -> setter.apply(new Builder<>())).mapExc(b -> new JavaGenTableSelection(b.transactionSupplier, b.catalogs, b.schemas, b.tablesAndViews));
	}
}
