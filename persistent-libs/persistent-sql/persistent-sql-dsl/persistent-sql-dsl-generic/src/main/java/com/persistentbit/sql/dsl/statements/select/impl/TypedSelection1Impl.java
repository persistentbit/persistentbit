package com.persistentbit.sql.dsl.statements.select.impl;

import com.persistentbit.collections.PList;
import com.persistentbit.collections.PMap;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.SqlWithParams;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ESelection;
import com.persistentbit.sql.dsl.expressions.Param;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.jdbc.ExprTypeJdbcConvert;
import com.persistentbit.sql.dsl.statements.SqlStatement;
import com.persistentbit.sql.dsl.statements.select.TypedSelection1;
import com.persistentbit.sql.dsl.statements.work.DbWorkP1;
import com.persistentbit.sql.dsl.statements.work.DbWorkP2;
import com.persistentbit.sql.dsl.statements.work.DbWorkP3;
import com.persistentbit.sql.dsl.statements.work.DbWorkP4;
import com.persistentbit.sql.work.DbWork;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * TODOC
 *
 * @author petermuys
 * @since 28/11/17
 */
public class TypedSelection1Impl<E1 extends DExpr<J1>, J1> implements TypedSelection1<E1, J1>, SqlStatement{

	final QueryImpl query;
	final E1 col1;

	final ExprContext context;

	public TypedSelection1Impl(QueryImpl query, E1 col1) {
		this.query = query;
		this.context = query.qc.context;
		this.col1 = col1;
	}

	public ExprTypeJdbcConvert<J1> getJdbcConverter() {
		return context.getJdbcConverter(col1);
	}

	@Override
	public SqlWithParams toSql() {
		return toSql(null);
	}

	protected SqlWithParams toSql(String preFixAlias) {
		E1     colAsSelection = context.toSqlSelection(col1, preFixAlias);
		String from           = query.qc.from.map(t -> context.getFromTableName(t)).toString(", ");

		SqlWithParams joins =
			SqlWithParams
				.empty
				.add(query.qc.joins.map(j -> j.toSql()), System.lineSeparator());
		SqlWithParams res = SqlWithParams.sql("SELECT ");
		res = res.add(context.toSql(colAsSelection));
		res = res.add(SqlWithParams.nl);
		res = res.add(" FROM " + from);
		res = res.add(SqlWithParams.nl);
		res = res.add(joins);
		if(query.qc.where != null){
			res = res.add(" WHERE ").add(context.toSql(query.qc.where));
		}
		return res;
	}

	@Override
	public SubQuery1<E1, J1> asSubQuery(String name) {
		return new SubQuery1<>(this,name);
	}

	@Override
	public ESelection<J1> asExpr() {
		return context.createESelection(this);
	}

	@Override
	public String toString() {
		return toSql().toString();
	}


	private DbWork<PList<J1>> buildList(PMap<String,Object> args){
		SqlWithParams           sql         = toSql();
		PList<DExpr>            expanded    = query.qc.context.expand(col1);
		ExprTypeJdbcConvert<J1> jdbcConvert = query.qc.context.getJdbcConverter(col1);
		return DbWork.function(args).code(trans -> con -> log -> {
			log.info("Sql:" + sql);
			try(PreparedStatement stat = con.prepareStatement(sql.getSql())){
				sql.setParams(args,stat);
				try(ResultSet rs = stat.executeQuery()){
					PList<J1> res = PList.empty();
					while(rs.next()){
						res = res.plus(jdbcConvert.read(1,rs));
					}
					return Result.success(res);
				}
			}
		});
	}
	private DbWork<J1> buildOne(PMap<String,Object> args){
		return buildList(args)
			.flatMap(l -> {
				if(l.isEmpty()){
					return Result.empty("Expected one record");
				}
				if(l.size()>1){
					return Result.failure("Expected one record, got " + l.size());
				}
				return Result.result(l.headOpt().orElse(null));
			});
	}


	@Override
	public DbWork<PList<J1>> list() {
		return buildList(PMap.empty());
	}

	@Override
	public DbWork<J1> one() {
		return buildOne(PMap.empty());

	}

	@Override
	public <P1 extends DExpr<PJ1>, PJ1> DbWorkP1<PJ1, PList<J1>> list(Param<P1> p1) {
		return arg1 -> buildList(PMap.val(p1.getName(),arg1));
	}

	@Override
	public <P1 extends DExpr<PJ1>, PJ1, P2 extends DExpr<PJ2>, PJ2> DbWorkP2<PJ1, PJ2, PList<J1>> list(Param<P1> p1, Param<P2> p2
	) {
		return (arg1,arg2) -> buildList(PMap.val(p1.getName(),arg1,p2.getName(),arg2));
	}

	@Override
	public <P1 extends DExpr<PJ1>, PJ1,
		P2 extends DExpr<PJ2>, PJ2,
		P3 extends DExpr<PJ3>, PJ3
		> DbWorkP3<PJ1, PJ2, PJ3, PList<J1>> list(Param<P1> p1, Param<P2> p2, Param<P3> p3) {
		return (arg1, arg2, arg3) -> buildList(PMap.val(p1.getName(), arg1, p2.getName(), arg2, p3.getName(), arg3));
	}

	@Override
	public <P1 extends DExpr<PJ1>, PJ1,
		P2 extends DExpr<PJ2>, PJ2,
		P3 extends DExpr<PJ3>, PJ3,
		P4 extends DExpr<PJ4>, PJ4
		> DbWorkP4<PJ1, PJ2, PJ3, PJ4, PList<J1>> list(Param<P1> p1, Param<P2> p2, Param<P3> p3, Param<P4> p4) {
		return (arg1, arg2, arg3, arg4) -> buildList(PMap.val(p1.getName(), arg1, p2.getName(), arg2, p3
			.getName(), arg3, p4.getName(), arg4));
	}

	@Override
	public <P1 extends DExpr<PJ1>, PJ1> DbWorkP1<PJ1, J1> one(Param<P1> p1) {
		return arg1 -> buildOne(PMap.val(p1.getName(),arg1));
	}

	@Override
	public <P1 extends DExpr<PJ1>, PJ1, P2 extends DExpr<PJ2>, PJ2> DbWorkP2<PJ1, PJ2, J1> one(Param<P1> p1, Param<P2> p2
	) {
		return (arg1,arg2) -> buildOne(PMap.val(p1.getName(),arg1,p2.getName(),arg2));
	}

	@Override
	public <P1 extends DExpr<PJ1>, PJ1, P2 extends DExpr<PJ2>, PJ2, P3 extends DExpr<PJ3>, PJ3> DbWorkP3<PJ1, PJ2, PJ3, J1> one(
		Param<P1> p1, Param<P2> p2, Param<P3> p3
	) {
		return (arg1, arg2, arg3) -> buildOne(PMap.val(p1.getName(), arg1, p2.getName(), arg2, p3.getName(), arg3));
	}

	@Override
	public <P1 extends DExpr<PJ1>, PJ1, P2 extends DExpr<PJ2>, PJ2, P3 extends DExpr<PJ3>, PJ3, P4 extends DExpr<PJ4>, PJ4> DbWorkP4<PJ1, PJ2, PJ3, PJ4, J1> one(
		Param<P1> p1, Param<P2> p2, Param<P3> p3, Param<P4> p4
	) {
		return (arg1, arg2, arg3, arg4) -> buildOne(PMap.val(p1.getName(), arg1, p2.getName(), arg2, p3
			.getName(), arg3, p4.getName(), arg4));
	}
}
