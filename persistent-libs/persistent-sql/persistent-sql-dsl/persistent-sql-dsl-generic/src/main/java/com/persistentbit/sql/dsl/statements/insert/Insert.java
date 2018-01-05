package com.persistentbit.sql.dsl.statements.insert;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.tables.Table;
import com.persistentbit.sql.transactions.DbTransaction;
import com.persistentbit.sql.work.DbWork;
import com.persistentbit.utils.Lazy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/12/17
 */
public abstract class Insert<T extends Table, AUTOGENKEY> implements DbWork<PList<InsertResult<AUTOGENKEY>>>{

	protected final ExprContext                      context;
	protected final T                                into;
	protected final PList<String>                    columnNames;
	protected final PSet<String>                     withDefaults;
	@Nullable
	protected final String                           autoGenKeyName;
	protected final PList<Object[]>                  rows;
	//private final   Lazy<String>                     sql;
	private final   Lazy<PList<ExprTypeJdbcConvert>> jdbcConverters;
	private final   Lazy<ExprTypeJdbcConvert>        autoGenKeyJdbcConverter;

	protected Insert(ExprContext context, T into,
					 PList<String> columnNames,
					 PSet<String> withDefaults,
					 String autoGenKeyName, PList<Object[]> rows
	) {
		this.context = context;
		this.into = into;
		this.columnNames = columnNames;
		this.withDefaults = withDefaults;
		this.autoGenKeyName = autoGenKeyName;
		this.rows = rows;

		this.jdbcConverters = Lazy.code(() ->
											context.getJdbcConverter(into.all()).expand()
		);
		this.autoGenKeyJdbcConverter = Lazy.code(() -> {
			if(autoGenKeyName == null) {
				return null;
			}
			for(int t = 0; t < columnNames.size(); t++) {
				if(columnNames.get(t).equals(autoGenKeyName)) {
					return jdbcConverters.get().get(t);
				}
			}
			throw new RuntimeException("Can't find autogen key '" + autoGenKeyName + "' in " + columnNames);
		});
	}


	@Override
	public Result<PList<InsertResult<AUTOGENKEY>>> run(DbTransaction transaction) {
		return Result.function().code(log -> {
			if(rows.isEmpty()) {
				return Result.empty("No Records added to insert ");
			}
			//String sql = this.sql.get();
			//log.info("Insert: " + sql);
			int statType = autoGenKeyName == null
				? Statement.NO_GENERATED_KEYS
				: Statement.RETURN_GENERATED_KEYS;

			return transaction.run(con -> {
				PList<InsertResult<AUTOGENKEY>> result = PList.empty();

				for(int r = 0; r < rows.size(); r++) {
					Object[] row             = rows.get(r);
					String   sql             = "INSERT INTO " + context.getFromTableName(into) + "(";
					String   names           = "";
					String   values          = "";
					boolean  isFirst         = true;
					int      autoGenKeyIndex = -1;
					for(int t = 0; t < row.length; t++) {

						String  name      = columnNames.get(t);
						boolean isAutoGen = autoGenKeyName != null && autoGenKeyName.equals(name);
						if(isAutoGen == false) {
							if(row[t] != null) {
								if(isFirst == false) {
									names += ", ";
									values += ", ";
								}
								isFirst = false;

								names += name;
								values += "?";
							}
						}
						else {
							autoGenKeyIndex = t;
						}

					}

					int index = 1;
					sql = sql + names + ") VALUES (" + values + ")";
					log.info("Insert: " + sql + " values " + row);
					try(PreparedStatement stat = con.prepareStatement(sql, statType)) {
						for(int c = 0; c < row.length; c++) {
							if(row[c] != null && c != autoGenKeyIndex) {
								ExprTypeJdbcConvert jdbcConvert = jdbcConverters.get().get(c);
								//log.info("Setting Param " + index + " with value + " + row[c]);
								jdbcConvert.setParam(index, stat, row[c]);
								index += jdbcConvert.columnCount();
							}
						}
						int    updateCount = stat.executeUpdate();
						Object keyValue    = null;
						if(autoGenKeyName != null) {
							try(ResultSet generatedKeys = stat.getGeneratedKeys()) {
								if(generatedKeys.next()) {
									keyValue = autoGenKeyJdbcConverter.get().read(1, generatedKeys);

								}
							}
						}
						result = result.plus(new InsertResult(updateCount, keyValue));
					}

				}


				return Result.success(result);
			});


		});

	}
}
