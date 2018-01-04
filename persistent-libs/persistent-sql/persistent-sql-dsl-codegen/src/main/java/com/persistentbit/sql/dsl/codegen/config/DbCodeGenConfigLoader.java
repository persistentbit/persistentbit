package com.persistentbit.sql.dsl.codegen.config;

import com.persistentbit.collections.PSet;
import com.persistentbit.result.Result;
import com.persistentbit.sql.connect.DbConnector;
import com.persistentbit.sql.dsl.codegen.importer.DbNameTransformer;
import com.persistentbit.sql.dsl.codegen.importer.JavaGenTableSelection;
import com.persistentbit.sql.meta.data.DbMetaCatalog;
import com.persistentbit.sql.meta.data.DbMetaColumn;
import com.persistentbit.sql.meta.data.DbMetaSchema;
import com.persistentbit.sql.meta.data.DbMetaTable;
import com.persistentbit.sql.transactions.DbTransaction;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 11/12/17
 */
public class DbCodeGenConfigLoader{

	static public Result<Supplier<DbTransaction>> createTransSup(Connector con){
		return Result.function(con).code(log ->
			DbConnector.fromUrl(
				con.getDriverClass()
				,con.getUrl()
				,con.getUserName().orElse(null)
				,con.getPassword().orElse(null)

			)//.map(c -> c.pooledConnector(1))
				.map(connector ->() -> DbTransaction.create(connector))
		);
	}

	static public Result<DbNameTransformer> createNameTransformer(Instance instance){
		return Result.function(instance).code(log -> {
			Function<String,String> toJavaName = instance.getNameConversionType().transformer();
			Function<String,String> toFieldJavaName = instance.getNameConversionType().fieldTransformer();
			Function<DbMetaCatalog,String> catalogNameToJava = meta ->
				meta.getName().map(n -> toJavaName.apply(n)).orElse("catalog").toLowerCase();

			Function<DbMetaSchema,String> schemaNameToJava = meta ->
				instance.getSchemas().find(sd -> sd.getSchemaName().equals(meta.getName()) && sd.getCatalogName().equals(meta.getCatalog().getName()))
						.flatMap(sd -> sd.getJavaName())
						.orElse(toJavaName.apply(meta.getName().orElse("schema")).toLowerCase());

			Function<DbMetaTable,String> tableNameToJava = meta ->
				instance.getSchemas().lazy()
						.find(sd -> sd.getSchemaName().equals(meta.getSchema().getName()))
						.flatMap(sd -> sd.getTables().find(t -> t.getTableName().equals(meta.getName())))
						.flatMap(td -> td.getJavaName())
						.orElse(toJavaName.apply(meta.getName()));
			BiFunction<DbMetaTable,DbMetaColumn,String> columNameToJava = (tableMeta, columnMeta) ->
				instance.getSchemas().lazy()
						.find(sd -> sd.getSchemaName().equals(tableMeta.getSchema().getName()))
						.flatMap(sd -> sd.getTables().find(t -> t.getTableName().equals(tableMeta.getName())))
						.flatMap(td -> td.getColumns().find(col -> col.getColumnName().equals(columnMeta.getName())))
						.map(cd -> cd.getJavaName())
						.orElse(toFieldJavaName.apply(columnMeta.getName()));
			Function<String,String> customTypeNameToJava = toJavaName;
			return Result.success(new DbNameTransformer(
				catalogNameToJava,schemaNameToJava,tableNameToJava,columNameToJava,customTypeNameToJava
			));
		});


	}

	static public Result<JavaGenTableSelection> createTableSelection(Supplier<DbTransaction> transSup, Instance instance){
		PSet<String> catalogNames = instance
			.getSchemas()
			.filter(s -> s.getCatalogName().isPresent())
			.map(s -> s.getCatalogName().get())
			.pset();


		Function<DbMetaSchema,Optional<SchemaDef>> findSchemaDef = schema ->
			instance.getSchemas().find(sd ->
				(sd.getCatalogName().isPresent()==false || sd.getCatalogName().equals(schema.getCatalog().getName())) &&
					(sd.getSchemaName().equals(schema.getName()))
			);


		Predicate<DbMetaSchema> schemaOk = schema -> findSchemaDef.apply(schema).isPresent();

		Predicate<DbMetaTable> tableOk = table -> {
			SchemaDef sd = findSchemaDef.apply(table.getSchema()).orElse(null);
			if(sd == null){
				return false;
			}
			return sd.getTables().find(t -> t.getTableName().equals(table.getName())).isPresent() ||
				(sd.getExcludeTables().contains(table.getName()) == false);
		};

		return Result.function(instance).code(log -> {
			Result<JavaGenTableSelection> result = new JavaGenTableSelection()
				.addCatalogs(cat -> catalogNames.contains(cat.getName().orElse(null))).run(transSup.get());

			result = result.flatMap(jts -> jts.addSchemas(schemaOk).run(transSup.get()));

			result = result.flatMap(jts -> jts.addTablesAndViews(tableOk).run(transSup.get()));

			return result;
		});

	}


}
