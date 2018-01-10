package com.persistentbit.sql.dsl.codegen.h2;

import com.persistentbit.sql.dsl.codegen.config.Instance;
import com.persistentbit.sql.dsl.codegen.importer.GenericDbImporter;
import com.persistentbit.sql.transactions.DbTransaction;

import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 3/01/18
 */
public class H2DbImporter extends GenericDbImporter{

	public H2DbImporter(Instance instance,
						Supplier<DbTransaction> transSup) {
		super(instance, transSup);
	}
}
