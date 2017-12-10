package com.persistentbit.sql.dsl.codegen.postgresql;

import com.persistentbit.collections.PBitList;
import com.persistentbit.collections.PByteList;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PMap;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.codegen.DbHandlingLevel;
import com.persistentbit.sql.dsl.codegen.DbNameTransformer;
import com.persistentbit.sql.dsl.codegen.dbjavafields.*;
import com.persistentbit.sql.dsl.codegen.generic.DbCustomType;
import com.persistentbit.sql.dsl.codegen.generic.DbEnumType;
import com.persistentbit.sql.dsl.codegen.generic.GenericDbImporterService;
import com.persistentbit.sql.dsl.postgres.rt.customtypes.*;
import com.persistentbit.sql.meta.data.*;
import com.persistentbit.sql.transactions.DbTransaction;
import com.persistentbit.sql.work.DbWork;
import com.persistentbit.string.UString;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 9/12/17
 */
public class PostgresDbImporterService extends GenericDbImporterService{


	@Override
	public String getDescription() {
		return "POSTGRES database import service";
	}

	@Override
	public DbHandlingLevel getHandlingLevel(DbMetaDatabase db) {
		if(db.getProductName().equals("PostgreSQL")){
			return DbHandlingLevel.full;
		}
		return DbHandlingLevel.not;
	}

	@Override
	protected DbWork<PList<DbEnumType>> loadEnumTypes(
		Supplier<DbTransaction> transSup,
		PList<DbMetaSchema> schemas,
		String rootPackage,
		DbNameTransformer nameTransformer
	){
		return DbWork.function().code(trans -> con -> log -> {
			String sql = "SELECT pg_type.typname AS enumtype,\n" +
				"     pg_enum.enumlabel AS enumlabel,\n" +
				"     pg_enum.enumsortorder as sortorder,\n" +
				"     n.nspname as schema\n" +
				" FROM pg_type\n" +
				" JOIN pg_enum ON pg_enum.enumtypid = pg_type.oid\n" +
				" join pg_catalog.pg_namespace n on n.oid = pg_type.typnamespace\n" +
				"order by schema,enumtype,sortorder";
			PMap<String,DbEnumType> enumTypes = PMap.empty();
			try(PreparedStatement stat = con.prepareStatement(sql)){

				try(ResultSet rs = stat.executeQuery()){
					while(rs.next()){
						String name = rs.getString("enumtype");
						String value = rs.getString("enumlabel");
						float sortOrder = rs.getFloat("sortorder");
						String schemaName = rs.getString("schema");
						DbMetaSchema schema = schemas.find(s -> s.getName().orElse("").equals(schemaName)).orElse(null);
						if(schema == null){
							throw new RuntimeException("Can't find schema " + schemaName + " for enum type " + name);
						}
						String fullName = schemaName + "." + name;
						DbEnumType type = enumTypes.getOrDefault(fullName,null);
						if(type == null){
							//A new type
							String packName = toJavaPackage(rootPackage,nameTransformer,schema);
							String clsName = nameTransformer.toJavaName(name);
							type = new DbEnumType(schema,name,packName,clsName);
						}
						type = type.addValue(value, UString.toJavaIdentifier(value));
						enumTypes = enumTypes.put(fullName,type);
					}

				}
			}
			return Result.success(enumTypes.values().plist());
		});
	}
	@Override
	protected DbJavaField getOtherJavaField(DbMetaColumn column, String javaName) {

		switch(column.getType().getDbTypeName().orElse(null)){
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
		}
	}

	@Override
	protected DbJavaFieldCustomObject getXmlJavaField(DbMetaColumn column, String javaName) {
		return new DbJavaFieldCustomObject(column, javaName, Xml.class);
	}

	@Override
	protected DbJavaField geDoubleDbJavaField(DbMetaColumn column, DbMetaDataType mt, String javaName) {
		if(mt.getDbTypeName().orElse("").equals("money")){
			return new DbJavaFieldCustomObject(column, javaName, Money.class);
		}

		return super.geDoubleDbJavaField(column, mt, javaName);
	}

	@Override
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

	}
}