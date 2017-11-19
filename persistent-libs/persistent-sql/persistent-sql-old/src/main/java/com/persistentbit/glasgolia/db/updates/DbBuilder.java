package com.persistentbit.glasgolia.db.updates;

import com.persistentbit.glasgolia.db.work.DbWork;
import com.persistentbit.result.OK;

import java.sql.Connection;

/**
 * Class used to create, update or drop all tables
 * in a database.<br>
 *
 * @author petermuys
 * @since 31/10/16
 */
public interface DbBuilder{

	/**
	 * Execute all the database update methods not registered in the SchemaHistory table.<br>
	 * If there is a declared method in this class with the same name,
	 * then that method is executed with a {@link Connection} as argument.<br>
	 * @return executed ok?
	 */
	DbWork<OK> buildOrUpdate();

	/**
	 * Drop all tables, views,... created by buildOrUpdate
	 *
	 * @return executed ok?
	 */
	DbWork<OK> dropAll();

	/**
	 * Check if there is at least 1  update done for this builder.<br>
	 *
	 * @return true if 1 update is done for this builder
	 */
	DbWork<Boolean> hasUpdatesThatAreDone();

	/**
	 * If there are any updates done: drop all and rebuild
	 *
	 * @return succes or failure
	 */
	default DbWork<OK> resetDb() {
		return DbWork.function().code(l -> ctx ->
			hasUpdatesThatAreDone().execute(ctx)
			.flatMapExc(hasUpdates -> {
				l.info("Do we have updates? " + hasUpdates);

			   	if(hasUpdates) {
					l.info("Now rebuild all..");
				   	l.info("Let's drop all");
				   	l.add(dropAll().execute(ctx)).orElseThrow();
			   	} else {
			   		l.info("No Updates...");
				}
				return buildOrUpdate().execute(ctx);
		   }));

	}

}
