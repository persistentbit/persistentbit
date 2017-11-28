package com.persistentbit.sql.dsl.codegen.posgresql;

import com.persistentbit.collections.PList;
import com.persistentbit.javacodegen.JField;
import com.persistentbit.sql.meta.data.DbMetaColumn;

/**
 * TODOC
 *
 * @author petermuys
 * @since 15/07/17
 */

public interface DbJavaField{
	JField createJField();
	JField createTableColumnField();
	String createTableColumnFieldInitializer();
	DbMetaColumn	getDbMetaColumn();
	String getJavaName();

	default PList<DbJavaFieldEnum> getUsedEnums() {
		return PList.empty();
	}
	default PList<DbJavaFieldStruct> getStructures() {
		return PList.empty();
	}
	default PList<DbJavaFieldDomain> getDomains(){
		return PList.empty();
	}
}
