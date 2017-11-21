package com.persistentbit.sql.transactions;

import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.result.Result;
import com.persistentbit.sql.transactions.impl.DbTransactionImpl;

import java.sql.Connection;
import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 21/11/17
 */
public interface DbTransaction{


	<R> Result<R> run(ThrowingFunction<Connection, Result<R>, Exception> code);

	DbTransaction newTransaction();
	DbTransaction nestedTransaction();
	DbTransaction readOnly();

	static DbTransaction	create(Supplier<Result<Connection>> connectionSupplier){
		return new DbTransactionImpl(connectionSupplier,false);
	}
}
