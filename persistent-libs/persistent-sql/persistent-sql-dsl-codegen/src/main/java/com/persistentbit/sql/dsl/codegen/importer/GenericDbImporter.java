package com.persistentbit.sql.dsl.codegen.importer;

import com.persistentbit.collections.PList;
import com.persistentbit.collections.PMap;
import com.persistentbit.collections.UPStreams;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.codegen.config.Instance;
import com.persistentbit.sql.dsl.codegen.config.SchemaDef;
import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.meta.DbMetaDataImporter;
import com.persistentbit.sql.meta.data.*;
import com.persistentbit.sql.transactions.DbTransaction;
import com.persistentbit.tuples.Tuple2;

import java.sql.Types;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author petermuys
 * @since 27/12/17
 */
public class GenericDbImporter{

	protected final Instance                      instance;
	protected final Supplier<DbTransaction>       transSup;
	protected       PMap<SchemaDef, DbMetaSchema> allSchemas;


	/*public GenericDbImporter(DbImportSettings settings) {
		this.settings = settings;
		this.transSup = settings.getTransactionSupplier();
	}*/
	public GenericDbImporter(Instance instance, Supplier<DbTransaction> transSup) {
		this.transSup = transSup;
		this.instance = instance;
	}

	protected CgContext createContext(Instance instance) {
		return new CgContext(instance);
	}

	public Result<CgContext> importDb() {
		return Result.function(instance).code(log -> {

			Result<DbMetaSchema> conSchema = log.add(DbMetaDataImporter.getSchemaFromConnection().run(transSup.get()));
			if(conSchema.isError()) {
				conSchema = Result.success(new DbMetaSchema(new DbMetaCatalog(null), null, null));
			}
			return conSchema.flatMap(cs -> getSchemasForInstance(cs))
				.ifPresent(rs -> { allSchemas = rs.orElseThrow(); })
				.map(allSchemas -> allSchemas.map(t -> getTablesForSchema(t._2, t._1)))
				.flatMap(schemaTables -> UPStreams.fromSequence(schemaTables))
				.map(schemaMetaTables -> schemaMetaTables.<DbMetaTable>flatten())
				.map(metaTables -> metaTables.map(mtTable -> generateJavaTable(mtTable)))
				.flatMap(rl -> Result.fromSequence(rl.list()).map(l -> PList.from(l)))
				.map(sl -> {
					CgContext context = createContext(instance);
					sl.forEach(context::register);
					return context;
				});

/*

			return DbMetaDataImporter
				.getAllSchemas()
				.run(transSup.get())
				.ifPresent(rs -> { allSchemas = rs.orElseThrow(); })
				.map(allSchemas -> settings.getTableSelection().getTablesAndViews())
				.map(metaTables -> metaTables.map(mtTable -> generateJavaTable(mtTable)))
				.flatMap(rl -> Result.fromSequence(rl.list()).map(l -> PList.from(l)))
				.map(sl -> {
					CgContext context = new CgContext(settings.getInstance());
					sl.forEach(context::register);
					return context;
				});*/
			//throw new ToDo();
		});
	}

	protected Optional<SchemaDef> getSchemaDef(DbMetaSchema schemaMeta) {
		return allSchemas.find(t -> t._2.equals(schemaMeta)).map(t -> t._1);
	}

	protected Result<PList<DbMetaTable>> getTablesForSchema(DbMetaSchema schemaMeta, SchemaDef schemaDef) {
		return Result.function(schemaMeta, schemaDef).code(log -> {
			return DbMetaDataImporter.getTablesAndViews(schemaMeta)
				.map(tableMetaList ->
						 tableMetaList
							 .filter(tableMeta ->
										 schemaDef.getExcludeTables()
											 .contains(tableMeta
														   .getName()) == false))
				.run(transSup.get());
		});
	}


	protected Result<PMap<SchemaDef, DbMetaSchema>> getSchemasForInstance(DbMetaSchema connectionSchema) {
		return Result.function(connectionSchema).code(log -> {
			log.info("Get Schema MetaData");
			PMap<SchemaDef, DbMetaSchema> result = PMap.empty();
			for(SchemaDef schemaDef : instance.getSchemas()) {
				//BUILD THE CATALOG AND SCHEMA NAME FROM THE INSTANCE SETTINGS AND THE CONNECTION SETTINGS
				String catName = schemaDef
					.getCatalogName()
					.orElse(null);
				//.orElse(connectionSchema.getCatalog().getName().orElse(null));
				String schemaName = schemaDef
					.getSchemaName()
					.orElse(null);
				//.orElse(connectionSchema.getName().orElse(null));
				Result<DbMetaSchema> schema = DbMetaDataImporter.getSchema(catName, schemaName).run(transSup.get());

				if(schema.isPresent() == false) {
					Result<PList<DbMetaCatalog>> cats = DbMetaDataImporter.getCatalogs()
						.map(catList -> catList.filter(cat -> cat.getName().get().equalsIgnoreCase(catName)))
						.run(transSup.get());
					if(cats.isPresent()) {
						result = result.put(schemaDef, new DbMetaSchema(new DbMetaCatalog(catName), null, null));
					}
					else {
						return schema.map(p -> null);
					}
				}
				else {
					result = result.put(schemaDef, schema.orElseThrow());
				}
			}
			return Result.success(result);
		});

	}


