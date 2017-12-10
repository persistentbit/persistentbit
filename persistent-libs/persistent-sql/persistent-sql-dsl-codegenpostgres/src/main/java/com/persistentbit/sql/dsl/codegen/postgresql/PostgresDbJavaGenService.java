package com.persistentbit.sql.dsl.codegen.postgresql;

import com.persistentbit.sql.dsl.codegen.DbHandlingLevel;
import com.persistentbit.sql.dsl.codegen.generic.GenericDbJavaGenService;
import com.persistentbit.sql.meta.data.DbMetaDatabase;

/**
 * TODOC
 *
 * @author petermuys
 * @since 10/12/17
 */
public class PostgresDbJavaGenService extends GenericDbJavaGenService{

	@Override
	public DbHandlingLevel getHandlingLevel(DbMetaDatabase db
	) {
		if(db.getProductName().equals("PostgreSQL")){
			return DbHandlingLevel.full;
		}
		return DbHandlingLevel.not;
	}

	@Override
	public String getDescription() {
		return "Postgresql Database Code generator";
	}

}
