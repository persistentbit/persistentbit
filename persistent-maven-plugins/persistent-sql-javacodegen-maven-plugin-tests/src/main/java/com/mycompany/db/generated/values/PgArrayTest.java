package com.mycompany.db.generated.values;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.ImmutableArray;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;

import java.util.Objects;
import java.util.function.Function;

public class PgArrayTest{

	private final long                    id;
	private final ImmutableArray<String>  strings;
	private final ImmutableArray<Integer> ints;
	
	
	@Generated
	public PgArrayTest(long id, ImmutableArray<String> strings, ImmutableArray<Integer> ints) {
		this.id = Objects.requireNonNull(id, "id can not be null");
		this.strings = Objects.requireNonNull(strings, "strings can not be null");
		this.ints = Objects.requireNonNull(ints, "ints can not be null");
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3>{

		private long                    id;
		private ImmutableArray<String>  strings;
		private ImmutableArray<Integer> ints;


		public Builder<SET, _T2, _T3> setId(long id) {
			this.id = id;
			return (Builder<SET, _T2, _T3>) this;
		}

		public Builder<_T1, SET, _T3> setStrings(ImmutableArray<String> strings) {
			this.strings = strings;
			return (Builder<_T1, SET, _T3>) this;
		}

		public Builder<_T1, _T2, SET> setInts(ImmutableArray<Integer> ints) {
			this.ints = ints;
			return (Builder<_T1, _T2, SET>) this;
		}
	}
	/**
	 * Get the value of field {@link #id}.<br>
	 * @return {@link #id}
	 */
	@Generated
	public long getId() {
		return this.id;
	}
	/**
	 * Create a copy of this PgArrayTest object with a new value for field {@link #id}.<br>
	 * @param id The new value for field {@link #id}
	 * @return A new instance of {@link PgArrayTest}
	 */
	@Generated
	public PgArrayTest withId(long id) {
		return new PgArrayTest(id, strings, ints);
	}
	/**
	 * Get the value of field {@link #strings}.<br>
	 * @return {@link #strings}
	 */
	@Generated
	public ImmutableArray<String> getStrings() {
		return this.strings;
	}
	/**
	 * Create a copy of this PgArrayTest object with a new value for field {@link #strings}.<br>
	 * @param strings The new value for field {@link #strings}
	 * @return A new instance of {@link PgArrayTest}
	 */
	@Generated
	public PgArrayTest withStrings(ImmutableArray<String> strings) {
		return new PgArrayTest(id, strings, ints);
	}
	/**
	 * Get the value of field {@link #ints}.<br>
	 * @return {@link #ints}
	 */
	@Generated
	public ImmutableArray<Integer> getInts() {
		return this.ints;
	}
	/**
	 * Create a copy of this PgArrayTest object with a new value for field {@link #ints}.<br>
	 * @param ints The new value for field {@link #ints}
	 * @return A new instance of {@link PgArrayTest}
	 */
	@Generated
	public PgArrayTest withInts(ImmutableArray<Integer> ints) {
		return new PgArrayTest(id, strings, ints);
	}
	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof PgArrayTest == false) return false;
		PgArrayTest obj = (PgArrayTest) o;
		if(id != obj.id) return false;
		if(!strings.equals(obj.strings)) return false;
		if(!ints.equals(obj.ints)) return false;
		return true;
	}
	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (int) (this.id ^ (this.id >>> 32));
		result = 31 * result + (this.strings != null ? this.strings.hashCode() : 0);
		result = 31 * result + (this.ints != null ? this.ints.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public String toString() {
		return "PgArrayTest[" +
			"id=" + id +
			", strings=" + strings + 
			", ints=" + ints + 
			']';
	}
	@Generated
	public PgArrayTest updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setId(this.id);
		b.setStrings(this.strings);
		b.setInts(this.ints);
		b = updater.apply(b);
		return new PgArrayTest(b.id, b.strings, b.ints);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static PgArrayTest build(
		ThrowingFunction<Builder<NOT, NOT, NOT>, Builder<SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new PgArrayTest(b.id, b.strings, b.ints);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Result<PgArrayTest> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT>, Builder<SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new PgArrayTest(b.id, b.strings, b.ints));
	}
}