	protected Result<TableTypeDef> generateJavaTable(DbMetaTable table) {
		return Result.function(table).code(l ->
											   UPStreams.fromSequence(
												   table.getColumns()
													   .map(col -> getTableField(table, col))
											   )
												   .map(fl -> new TableTypeDef(createTableName(table), PList.from(fl)))
		);
	}


	private CgTableName createTableName(DbMetaTable table) {
		return new CgTableName(
			table.getSchema().getCatalog().getName().orElse(null),
			table.getSchema().getName().orElse(null),
			table.getName()
		);
	}

	protected Result<SimpleTableField> createSimpleField(Class<? extends DExpr> exprCls, DbMetaTable table,
														 DbMetaColumn column) {
		DbMetaDataType mt = column.getType();
		//String         javaName = nameTransformer.toJavaName(table,column);
		boolean isNullable = mt.getIsNullable();
		boolean isAutoGen  = mt.getIsAutoIncrement();
		boolean hasDefault = column.getDefaultValue().isPresent();
		String  columnName = column.getName();

		boolean isPrimKey = table.primKey.find(mc -> mc.getName().equals(columnName)).isPresent();

		CgTableName tableName = createTableName(table);
		return Result.success(new SimpleTableField(TypeRef.create(exprCls),
												   tableName,
												   columnName,
												   isNullable,
												   hasDefault,
												   isPrimKey,
												   isAutoGen
		));
	}

	protected Result<TableField> createField(Class<? extends DExpr> exprCls, DbMetaTable table, DbMetaColumn column) {
		return createSimpleField(exprCls, table, column).map(tf -> tf);
	}

	protected Result<TableField> getTableField(
		DbMetaTable table,
		DbMetaColumn column
	) {
		return Result.function(table, column).code(log -> {

			DbMetaDataType mt = column.getType();

			switch(mt.getSqlType()) {
				case Types.BINARY:
					if(mt.getColumnSize() == 1) {
						return createField(EByte.class, table, column);
					}
				case Types.VARBINARY:
				case Types.LONGVARBINARY:
				case Types.BLOB:
					return createField(EByteList.class, table, column);
				case Types.BOOLEAN:
					return createField(EBool.class, table, column);
				case Types.BIGINT:
					return createField(ELong.class, table, column);
				case Types.BIT: {
					if(mt.getColumnSize() == 1) {
						return createField(EBool.class, table, column);
					}
					return createField(EBitList.class, table, column);
				}
				case Types.CHAR:
				case Types.NCHAR:
				case Types.VARCHAR:
				case Types.NVARCHAR:
				case Types.CLOB:
				case Types.LONGNVARCHAR:
				case Types.NCLOB:
				/*
				TODO: Enums not yet supported

				DbEnumType enumType = enumTypes.find(e -> e.getName().equals(mt.getDbTypeName().get())).orElse(null);
				if(enumType != null){
					return new DbJavaFieldEnum(
						column,
						javaName,
						enumType,
						nameTransformer.toJavaName(enumType.getName()),
						toJavaPackage(rootPackage,nameTransformer,enumType.getSchema()));
				}*/
					return createField(EString.class, table, column);
				case Types.DATE:
					return createField(EDate.class, table, column);

				case Types.NUMERIC:
				case Types.DECIMAL:
					return createField(EBigDecimal.class, table, column);
				case Types.DOUBLE:
					return createField(EDouble.class, table, column);
				case Types.REAL:
				case Types.FLOAT:
					return createField(EFloat.class, table, column);
				case Types.INTEGER:
					return createField(EInt.class, table, column);

				case Types.SMALLINT:
					return createField(EShort.class, table, column);
				case Types.TIMESTAMP:
					//if(mt.getDbTypeName().get().equals("timestamp")) {
					//
					//return new DbJavaFieldCustomObject(column, javaName, LocalDateTime.class);
					//} else {
					//					return new DbJavaFieldCustomObject(column, javaName, ZonedDateTime.class);
					//}
					return createField(EDateTime.class, table, column);

				case Types.TIME:
					return createField(ETime.class, table, column);


				case Types.TINYINT:
					return createField(EByte.class, table, column);

				case Types.STRUCT:
					return createStructField(table, column);
				//throw new ToDo("Struct not yet supported");
				//String pack = toJavaPackage(rootPackage,nameTransformer,table.getSchema());
				//String clsName = nameTransformer.toJavaName(mt.getDbTypeName().get());
				//return new DbJavaFieldStruct(
				//					column,
				//javaName,
				//getCustomTypeMetaTable(transSup,schemas,table.getSchema(),mt.getDbTypeName().get()).get(),clsName,pack);
				case Types.JAVA_OBJECT:
					return createObjectField(table, column);
				case Types.SQLXML:
					return createField(EString.class, table, column);
				case Types.DISTINCT:
					return createDistinctField(table, column);
				//DbMetaUDT udt = domains.find(u -> u.getName().equals(mt.getDbTypeName().orElse(null))).get();
				//return new DbJavaFieldDomain(column,udt,javaName,nameTransformer.toJavaName(udt.getName()),this.toJavaPackage(rootPackage,nameTransformer,udt.getSchema()));


				case Types.ARRAY:
					return createArrayField(table, column);
				//return createArrayField(rootPackage,nameTransformer,javaName, table, column,customTypes,enumTypes,domains);

				case Types.OTHER:
				default:
					return createOtherField(table, column);
			}
		});

	}

