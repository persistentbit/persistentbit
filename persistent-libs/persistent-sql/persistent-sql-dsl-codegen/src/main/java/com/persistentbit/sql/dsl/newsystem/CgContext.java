package com.persistentbit.sql.dsl.newsystem;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PMap;
import com.persistentbit.javacodegen.JJavaFile;
import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.sql.dsl.expressions.EDateTime;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.string.UString;

import java.time.LocalDateTime;
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
	private PMap<TypeRef, TypeDef> defs = PMap.empty();


	public CgContext(String basePackage) {
		this.basePackage = basePackage;
		register(new SimpleTypeDef(EBool.class, Boolean.class));
		register(new SimpleTypeDef(EString.class, String.class));
		register(new SimpleTypeDef(EDateTime.class, LocalDateTime.class));
		register(new SimpleTypeDef(ELong.class, Long.class));

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
		return PList.from(generated.values());
	}

	public String catalogNameToJava(@Nullable String catName) {
		return catName == null ? "catalog" : catName.toLowerCase();
	}

	public String schemaNameToJava(@Nullable String catName, @Nullable String schemaName) {
		return schemaName == null ? "schema" : schemaName.toLowerCase();
	}

	public String tableNameToJava(@Nullable String catlogName, @Nullable String schemaName, String tableName) {
		return UString.firstUpperCase(UString.snake_toCamelCase(tableName));
	}

	public String columnNameToJava(CgTableName tableName, String columnName
	) {
		return UString.snake_toCamelCase(columnName);
	}


	public TypeRef createTableTypeRef(CgTableName tableName) {
		String catalog = tableName.getCatalogName().orElse(null);
		String schema  = tableName.getSchemaName().orElse(null);
		String table   = tableName.getTableName().orElse(null);
		return TypeRef.create(
			catalogNameToJava(catalog)
				+ "." + schemaNameToJava(catalog, schema),
			"T" + tableNameToJava(catalog, schema, table)
		);
	}

	public TypeRef createExprTypeRef(CgTableName tableName) {
		String catalog = tableName.getCatalogName().orElse(null);
		String schema  = tableName.getSchemaName().orElse(null);
		String table   = tableName.getTableName().orElse(null);
		return TypeRef.create(
			catalogNameToJava(catalog)
				+ "." + schemaNameToJava(catalog, schema),
			"E" + tableNameToJava(catalog, schema, table)
		);
	}

	public TypeRef createValueTypeRef(CgTableName tableName) {
		String catalog = tableName.getCatalogName().orElse(null);
		String schema  = tableName.getSchemaName().orElse(null);
		String table   = tableName.getTableName().orElse(null);
		return TypeRef.create(
			catalogNameToJava(catalog)
				+ "." + schemaNameToJava(catalog, schema),
			tableNameToJava(catalog, schema, table)
		);
	}

	public TypeRef createTypeFactoryTypeRef(CgTableName tableName) {
		String catalog = tableName.getCatalogName().orElse(null);
		String schema  = tableName.getSchemaName().orElse(null);
		String table   = tableName.getTableName().orElse(null);
		return TypeRef.create(
			catalogNameToJava(catalog)
				+ "." + schemaNameToJava(catalog, schema),
			tableNameToJava(catalog, schema, table) + "TypeFactory"
		);
	}

	public TypeRef createInsertTypeRef(CgTableName tableName) {
		String catalog = tableName.getCatalogName().orElse(null);
		String schema  = tableName.getSchemaName().orElse(null);
		String table   = tableName.getTableName().orElse(null);
		return TypeRef.create(
			catalogNameToJava(catalog)
				+ "." + schemaNameToJava(catalog, schema),
			"Insert" + tableNameToJava(catalog, schema, table)
		);
	}
}
