package com.persistentbit.sql.dsl.codegen.h2;

import com.persistentbit.sql.dsl.codegen.DbHandlingLevel;
import com.persistentbit.sql.dsl.codegen.generic.GenericDbJavaGenService;
import com.persistentbit.sql.dsl.exprcontext.DbContext;
import com.persistentbit.sql.dsl.generic_old.DbGeneric;
import com.persistentbit.sql.dsl.h2.rt.DbH2;
import com.persistentbit.sql.dsl.h2.rt.H2DbContext;
import com.persistentbit.sql.meta.data.DbMetaDatabase;


/**
 * TODOC
 *
 * @author petermuys
 * @since 11/12/17
 */
public class H2DbJavaGenService extends GenericDbJavaGenService{

	@Override
	public DbHandlingLevel getHandlingLevel(DbMetaDatabase db
	) {
		if(db.getProductName().equals("H2")){
			return DbHandlingLevel.full;
		}
		return DbHandlingLevel.not;
	}

	@Override
	public String getDescription() {
		return "H2 Database Code generator";
	}


	protected Class<? extends DbGeneric>	getDbClass(boolean isFullDb){
		return isFullDb ? DbH2.class : super.getDbClass(isFullDb);
	}
	protected Class<? extends DbContext> getContextClass(boolean isFullDb){
		return isFullDb ? H2DbContext.class : super.getContextClass(isFullDb);
	}
}