	protected Result<TableField> createArrayField(DbMetaTable table, DbMetaColumn column) {
		return createField(EObject.class, table, column);
		//return Result.failure("Array objects not yet implemented for " + table.name + " " + column.getName());
	}

	protected Result<TableField> createStructField(DbMetaTable table, DbMetaColumn column) {
		return createField(EObject.class, table, column);
		//return Result.failure("Array objects not yet implemented for " + table.name + " " + column.getName());
	}

	protected Result<TableField> createDistinctField(DbMetaTable table, DbMetaColumn column) {
			/*
	PList<DbMetaUDT> udts = selection
		.getSchemas()
		.map(schema -> DbMetaDataImporter.getUDT(schema,null,new int[] {Types.DISTINCT}).transaction(run).orElseThrow())
		.<DbMetaUDT>flatten()
		.plist();
		udts.forEach(udt -> {
		System.out.println("UDT: " + udt);
	});*/
		//String typeName = column.getType().getDbTypeName().orElse(null);
		Tuple2<Optional<DbMetaSchema>, String> typeNameTuple =
			parseUDTDataTypeName(column.getType().getDbTypeName().get());
		return DbMetaDataImporter
			.getUDT(
				typeNameTuple._1.orElse(table.getSchema()),
				typeNameTuple._2,
				new int[]{Types.DISTINCT}
			)
			.run(transSup.get())
			.flatMap(list -> Result.fromOpt(list.headOpt()))
			.flatMap(domain -> createDomainField(domain, table, column))
			.flatMapEmpty(empty -> createField(EObject.class, table, column));


		//return Result.failure("Array objects not yet implemented for " + table.name + " " + column.getName());
	}

	protected Tuple2<Optional<DbMetaSchema>, String> parseUDTDataTypeName(String name) {
		String[] split = name.split("\\.");
		switch(split.length) {
			case 1:
				return Tuple2.of(Optional.empty(), removeNameQuotes(name));
			case 2:
				return Tuple2.of(allSchemas.values().find(s -> s.getName()
					.equals(Optional.of(removeNameQuotes(split[0])))), removeNameQuotes(split[1]));
			case 3:
				return Tuple2.of(
					allSchemas.values()
						.find(s ->
								  s.getCatalog().getName().equals(Optional.of(removeNameQuotes(split[0]))) &&
									  s.getName().equals(Optional.of(removeNameQuotes(split[1])))
						), removeNameQuotes(split[3]));
			default:
				return Tuple2.of(Optional.empty(), removeNameQuotes(name));
		}
	}

	protected String removeNameQuotes(String name) {
		return name
			.replaceAll("\"", "")
			.replaceAll("\'", "");

	}

	protected Result<TableField> createDomainField(DbMetaUDT udt, DbMetaTable table, DbMetaColumn column) {

		DbMetaDataType mt = column.getType();
		//String         javaName = nameTransformer.toJavaName(table,column);
		boolean isNullable = column.getType().getIsNullable();
		boolean isAutoGen  = mt.getIsAutoIncrement();
		boolean hasDefault = column.getDefaultValue().isPresent();
		String  columnName = column.getName();

		boolean isPrimKey = table.primKey.find(mc -> mc.getName().equals(columnName)).isPresent();

		CgTableName tableName = createTableName(table);
		TypeRef     typeRef   = TypeRef.create(getDomainTypeClass(udt, table, column));
		return Result
			.success(new SimpleTableField(typeRef, tableName, columnName, isNullable, hasDefault, isPrimKey, isAutoGen));
	}

