package com.pbtest.postgres.values;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.postgres.rt.customtypes.Interval;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;

public class CustomTypes{

	private final UUID     tUuid;
	private final Interval tInterval;


	@Generated
	public CustomTypes(UUID tUuid, Interval tInterval) {
		this.tUuid = Objects.requireNonNull(tUuid, "tUuid can not be null");
		this.tInterval = Objects.requireNonNull(tInterval, "tInterval can not be null");
	}

	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2>{

		private UUID     tUuid;
		private Interval tInterval;


		public Builder<SET, _T2> setTUuid(UUID tUuid) {
			this.tUuid = tUuid;
			return (Builder<SET, _T2>) this;
		}

		public Builder<_T1, SET> setTInterval(Interval tInterval) {
			this.tInterval = tInterval;
			return (Builder<_T1, SET>) this;
		}
	}

	/**
	 * Get the value of field {@link #tUuid}.<br>
	 *
	 * @return {@link #tUuid}
	 */
	@Generated
	public UUID getTUuid() {
		return this.tUuid;
	}

	/**
	 * Create a copy of this CustomTypes object with a new value for field {@link #tUuid}.<br>
	 *
	 * @param tUuid The new value for field {@link #tUuid}
	 *
	 * @return A new instance of {@link CustomTypes}
	 */
	@Generated
	public CustomTypes withTUuid(UUID tUuid) {
		return new CustomTypes(tUuid, tInterval);
	}

	/**
	 * Get the value of field {@link #tInterval}.<br>
	 *
	 * @return {@link #tInterval}
	 */
	@Generated
	public Interval getTInterval() {
		return this.tInterval;
	}

	/**
	 * Create a copy of this CustomTypes object with a new value for field {@link #tInterval}.<br>
	 *
	 * @param tInterval The new value for field {@link #tInterval}
	 *
	 * @return A new instance of {@link CustomTypes}
	 */
	@Generated
	public CustomTypes withTInterval(Interval tInterval) {
		return new CustomTypes(tUuid, tInterval);
	}

	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof CustomTypes == false) return false;
		CustomTypes obj = (CustomTypes) o;
		if(!tUuid.equals(obj.tUuid)) return false;
		if(!tInterval.equals(obj.tInterval)) return false;
		return true;
	}

	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (this.tUuid != null ? this.tUuid.hashCode() : 0);
		result = 31 * result + (this.tInterval != null ? this.tInterval.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "CustomTypes[" +
			"tUuid=" + tUuid +
			", tInterval=" + tInterval +
			']';
	}

	@Generated
	public CustomTypes updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setTUuid(this.tUuid);
		b.setTInterval(this.tInterval);
		b = updater.apply(b);
		return new CustomTypes(b.tUuid, b.tInterval);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static CustomTypes build(ThrowingFunction<Builder<NOT, NOT>, Builder<SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new CustomTypes(b.tUuid, b.tInterval);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static Result<CustomTypes> buildExc(
		ThrowingFunction<Builder<NOT, NOT>, Builder<SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new CustomTypes(b.tUuid, b.tInterval));
	}
}
