package com.persistentbit.sql.dsl.statements.select.impl;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.*;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.EBool;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.tables.Table;
import com.persistentbit.tuples.Tuple2;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 22/12/17
 */
@CaseClass
public class QueryCtx {

	final ExprContext          context;
	final PList<Table>         from;
	@DefaultEmpty
	final PList<JoinImpl>      joins;
	@Nullable
	final EBool                where;
	@DefaultEmpty
	final PList<OrderBy>       orderBy;
	@Nullable
	final Tuple2<ELong, ELong> limitAndOffset;
	@DefaultValue("false")
	final boolean              distinct;
	
	
	@Generated
	public QueryCtx(ExprContext context, PList<Table> from, @Nullable PList<JoinImpl> joins, @Nullable EBool where,
					@Nullable PList<OrderBy> orderBy, @Nullable Tuple2<ELong, ELong> limitAndOffset,
					@Nullable Boolean distinct) {
			this.context = Objects.requireNonNull(context, "context can not be null");
			this.from = Objects.requireNonNull(from, "from can not be null");
			this.joins = joins == null ? PList.empty() : joins;
			this.where = where;
			this.orderBy = orderBy == null ? PList.empty() : orderBy;
			this.limitAndOffset = limitAndOffset;
		this.distinct = distinct == null ? false : distinct;
	}
	@Generated
	public QueryCtx(ExprContext context, PList<Table> from){
		this(context, from, null, null, null, null, null);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2> {

		private ExprContext          context;
		private PList<Table>         from;
		private PList<JoinImpl>      joins;
		private EBool                where;
		private PList<OrderBy>       orderBy;
		private Tuple2<ELong, ELong> limitAndOffset;
		private boolean              distinct;
		
		
		public  Builder<SET, _T2>	setContext(ExprContext context){
			this.context	=	context;
			return (Builder<SET, _T2>)this;
		}
		public  Builder<_T1, SET>	setFrom(PList<Table> from){
			this.from	=	from;
			return (Builder<_T1, SET>)this;
		}
		public  Builder<_T1, _T2>	setJoins(@Nullable PList<JoinImpl> joins){
			this.joins	=	joins;
			return this;
		}
		public  Builder<_T1, _T2>	setWhere(@Nullable EBool where){
			this.where	=	where;
			return this;
		}
		public  Builder<_T1, _T2>	setOrderBy(@Nullable PList<OrderBy> orderBy){
			this.orderBy	=	orderBy;
			return this;
		}

		public Builder<_T1, _T2> setLimitAndOffset(@Nullable Tuple2<ELong, ELong> limitAndOffset) {
			this.limitAndOffset	=	limitAndOffset;
			return this;
		}

		public Builder<_T1, _T2> setDistinct(@Nullable Boolean distinct) {
			this.distinct = distinct;
			return this;
		}
	}
	/**
	 * Get the value of field {@link #context}.<br>
	 * @return {@link #context}
	 */
	@Generated
	public  ExprContext	getContext(){
		return this.context;
	}
	/**
	 * Create a copy of this QueryCtx object with a new value for field {@link #context}.<br>
	 * @param context The new value for field {@link #context}
	 * @return A new instance of {@link QueryCtx}
	 */
	@Generated
	public  QueryCtx	withContext(ExprContext context){
		return new QueryCtx(context, from, joins, where, orderBy, limitAndOffset, distinct);
	}
	/**
	 * Get the value of field {@link #from}.<br>
	 * @return {@link #from}
	 */
	@Generated
	public  PList<Table>	getFrom(){
		return this.from;
	}
	/**
	 * Create a copy of this QueryCtx object with a new value for field {@link #from}.<br>
	 * @param from The new value for field {@link #from}
	 * @return A new instance of {@link QueryCtx}
	 */
	@Generated
	public  QueryCtx	withFrom(PList<Table> from){
		return new QueryCtx(context, from, joins, where, orderBy, limitAndOffset, distinct);
	}
	/**
	 * Get the value of field {@link #joins}.<br>
	 * @return {@link #joins}
	 */
	@Generated
	public  PList<JoinImpl>	getJoins(){
		return this.joins;
	}
	/**
	 * Create a copy of this QueryCtx object with a new value for field {@link #joins}.<br>
	 * @param joins The new value for field {@link #joins}
	 * @return A new instance of {@link QueryCtx}
	 */
	@Generated
	public  QueryCtx	withJoins(@Nullable PList<JoinImpl> joins){
		return new QueryCtx(context, from, joins, where, orderBy, limitAndOffset, distinct);
	}
	/**
	 * Get the value of field {@link #where}.<br>
	 * @return {@link #where}
	 */
	@Generated
	public  Optional<EBool>	getWhere(){
		return Optional.ofNullable(this.where);
	}
	/**
	 * Create a copy of this QueryCtx object with a new value for field {@link #where}.<br>
	 * @param where The new value for field {@link #where}
	 * @return A new instance of {@link QueryCtx}
	 */
	@Generated
	public  QueryCtx	withWhere(@Nullable EBool where){
		return new QueryCtx(context, from, joins, where, orderBy, limitAndOffset, distinct);
	}
	/**
	 * Get the value of field {@link #orderBy}.<br>
	 * @return {@link #orderBy}
	 */
	@Generated
	public  PList<OrderBy>	getOrderBy(){
		return this.orderBy;
	}
	/**
	 * Create a copy of this QueryCtx object with a new value for field {@link #orderBy}.<br>
	 * @param orderBy The new value for field {@link #orderBy}
	 * @return A new instance of {@link QueryCtx}
	 */
	@Generated
	public  QueryCtx	withOrderBy(@Nullable PList<OrderBy> orderBy){
		return new QueryCtx(context, from, joins, where, orderBy, limitAndOffset, distinct);
	}
	/**
	 * Get the value of field {@link #limitAndOffset}.<br>
	 * @return {@link #limitAndOffset}
	 */
	@Generated
	public Optional<Tuple2<ELong, ELong>> getLimitAndOffset() {
		return Optional.ofNullable(this.limitAndOffset);
	}
	/**
	 * Create a copy of this QueryCtx object with a new value for field {@link #limitAndOffset}.<br>
	 * @param limitAndOffset The new value for field {@link #limitAndOffset}
	 * @return A new instance of {@link QueryCtx}
	 */
	@Generated
	public QueryCtx withLimitAndOffset(@Nullable Tuple2<ELong, ELong> limitAndOffset) {
		return new QueryCtx(context, from, joins, where, orderBy, limitAndOffset, distinct);
	}

	/**
	 * Get the value of field {@link #distinct}.<br>
	 *
	 * @return {@link #distinct}
	 */
	@Generated
	public boolean getDistinct() {
		return this.distinct;
	}

	/**
	 * Create a copy of this QueryCtx object with a new value for field {@link #distinct}.<br>
	 *
	 * @param distinct The new value for field {@link #distinct}
	 *
	 * @return A new instance of {@link QueryCtx}
	 */
	@Generated
	public QueryCtx withDistinct(@Nullable Boolean distinct) {
		return new QueryCtx(context, from, joins, where, orderBy, limitAndOffset, distinct);
	}
	@Generated
	@Override
	public  boolean	equals(@Nullable Object o){
		if(this == o) return true;
		if(o instanceof QueryCtx == false) return false;
		QueryCtx obj = (QueryCtx)o;
		if(!context.equals(obj.context)) return false;
		if(!from.equals(obj.from)) return false;
		if(!joins.equals(obj.joins)) return false;
		if(where != null ? !where.equals(obj.where) : obj.where!= null) return false;
		if(!orderBy.equals(obj.orderBy)) return false;
		if(limitAndOffset != null ? !limitAndOffset.equals(obj.limitAndOffset) : obj.limitAndOffset != null)
			return false;
		if(distinct!= obj.distinct) return false;
		return true;
	}
	@Generated
	@Override
	public  int	hashCode(){
		int result;
		result = (this.context != null ? this.context.hashCode() : 0);
		result = 31 * result + (this.from != null ? this.from.hashCode() : 0);
		result = 31 * result + (this.joins != null ? this.joins.hashCode() : 0);
		result = 31 * result + (this.where != null ? this.where.hashCode() : 0);
		result = 31 * result + (this.orderBy != null ? this.orderBy.hashCode() : 0);
		result = 31 * result + (this.limitAndOffset != null ? this.limitAndOffset.hashCode() : 0);
		result = 31 * result + (this.distinct ? 1 : 0);
		return result;
	}
	@Generated
	@Override
	public  String	toString(){
		return "QueryCtx[" + 
			"context=" + context + 
			", from=" + from + 
			", joins=" + joins +
			", where=" + where + 
			", orderBy=" + orderBy +
			", limitAndOffset=" + limitAndOffset +
			", distinct=" + distinct + 
			']';
	}
	@Generated
	public  QueryCtx	updated(Function<Builder,Builder> updater){
		Builder b = new Builder();
		b.setContext(this.context);
		b.setFrom(this.from);
		b.setJoins(this.joins);
		b.setWhere(this.where);
		b.setOrderBy(this.orderBy);
		b.setLimitAndOffset(this.limitAndOffset);
		b.setDistinct(this.distinct);
		b = updater.apply(b);
		return new QueryCtx(b.context, b.from, b.joins, b.where, b.orderBy, b.limitAndOffset, b.distinct);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public  static QueryCtx	build(ThrowingFunction<Builder<NOT,NOT>, Builder<SET,SET>, Exception> setter){
		Builder b = setter.toNonChecked().apply(new Builder());
		return new QueryCtx(b.context, b.from, b.joins, b.where, b.orderBy, b.limitAndOffset, b.distinct);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Result<QueryCtx> buildExc(ThrowingFunction<Builder<NOT, NOT>, Builder<SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new QueryCtx(b.context, b.from, b.joins, b.where, b.orderBy, b.limitAndOffset, b.distinct));
	}
}
