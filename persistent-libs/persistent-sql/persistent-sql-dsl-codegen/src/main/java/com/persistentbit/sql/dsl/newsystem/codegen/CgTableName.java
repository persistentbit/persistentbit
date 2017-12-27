package com.persistentbit.sql.dsl.newsystem.codegen;

import com.persistentbit.code.annotations.Nullable;

import java.util.Optional;

/**
 * TODOC
 *
 * @author petermuys
 * @since 26/12/17
 */
public class CgTableName{

	@Nullable
	private final String catalogName;
	@Nullable
	private final String schemaName;
	@Nullable
	private final String tableName;

	public CgTableName(
		@Nullable
			String catalogName,
		@Nullable
			String schemaName,
		@Nullable
			String tableName
	) {
		this.catalogName = catalogName;
		this.schemaName = schemaName;
		this.tableName = tableName;
	}

	public CgTableName(String tableName) {
		this(null, null, tableName);
	}

	Optional<String> getCatalogName() {
		return Optional.ofNullable(catalogName);
	}

	Optional<String> getSchemaName() {
		return Optional.ofNullable(schemaName);
	}

	Optional<String> getTableName() {
		return Optional.ofNullable(tableName);
	}


}
