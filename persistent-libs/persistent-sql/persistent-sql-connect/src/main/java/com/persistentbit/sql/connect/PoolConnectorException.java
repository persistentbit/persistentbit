package com.persistentbit.sql.connect;

/**
 * TODOC
 *
 * @author petermuys
 * @since 19/11/17
 */
public class PoolConnectorException extends RuntimeException{

	public PoolConnectorException() {
	}

	public PoolConnectorException(String message) {
		super(message);
	}

	public PoolConnectorException(String message, Throwable cause) {
		super(message, cause);
	}

	public PoolConnectorException(Throwable cause) {
		super(cause);
	}

	public PoolConnectorException(String message, Throwable cause, boolean enableSuppression,
								  boolean writableStackTrace
	) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
