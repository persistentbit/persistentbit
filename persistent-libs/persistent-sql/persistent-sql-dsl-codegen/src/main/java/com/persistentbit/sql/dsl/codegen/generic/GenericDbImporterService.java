package com.persistentbit.sql.dsl.codegen.generic;

import com.persistentbit.collections.*;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.codegen.*;
import com.persistentbit.sql.dsl.codegen.dbjavafields.*;
import com.persistentbit.sql.meta.DbMetaDataImporter;
import com.persistentbit.sql.meta.data.*;
import com.persistentbit.sql.transactions.DbTransaction;
import com.persistentbit.sql.work.DbWork;

import java.math.BigDecimal;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 9/12/17
 */
public class GenericDbImporterService implements DbImporterService{


	@Override
	public DbHandlingLevel getHandlingLevel(DbMetaDatabase db) {
		return DbHandlingLevel.onlyGeneric;
	}

	@Override
	public String getDescription() {
		return "Generic Database Importer Service";
	}

	@Override
	public Result<DbDefinition> importDb(DbImportSettings settings) {
		return Result.function(settings).code(log -> {

			Supplier<DbTransaction> transSup        = settings.getTransactionSupplier();
			PList<DbMetaSchema>     schemas         = settings.getTableSelection().getSchemas();
			String                  rootPackage     = settings.getRootPackage();
			DbNameTransformer       nameTransformer = settings.getNameTransformer();


			log.info("Getting all Schemas");
			PList<DbMetaSchema> allSchemas = DbMetaDataImporter
				.getAllSchemas()
				.run(transSup.get())
				.orElseThrow();

			log.info("Getting all Enums");
			PList<DbEnumType>   enumTypes  = loadEnumTypes(transSup,allSchemas,rootPackage,nameTransformer)
				.run(transSup.get())
				.orElseThrow();

			log.info("Loading Custom types");
			PList<DbCustomType> customTypes = loadCustomTypes(transSup,schemas,nameTransformer);

			log.info("Loading UDT's");
			PList<DbMetaUDT> udts = loadUDTs(transSup,schemas);

			log.info("Adding Custum Types fields");
			PList<DbCustomType> customTypesWithFields = addCustomTypesFields(transSup,schemas,
				rootPackage, nameTransformer, enumTypes, customTypes, udts);

			log.info("Load Tables");
			PList<DbMetaTable> tables = settings.getTableSelection().getTablesAndViews();
			PList<DbJavaTable> javaTables = tables
				.map(table -> generateJavaTable(transSup,schemas,rootPackage,nameTransformer,table,customTypes,enumTypes,udts));

			log.info("Find used Structures");
			PSet<DbCustomType> usedTypes = findUsedStructures(javaTables,customTypesWithFields);

			log.info("Find Used Enums");
			PSet<DbEnumType> usedEnums = findUsedEnums(javaTables, enumTypes);

			log.info("Find used domains");
			PSet<DbMetaUDT> usedDomains = findUsedDomains(javaTables,usedTypes);
			return Result.success(DbDefinition.build(b -> b
				.setTables(javaTables)
				.setCustomTypes(usedTypes)
				.setDomainObjects(usedDomains)
				.setEnumTypes(usedEnums)
			));
		});
	}

	protected PSet<DbMetaUDT> findUsedDomains(PList<DbJavaTable> javaTables,PStream<DbCustomType> usedTypes){
		PSet<DbMetaUDT> usedDomains = PSet.empty();
		for(DbJavaTable table : javaTables){
			for(DbJavaField field : table.getJavaFields()){
				for(DbJavaFieldDomain element : field.getDomains()){
					usedDomains = usedDomains.plus(
						element.getUdtMeta()
					);
				}
			}
		}
		for(DbCustomType customType : usedTypes){
			for(DbJavaField field : customType.getFields()){
				for(DbJavaFieldDomain element : field.getDomains()){
					usedDomains = usedDomains.plus(
						element.getUdtMeta()
					);
				}
			}
		}
		return usedDomains;
	}

