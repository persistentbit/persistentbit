package com.persistentbit.sql.connect.impl;


import com.persistentbit.result.Result;
import com.persistentbit.sql.connect.DbConnector;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Objects;

/**
 * {@link Connection} supplier that used a {@link DataSource} to create new connections
 *
 * @author Peter Muys
 * @see DbConnector
 */
public class DbDatasourceConnector implements DbConnector{

	private final DataSource dataSource;

	public DbDatasourceConnector(DataSource dataSource) {
		this.dataSource = Objects.requireNonNull(dataSource);
	}


	@Override
	public Result<Connection> get() {
		return Result.function().code(l -> {
			Connection c = dataSource.getConnection();
			c.setAutoCommit(false);
			return Result.success(c);
		});
	}

}
