package com.persistentbit.sql.dsl.codegen.postgresql;

import com.persistentbit.javacodegen.JField;
import com.persistentbit.sql.dsl.codegen.DbHandlingLevel;
import com.persistentbit.sql.dsl.codegen.generic.GenericDbJavaGenService;
import com.persistentbit.sql.dsl.exprcontext.DbContext;
import com.persistentbit.sql.dsl.generic_old.DbGeneric;
import com.persistentbit.sql.dsl.postgres.rt.DbPostgres;
import com.persistentbit.sql.dsl.postgres.rt.PostgresDbContext;
import com.persistentbit.sql.dsl.postgres.rt.customtypes.Xml;
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

	@Override
	protected JField createXmlJField(String fname) {
		return new JField(fname, Xml.class);
	}
	protected Class<? extends DbGeneric>	getDbClass(boolean isFullDb){
		return isFullDb ? DbPostgres.class : super.getDbClass(isFullDb);
	}
	protected Class<? extends DbContext> getContextClass(boolean isFullDb){
		return isFullDb ? PostgresDbContext.class : super.getContextClass(isFullDb);
	}
}