	protected PSet<DbCustomType> findUsedStructures(PList<DbJavaTable> javaTables,PList<DbCustomType> customTypesWithFields) {
		PSet<DbCustomType> usedTypes = PSet.empty();
		for(DbJavaTable table : javaTables){
			for(DbJavaField field : table.getJavaFields()){
				for(DbJavaFieldStruct structField : field.getStructures()){
					usedTypes = usedTypes.plus(
						customTypesWithFields.find(ct -> ct.getDefinition().equals(structField.getCustomTypeDbMeta())).get()
					);
				}
			}
		}
		for(DbCustomType ct : customTypesWithFields){
			for(DbJavaField field : ct.getFields()){
				for(DbJavaFieldStruct structField : field.getStructures()){
					usedTypes = usedTypes.plus(
						customTypesWithFields.find(uct -> uct.getDefinition().equals(structField.getCustomTypeDbMeta())).get()
					);
				}
			}
		}
		return usedTypes;
	}

	protected PSet<DbEnumType> findUsedEnums(PList<DbJavaTable> javaTables,PList<DbEnumType> enumTypes) {
		PSet<DbEnumType> usedEnums = PSet.empty();
		for(DbJavaTable table : javaTables){
			for(DbJavaField field : table.getJavaFields()){
				for(DbJavaFieldEnum enumField : field.getUsedEnums()){
					usedEnums = usedEnums.plus(
						enumTypes.find(et -> enumField.getEnumType().equals(et)).get()
					);
				}
			}
		}
		return usedEnums;
	}

	protected DbJavaTable	generateJavaTable(
		Supplier<DbTransaction> transSup,
		PList<DbMetaSchema> schemas,
		String rootPackage,
		DbNameTransformer nameTransformer,
		DbMetaTable table,
		PList<DbCustomType> customTypes,
		PList<DbEnumType> enumTypes,
		PList<DbMetaUDT> domains
	){
		PList<DbJavaField> fields = table
			.getColumns()
			.map(col -> getDbJavaField(transSup,schemas,rootPackage,table, col,customTypes,enumTypes,domains,nameTransformer));
		String javaClassName = nameTransformer.toJavaName(table);
		String packName = rootPackage
			+ "." + nameTransformer.toJavaName(table.getSchema().getCatalog())
			+ "." + nameTransformer.toJavaName(table.getSchema());
		return new DbJavaTable(table,fields, javaClassName,packName);
	}

	protected PList<DbCustomType> addCustomTypesFields(
		Supplier<DbTransaction> transSup,
		PList<DbMetaSchema> schemas,
		String rootPackage,
		DbNameTransformer nameTransformer,
		PList<DbEnumType> enumTypes,
		PList<DbCustomType> customTypes,
		PList<DbMetaUDT> udts
	) {
		return customTypes.map(dbCustomType -> {
			DbMetaTable  ctTable = dbCustomType.getDefinition();
			DbCustomType res     = dbCustomType;
			for(DbMetaColumn col : res.getDefinition().getColumns()){
				DbJavaField newField = getDbJavaField(
					transSup,schemas,rootPackage,
					ctTable,
					col.withType(col.getType().withIsNullable(false)),
					customTypes,enumTypes,udts,
					nameTransformer);
				res = res.withFields(res.getFields().plus(newField));
			}
			return res;
		});
	}

	protected PList<DbMetaUDT> loadUDTs(Supplier<DbTransaction> transSup, PList<DbMetaSchema> schemas){
		PList<DbMetaUDT> udts = schemas
			.map(schema ->
				DbMetaDataImporter.getUDT(schema,null,new int[] {Types.DISTINCT})
								  .run(transSup.get())
								  .orElseThrow())
			.<DbMetaUDT>flatten()
			.plist();
		return udts;
	}

	protected PList<DbCustomType> loadCustomTypes(Supplier<DbTransaction> transSup, PList<DbMetaSchema> schemas, DbNameTransformer nameTransformer) {
		return schemas
			.map(schema -> DbMetaDataImporter.getTypes(schema).run(transSup.get()).orElseThrow()
											 .map(metaTable -> new DbCustomType(metaTable, nameTransformer.toJavaName(metaTable), PList.empty()))
			)
			.<DbCustomType>flatten()
			.plist();
	}

