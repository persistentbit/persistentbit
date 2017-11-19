package com.persistentbit.glasgolia.db.transactions;

import com.persistentbit.result.Result;
import com.persistentbit.sql.connect.DbConnector;

import java.sql.Connection;

/**
 * TODOC
 *
 * @author petermuys
 * @since 12/01/17
 */
public interface DbTransaction{

	DbTransaction createNew();

	Result<Connection> get();

	static DbTransaction	create(DbConnector connector){
		return new DbTransactionImpl(connector);
	}
}
