package com.persistentbit.people;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.CaseClass;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 10/01/18
 */
@CaseClass
public class PersonInTime{

	private final long                 id;
	private final LocalDateTime        pointInTime;
	@Nullable
	private final PersonBaseInfoInTime baseInfo;


	@Generated
	public PersonInTime(long id, LocalDateTime pointInTime, @Nullable PersonBaseInfoInTime baseInfo) {
		this.id = Objects.requireNonNull(id, "id can not be null");
		this.pointInTime = Objects.requireNonNull(pointInTime, "pointInTime can not be null");
		this.baseInfo = baseInfo;
	}

	@Generated
	public PersonInTime(long id, LocalDateTime pointInTime) {
		this(id, pointInTime, null);
	}

	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2>{

		private long                 id;
		private LocalDateTime        pointInTime;
		private PersonBaseInfoInTime baseInfo;


		public Builder<SET, _T2> setId(long id) {
			this.id = id;
			return (Builder<SET, _T2>) this;
		}

		public Builder<_T1, SET> setPointInTime(LocalDateTime pointInTime) {
			this.pointInTime = pointInTime;
			return (Builder<_T1, SET>) this;
		}

		public Builder<_T1, _T2> setBaseInfo(@Nullable PersonBaseInfoInTime baseInfo) {
			this.baseInfo = baseInfo;
			return this;
		}
	}

	/**
	 * Get the value of field {@link #id}.<br>
	 *
	 * @return {@link #id}
	 */
	@Generated
	public long getId() {
		return this.id;
	}

	/**
	 * Create a copy of this PersonInTime object with a new value for field {@link #id}.<br>
	 *
	 * @param id The new value for field {@link #id}
	 *
	 * @return A new instance of {@link PersonInTime}
	 */
	@Generated
	public PersonInTime withId(long id) {
		return new PersonInTime(id, pointInTime, baseInfo);
	}

	/**
	 * Get the value of field {@link #pointInTime}.<br>
	 *
	 * @return {@link #pointInTime}
	 */
	@Generated
	public LocalDateTime getPointInTime() {
		return this.pointInTime;
	}

	/**
	 * Create a copy of this PersonInTime object with a new value for field {@link #pointInTime}.<br>
	 *
	 * @param pointInTime The new value for field {@link #pointInTime}
	 *
	 * @return A new instance of {@link PersonInTime}
	 */
	@Generated
	public PersonInTime withPointInTime(LocalDateTime pointInTime) {
		return new PersonInTime(id, pointInTime, baseInfo);
	}

	/**
	 * Get the value of field {@link #baseInfo}.<br>
	 *
	 * @return {@link #baseInfo}
	 */
	@Generated
	public Optional<PersonBaseInfoInTime> getBaseInfo() {
		return Optional.ofNullable(this.baseInfo);
	}

	/**
	 * Create a copy of this PersonInTime object with a new value for field {@link #baseInfo}.<br>
	 *
	 * @param baseInfo The new value for field {@link #baseInfo}
	 *
	 * @return A new instance of {@link PersonInTime}
	 */
	@Generated
	public PersonInTime withBaseInfo(@Nullable PersonBaseInfoInTime baseInfo) {
		return new PersonInTime(id, pointInTime, baseInfo);
	}

	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof PersonInTime == false) return false;
		PersonInTime obj = (PersonInTime) o;
		if(id != obj.id) return false;
		if(!pointInTime.equals(obj.pointInTime)) return false;
		if(baseInfo != null ? !baseInfo.equals(obj.baseInfo) : obj.baseInfo != null) return false;
		return true;
	}

	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (int) (this.id ^ (this.id >>> 32));
		result = 31 * result + (this.pointInTime != null ? this.pointInTime.hashCode() : 0);
		result = 31 * result + (this.baseInfo != null ? this.baseInfo.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "PersonInTime[" +
			"id=" + id +
			", pointInTime=" + pointInTime +
			", baseInfo=" + baseInfo +
			']';
	}

	@Generated
	public PersonInTime updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setId(this.id);
		b.setPointInTime(this.pointInTime);
		b.setBaseInfo(this.baseInfo);
		b = updater.apply(b);
		return new PersonInTime(b.id, b.pointInTime, b.baseInfo);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static PersonInTime build(ThrowingFunction<Builder<NOT, NOT>, Builder<SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new PersonInTime(b.id, b.pointInTime, b.baseInfo);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static Result<PersonInTime> buildExc(
		ThrowingFunction<Builder<NOT, NOT>, Builder<SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new PersonInTime(b.id, b.pointInTime, b.baseInfo));
	}
}
