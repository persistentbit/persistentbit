package com.persistentbit.sql.meta;


import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.collections.UPStreams;
import com.persistentbit.result.Result;
import com.persistentbit.sql.meta.data.*;
import com.persistentbit.sql.utils.UJdbc;
import com.persistentbit.sql.work.DbWork;
import com.persistentbit.utils.exceptions.ToDo;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

/**
 * TODOC
 *
 * @author petermuys
 * @since 3/06/17
 */
public class DbMetaDataImporter{

	public static DbWork<DbMetaDatabase> getDatabase(){
		return DbWork.function().code(trans -> con -> log -> {
			DatabaseMetaData dbm = con.getMetaData();
			DbMetaDatabase res = DbMetaDatabase.build(b -> b
				.setCatalogSeparator(dbm.getCatalogSeparator())
				.setDbMajorVersion(dbm.getDatabaseMajorVersion())
				.setDbMinorVersion(dbm.getDatabaseMinorVersion())
				.setDriveName(dbm.getDriverName())
				.setDriverMajorVersion(dbm.getDriverMajorVersion())
				.setDriverMinorVersion(dbm.getDriverMinorVersion())
				.setJdbcMajorVersion(dbm.getJDBCMajorVersion())
				.setJdbcMinorVersion(dbm.getJDBCMinorVersion())
				.setProductName(dbm.getDatabaseProductName())
				.setUrl(dbm.getURL())
				.setSupportsGetGeneratedKeys(dbm.supportsGetGeneratedKeys())
			);

			return Result.success(res);
		});
	}

	public static DbWork<PList<DbMetaCatalog>> getCatalogs(){
		return DbWork.function().code(trans -> con -> log ->
			UJdbc.getList(con.getMetaData().getCatalogs(),
				rs -> new DbMetaCatalog(rs.getString("TABLE_CAT"))
			)
		);

	}
	public static DbWork<PList<DbMetaSchema>> getSchemas(DbMetaCatalog catalog) {
		return DbWork.function(catalog).code(trans -> con -> log ->
			UJdbc.getList(con.getMetaData().getSchemas(catalog.getName().orElse(""),null),
				rs -> DbMetaSchema.buildExc(b -> b
					.setCatalog(catalog)
					.setName(rs.getString("TABLE_SCHEM"))
				).orElseThrow()
			)
		);

	}


	public static DbWork<PList<DbMetaSchema>> getAllSchemas(){
		return DbWork.function().code(trans -> con -> log ->
			getCatalogs().run(trans)
			.flatMap( catList ->
				UPStreams.fromSequence(
					catList.mapExc(cat -> getSchemas(cat).run(trans))
			    ).map(PStream::<DbMetaSchema>flatten)
					.map(PStream::plist)
			));
	}

	public static DbWork<PList<String>> getTableTypes(DbMetaSchema schema){
		return DbWork.function(schema).code(trans -> con -> log ->
			UJdbc.getList(con.getMetaData().getTableTypes(),
				rs -> rs.getString("TABLE_TYPE")
			)
		);

	}

	public static DbWork<PList<DbMetaTable>> getTables(DbMetaSchema schema){
		return getTables(schema,"TABLE");
	}

	public static DbWork<PList<DbMetaTable>> getViews(DbMetaSchema schema) { return getTables(schema,"VIEW");}

	public static DbWork<PList<DbMetaTable>> getTablesAndViews(DbMetaSchema schema){
		return DbWork.function(schema).code(trans -> con -> log ->
			getTables(schema).run(trans)
				 .flatMap(tables -> getViews(schema).run(trans)
					 .map(views -> tables.plusAll(views))
				 )
		);
	}
	public static DbWork<PList<DbMetaTable>> getTypes(DbMetaSchema schema){
		return DbWork.function(schema).code(trans -> con -> log -> {
			PList<DbMetaTable> res = PList.empty();

			return Result.success(res);
		});

	}

