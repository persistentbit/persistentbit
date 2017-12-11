package com.persistentbit.sql.transactions.impl;

import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.logging.LogCollector;
import com.persistentbit.result.Result;
import com.persistentbit.sql.transactions.DbTransaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 21/11/17
 */
public class DbTransactionImpl implements DbTransaction{
	private final Supplier<Result<Connection>> connectionSupplier;
	private Connection currentConnection;
	private LogCollector	log;
	private Result failure;
	private boolean readOnly;

	public DbTransactionImpl(Supplier<Result<Connection>> connectionSupplier, boolean readOnly){
		this.connectionSupplier = connectionSupplier;
		this.readOnly = readOnly;
	}

	@Override
	public DbTransaction readOnly() {
		return new DbTransactionImpl(connectionSupplier,true);
	}

	@Override
	public <R> Result<R> run(ThrowingFunction<Connection, Result<R>, Exception> code) {
		if(failure != null){
			//We have an error on this
			return failure.map(t -> null);
		}
		if(currentConnection == null){
			//Run in a new initial Transaction...
			return doTransaction(code);

		} else {
			//Run in existing transaction
			return doExistingTransaction(code);

		}

	}

	private <R> Result<R> doExistingTransaction(ThrowingFunction<Connection,Result<R>, Exception> code){
		return Result.function().code(l -> {
			try{
				l.info("Starting in existing transaction");
				Result<R> res = code.apply(currentConnection);
				l.info("Done in existing transaction");
				return res;

			}catch(Exception e){
				Result<R> newFail = Result.failure(new RuntimeException("Transaction rollback",e));
				close();

				failure = newFail;
				return newFail;
			}
		});
	}

	private <R> Result<R> doTransaction(ThrowingFunction<Connection, Result<R>, Exception> code) {

		return Result.function().code(l -> {
			l.info("Starting new Transaction");

			Result<Connection> resCon = l.add(connectionSupplier.get());
			if(resCon.isPresent() == false){
				failure = resCon;
				return failure.map(t -> null);
			}

			currentConnection = resCon
				.mapExc(con -> {
					con.setAutoCommit(false);
					con.setReadOnly(readOnly);
					return con;
				}).orElseThrow();

			Result<R> codeResult = l.add(code.apply(currentConnection));

			if(codeResult.isError()){
				l.warning("Transaction rolled back");
				currentConnection.rollback();
			} else {
				l.info("Transaction committed");
				currentConnection.commit();
				//currentConnection = null;
			}
			close();
			return codeResult;
		});
	}
	private void close(){
		if(currentConnection != null){
			try {
				currentConnection.close();

			} catch(SQLException e) {
				;
			}
		}
	}

	@Override
	public DbTransaction newTransaction() {
		return new DbTransactionImpl(connectionSupplier,readOnly);
	}

	@Override
	public DbTransaction nestedTransaction() {
		throw new RuntimeException("todo");
	}
}
