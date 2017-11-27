 package com.persistentbit.glasgolia.jaql.expr;

import com.persistentbit.collections.PList;
import com.persistentbit.glasgolia.jaql.*;
import com.persistentbit.result.Result;
import com.persistentbit.sql.work.DbWork;
import com.persistentbit.sql.work.DbWorkContext;
import com.persistentbit.tuples.Tuple2;
import com.persistentbit.utils.exceptions.ToDo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Peter Muys
 * @since 14/10/16
 */
public abstract class BaseSelection<T> implements ETypeSelection<T> {

    private final Query   query;
    private final Expr<T> selection;

    public BaseSelection(Query query, Expr<T> selection) {
        this.query = query;
        this.selection = selection;
    }

    @Override
    public ETypeObject<T> withNewParent(ETypePropertyParent newParent) {
        throw new ToDo();
    }

    public Query getQuery() {
        return query;
    }

    @Override
    public Result<PList<T>> execute(DbWorkContext ctx) throws Exception {
        return Result.function(ctx).code(log -> {
            QuerySqlBuilder b = new QuerySqlBuilder(this, ctx);

            log.info(b.generateNoParams());

            Tuple2<String, Consumer<PreparedStatement>> generatedQuery = b.generate();
            return ctx.get().flatMapExc(con -> {
                try(PreparedStatement s = con.prepareStatement(generatedQuery._1)) {
                    generatedQuery._2.accept(s);
                    ExprRowReader exprReader = new ExprRowReader();
                    try(ResultSet rs = s.executeQuery()) {
                        ResultSetRowReader rowReader = new ResultSetRowReader(rs);
                        PList<T>           res       = PList.empty();
                        while(rs.next()) {
                            res = res.plus(read(rowReader, exprReader));
                            rowReader.nextRow();
                        }
                        return Result.success(res);
                    }
                }
            });

        });
    }



    public <U> BaseSelection<U> mapSelection(Function<T, U> recordMapper) {
        return new Selection1<>(query, new EMapper<>(selection, recordMapper));
    }

    public DbWork<T> justOne() {
        return DbWork.function().code(l -> ctx ->
            this.execute(ctx).flatMap(list -> Result.fromOpt(list.headOpt()))
        );
    }

    @Override
    public String _asParentName(ExprToSqlContext context, String propertyName) {
        return context.uniqueInstanceName(this, "Selection") + "." + propertyName;
    }

    @Override
    public PList<Expr<?>> _asExprValues(T value) {
        throw new ToDo();
    }

    @Override
    public String _toSql(ExprToSqlContext context) {
        QuerySqlBuilder b = new QuerySqlBuilder(this, context.getDbContext());
        return b.generate(context, true);
    }


    public class SelectionProperty<E> implements Expr<E> {

        private String propertyName;
        private Expr<E> expr;

        public SelectionProperty(String propertyName, Expr<E> expr) {
            this.propertyName = propertyName;
            this.expr = expr;
        }

        @Override
        public String toString() {
            return "Selection." + propertyName;
        }


        public ETypeObject getParent() {
            return BaseSelection.this;
        }

        public String getColumnName() {
            return propertyName;
        }

        public String getPropertyName() {
            return propertyName;
        }


        @Override
        public E read(RowReader _rowReader, ExprRowReaderCache _cache) {
            return expr.read(_rowReader, _cache);
        }

        @Override
        public String _toSql(ExprToSqlContext context) {
            return _asParentName(context, propertyName);

        }

        @Override
        public PList<Expr<?>> _expand() {
            return PList.val(expr);
        }

        public Expr<E> _getExpr() {
            return expr;
        }
    }
}
