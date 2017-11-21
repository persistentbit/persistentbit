package com.persistentbit.sql.connect;


import com.persistentbit.result.Result;
import com.persistentbit.sql.connect.impl.DbDatasourceConnector;
import com.persistentbit.sql.connect.impl.DbPoolConnector;
import com.persistentbit.sql.connect.impl.DbSimpleConnector;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * A Supplier of a database {@link Connection}
 *
 * @author petermuys
 * @since 2/06/17
 * @see DbDatasourceConnector
 * @see DbPoolConnector
 * @see DbSimpleConnector
 */
@FunctionalInterface
public interface DbConnector extends Supplier<Result<Connection>>{


	/**
	 * Create a new Database connection.<br>
	 * AutoCommit on the connection should be disabled.<br>
	 *
	 * @return The Result with the new Connection.
	 */
	Result<Connection> get();

	static DbConnector fromDataSource(DataSource dataSource){
		return new DbDatasourceConnector(dataSource);
	}

	static Result<DbConnector> fromUrl(String driverClass, String url, String userName, String passWord){
		return DbSimpleConnector.create(driverClass,url,userName,passWord).map(t->t);
	}

	default DbConnector pooledConnector(int poolSize,Consumer<Connection> connectionResetter){
		return new DbPoolConnector(this,poolSize, connectionResetter);
	}

	default DbConnector pooledConnector(int poolSize){
		return new DbPoolConnector(this,poolSize);
	}
}
