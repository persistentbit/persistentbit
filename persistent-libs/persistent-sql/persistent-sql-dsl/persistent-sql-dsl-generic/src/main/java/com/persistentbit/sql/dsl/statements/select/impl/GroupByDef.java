package com.persistentbit.sql.dsl.statements.select.impl;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.CaseClass;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.DExpr;

import java.util.Objects;
import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 6/01/18
 */
@CaseClass
public class GroupByDef{

	private final GroupByType  type;
	private final PList<DExpr> expressions;
	
	
	@Generated
	public GroupByDef(GroupByType type, PList<DExpr> expressions) {
		this.type = Objects.requireNonNull(type, "type can not be null");
		this.expressions = Objects.requireNonNull(expressions, "expressions can not be null");
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2>{

		private GroupByType  type;
		private PList<DExpr> expressions;


		public Builder<SET, _T2> setType(GroupByType type) {
			this.type = type;
			return (Builder<SET, _T2>) this;
		}

		public Builder<_T1, SET> setExpressions(PList<DExpr> expressions) {
			this.expressions = expressions;
			return (Builder<_T1, SET>) this;
		}
	}
	/**
	 * Get the value of field {@link #type}.<br>
	 * @return {@link #type}
	 */
	@Generated
	public GroupByType getType() {
		return this.type;
	}
	/**
	 * Create a copy of this GroupByDef object with a new value for field {@link #type}.<br>
	 * @param type The new value for field {@link #type}
	 * @return A new instance of {@link GroupByDef}
	 */
	@Generated
	public GroupByDef withType(GroupByType type) {
		return new GroupByDef(type, expressions);
	}
	/**
	 * Get the value of field {@link #expressions}.<br>
	 * @return {@link #expressions}
	 */
	@Generated
	public PList<DExpr> getExpressions() {
		return this.expressions;
	}
	/**
	 * Create a copy of this GroupByDef object with a new value for field {@link #expressions}.<br>
	 * @param expressions The new value for field {@link #expressions}
	 * @return A new instance of {@link GroupByDef}
	 */
	@Generated
	public GroupByDef withExpressions(PList<DExpr> expressions) {
		return new GroupByDef(type, expressions);
	}
	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof GroupByDef == false) return false;
		GroupByDef obj = (GroupByDef) o;
		if(!type.equals(obj.type)) return false;
		if(!expressions.equals(obj.expressions)) return false;
		return true;
	}
	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (this.type != null ? this.type.hashCode() : 0);
		result = 31 * result + (this.expressions != null ? this.expressions.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public String toString() {
		return "GroupByDef[" +
			"type=" + type +
			", expressions=" + expressions + 
			']';
	}
	@Generated
	public GroupByDef updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setType(this.type);
		b.setExpressions(this.expressions);
		b = updater.apply(b);
		return new GroupByDef(b.type, b.expressions);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static GroupByDef build(ThrowingFunction<Builder<NOT, NOT>, Builder<SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new GroupByDef(b.type, b.expressions);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Result<GroupByDef> buildExc(
		ThrowingFunction<Builder<NOT, NOT>, Builder<SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new GroupByDef(b.type, b.expressions));
	}
}
