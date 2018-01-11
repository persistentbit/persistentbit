package com.pbtest.postgres.values;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;
import com.persistentbit.string.UString;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class People{

	private final long      personId;
	private final String    salutationCode;
	private final String    nameFirst;
	@Nullable
	private final String    nameMiddle;
	private final String    nameLast;
	private final String    genderCode;
	@Nullable
	private final LocalDate birthDay;
	
	
	@Generated
	public People(long personId, String salutationCode, String nameFirst, @Nullable String nameMiddle, String nameLast,
				  String genderCode, @Nullable LocalDate birthDay) {
		this.personId = Objects.requireNonNull(personId, "personId can not be null");
		this.salutationCode = Objects.requireNonNull(salutationCode, "salutationCode can not be null");
		this.nameFirst = Objects.requireNonNull(nameFirst, "nameFirst can not be null");
		this.nameMiddle = nameMiddle;
		this.nameLast = Objects.requireNonNull(nameLast, "nameLast can not be null");
		this.genderCode = Objects.requireNonNull(genderCode, "genderCode can not be null");
		this.birthDay = birthDay;
	}

	@Generated
	public People(long personId, String salutationCode, String nameFirst, String nameLast, String genderCode) {
		this(personId, salutationCode, nameFirst, null, nameLast, genderCode, null);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3, _T4, _T5>{

		private long      personId;
		private String    salutationCode;
		private String    nameFirst;
		private String    nameMiddle;
		private String    nameLast;
		private String    genderCode;
		private LocalDate birthDay;


		public Builder<SET, _T2, _T3, _T4, _T5> setPersonId(long personId) {
			this.personId = personId;
			return (Builder<SET, _T2, _T3, _T4, _T5>) this;
		}

		public Builder<_T1, SET, _T3, _T4, _T5> setSalutationCode(String salutationCode) {
			this.salutationCode = salutationCode;
			return (Builder<_T1, SET, _T3, _T4, _T5>) this;
		}

		public Builder<_T1, _T2, SET, _T4, _T5> setNameFirst(String nameFirst) {
			this.nameFirst = nameFirst;
			return (Builder<_T1, _T2, SET, _T4, _T5>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setNameMiddle(@Nullable String nameMiddle) {
			this.nameMiddle = nameMiddle;
			return this;
		}

		public Builder<_T1, _T2, _T3, SET, _T5> setNameLast(String nameLast) {
			this.nameLast = nameLast;
			return (Builder<_T1, _T2, _T3, SET, _T5>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, SET> setGenderCode(String genderCode) {
			this.genderCode = genderCode;
			return (Builder<_T1, _T2, _T3, _T4, SET>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setBirthDay(@Nullable LocalDate birthDay) {
			this.birthDay = birthDay;
			return this;
		}
	}
	/**
	 * Get the value of field {@link #personId}.<br>
	 * @return {@link #personId}
	 */
	@Generated
	public long getPersonId() {
		return this.personId;
	}
	/**
	 * Create a copy of this People object with a new value for field {@link #personId}.<br>
	 * @param personId The new value for field {@link #personId}
	 * @return A new instance of {@link People}
	 */
	@Generated
	public People withPersonId(long personId) {
		return new People(personId, salutationCode, nameFirst, nameMiddle, nameLast, genderCode, birthDay);
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
	 * Create a copy of this People object with a new value for field {@link #salutationCode}.<br>
	 *
	 * @param salutationCode The new value for field {@link #salutationCode}
	 *
	 * @return A new instance of {@link People}
	 */
	@Generated
	public People withSalutationCode(String salutationCode) {
		return new People(personId, salutationCode, nameFirst, nameMiddle, nameLast, genderCode, birthDay);
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
	 * Create a copy of this People object with a new value for field {@link #nameFirst}.<br>
	 *
	 * @param nameFirst The new value for field {@link #nameFirst}
	 *
	 * @return A new instance of {@link People}
	 */
	@Generated
	public People withNameFirst(String nameFirst) {
		return new People(personId, salutationCode, nameFirst, nameMiddle, nameLast, genderCode, birthDay);
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
	 * Create a copy of this People object with a new value for field {@link #nameMiddle}.<br>
	 *
	 * @param nameMiddle The new value for field {@link #nameMiddle}
	 *
	 * @return A new instance of {@link People}
	 */
	@Generated
	public People withNameMiddle(@Nullable String nameMiddle) {
		return new People(personId, salutationCode, nameFirst, nameMiddle, nameLast, genderCode, birthDay);
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
	 * Create a copy of this People object with a new value for field {@link #nameLast}.<br>
	 *
	 * @param nameLast The new value for field {@link #nameLast}
	 *
	 * @return A new instance of {@link People}
	 */
	@Generated
	public People withNameLast(String nameLast) {
		return new People(personId, salutationCode, nameFirst, nameMiddle, nameLast, genderCode, birthDay);
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
	 * Create a copy of this People object with a new value for field {@link #genderCode}.<br>
	 *
	 * @param genderCode The new value for field {@link #genderCode}
	 *
	 * @return A new instance of {@link People}
	 */
	@Generated
	public People withGenderCode(String genderCode) {
		return new People(personId, salutationCode, nameFirst, nameMiddle, nameLast, genderCode, birthDay);
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
	 * Create a copy of this People object with a new value for field {@link #birthDay}.<br>
	 *
	 * @param birthDay The new value for field {@link #birthDay}
	 *
	 * @return A new instance of {@link People}
	 */
	@Generated
	public People withBirthDay(@Nullable LocalDate birthDay) {
		return new People(personId, salutationCode, nameFirst, nameMiddle, nameLast, genderCode, birthDay);
	}

	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof People == false) return false;
		People obj = (People) o;
		if(personId != obj.personId) return false;
		if(!salutationCode.equals(obj.salutationCode)) return false;
		if(!nameFirst.equals(obj.nameFirst)) return false;
		if(nameMiddle != null ? !nameMiddle.equals(obj.nameMiddle) : obj.nameMiddle!= null) return false;
		if(!nameLast.equals(obj.nameLast)) return false;
		if(!genderCode.equals(obj.genderCode)) return false;
		if(birthDay != null ? !birthDay.equals(obj.birthDay) : obj.birthDay != null) return false;
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
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "People[" +
			"personId=" + personId +
			", salutationCode=" + (salutationCode == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(salutationCode), 32, "...") + '\"') +
			", nameFirst=" + (nameFirst == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(nameFirst), 32, "...") + '\"') +
			", nameMiddle=" + (nameMiddle == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(nameMiddle), 32, "...") + '\"') +
			", nameLast=" + (nameLast == null ? "null" : '\"' + UString.present(UString
																					.escapeToJavaString(nameLast), 32, "...") + '\"') +
			", genderCode=" + (genderCode == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(genderCode), 32, "...") + '\"') +
			", birthDay=" + birthDay +
			']';
	}

	@Generated
	public People updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setPersonId(this.personId);
		b.setSalutationCode(this.salutationCode);
		b.setNameFirst(this.nameFirst);
		b.setNameMiddle(this.nameMiddle);
		b.setNameLast(this.nameLast);
		b.setGenderCode(this.genderCode);
		b.setBirthDay(this.birthDay);
		b = updater.apply(b);
		return new People(b.personId, b.salutationCode, b.nameFirst, b.nameMiddle, b.nameLast, b.genderCode, b.birthDay);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static People build(
		ThrowingFunction<Builder<NOT,NOT,NOT,NOT,NOT>, Builder<SET,SET,SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new People(b.personId, b.salutationCode, b.nameFirst, b.nameMiddle, b.nameLast, b.genderCode, b.birthDay);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static Result<People> buildExc(ThrowingFunction<Builder<NOT,NOT,NOT,NOT,NOT>, Builder<SET,SET,SET,SET,SET>,Exception> setter){
		return Result.noExceptions(() -> setter.apply(new Builder<>())).mapExc(b -> new People(b.personId, b.salutationCode, b.nameFirst, b.nameMiddle, b.nameLast, b.genderCode, b.birthDay));
	}
}