	protected Class<? extends DExpr> getDomainTypeClass(DbMetaUDT udt, DbMetaTable table, DbMetaColumn column) {
		switch(udt.getBaseType()) {
			case Types.BLOB:
			case Types.BINARY:
			case Types.LONGVARBINARY:
			case Types.VARBINARY:
				return EByteList.class;
			case Types.BOOLEAN:
				return EBool.class;
			case Types.BIGINT:
				return ELong.class;
			case Types.BIT:
				return EBool.class;
			case Types.CHAR:
			case Types.CLOB:
			case Types.LONGNVARCHAR:
			case Types.NCHAR:
			case Types.NCLOB:
			case Types.NVARCHAR:
			case Types.VARCHAR:
			case Types.LONGVARCHAR:
				return EString.class;
			case Types.DATE:
				return EDate.class;
			case Types.NUMERIC:
			case Types.DECIMAL:
				return EBigDecimal.class;
			case Types.DOUBLE:
				return EDouble.class;
			case Types.REAL:
			case Types.FLOAT:
				return EFloat.class;
			case Types.INTEGER:
				return EInt.class;
			case Types.SMALLINT:
				return EShort.class;
			case Types.SQLXML:
				return EObject.class;
			case Types.TIME:
				return ETime.class;
			case Types.TIMESTAMP:
				return EDateTime.class;
			case Types.TINYINT:
				return EByte.class;
			case Types.OTHER:
			case Types.JAVA_OBJECT:
			case Types.DISTINCT:
			case Types.ARRAY:
			case Types.STRUCT:
			case Types.DATALINK:
			case Types.NULL:
			case Types.REF:
			case Types.REF_CURSOR:
			case Types.ROWID:
			case Types.TIME_WITH_TIMEZONE:
			case Types.TIMESTAMP_WITH_TIMEZONE:
			default:
				return EObject.class;
		}
	}

	protected Result<TableField> createObjectField(DbMetaTable table, DbMetaColumn column) {
		return createField(EObject.class, table, column);
		//return Result.failure("Array objects not yet implemented for " + table.name + " " + column.getName());
	}

	protected Result<TableField> createOtherField(DbMetaTable table, DbMetaColumn column) {
		return createField(EObject.class, table, column);
		//return Result.failure("Array objects not yet implemented for " + table.name + " " + column.getName());
	}

/*
	private Class<? extends DExpr> getDomainTypeClass(DbMetaUDT udt){
		return Result.function(udt).code(l -> {

			switch(udt.getBaseType()){
				case Types.BLOB:
				case Types.BINARY:
				case Types.LONGVARBINARY:
				case Types.VARBINARY:
					return EByteList.class;
				case Types.BOOLEAN:
					return EBool.class;
				case Types.BIGINT:
					return EBigDecimal.class;
				case Types.BIT:
					return EBitList.class;
				case Types.CHAR:
				case Types.CLOB:
				case Types.LONGNVARCHAR:
				case Types.NCHAR:
				case Types.NCLOB:
				case Types.NVARCHAR:
				case Types.VARCHAR:
				case Types.LONGVARCHAR:
					return EString.class;
					break;
				case Types.DATE:
					return EDateTime.class;
				case Types.NUMERIC:
				case Types.DECIMAL:
					return EBigDecimal.class;
				case Types.DOUBLE:
					return EDouble.class;
				case Types.REAL:
				case Types.FLOAT:
					return EFloat.class;
				case Types.INTEGER:
					return EInt.class;
				case Types.SMALLINT:
					return EShort.class;
				case Types.SQLXML:
					return EObject.class;
				case Types.TIME:
					return ETime.class;
				case Types.TIMESTAMP:
					return EDateTime.class;
				case Types.TINYINT:
					return EByte.class;
				case Types.OTHER:
					field = new JField(fname,Object.class);
					break;
				case Types.JAVA_OBJECT:
				case Types.DISTINCT:
				case Types.ARRAY:
				case Types.STRUCT:
				case Types.DATALINK:
				case Types.NULL:
				case Types.REF:
				case Types.REF_CURSOR:
				case Types.ROWID:
				case Types.TIME_WITH_TIMEZONE:
				case Types.TIMESTAMP_WITH_TIMEZONE:
				default:
					return Result.failure("Can't convert domain: " + udt);
			}
			field = field.asNullable();
			cls = cls.addField(field);
			cls = cls.addMainConstructor(AccessLevel.Public);
			cls = cls.addMethod(field.createGetter());
			JJavaFile file = new JJavaFile(toJavaPackage(udt.getSchema()))
				.addClass(cls);
			return Result.success(file.toJavaSource());
		});
	}*/
}
