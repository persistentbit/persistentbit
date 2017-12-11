package com.persistentbit.sql.dsl.codegen.h2;

import com.persistentbit.sql.dsl.codegen.DbHandlingLevel;
import com.persistentbit.sql.dsl.codegen.generic.GenericDbImporterService;
import com.persistentbit.sql.meta.data.DbMetaDatabase;

/**
 * TODOC
 *
 * @author petermuys
 * @since 11/12/17
 */
public class H2DbImporterService extends GenericDbImporterService{


	@Override
	public String getDescription() {
		return "H2 database import service";
	}

	@Override
	public DbHandlingLevel getHandlingLevel(DbMetaDatabase db) {
		if(db.getProductName().equals("H2")) {
			return DbHandlingLevel.full;
		}
		return DbHandlingLevel.not;
	}


}