	protected DbWork<PList<DbEnumType>> loadEnumTypes(
		Supplier<DbTransaction> transSup,
		PList<DbMetaSchema> schemas,
		String rootPackage,
		DbNameTransformer nameTransformer
	){
		return DbWork.defaultWork(PList.empty());
	}


	protected DbJavaField getDbJavaField(
		Supplier<DbTransaction> transSup,
		PList<DbMetaSchema> schemas,
		String rootPackage,
		DbMetaTable table,
		DbMetaColumn column,
		PList<DbCustomType> customTypes,
		PList<DbEnumType> enumTypes,
		PList<DbMetaUDT> domains,
		DbNameTransformer nameTransformer
	) {
		DbMetaDataType mt       = column.getType();
		String         javaName = nameTransformer.toJavaName(table,column);
		switch(mt.getSqlType()){
			case Types.BINARY:
				if(mt.getColumnSize() == 1){
					return new DbJavaFieldPrimitiveType(column, javaName, byte.class);
				}
			case Types.VARBINARY:
			case Types.LONGVARBINARY:
			case Types.BLOB:
				return new DbJavaFieldCustomObject(column,javaName,PByteList.class);
			case Types.BOOLEAN:
				return new DbJavaFieldPrimitiveType(column, javaName, boolean.class);

			case Types.BIGINT:
				return new DbJavaFieldPrimitiveType(column, javaName, long.class);
			case Types.BIT: {
				return getDbJavaFieldBit(column, javaName);

			}
			case Types.CHAR:
			case Types.NCHAR:
			case Types.VARCHAR:
			case Types.NVARCHAR:
			case Types.CLOB:
			case Types.LONGNVARCHAR:
			case Types.NCLOB:
				DbEnumType enumType = enumTypes.find(e -> e.getName().equals(mt.getDbTypeName().get())).orElse(null);
				if(enumType != null){
					return new DbJavaFieldEnum(
						column,
						javaName,
						enumType,
						nameTransformer.toJavaName(enumType.getName()),
						toJavaPackage(rootPackage,nameTransformer,enumType.getSchema()));
				}
				return new DbJavaFieldCustomObject(column, javaName, String.class);
			case Types.DATE:
				return new DbJavaFieldCustomObject(column, javaName, LocalDate.class);

			case Types.NUMERIC:
			case Types.DECIMAL:
				return new DbJavaFieldCustomObject(column, javaName, BigDecimal.class);
			case Types.DOUBLE:
				return geDoubleDbJavaField(column, mt, javaName);
			case Types.REAL:
			case Types.FLOAT:
				return new DbJavaFieldPrimitiveType(column, javaName, float.class);
			case Types.INTEGER:
				return new DbJavaFieldPrimitiveType(column, javaName, int.class);

			case Types.SMALLINT:
				return new DbJavaFieldPrimitiveType(column, javaName, short.class);
			case Types.TIMESTAMP:
				if(mt.getDbTypeName().get().equals("timestamp")) {
					return new DbJavaFieldCustomObject(column, javaName, LocalDateTime.class);
				} else {
					return new DbJavaFieldCustomObject(column, javaName, ZonedDateTime.class);
				}
			case Types.TIME:
				return new DbJavaFieldCustomObject(column, javaName, LocalTime.class);

			case Types.TINYINT:
				return new DbJavaFieldPrimitiveType(column, javaName, byte.class);
			case Types.STRUCT:
				String pack = toJavaPackage(rootPackage,nameTransformer,table.getSchema());
				String clsName = nameTransformer.toJavaName(mt.getDbTypeName().get());
				return new DbJavaFieldStruct(
					column,
					javaName,
					getCustomTypeMetaTable(transSup,schemas,table.getSchema(),mt.getDbTypeName().get()).get(),clsName,pack);
			case Types.JAVA_OBJECT:
				throw new RuntimeException("Not Implemented: JAVA_OBJECT for " + column );
			case Types.SQLXML:
				return getXmlJavaField(column, javaName);
			case Types.DISTINCT:
				DbMetaUDT udt = domains.find(u -> u.getName().equals(mt.getDbTypeName().orElse(null))).get();
				return new DbJavaFieldDomain(column,udt,javaName,nameTransformer.toJavaName(udt.getName()),this.toJavaPackage(rootPackage,nameTransformer,udt.getSchema()));



			case Types.ARRAY:
				return createArrayField(rootPackage,nameTransformer,javaName, table, column,customTypes,enumTypes,domains);

			case Types.OTHER:
				return getOtherJavaField(column, javaName);

			default:
				throw new RuntimeException("Unknown: " + column);
		}
	}

