package com.persistentbit.sql.dsl.codegen.importer;

import com.persistentbit.collections.PList;
import com.persistentbit.collections.UPStreams;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.meta.DbMetaDataImporter;
import com.persistentbit.sql.meta.data.DbMetaColumn;
import com.persistentbit.sql.meta.data.DbMetaDataType;
import com.persistentbit.sql.meta.data.DbMetaSchema;
import com.persistentbit.sql.meta.data.DbMetaTable;
import com.persistentbit.sql.transactions.DbTransaction;
import com.persistentbit.utils.exceptions.ToDo;

import java.sql.Types;
import java.util.function.Supplier;

/**
 * @author petermuys
 * @since 27/12/17
 */
public class GenericDbImporter{

	protected final DbImportSettings        settings;
	protected final Supplier<DbTransaction> transSup;
	protected       PList<DbMetaSchema>     allSchemas;


	public GenericDbImporter(DbImportSettings settings) {
		this.settings = settings;
		this.transSup = settings.getTransactionSupplier();
	}

	public Result<CgContext> importDb() {
		return Result.function(settings).code(log -> {

			//DbNameTransformer nameTransformer = settings.getNameTransformer();

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
				});
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
					return createField(EByteList.class, table, column);

				case Types.STRUCT:
					throw new ToDo("Struct not yet supported");
					//String pack = toJavaPackage(rootPackage,nameTransformer,table.getSchema());
					//String clsName = nameTransformer.toJavaName(mt.getDbTypeName().get());
					//return new DbJavaFieldStruct(
					//					column,
					//javaName,
					//getCustomTypeMetaTable(transSup,schemas,table.getSchema(),mt.getDbTypeName().get()).get(),clsName,pack);
				case Types.JAVA_OBJECT:
					return Result.failure("Not Implemented: JAVA_OBJECT for " + column);
				case Types.SQLXML:
					return createField(EString.class, table, column);
				case Types.DISTINCT:
					return Result.failure("Domain objects not yet implemented");
				//DbMetaUDT udt = domains.find(u -> u.getName().equals(mt.getDbTypeName().orElse(null))).get();
				//return new DbJavaFieldDomain(column,udt,javaName,nameTransformer.toJavaName(udt.getName()),this.toJavaPackage(rootPackage,nameTransformer,udt.getSchema()));


				case Types.ARRAY:
					return createArrayField(table, column);
				//return createArrayField(rootPackage,nameTransformer,javaName, table, column,customTypes,enumTypes,domains);

				case Types.OTHER:
					return Result.failure("OTHER objects not yet implemented");
				//return getOtherJavaField(column, javaName);

				default:
					return Result.failure("Unknown: " + column);
			}
		});

	}

	protected Result<TableField> createArrayField(DbMetaTable table, DbMetaColumn column) {
		return Result.failure("Array objects not yet implemented for " + table.name + " " + column.getName());
	}


}