	public static DbWork<PList<DbMetaTable>> getTables(DbMetaSchema schema, @Nullable String typeName){
		return DbWork.function(schema,typeName).code(trans -> con -> log -> {
			ResultSet tablesMeta = con.getMetaData().getTables(
				schema.getCatalog().getName().orElse(""),
				schema.getName().orElse(""),
				null,
				typeName == null ? null : new String[]{typeName}
			);
			Result<PList<DbMetaTable>> tables = UJdbc.getList(tablesMeta, rs -> {
				String table_cat   = rs.getString("TABLE_CAT");
				String table_schem = rs.getString("TABLE_SCHEM");
				String table_name  = rs.getString("TABLE_NAME");
				String table_type  = rs.getString("TABLE_TYPE");
				String remarks     = rs.getString("REMARKS");


				//String type_cat = rs.getString("TYPE_CAT");
				//String type_schem = rs.getString("TYPE_SCHEM");
				//String type_name = rs.getString("TYPE_NAME");
				//String self_referencing_col_name = rs.getString("SELF_REFERENCING_COL_NAME");
				//String ref_generation = rs.getString("REF_GENERATION");

				return DbMetaTable.build(b -> b
					.setType(table_type)
					.setSchema(schema)
					.setName(table_name)
					.setComment(remarks)
				);
			});
/*
			PList<String> primKeys = UJdbc.getList(con.getMetaData().getPrimaryKeys(table_cat,table_schem,table_name),
				rspk -> rspk.getString("COLUMN_NAME")
			).orElseThrow();
*/


			return tables.flatMap(tableList ->
				UPStreams.fromSequence(
					tableList.mapExc(table -> loadColumns(table).run(trans)))
						 .map(PStream::plist)
			).flatMap(mtList ->
				UPStreams.fromSequence(mtList.mapExc(mt -> {
					Result<PList<DbMetaColumn>> primKeys = UJdbc.getList(con.getMetaData().getPrimaryKeys(
						mt.getSchema().getCatalog().getName().orElse(null),
						mt.getSchema().getName().orElse(null),
						mt.getName()),
						rspk -> rspk.getString("COLUMN_NAME")
					).map(nameList ->
						nameList.map(item -> mt.getColumns().find(c -> c.getName().equals(item)).get()
					));
					return primKeys.map(mt::withPrimKey);
				})).map(PStream::plist)
			);
		});
	}

	public static DbWork<PList<DbMetaUDT>> getUDT(DbMetaSchema schema, @Nullable  String typeName, @Nullable int[] sqlTypes){
		return DbWork.function(schema,typeName).code(trans -> con -> log -> {
			Result<PList<DbMetaUDT>> types = UJdbc.getList(con.getMetaData().getUDTs(
				schema.getCatalog().getName().orElse(""),
				schema.getName().orElse(""),
				typeName,
				sqlTypes
			), rs -> {
				String type_cat = rs.getString("TYPE_CAT");
				String type_schem = rs.getString("TYPE_SCHEM");
				String type_name = rs.getString("TYPE_NAME");
				String class_name = rs.getString("CLASS_NAME");
				int data_type = rs.getInt("DATA_TYPE");
				String remarks = rs.getString("REMARKS");
				int base_type = rs.getInt("BASE_TYPE");
				//String type_cat = rs.getString("TYPE_CAT");
				//String type_schem = rs.getString("TYPE_SCHEM");
				//String type_name = rs.getString("TYPE_NAME");
				//String self_referencing_col_name = rs.getString("SELF_REFERENCING_COL_NAME");
				//String ref_generation = rs.getString("REF_GENERATION");
				return DbMetaUDT.build(b -> b
					.setSchema(schema)
					.setBaseType(base_type)
					.setDataType(data_type)
					.setJavaClassName(class_name)
					.setName(type_name)
					.setRemarks(remarks)
				);
			});
			return types;
		});

	}

