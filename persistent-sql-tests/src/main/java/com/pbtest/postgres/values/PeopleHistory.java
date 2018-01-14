package com.pbtest.postgres.values;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;
import com.persistentbit.string.UString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class PeopleHistory{

	private final long          personId;
	private final String        salutationCode;
	private final String        nameFirst;
	@Nullable
	private final String        nameMiddle;
	private final String        nameLast;
	private final String        genderCode;
	@Nullable
	private final LocalDate     birthDay;
	private final LocalDateTime startTime;
	@Nullable
	private final LocalDateTime endTime;


	@Generated
	public PeopleHistory(long personId, String salutationCode, String nameFirst, @Nullable String nameMiddle,
						 String nameLast, String genderCode, @Nullable LocalDate birthDay, LocalDateTime startTime,
						 @Nullable LocalDateTime endTime) {
		this.personId = Objects.requireNonNull(personId, "personId can not be null");
		this.salutationCode = Objects.requireNonNull(salutationCode, "salutationCode can not be null");
		this.nameFirst = Objects.requireNonNull(nameFirst, "nameFirst can not be null");
		this.nameMiddle = nameMiddle;
		this.nameLast = Objects.requireNonNull(nameLast, "nameLast can not be null");
		this.genderCode = Objects.requireNonNull(genderCode, "genderCode can not be null");
		this.birthDay = birthDay;
		this.startTime = Objects.requireNonNull(startTime, "startTime can not be null");
		this.endTime = endTime;
	}

	@Generated
	public PeopleHistory(long personId, String salutationCode, String nameFirst, String nameLast, String genderCode,
						 LocalDateTime startTime) {
		this(personId, salutationCode, nameFirst, null, nameLast, genderCode, null, startTime, null);
	}

	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3, _T4, _T5, _T6>{

		private long          personId;
		private String        salutationCode;
		private String        nameFirst;
		private String        nameMiddle;
		private String        nameLast;
		private String        genderCode;
		private LocalDate     birthDay;
		private LocalDateTime startTime;
		private LocalDateTime endTime;


		public Builder<SET, _T2, _T3, _T4, _T5, _T6> setPersonId(long personId) {
			this.personId = personId;
			return (Builder<SET, _T2, _T3, _T4, _T5, _T6>) this;
		}

		public Builder<_T1, SET, _T3, _T4, _T5, _T6> setSalutationCode(String salutationCode) {
			this.salutationCode = salutationCode;
			return (Builder<_T1, SET, _T3, _T4, _T5, _T6>) this;
		}

		public Builder<_T1, _T2, SET, _T4, _T5, _T6> setNameFirst(String nameFirst) {
			this.nameFirst = nameFirst;
			return (Builder<_T1, _T2, SET, _T4, _T5, _T6>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, _T6> setNameMiddle(@Nullable String nameMiddle) {
			this.nameMiddle = nameMiddle;
			return this;
		}

		public Builder<_T1, _T2, _T3, SET, _T5, _T6> setNameLast(String nameLast) {
			this.nameLast = nameLast;
			return (Builder<_T1, _T2, _T3, SET, _T5, _T6>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, SET, _T6> setGenderCode(String genderCode) {
			this.genderCode = genderCode;
			return (Builder<_T1, _T2, _T3, _T4, SET, _T6>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, _T6> setBirthDay(@Nullable LocalDate birthDay) {
			this.birthDay = birthDay;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, SET> setStartTime(LocalDateTime startTime) {
			this.startTime = startTime;
			return (Builder<_T1, _T2, _T3, _T4, _T5, SET>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, _T6> setEndTime(@Nullable LocalDateTime endTime) {
			this.endTime = endTime;
			return this;
		}
	}

	/**
	 * Get the value of field {@link #personId}.<br>
	 *
	 * @return {@link #personId}
	 */
	@Generated
	public long getPersonId() {
		return this.personId;
	}

	/**
	 * Create a copy of this PeopleHistory object with a new value for field {@link #personId}.<br>
	 *
	 * @param personId The new value for field {@link #personId}
	 *
	 * @return A new instance of {@link PeopleHistory}
	 */
	@Generated
	public PeopleHistory withPersonId(long personId) {
		return new PeopleHistory(personId, salutationCode, nameFirst, nameMiddle, nameLast, genderCode, birthDay, startTime, endTime);
	}

	/**
	 * Get the value of field {@link #salutationCode}.<br>
	 *
	 * @return {@link #salutationCode}
	 */
	@Generated
	public String getSalutationCode() {
		return this.salutationCode;
	}

	/**
	 * Create a copy of this PeopleHistory object with a new value for field {@link #salutationCode}.<br>
	 *
	 * @param salutationCode The new value for field {@link #salutationCode}
	 *
	 * @return A new instance of {@link PeopleHistory}
	 */
	@Generated
	public PeopleHistory withSalutationCode(String salutationCode) {
		return new PeopleHistory(personId, salutationCode, nameFirst, nameMiddle, nameLast, genderCode, birthDay, startTime, endTime);
	}

	/**
	 * Get the value of field {@link #nameFirst}.<br>
	 *
	 * @return {@link #nameFirst}
	 */
	@Generated
	public String getNameFirst() {
		return this.nameFirst;
	}

	/**
	 * Create a copy of this PeopleHistory object with a new value for field {@link #nameFirst}.<br>
	 *
	 * @param nameFirst The new value for field {@link #nameFirst}
	 *
	 * @return A new instance of {@link PeopleHistory}
	 */
	@Generated
	public PeopleHistory withNameFirst(String nameFirst) {
		return new PeopleHistory(personId, salutationCode, nameFirst, nameMiddle, nameLast, genderCode, birthDay, startTime, endTime);
	}

	/**
	 * Get the value of field {@link #nameMiddle}.<br>
	 *
	 * @return {@link #nameMiddle}
	 */
	@Generated
	public Optional<String> getNameMiddle() {
		return Optional.ofNullable(this.nameMiddle);
	}

	/**
	 * Create a copy of this PeopleHistory object with a new value for field {@link #nameMiddle}.<br>
	 *
	 * @param nameMiddle The new value for field {@link #nameMiddle}
	 *
	 * @return A new instance of {@link PeopleHistory}
	 */
	@Generated
	public PeopleHistory withNameMiddle(@Nullable String nameMiddle) {
		return new PeopleHistory(personId, salutationCode, nameFirst, nameMiddle, nameLast, genderCode, birthDay, startTime, endTime);
	}

	/**
	 * Get the value of field {@link #nameLast}.<br>
	 *
	 * @return {@link #nameLast}
	 */
	@Generated
	public String getNameLast() {
		return this.nameLast;
	}

	/**
	 * Create a copy of this PeopleHistory object with a new value for field {@link #nameLast}.<br>
	 *
	 * @param nameLast The new value for field {@link #nameLast}
	 *
	 * @return A new instance of {@link PeopleHistory}
	 */
	@Generated
	public PeopleHistory withNameLast(String nameLast) {
		return new PeopleHistory(personId, salutationCode, nameFirst, nameMiddle, nameLast, genderCode, birthDay, startTime, endTime);
	}

	/**
	 * Get the value of field {@link #genderCode}.<br>
	 *
	 * @return {@link #genderCode}
	 */
	@Generated
	public String getGenderCode() {
		return this.genderCode;
	}

	/**
	 * Create a copy of this PeopleHistory object with a new value for field {@link #genderCode}.<br>
	 *
	 * @param genderCode The new value for field {@link #genderCode}
	 *
	 * @return A new instance of {@link PeopleHistory}
	 */
	@Generated
	public PeopleHistory withGenderCode(String genderCode) {
		return new PeopleHistory(personId, salutationCode, nameFirst, nameMiddle, nameLast, genderCode, birthDay, startTime, endTime);
	}

	/**
	 * Get the value of field {@link #birthDay}.<br>
	 *
	 * @return {@link #birthDay}
	 */
	@Generated
	public Optional<LocalDate> getBirthDay() {
		return Optional.ofNullable(this.birthDay);
	}

	/**
	 * Create a copy of this PeopleHistory object with a new value for field {@link #birthDay}.<br>
	 *
	 * @param birthDay The new value for field {@link #birthDay}
	 *
	 * @return A new instance of {@link PeopleHistory}
	 */
	@Generated
	public PeopleHistory withBirthDay(@Nullable LocalDate birthDay) {
		return new PeopleHistory(personId, salutationCode, nameFirst, nameMiddle, nameLast, genderCode, birthDay, startTime, endTime);
	}

	/**
	 * Get the value of field {@link #startTime}.<br>
	 *
	 * @return {@link #startTime}
	 */
	@Generated
	public LocalDateTime getStartTime() {
		return this.startTime;
	}

	/**
	 * Create a copy of this PeopleHistory object with a new value for field {@link #startTime}.<br>
	 *
	 * @param startTime The new value for field {@link #startTime}
	 *
	 * @return A new instance of {@link PeopleHistory}
	 */
	@Generated
	public PeopleHistory withStartTime(LocalDateTime startTime) {
		return new PeopleHistory(personId, salutationCode, nameFirst, nameMiddle, nameLast, genderCode, birthDay, startTime, endTime);
	}

	/**
	 * Get the value of field {@link #endTime}.<br>
	 *
	 * @return {@link #endTime}
	 */
	@Generated
	public Optional<LocalDateTime> getEndTime() {
		return Optional.ofNullable(this.endTime);
	}

	/**
	 * Create a copy of this PeopleHistory object with a new value for field {@link #endTime}.<br>
	 *
	 * @param endTime The new value for field {@link #endTime}
	 *
	 * @return A new instance of {@link PeopleHistory}
	 */
	@Generated
	public PeopleHistory withEndTime(@Nullable LocalDateTime endTime) {
		return new PeopleHistory(personId, salutationCode, nameFirst, nameMiddle, nameLast, genderCode, birthDay, startTime, endTime);
	}

	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof PeopleHistory == false) return false;
		PeopleHistory obj = (PeopleHistory) o;
		if(personId != obj.personId) return false;
		if(!salutationCode.equals(obj.salutationCode)) return false;
		if(!nameFirst.equals(obj.nameFirst)) return false;
		if(nameMiddle != null ? !nameMiddle.equals(obj.nameMiddle) : obj.nameMiddle != null) return false;
		if(!nameLast.equals(obj.nameLast)) return false;
		if(!genderCode.equals(obj.genderCode)) return false;
		if(birthDay != null ? !birthDay.equals(obj.birthDay) : obj.birthDay != null) return false;
		if(!startTime.equals(obj.startTime)) return false;
		if(endTime != null ? !endTime.equals(obj.endTime) : obj.endTime != null) return false;
		return true;
	}

	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (int) (this.personId ^ (this.personId >>> 32));
		result = 31 * result + (this.salutationCode != null ? this.salutationCode.hashCode() : 0);
		result = 31 * result + (this.nameFirst != null ? this.nameFirst.hashCode() : 0);
		result = 31 * result + (this.nameMiddle != null ? this.nameMiddle.hashCode() : 0);
		result = 31 * result + (this.nameLast != null ? this.nameLast.hashCode() : 0);
		result = 31 * result + (this.genderCode != null ? this.genderCode.hashCode() : 0);
		result = 31 * result + (this.birthDay != null ? this.birthDay.hashCode() : 0);
		result = 31 * result + (this.startTime != null ? this.startTime.hashCode() : 0);
		result = 31 * result + (this.endTime != null ? this.endTime.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "PeopleHistory[" +
			"personId=" + personId +
			", salutationCode=" + (salutationCode == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(salutationCode), 32, "...") + '\"') +
			", nameFirst=" + (nameFirst == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(nameFirst), 32, "...") + '\"') +
			", nameMiddle=" + (nameMiddle == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(nameMiddle), 32, "...") + '\"') +
			", nameLast=" + (nameLast == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(nameLast), 32, "...") + '\"') +
			", genderCode=" + (genderCode == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(genderCode), 32, "...") + '\"') +
			", birthDay=" + birthDay +
			", startTime=" + startTime +
			", endTime=" + endTime +
			']';
	}

	@Generated
	public PeopleHistory updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setPersonId(this.personId);
		b.setSalutationCode(this.salutationCode);
		b.setNameFirst(this.nameFirst);
		b.setNameMiddle(this.nameMiddle);
		b.setNameLast(this.nameLast);
		b.setGenderCode(this.genderCode);
		b.setBirthDay(this.birthDay);
		b.setStartTime(this.startTime);
		b.setEndTime(this.endTime);
		b = updater.apply(b);
		return new PeopleHistory(b.personId, b.salutationCode, b.nameFirst, b.nameMiddle, b.nameLast, b.genderCode, b.birthDay, b.startTime, b.endTime);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static PeopleHistory build(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new PeopleHistory(b.personId, b.salutationCode, b.nameFirst, b.nameMiddle, b.nameLast, b.genderCode, b.birthDay, b.startTime, b.endTime);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static Result<PeopleHistory> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new PeopleHistory(b.personId, b.salutationCode, b.nameFirst, b.nameMiddle, b.nameLast, b.genderCode, b.birthDay, b.startTime, b.endTime));
	}
}
