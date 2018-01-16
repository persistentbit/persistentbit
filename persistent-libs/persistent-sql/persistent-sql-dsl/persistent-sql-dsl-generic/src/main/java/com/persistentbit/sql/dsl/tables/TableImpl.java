package com.persistentbit.sql.dsl.tables;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.sql.dsl.Sql;
import com.persistentbit.sql.dsl.expressions.DExpr;

/**
 * TODOC
 *
 * @author petermuys
 * @since 19/12/17
 */
public interface TableImpl<EALL extends DExpr<J>,J> extends Table<EALL,J>{

	Class<EALL> getTypeClass();

	Sql getFromName(@Nullable String defaultCatalog, @Nullable String defaultSchema);


}
