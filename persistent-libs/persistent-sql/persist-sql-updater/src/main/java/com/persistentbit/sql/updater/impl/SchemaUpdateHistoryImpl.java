package com.persistentbit.sql.updater.impl;


import com.persistentbit.collections.PList;
import com.persistentbit.result.OK;
import com.persistentbit.result.Result;
import com.persistentbit.sql.updater.SchemaUpdateHistory;
import com.persistentbit.sql.work.DbWork;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Implements A {@link SchemaUpdateHistory} interface by using a db table<br>
 */
public class SchemaUpdateHistoryImpl implements SchemaUpdateHistory{

	private final String schemaName;
	private final String tableName;


	/**
	 * Creates an instance with 'schema_history' as table name and no schema name
	 */
	public SchemaUpdateHistoryImpl(String schemaName) {
		this(schemaName,"schema_history");
	}


	/**
	 * @param tableName The table name for the schema history table.
	 */
	public SchemaUpdateHistoryImpl(String schemaName, String tableName) {
		this.schemaName = schemaName;
		this.tableName = tableName;
	}


	@Override
	public DbWork<Boolean> isDone(String packageName, String updateName) {
		return DbWork.create(trans -> con -> Result.function(packageName,updateName).code(l ->
			createTableIfNotExist().run(trans)
				.flatMapExc(ok -> {
					String sql = "select count(1) from " + tableName +
						" where package_name=?  and update_name=?";
					l.info("Executing " + sql);
					try(PreparedStatement stat = con.prepareStatement(sql)) {
						stat.setString(1, packageName);
						stat.setString(2, updateName);
						try(ResultSet rs = stat.executeQuery()) {
							rs.next();
							return Result.success(rs.getInt(1));
						}
					}

				})
				.map(count -> count > 0)

		));

	}

	private DbWork<OK> createTableIfNotExist() {
		return DbWork.create(trans -> con -> Result.function().code(l ->
			schemaHistoryTableExists().run(trans)
				.flatMapExc(exists -> {
					if(exists == false) {
						try(Statement stat = con.createStatement()) {
							stat.execute("CREATE TABLE " + tableName + " (" +
								"  createdDate TIMESTAMP          NOT NULL DEFAULT current_timestamp," +
								"  package_name  VARCHAR(80)        NOT NULL," +
								"  update_name  VARCHAR(80)        NOT NULL," +
								"  CONSTRAINT " + tableName + "_uc UNIQUE (package_name,update_name)" +
								")");
						}
						return OK.result;

					}
					return OK.result;
				})

		));

	}

	private DbWork<Boolean> schemaHistoryTableExists() {
		return DbWork.create(trans -> con -> Result.function().code(l ->
			Result.success(
				tableExists(tableName).run(trans).orElseThrow() ||
					tableExists(tableName.toLowerCase()).run(trans).orElseThrow() ||
					tableExists(tableName.toUpperCase()).run(trans).orElseThrow()
			)
		));

	}

	private DbWork<Boolean> tableExists(String tableName) {
		return DbWork.create(trans -> con -> Result.function().code(l -> {
			DatabaseMetaData dbm = con.getMetaData();

			try(ResultSet rs = dbm.getTables(null, schemaName, tableName, null)) {
				while(rs.next()) {
					String tn = rs.getString("table_name");
					if(tableName.equalsIgnoreCase(tableName)) {
						return Result.success(true);
					}
				}
				return Result.success(false);
			}
		}));

	}

	@Override
	public DbWork<PList<String>> getUpdatesDone(String packageName) {
		return DbWork.create(trans -> con -> Result.function().code(l ->
			schemaHistoryTableExists().run(trans)
				.flatMapExc(exists -> {
					PList<String> result = PList.empty();
					if(exists) {
						try(PreparedStatement stat = con.prepareStatement("select update_name from " +
							tableName + " where package_name=?")) {

							stat.setString(1, packageName);

							try(ResultSet rs = stat
								.executeQuery()) {
								while(rs.next()) {
									result = result
										.plus(rs.getString(1));
								}

							}
						}
					}
					return Result.success(result);
				})
		));


	}


	@Override
	public DbWork<OK> setDone(String packageName, String updateName) {
		DbWork<OK> insert = DbWork.create(trans -> con -> {
			String sql = "insert into " + tableName + "(package_name,update_name) values(?,?)";
			try(PreparedStatement s = con.prepareStatement(sql)) {
				s.setString(1, packageName);
				s.setString(2, updateName);
				if(s.executeUpdate() != 1) {
					return Result
						.failure(new RuntimeException("Expected 1 update for " + packageName + "." + updateName));
				}
			}
			return OK.result;
		});
		return DbWork.create(trans -> con -> Result.function(packageName, updateName).code(l ->
				createTableIfNotExist().run(trans)
				  .flatMap(ok -> insert.run(trans))
		));
	}

	@Override
	public DbWork<OK> removeUpdateHistory(String packageName) {
		DbWork<OK> delete = DbWork.create(trans -> con ->  {
			String sql = "delete from " + tableName + " where package_name = ?";
			try(PreparedStatement s = con.prepareStatement(sql)) {
				s.setString(1, packageName);
				s.executeUpdate();
			}
			try(PreparedStatement s = con.prepareStatement("select count(1) from " + tableName)) {
				int count = 0;
				try(ResultSet rs = s.executeQuery()) {
					rs.next();
					count = rs.getInt(1);
				}
				if(count == 0) {
					try(PreparedStatement ds = con.prepareStatement("drop table " + tableName)) {
						ds.executeUpdate();
					}
				}

			}
			return OK.result;

		});
		return DbWork.create(trans -> con -> Result.function(packageName).code(l ->
			schemaHistoryTableExists().run(trans)
				.flatMap(exists -> {
					if(exists) {
						return delete.run(trans);
					}
					return OK.result;
				})
		));
	}
}