	public static DbWork<DbMetaTable> loadColumns(DbMetaTable table){
		return DbWork.function(table).code(trans -> con -> log -> {
			Result<PList<DbMetaColumn>> columns =  UJdbc.getList(
				con.getMetaData().getColumns(
					table.getSchema().getCatalog().getName().orElse(""),
					table.getSchema().getName().orElse(""),
					table.getName(),null)
				,rs -> {
					String name = rs.getString("COLUMN_NAME");
					int dataType = rs.getInt("DATA_TYPE");
					String typeName = rs.getString("TYPE_NAME");
					int columnSize = rs.getInt("COLUMN_SIZE");
					int decimalDigits = rs.getInt("DECIMAL_DIGITS");
					int nullable = rs.getInt("NULLABLE");
					String remarks = rs.getString("REMARKS");
					String column_def = rs.getString("COLUMN_DEF");
					int charOctet_length = rs.getInt("CHAR_OCTET_LENGTH");
					int ordinal_position = rs.getInt("ORDINAL_POSITION");
					String isNullable = rs.getString("IS_NULLABLE");
					//String scopeCatalog = rs.getString("SCOPE_CATALOG");
					//String scopeSchema = rs.getString("SCOPE_SCHEMA");
					//String scopeTable = rs.getString("SCOPE_TABLE");
					int sourceDataType = rs.getInt("SOURCE_DATA_TYPE");
					String isAutoIncrement = rs.getString("IS_AUTOINCREMENT");
					//String isGeneratedColumn = rs.getString("IS_GENERATEDCOLUMN");
					DbMetaDataType type = new DbMetaDataType(dataType)
						.withIsAutoIncrement(isAutoIncrement.equalsIgnoreCase("YES"))
						.withIsNullable(isNullable.equalsIgnoreCase("YES"))
						.withDbTypeName(typeName)
						.withColumnSize(columnSize)
						.withDecimalDigits(decimalDigits);

					return DbMetaColumn.build(b -> b
						.setComment(remarks)
						.setDefaultValue(column_def)
						.setName(name)
						.setType(type)
					);

				}
			);
			return columns.map(cols -> table.withColumns(cols));
		});

	}


	public static DbWork<DbMetaSchema> importSchema(String catalogName, String schemaName){
		return DbWork.function(catalogName, schemaName).code(trans -> con -> log -> {
			throw new ToDo();
		});
//		return DbWork.function(catalogName,schemaName).code(log -> ctx -> ctx.get().<DbMetaSchema>flatMapExc(con ->{
//			throw new ToDo();
			/*DatabaseMetaData md = con.getMetaData();
			return UJdbc.getList(md.getSchemas(),rs -> {
				return new DbMetaSchema(
					rs.getString("TABLE_SCHEM"),
					rs.getString("TABLE_CATALOG")
				);
			}).flatMap(list ->
					  Result.fromOpt(
					  	list.find(schem -> schemaName.equals(schem.getName().orElse(null))))
			)
			.<DbMetaSchema>flatMapExc(schema -> {
				log.info("Found schema: " + schema.getCatalogName() + ", " + schema.getName());

				try(ResultSet ttypes = md.getTableTypes()){

					while(ttypes.next()){

					}
				}

				ResultSet rs = md.getTables(null, schemaName,"%", new String[]{ "TABLE","VIEW"});
				while(rs.next()){
					String tableName = rs.getString("TABLE_NAME");
					String tableType = rs.getString("TABLE_TYPE");
					String tableComment = rs.getString("REMARKS");
					log.info("Found Table " + tableName);
					DbMetaTable.Type setType;
					switch(tableType){
						case "TABLE": setType = DbMetaTable.Type.table;break;
						case "VIEW": setType = DbMetaTable.Type.view;break;
						default: return Result.failure("Don't know tabletype " + tableType);
					}
					DbMetaTable set = new DbMetaTable(setType,tableName).comment(tableComment);

				}
				return Result.success(schema);
			});*/

		//}));
	}

	private Result<DbMetaTable> importSet(DbMetaSchema schema, DbMetaTable set){
		throw new ToDo();
	}

}
