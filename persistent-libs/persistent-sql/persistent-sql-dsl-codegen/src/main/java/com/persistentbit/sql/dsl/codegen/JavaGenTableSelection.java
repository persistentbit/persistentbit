package com.persistentbit.sql.dsl.codegen;

import java.lang.SuppressWarnings;
import com.persistentbit.collections.UPStreams;
import com.persistentbit.javacodegen.annotations.NoGet;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.sql.meta.data.DbMetaCatalog;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.collections.PList;
import java.util.function.Function;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.CaseClass;
import com.persistentbit.sql.meta.data.DbMetaTable;
import com.persistentbit.result.Result;
import com.persistentbit.javacodegen.annotations.DefaultEmpty;
import com.persistentbit.printable.PrintableText;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.sql.work.DbWork;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.sql.meta.data.DbMetaSchema;
import com.persistentbit.sql.meta.DbMetaDataImporter;
import com.persistentbit.javacodegen.annotations.NoWith;
import java.util.function.Predicate;

/**
 * TODOC
 *
 * @author petermuys
 * @since 11/07/17
 */
@CaseClass
@NoWith
public class JavaGenTableSelection {
	@DefaultEmpty
	private  final	PList<DbMetaCatalog>	catalogs;
	@DefaultEmpty
	private  final	PList<DbMetaSchema>	schemas;
	@DefaultEmpty
	private  final	PList<DbMetaTable>	tablesAndViews;
	
	
	@Generated
	public JavaGenTableSelection(@Nullable PList<DbMetaCatalog> catalogs, @Nullable PList<DbMetaSchema> schemas, @Nullable PList<DbMetaTable> tablesAndViews){
			this.catalogs = catalogs == null ? PList.empty() : catalogs;
			this.schemas = schemas == null ? PList.empty() : schemas;
			this.tablesAndViews = tablesAndViews == null ? PList.empty() : tablesAndViews;
	}
	@Generated
	public JavaGenTableSelection(){
			this(null, null, null);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder {
		private	PList<DbMetaCatalog>	catalogs;
		private	PList<DbMetaSchema>	schemas;
		private	PList<DbMetaTable>	tablesAndViews;
		
		
		public  Builder	setCatalogs(@Nullable PList<DbMetaCatalog> catalogs){
			this.catalogs	=	catalogs;
			return this;
		}
		public  Builder	setSchemas(@Nullable PList<DbMetaSchema> schemas){
			this.schemas	=	schemas;
			return this;
		}
		public  Builder	setTablesAndViews(@Nullable PList<DbMetaTable> tablesAndViews){
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
	public  DbWork<JavaGenTableSelection>	addCatalogs(Predicate<DbMetaCatalog> catalogFilter){
	    return DbMetaDataImporter.getCatalogs().map(catList -> catList.filter(catalogFilter)).map(catList -> updated(b -> b.setCatalogs(catalogs.plusAll(catList))));
	}
	public  DbWork<JavaGenTableSelection>	addSchemas(Predicate<DbMetaSchema> schemaFilter){
	    Predicate<DbMetaSchema> catFilter = schema -> catalogs.contains(schema.getCatalog());
	    DbWork<PList<DbMetaSchema>> allSchemas = DbMetaDataImporter.getAllSchemas();
	    return allSchemas.map(schemaList -> schemaList.filter(catFilter.and(schemaFilter))).map(schemaList -> updated(b -> b.setSchemas(schemas.plusAll(schemaList))));
	}
	public  DbWork<JavaGenTableSelection>	addTablesAndViews(Predicate<DbMetaTable> tableFilter){
	    return trans -> UPStreams.fromSequence(schemas.map(schema -> DbMetaDataImporter.getTablesAndViews(schema).run(trans).map(tableList -> tableList.filter(tableFilter)))).map(listStream -> listStream.<DbMetaTable>flatten().plist()).map(tableList -> updated(b -> b.setTablesAndViews(tablesAndViews.plusAll(tableList))));
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
		if(!catalogs.equals(obj.catalogs)) return false;
		if(!schemas.equals(obj.schemas)) return false;
		if(!tablesAndViews.equals(obj.tablesAndViews)) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.catalogs != null ? this.catalogs.hashCode() : 0);
		result = 31 * result + (this.schemas != null ? this.schemas.hashCode() : 0);
		result = 31 * result + (this.tablesAndViews != null ? this.tablesAndViews.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "JavaGenTableSelection[" + 
			"catalogs=" + catalogs + 
			", schemas=" + schemas + 
			", tablesAndViews=" + tablesAndViews + 
			']';
	}
	@Generated
	public  JavaGenTableSelection	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setCatalogs(this.catalogs);
		b.setSchemas(this.schemas);
		b.setTablesAndViews(this.tablesAndViews);
		b = updater.apply(b);
		return new JavaGenTableSelection(b.catalogs, b.schemas, b.tablesAndViews);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static JavaGenTableSelection	build(ThrowingFunction<Builder, Builder, Exception> setter){
		Builder b = setter.toNonChecked().apply(new Builder());
		return new JavaGenTableSelection(b.catalogs, b.schemas, b.tablesAndViews);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static Result<JavaGenTableSelection>	buildExc(ThrowingFunction<Builder, Builder,Exception> setter){
		return Result.noExceptions(() -> setter.apply(new Builder())).mapExc(b -> new JavaGenTableSelection(b.catalogs, b.schemas, b.tablesAndViews));
	}
}
