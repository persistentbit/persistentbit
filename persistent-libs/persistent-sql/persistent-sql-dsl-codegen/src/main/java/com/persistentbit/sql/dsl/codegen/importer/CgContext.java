package com.persistentbit.sql.dsl.codegen.importer;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.ImmutableArray;
import com.persistentbit.collections.PByteList;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PMap;
import com.persistentbit.javacodegen.JClass;
import com.persistentbit.javacodegen.JJavaFile;
import com.persistentbit.javacodegen.JMethod;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.codegen.config.ColumnDef;
import com.persistentbit.sql.dsl.codegen.config.Instance;
import com.persistentbit.sql.dsl.codegen.config.SchemaDef;
import com.persistentbit.sql.dsl.codegen.config.TableDef;
import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.numbers.*;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.others.*;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.time.EDateTimeTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.time.EDateTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.time.ETimeTypeFactory;
import com.persistentbit.sql.dsl.genericdb.DbGeneric;
import com.persistentbit.sql.dsl.statements.insert.Insert;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.statements.select.impl.QueryImpl;
import com.persistentbit.sql.dsl.statements.update.Update;
import com.persistentbit.string.UString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * TODOC
 *
 * @author petermuys
 * @since 25/12/17
 */
public class CgContext{

	private final Instance instance;
	private final String   basePackage;
	private final String   dbClassName;
	private PMap<TypeRef, TypeDef> defs         = PMap.empty();
	private Class                  dbSuperClass = DbGeneric.class;
	private Class queryImplClass;
	private Class queryInterfaceClass;
	private Class insertImplClass;
	private Class updateImplClass;


	public CgContext(Instance instance) {
		this.instance = instance;
		this.basePackage = instance.getCodeGen().getRootPackage();
		this.dbClassName = instance.getJavaDbName();
		this.queryImplClass = QueryImpl.class;
		this.queryInterfaceClass = Query.class;
		this.insertImplClass = Insert.class;
		this.updateImplClass = Update.class;
		register(new SimpleTypeDef(EObject.class, Object.class, EObjectTypeFactory.class));
		register(new SimpleTypeDef(EBool.class, Boolean.class, EBoolTypeFactory.class));
		register(new SimpleTypeDef(EByte.class, Byte.class, EByteTypeFactory.class));
		register(new SimpleTypeDef(EInt.class, Integer.class, EIntTypeFactory.class));
		register(new SimpleTypeDef(EShort.class, Short.class, EShortTypeFactory.class));
		register(new SimpleTypeDef(ELong.class, Long.class, ELongTypeFactory.class));
		register(new SimpleTypeDef(EFloat.class, Float.class, EFloatTypeFactory.class));
		register(new SimpleTypeDef(EDouble.class, Double.class, EDoubleTypeFactory.class));
		register(new SimpleTypeDef(EBigDecimal.class, BigDecimal.class, EBigDecimalTypeFactory.class));
		register(new SimpleTypeDef(EString.class, String.class, EStringTypeFactory.class));
		register(new SimpleTypeDef(EDateTime.class, LocalDateTime.class, EDateTimeTypeFactory.class));
		register(new SimpleTypeDef(EDate.class, LocalDate.class, EDateTypeFactory.class));
		register(new SimpleTypeDef(ETime.class, LocalTime.class, ETimeTypeFactory.class));
		register(new SimpleTypeDef(EArray.class, ImmutableArray.class, EArrayTypeFactory.class));
		//register(new SimpleTypeDef(EBitList.class, PBitList.class, EBitList));
		register(new SimpleTypeDef(EByteList.class, PByteList.class, EByteListTypeFactory.class));

	}

	public <E1 extends DExpr<J1>, J1> CgContext register(Class<E1> exprClass, Class<J1> javaCls,
														 Class<? extends ExprTypeFactory<E1, J1>> classTypeFactory) {
		return register(new SimpleTypeDef(exprClass, javaCls, classTypeFactory));
	}

	public Class getQueryInterfaceClass() {
		return queryInterfaceClass;
	}

	public CgContext setQueryInterfaceClass(Class queryInterfaceClass) {
		this.queryInterfaceClass = queryInterfaceClass;
		return this;
	}

	public Class getQueryImplClass() {
		return queryImplClass;
	}

	public CgContext setQueryImplClass(Class queryImplClass) {
		this.queryImplClass = queryImplClass;
		return this;
	}

	public Class getInsertImplClass() {
		return insertImplClass;
	}

	public CgContext setInsertImplClass(Class insertImplClass) {
		this.insertImplClass = insertImplClass;
		return this;
	}

	public Class getUpdateImplClass() {
		return updateImplClass;
	}