	protected DbJavaField getDbJavaFieldBit(DbMetaColumn column, String javaName) {
		if(column.getType().getColumnSize() == 1){
			return new DbJavaFieldPrimitiveType(column, javaName, boolean.class);
		}
		return new DbJavaFieldCustomObject(column, javaName, PBitList.class);
	}

	protected DbJavaField geDoubleDbJavaField(DbMetaColumn column, DbMetaDataType mt, String javaName) {
		return new DbJavaFieldPrimitiveType(column, javaName, double.class);
	}

	protected DbJavaFieldCustomObject getXmlJavaField(DbMetaColumn column, String javaName) {
		throw new UnsupportedOperationException("XML field not supported: " + column);
	}

	protected DbJavaField getOtherJavaField(DbMetaColumn column, String javaName) {
		throw new UnsupportedOperationException("Unsupported: " + column + ", " + javaName);
		/*switch(column.getType().getDbTypeName().orElse(null)){
			case "interval":
				return new DbJavaFieldCustomObject(column, javaName, Interval.class);
			case "cidr":
				return new DbJavaFieldCustomObject(column, javaName, Cidr.class);
			case "inet":
				return new DbJavaFieldCustomObject(column, javaName, Inet.class);
			case "macaddr":
				return new DbJavaFieldCustomObject(column, javaName, Macaddr.class);
			case "uuid":
				return new DbJavaFieldCustomObject(column, javaName, UUID.class);
			case "varbit":
				return new DbJavaFieldCustomObject(column, javaName, PBitList.class);
			case "tsvector":
				return new DbJavaFieldCustomObject(column, javaName, Tsvector.class);
			case "tsquery":
				return new DbJavaFieldCustomObject(column, javaName, Tsquery.class);
			case "json":
				return new DbJavaFieldCustomObject(column, javaName, Json.class);
			default:
				throw new RuntimeException("Don't know other type " + column.getType().getDbTypeName() + " for " + column);
		}*/
	}

	protected String toJavaPackage(String rootPackage, DbNameTransformer nameTransformer, DbMetaSchema schema){
		return rootPackage
			+ "." + nameTransformer.toJavaName(schema.getCatalog())
			+ "." + nameTransformer.toJavaName(schema);
	}

