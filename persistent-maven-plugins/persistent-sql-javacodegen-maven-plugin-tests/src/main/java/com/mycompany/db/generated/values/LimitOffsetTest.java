package com.mycompany.db.generated.values;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;

import java.util.Objects;
import java.util.function.Function;

public class LimitOffsetTest{

	private final int  id;
	private final long value;


	@Generated
	public LimitOffsetTest(int id, long value) {
		this.id = Objects.requireNonNull(id, "id can not be null");
		this.value = Objects.requireNonNull(value, "value can not be null");
	}

	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2>{

		private int  id;
		private long value;


		public Builder<SET, _T2> setId(int id) {
			this.id = id;
			return (Builder<SET, _T2>) this;
		}

		public Builder<_T1, SET> setValue(long value) {
			this.value = value;
			return (Builder<_T1, SET>) this;
		}
	}

	/**
	 * Get the value of field {@link #id}.<br>
	 *
	 * @return {@link #id}
	 */
	@Generated
	public int getId() {
		return this.id;
	}

	/**
	 * Create a copy of this LimitOffsetTest object with a new value for field {@link #id}.<br>
	 *
	 * @param id The new value for field {@link #id}
	 *
	 * @return A new instance of {@link LimitOffsetTest}
	 */
	@Generated
	public LimitOffsetTest withId(int id) {
		return new LimitOffsetTest(id, value);
	}

	/**
	 * Get the value of field {@link #value}.<br>
	 *
	 * @return {@link #value}
	 */
	@Generated
	public long getValue() {
		return this.value;
	}

	/**
	 * Create a copy of this LimitOffsetTest object with a new value for field {@link #value}.<br>
	 *
	 * @param value The new value for field {@link #value}
	 *
	 * @return A new instance of {@link LimitOffsetTest}
	 */
	@Generated
	public LimitOffsetTest withValue(long value) {
		return new LimitOffsetTest(id, value);
	}

	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof LimitOffsetTest == false) return false;
		LimitOffsetTest obj = (LimitOffsetTest) o;
		if(id != obj.id) return false;
		if(value != obj.value) return false;
		return true;
	}

	@Generated
	@Override
	public int hashCode() {
		int result;
		result = this.id;
		result = 31 * result + (int) (this.value ^ (this.value >>> 32));
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "LimitOffsetTest[" +
			"id=" + id +
			", value=" + value +
			']';
	}

	@Generated
	public LimitOffsetTest updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setId(this.id);
		b.setValue(this.value);
		b = updater.apply(b);
		return new LimitOffsetTest(b.id, b.value);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static LimitOffsetTest build(ThrowingFunction<Builder<NOT, NOT>, Builder<SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new LimitOffsetTest(b.id, b.value);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static Result<LimitOffsetTest> buildExc(
		ThrowingFunction<Builder<NOT, NOT>, Builder<SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>())).mapExc(b -> new LimitOffsetTest(b.id, b.value));
	}
}
