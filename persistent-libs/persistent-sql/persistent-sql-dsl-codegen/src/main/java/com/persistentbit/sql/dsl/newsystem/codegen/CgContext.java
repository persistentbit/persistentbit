package com.persistentbit.sql.dsl.newsystem.codegen;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PByteList;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PMap;
import com.persistentbit.javacodegen.*;
import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.numbers.*;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.others.EBoolTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.others.EByteListTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.others.EStringTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.time.EDateTimeTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.time.EDateTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.time.ETimeTypeFactory;
import com.persistentbit.string.UString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * TODOC
 *
 * @author petermuys
 * @since 25/12/17
 */
public class CgContext{

	private final String basePackage;
	private final String dbClassName;
	private PMap<TypeRef, TypeDef> defs = PMap.empty();


	public CgContext(String basePackage, String dbClassName) {
		this.basePackage = basePackage;
		this.dbClassName = dbClassName;
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
		//register(new SimpleTypeDef(EBitList.class, PBitList.class, EBitList));
		register(new SimpleTypeDef(EByteList.class, PByteList.class, EByteListTypeFactory.class));

	}

	public CgContext(String basePackage) {
		this(basePackage, "Db");
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

	private JJavaFile generateDb() {
		Map<String, JJavaFile> generated = new HashMap<>();

		for(TypeDef td : defs.values()) {
			td.init(this);
		}


		JClass dbClass = new JClass(dbClassName);
		dbClass = dbClass.addField(
			new JField("_context", ExprContext.class)
				.initValue("new ExprContext()")
				.withAccessLevel(AccessLevel.Private)
				.asFinal()
				.asStatic()
		).addImport(ExprContext.class);


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

	public PList<JJavaFile> generateAll() {
		Map<String, JJavaFile> generated = new HashMap<>();

		for(TypeDef td : defs.values()) {
			td.init(this);
		}


		for(TypeDef td : defs.values()) {
			PList<JJavaFile> tdGen = td.generate(this);
			tdGen.forEach(jj -> {
				jj.getClasses()
					.forEach(cls ->
								 generated.put(jj.getPackageName() + "." + cls.getClassName(), jj)
					);

			});

		}


		return PList.from(generated.values())
			.plus(generateDb());
	}

	public String catalogNameToJava(@Nullable String catName) {
		return catName == null ? "catalog" : catName.trim().toLowerCase();
	}

	public String schemaNameToJava(@Nullable String catName, @Nullable String schemaName) {
		return schemaName == null ? "schema" : schemaName.trim().toLowerCase();
	}

	public String tableNameToJava(CgTableName tableName) {
		return UString.firstUpperCase(UString.snake_toCamelCase(tableName.getTableName().get().trim()));
	}

	public String columnNameToJava(CgTableName tableName, String columnName
	) {

		return UString.snake_toCamelCase(columnName.trim());
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
