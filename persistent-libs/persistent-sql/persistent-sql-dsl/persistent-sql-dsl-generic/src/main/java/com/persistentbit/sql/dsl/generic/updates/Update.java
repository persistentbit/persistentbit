package com.persistentbit.sql.dsl.generic.updates;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.exprcontext.DbContext;
import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.DExprTable;
import com.persistentbit.sql.transactions.DbTransaction;
import com.persistentbit.sql.work.DbWork;
import com.persistentbit.tuples.Tuple2;
import com.persistentbit.utils.exceptions.ToDo;

/**
 * TODOC
 *
 * @author petermuys
 * @since 15/12/17
 */
public class Update implements DbWork<Integer>{
	private final DbContext dbContext;
	private final DExprTable	table;
	@Nullable
	private final DExpr<Boolean>	where;
	private final PList<Tuple2<DExpr,DExpr>> set;

	public Update(DbContext dbContext, DExprTable table, @Nullable DExpr<Boolean> where,
				  PList<Tuple2<DExpr, DExpr>> set
	) {
		this.dbContext = dbContext;
		this.table = table;
		this.where = where;
		this.set = set;
	}

	public Update(DbContext dbContext, DExprTable table){
		this(dbContext,table,null,PList.empty());
	}

	public <V> Update set(DExpr<V> property, DExpr<? extends V> value){
		return new Update(dbContext,table,where, set.plus(Tuple2.of(property,value)));
	}
	/*
	public Update set(EBool property, boolean value){
		return set(property,dbContext.val(value));
	}
	public Update set(DExpr<Byte> prop, Byte val){
		return set(prop,dbContext.val(val));
	}
	public Update set(DExpr<Short> prop,Short val){
		return set(prop,dbContext.val(val));
	}
	public Update set(DExpr<Integer> prop,Integer val){
		return set(prop, dbContext.val(val));
	}
	public Update set(DExpr<Long> prop, Long val){
		return set(prop, dbContext.val(val));
	}
	public Update set(DExpr<Double> prop, Double val){
		return set(prop,dbContext.val(val));
	}
	public Update set(DExpr<BigDecimal> prop, BigDecimal val){
		return set(prop,dbContext.val(val));
	}
	public Update set(DExpr<String> prop, String val){
		return set(prop,dbContext.val(val));
	}
	public Update set(DExpr<LocalDate> prop, LocalDate val){
		return set(prop,dbContext.val(val));
	}
	public Update set(DExpr<LocalDateTime> prop, LocalDateTime val){
		return set(prop,dbContext.val(val));
	}
*/
	public Update where(DExpr<Boolean> where){
		return new Update(dbContext,table,where,set);
	}

	@Override
	public Result<Integer> run(DbTransaction transaction) {
		throw new ToDo();
	}
}
