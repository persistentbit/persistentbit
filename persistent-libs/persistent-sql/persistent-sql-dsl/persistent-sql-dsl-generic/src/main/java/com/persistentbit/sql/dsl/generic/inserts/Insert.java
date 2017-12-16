package com.persistentbit.sql.dsl.generic.inserts;

import com.persistentbit.collections.PList;
import com.persistentbit.collections.POrderedSet;
import com.persistentbit.collections.PStream;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.exprcontext.DbContext;
import com.persistentbit.sql.dsl.exprcontext.DbSqlContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprTable;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImpl;
import com.persistentbit.sql.dsl.generic.expressions.impl.DImplTable;
import com.persistentbit.sql.dsl.generic.expressions.impl.DInternal;
import com.persistentbit.sql.dsl.generic.query.impl.SqlWithParams;
import com.persistentbit.sql.transactions.DbTransaction;
import com.persistentbit.sql.utils.rowreader.ResultSetRowReader;
import com.persistentbit.sql.work.DbWork;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

/**
 * TODOC
 *
 * @author petermuys
 * @since 5/12/17
 */
public class Insert implements DbWork<InsertResult>{
	private final DbContext dbContext;
	private final DExprTable	into;
	private final PList<String> columnNames;
	private final POrderedSet<DExpr> autoGenColumns;
	private final PList<PList<DExpr>>  rows;

	private Insert(
		DbContext dbContext,
		DExprTable into,
		PList<String> columnNames,
		POrderedSet<DExpr> autoGenColumns,
		PList<PList<DExpr>> rows
	){
		this.dbContext = Objects.requireNonNull(dbContext);
		this.into = Objects.requireNonNull(into);
		this.columnNames = Objects.requireNonNull(columnNames);
		this.autoGenColumns = Objects.requireNonNull(autoGenColumns);
		this.rows = rows;
	}
	public Insert(DbContext dbContext, DExprTable into, PList<String> columnNames){
		this(dbContext,into,columnNames,POrderedSet.empty().empty(),PList.empty());
	}
	public Insert(DbContext dbContext, DExprTable into, DExpr...columns){
		this(dbContext,into,
			PList.val(columns)
				 .map(fc -> ((DInternal<?>)DImpl._get(fc))._expand())
				 .map(ecl -> ecl.map(ec -> DImpl._get(ec)._getColumnName()))
				.<String>flatten().plist()
		);
	}
	public Insert(DbContext dbContext, DExprTable into){
		this(dbContext,into,into.all());
	}

	public Insert	withAutoGenerated(DExpr...autoGenerated){
		return new Insert(
			dbContext,
			into,
			columnNames,
			PStream.val(autoGenerated).map(e -> DImpl._get(e)._expand()).<DExpr>flatten().porderedset(),
			rows
		);
	}

	public Insert row(DExpr...rowValues){
		PList<DExpr> r = PList.val(rowValues).map(c -> DImpl._get(c)._expand()).<DExpr>flatten().plist();
		if(r.size() != columnNames.size()){
			throw new RuntimeException("Can't insert " + r + " in " + columnNames);
		}
		return new Insert(dbContext,into,columnNames,autoGenColumns,rows.plus(r));
	}

	@Override
	public Result<InsertResult> run(DbTransaction transaction) {
		if(rows.isEmpty()){
			return Result.failure("No Rows to add");
		}
		PList<DInternal> autoGenInternals = autoGenColumns.map(expr -> DImpl._get(expr)).plist();

		POrderedSet<String> autoGenColumnNames =
			autoGenInternals.map(DInternal::_getColumnName).porderedset();

		PList<String> columnNamesWithoutAutoGen =
			columnNames.filterNotContainedIn(autoGenColumnNames).plist();
		return transaction.run(con -> Result.function().code(log -> {
			SqlWithParams baseSql = SqlWithParams.sql("INSERT INTO " + DImplTable._get(into).getFullTableName() + " (" + columnNamesWithoutAutoGen.toString(", ") + ")").add(SqlWithParams.nl);
			baseSql = baseSql.add("VALUES");
			DbSqlContext sqlContext = dbContext.createSqlContext();
			InsertResult result = InsertResult.empty();
			for(PList<DExpr> row : rows){

				SqlWithParams sql = baseSql.add("(");
				boolean first = true;
				for(int t=0; t<columnNames.size();t++){
					if(autoGenColumnNames.contains(columnNames.get(t))){
						continue;
					}
					DInternal internal = DImpl._get(row.get(t));
					if(first == false){
						sql = sql.add(", ");
					}
					first = false;
					sql = sql.add(internal._toSql(sqlContext));
				}
				sql = sql.add(")");
				int statType = autoGenColumns.isEmpty() ? Statement.NO_GENERATED_KEYS : Statement.RETURN_GENERATED_KEYS;
				log.info("Insert: " + sql);
				try(PreparedStatement prepStat = con.prepareStatement(sql.getSql(),statType)){
					sql.setParams(prepStat);
					int            count         = prepStat.executeUpdate();
					PList<Object> resultAutoGen = PList.empty();
					if(autoGenColumns.isEmpty() == false){
						try(ResultSet generatedKeys = prepStat.getGeneratedKeys()){
							if(generatedKeys.next()) {
								ResultSetRowReader rowReader = sqlContext.createResultSetRowReader(generatedKeys);
								resultAutoGen = autoGenInternals.map(internal -> internal._read(sqlContext, rowReader));
							}
						}

					}
					result = result.add(new InsertResult(count,PList.val(resultAutoGen)));
				}
			}

			return Result.success(result);
		}));


	}
}
