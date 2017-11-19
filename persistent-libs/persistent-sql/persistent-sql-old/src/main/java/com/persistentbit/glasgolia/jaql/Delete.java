package com.persistentbit.glasgolia.jaql;

import com.persistentbit.sql.work.DbWork;
import com.persistentbit.sql.work.DbWorkContext;
import com.persistentbit.glasgolia.jaql.expr.ETypeBoolean;
import com.persistentbit.glasgolia.jaql.expr.ETypeObject;
import com.persistentbit.result.Result;
import com.persistentbit.tuples.Tuple2;

import java.sql.PreparedStatement;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Represents an SQL delete statement
 *
 * @author Peter Muys
 * @since 13/10/2016
 */
public class Delete implements DbWork<Integer>{

	private final ETypeObject  table;
	private final ETypeBoolean where;

	private Delete(ETypeObject table, ETypeBoolean where) {
		this.table = Objects.requireNonNull(table);
		this.where = where;
	}

	public Delete(ETypeObject table) {
		this(table, null);
	}


	public Delete where(ETypeBoolean whereExpr) {
		return new Delete(table, whereExpr);
	}

	@Override
	public Result<Integer> execute(DbWorkContext ctx) throws Exception {
		return Result.function(ctx).code(log -> {
			DeleteSqlBuilder b = new DeleteSqlBuilder(ctx, this);
			log.info(b.generateNoParams());

			Tuple2<String, Consumer<PreparedStatement>> generatedQuery = b.generate();
			return ctx.get().flatMapExc(con -> {
				try(PreparedStatement s = con.prepareStatement(generatedQuery._1)) {
					generatedQuery._2.accept(s);
					return Result.success(s.executeUpdate());
				}
			});

		});
	}


	public ETypeObject getTable() {
		return table;
	}

	public ETypeBoolean getWhere() {
		return where;
	}


}