	public CgContext setUpdateImplClass(Class updateImplClass) {
		this.updateImplClass = updateImplClass;
		return this;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public CgContext register(TypeDef typeDef) {
		TypeRef ref = typeDef.getRef(this);
		defs = defs.put(ref, typeDef);
		return this;
	}

	public TypeDef getTypeDef(TypeRef ref) {
		TypeDef def = defs.get(ref);
		if(def == null) {
			throw new RuntimeException("Can't find def for " + ref);
		}
		return def;
	}


	public CgContext setDbSuperClass(Class dbSuperClass) {
		this.dbSuperClass = dbSuperClass;
		return this;
	}

	private JJavaFile generateDb() {
		Map<String, JJavaFile> generated = new HashMap<>();

		for(TypeDef td : defs.values()) {
			td.init(this);
		}


		JClass dbClass = new JClass(dbClassName);
		dbClass = dbClass.addImport(ExprContext.class);
		dbClass = dbClass.extendsDef(dbSuperClass.getSimpleName()).addImport(dbSuperClass);
		//dbClass = dbClass.addField(
		//	new JField("_context", ExprContext.class)
		//		.initValue("new ExprContext()")
		//		.withAccessLevel(AccessLevel.Private)
		//		.asFinal()
		//		.asStatic()
		//).addImport(ExprContext.class);


		TypeRef      dbTypeRef = TypeRef.create(basePackage, dbClassName);
		DbGenContext dbContext = new DbGenContext(dbTypeRef, dbClass, "_context", pw -> {});

		for(TypeDef td : defs.values()) {

			dbContext = td.generateDb(this, dbContext);
		}

		JJavaFile dbFile = new JJavaFile(dbTypeRef.getPackageName())
			.addClass(dbContext.getCls()
						  .addMethod(
							  new JMethod("").withCode(dbContext.getInitCode())
								  .asStatic()

						  )
			);
		return dbFile;
	}

	public Result<PList<JJavaFile>> generateAll() {
		return Result.function().code(log -> {
			Map<String, JJavaFile> generated = new HashMap<>();

			for(TypeDef td : defs.values()) {
				td.init(this);
			}


			for(TypeDef td : defs.values()) {
				PList<JJavaFile> tdGen = td.generate(this).orElseThrow();
				tdGen.forEach(jj -> {
					jj.getClasses()
						.forEach(cls ->
									 generated.put(jj.getPackageName() + "." + cls.getClassName(), jj)
						);

				});

			}


			return Result.success(PList.from(generated.values())
									  .plus(generateDb()));
		});

	}

	public String catalogNameToJava(@Nullable String catName) {

		return catName == null ? "catalog" : instance.getNameConversionType().transformer().apply(catName)
			.toLowerCase();
	}

	public String schemaNameToJava(@Nullable String catName, @Nullable String schemaName) {
		return schemaName == null ? "schema" : instance.getNameConversionType().transformer().apply(schemaName)
			.toLowerCase();
	}


	private Optional<SchemaDef> findSchemaDef(CgTableName tableName) {
		return instance.getSchemas()
			.find(sd -> sd.getCatalogName().equals(tableName.getCatalogName()) && sd.getSchemaName()
				.equals(tableName.getSchemaName()));
	}

	private Optional<TableDef> findTableDef(CgTableName tableName) {
		return findSchemaDef(tableName)
			.flatMap(schemaDef -> schemaDef.getTables()
				.find(t -> t.getTableName().equals(tableName.getTableName().orElse(null))));
	}

	private Optional<ColumnDef> findColumnDef(CgTableName tableName, String columnName) {
		return findTableDef(tableName)
			.flatMap(td -> td.getColumns().find(cd -> cd.getColumnName().equals(columnName)));
	}

	public String tableNameToJava(CgTableName tableName) {

		TableDef tableDef = findTableDef(tableName).orElse(null);

		if(tableDef != null && tableDef.getJavaName().isPresent()) {
			return tableDef.getJavaName().get();
		}

		return instance.getNameConversionType().transformer().apply(tableName.getTableName().get().trim());
	}

	public String columnNameToJava(CgTableName tableName, String columnName) {
		return findColumnDef(tableName, columnName)
			.map(cd -> cd.getJavaName())
			.orElse(instance.getNameConversionType().fieldTransformer().apply(columnName));
	}


	public TypeRef createTableTypeRef(CgTableName tableName) {

		return TypeRef.create("tables",
							  "T" + tableNameToJava(tableName)
		);
	}

	public TypeRef createExprTypeRef(CgTableName tableName) {

		return TypeRef.create("expressions",
							  "E" + tableNameToJava(tableName)
		);
	}

	public TypeRef createValueTypeRef(CgTableName tableName) {

		return TypeRef.create("values",
							  tableNameToJava(tableName)
		);
	}

	public TypeRef createTypeFactoryTypeRef(CgTableName tableName) {

		return TypeRef.create("impl.typefactories",
							  tableNameToJava(tableName) + "TypeFactory"
		);
	}

	public TypeRef createInsertTypeRef(CgTableName tableName) {

		return TypeRef.create("inserts",
							  "Insert" + tableNameToJava(tableName)
		);
	}

	public String tableNameToJavaInstanceName(CgTableName tableName) {
		return UString.firstLowerCase(tableNameToJava(tableName));
	}
}