	protected DbJavaFieldArray createArrayField(
		String rootPackage,
		DbNameTransformer nameTransformer,
		String javaName,
		DbMetaTable table,
		DbMetaColumn column,
		PList<DbCustomType> customTypes,
		PList<DbEnumType> enumTypes,
		PList<DbMetaUDT> udts
	){
		throw new UnsupportedOperationException("Arrays are Unsupported for Generic Database Importer");
		/*
		String dbTypeName = column.getType().getDbTypeName().orElse(null);
		DbJavaField element;
		switch(dbTypeName){
			case "_numeric":
				element= new DbJavaFieldCustomObject(column, javaName, BigDecimal.class);
				break;
			case "_int8":
				element = new DbJavaFieldPrimitiveType(column, javaName, long.class);
				break;
			case "_int4":
				element = new DbJavaFieldPrimitiveType(column, javaName, int.class);
				break;
			case "_int2":
				element = new DbJavaFieldPrimitiveType(column, javaName, short.class);
				break;
			case "_float4":
				element = new DbJavaFieldPrimitiveType(column, javaName, float.class);
				break;
			case "_float8":
				element = new DbJavaFieldPrimitiveType(column, javaName, double.class);
				break;
			case "_money":
				element = new DbJavaFieldCustomObject(column, javaName, Money.class);
				break;
			case "_varchar":
			case "_text":
			case "_bpchar":
				element = new DbJavaFieldCustomObject(column, javaName, String.class);
				break;
			case "_bytea":
				element = new DbJavaFieldCustomObject(column,javaName,PByteList.class);
				break;
			case "_timestamp":
				element = new DbJavaFieldCustomObject(column, javaName, LocalDateTime.class);
				break;
			case "_timestamptz":
				element = new DbJavaFieldCustomObject(column, javaName, ZonedDateTime.class);
				break;
			case "_date":
				element = new DbJavaFieldCustomObject(column, javaName, LocalDate.class);
				break;
			case "_time":
				element = new DbJavaFieldCustomObject(column, javaName, Time.class);
				break;
			case "_timetz":
				element = new DbJavaFieldCustomObject(column, javaName, Time.class);
				break;
			case "_interval":
				element = new DbJavaFieldCustomObject(column, javaName, Interval.class);
				break;
			case "_bool":
				element = new DbJavaFieldPrimitiveType(column, javaName, boolean.class);
				break;
			case "_cidr":
				element = new DbJavaFieldCustomObject(column, javaName, Cidr.class);
				break;
			case "_inet":
				element = new DbJavaFieldCustomObject(column, javaName, Inet.class);
				break;
			case "_macaddr":
				element = new DbJavaFieldCustomObject(column, javaName, Macaddr.class);
				break;
			case "_bit":
			case "_varbit":
				element = new DbJavaFieldCustomObject(column,javaName, PBitList.class);
				break;
			case "_tsvector":
				element = new DbJavaFieldCustomObject(column, javaName, Tsvector.class);
				break;

			case "_tsquery":
				element = new DbJavaFieldCustomObject(column, javaName, Tsquery.class);
				break;

			case "_uuid":
				element = new DbJavaFieldCustomObject(column, javaName, UUID.class);
				break;
			case "_xml":
				element = new DbJavaFieldCustomObject(column, javaName, Xml.class);
				break;

			case "_json":
				element = new DbJavaFieldCustomObject(column, javaName, Json.class);
				break;

			//case "_enum_test":
			//case "_full_name":
			//case "us_postal_code":

			default:
				DbEnumType enumType = enumTypes.find(et -> ("_" + et.getName()).equals(dbTypeName)).orElse(null);
				if(enumType != null){

					element = new DbJavaFieldEnum(column,javaName,enumType,nameTransformer.toJavaName(enumType.getName()),toJavaPackage(rootPackage,nameTransformer,enumType.getSchema()));
					break;
				}
				DbCustomType customType = customTypes.find(ct -> ("_" + ct.getDefinition().getName()).equals(dbTypeName)).orElse(null);
				if(customType != null){
					String pack = toJavaPackage(rootPackage,nameTransformer,customType.getDefinition().getSchema());
					String clsName = nameTransformer.toJavaName(customType.getDefinition().getName());
					element = new DbJavaFieldStruct(column,javaName,customType.getDefinition(),clsName,pack);
					break;
				}


				throw new RuntimeException("Don't know array element type " + dbTypeName + " for " + column);
		}

		return new DbJavaFieldArray(column,javaName, element);
		*/
	}
	protected Optional<DbMetaTable> getCustomTypeMetaTable(
		Supplier<DbTransaction> transSup,
		PList<DbMetaSchema> schemas,
		DbMetaSchema usedInSchema,
		String dbTypeName
	){
		//FIRST, CHECK THE CURRENT SCHEMA
		for(DbMetaTable custom : DbMetaDataImporter.getTypes(usedInSchema).run(transSup.get()).orElseThrow()){
			if(custom.getName().equals(dbTypeName)){
				return Optional.of(custom);
			}
		}
		//NOW CHECK THE REST
		PList<DbMetaSchema> allschemas = schemas.filter(schema -> usedInSchema.equals(schema) == false);
		for(DbMetaSchema schemaToCheck : allschemas){
			for(DbMetaTable custom : DbMetaDataImporter.getTypes(schemaToCheck).run(transSup.get()).orElseThrow()){
				if(custom.getName().equals(dbTypeName)){
					return Optional.of(custom);
				}
			}
		}
		return Optional.empty();
	}
}
