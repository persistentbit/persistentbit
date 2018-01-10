package com.persistentbit.people;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.CaseClass;
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

/**
 * TODOC
 *
 * @author petermuys
 * @since 10/01/18
 */
@CaseClass
public class PersonBaseInfoInTime{

	private final Long          personId;
	private final LocalDateTime pointInTime;
	private final String        firstName;
	@Nullable
	private final String        middleName;
	private final String        lastName;
	private final LocalDate     birthDay;
	private final PersonGender  gender;
	private final String        sallutationCode;


	@Generated
	public PersonBaseInfoInTime(Long personId, LocalDateTime pointInTime, String firstName, @Nullable String middleName,
								String lastName, LocalDate birthDay, PersonGender gender, String sallutationCode) {
		this.personId = Objects.requireNonNull(personId, "personId can not be null");
		this.pointInTime = Objects.requireNonNull(pointInTime, "pointInTime can not be null");
		this.firstName = Objects.requireNonNull(firstName, "firstName can not be null");
		this.middleName = middleName;
		this.lastName = Objects.requireNonNull(lastName, "lastName can not be null");
		this.birthDay = Objects.requireNonNull(birthDay, "birthDay can not be null");
		this.gender = Objects.requireNonNull(gender, "gender can not be null");
		this.sallutationCode = Objects.requireNonNull(sallutationCode, "sallutationCode can not be null");
	}

	@Generated
	public PersonBaseInfoInTime(Long personId, LocalDateTime pointInTime, String firstName, String lastName,
								LocalDate birthDay, PersonGender gender, String sallutationCode) {
		this(personId, pointInTime, firstName, null, lastName, birthDay, gender, sallutationCode);
	}

	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7>{

		private Long          personId;
		private LocalDateTime pointInTime;
		private String        firstName;
		private String        middleName;
		private String        lastName;
		private LocalDate     birthDay;
		private PersonGender  gender;
		private String        sallutationCode;


		public Builder<SET, _T2, _T3, _T4, _T5, _T6, _T7> setPersonId(Long personId) {
			this.personId = personId;
			return (Builder<SET, _T2, _T3, _T4, _T5, _T6, _T7>) this;
		}

		public Builder<_T1, SET, _T3, _T4, _T5, _T6, _T7> setPointInTime(LocalDateTime pointInTime) {
			this.pointInTime = pointInTime;
			return (Builder<_T1, SET, _T3, _T4, _T5, _T6, _T7>) this;
		}

		public Builder<_T1, _T2, SET, _T4, _T5, _T6, _T7> setFirstName(String firstName) {
			this.firstName = firstName;
			return (Builder<_T1, _T2, SET, _T4, _T5, _T6, _T7>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7> setMiddleName(@Nullable String middleName) {
			this.middleName = middleName;
			return this;
		}

		public Builder<_T1, _T2, _T3, SET, _T5, _T6, _T7> setLastName(String lastName) {
			this.lastName = lastName;
			return (Builder<_T1, _T2, _T3, SET, _T5, _T6, _T7>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, SET, _T6, _T7> setBirthDay(LocalDate birthDay) {
			this.birthDay = birthDay;
			return (Builder<_T1, _T2, _T3, _T4, SET, _T6, _T7>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, SET, _T7> setGender(PersonGender gender) {
			this.gender = gender;
			return (Builder<_T1, _T2, _T3, _T4, _T5, SET, _T7>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, _T6, SET> setSallutationCode(String sallutationCode) {
			this.sallutationCode = sallutationCode;
			return (Builder<_T1, _T2, _T3, _T4, _T5, _T6, SET>) this;
		}
	}

	/**
	 * Get the value of field {@link #personId}.<br>
	 *
	 * @return {@link #personId}
	 */
	@Generated
	public Long getPersonId() {
		return this.personId;
	}

	/**
	 * Create a copy of this PersonBaseInfoInTime object with a new value for field {@link #personId}.<br>
	 *
	 * @param personId The new value for field {@link #personId}
	 *
	 * @return A new instance of {@link PersonBaseInfoInTime}
	 */
	@Generated
	public PersonBaseInfoInTime withPersonId(Long personId) {
		return new PersonBaseInfoInTime(personId, pointInTime, firstName, middleName, lastName, birthDay, gender, sallutationCode);
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
	 * Create a copy of this PersonBaseInfoInTime object with a new value for field {@link #pointInTime}.<br>
	 *
	 * @param pointInTime The new value for field {@link #pointInTime}
	 *
	 * @return A new instance of {@link PersonBaseInfoInTime}
	 */
	@Generated
	public PersonBaseInfoInTime withPointInTime(LocalDateTime pointInTime) {
		return new PersonBaseInfoInTime(personId, pointInTime, firstName, middleName, lastName, birthDay, gender, sallutationCode);
	}

	/**
	 * Get the value of field {@link #firstName}.<br>
	 *
	 * @return {@link #firstName}
	 */
	@Generated
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Create a copy of this PersonBaseInfoInTime object with a new value for field {@link #firstName}.<br>
	 *
	 * @param firstName The new value for field {@link #firstName}
	 *
	 * @return A new instance of {@link PersonBaseInfoInTime}
	 */
	@Generated
	public PersonBaseInfoInTime withFirstName(String firstName) {
		return new PersonBaseInfoInTime(personId, pointInTime, firstName, middleName, lastName, birthDay, gender, sallutationCode);
	}

	/**
	 * Get the value of field {@link #middleName}.<br>
	 *
	 * @return {@link #middleName}
	 */
	@Generated
	public Optional<String> getMiddleName() {
		return Optional.ofNullable(this.middleName);
	}

	/**
	 * Create a copy of this PersonBaseInfoInTime object with a new value for field {@link #middleName}.<br>
	 *
	 * @param middleName The new value for field {@link #middleName}
	 *
	 * @return A new instance of {@link PersonBaseInfoInTime}
	 */
	@Generated
	public PersonBaseInfoInTime withMiddleName(@Nullable String middleName) {
		return new PersonBaseInfoInTime(personId, pointInTime, firstName, middleName, lastName, birthDay, gender, sallutationCode);
	}

	/**
	 * Get the value of field {@link #lastName}.<br>
	 *
	 * @return {@link #lastName}
	 */
	@Generated
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Create a copy of this PersonBaseInfoInTime object with a new value for field {@link #lastName}.<br>
	 *
	 * @param lastName The new value for field {@link #lastName}
	 *
	 * @return A new instance of {@link PersonBaseInfoInTime}
	 */
	@Generated
	public PersonBaseInfoInTime withLastName(String lastName) {
		return new PersonBaseInfoInTime(personId, pointInTime, firstName, middleName, lastName, birthDay, gender, sallutationCode);
	}

	/**
	 * Get the value of field {@link #birthDay}.<br>
	 *
	 * @return {@link #birthDay}
	 */
	@Generated
	public LocalDate getBirthDay() {
		return this.birthDay;
	}

	/**
	 * Create a copy of this PersonBaseInfoInTime object with a new value for field {@link #birthDay}.<br>
	 *
	 * @param birthDay The new value for field {@link #birthDay}
	 *
	 * @return A new instance of {@link PersonBaseInfoInTime}
	 */
	@Generated
	public PersonBaseInfoInTime withBirthDay(LocalDate birthDay) {
		return new PersonBaseInfoInTime(personId, pointInTime, firstName, middleName, lastName, birthDay, gender, sallutationCode);
	}

	/**
	 * Get the value of field {@link #gender}.<br>
	 *
	 * @return {@link #gender}
	 */
	@Generated
	public PersonGender getGender() {
		return this.gender;
	}

	/**
	 * Create a copy of this PersonBaseInfoInTime object with a new value for field {@link #gender}.<br>
	 *
	 * @param gender The new value for field {@link #gender}
	 *
	 * @return A new instance of {@link PersonBaseInfoInTime}
	 */
	@Generated
	public PersonBaseInfoInTime withGender(PersonGender gender) {
		return new PersonBaseInfoInTime(personId, pointInTime, firstName, middleName, lastName, birthDay, gender, sallutationCode);
	}

	/**
	 * Get the value of field {@link #sallutationCode}.<br>
	 *
	 * @return {@link #sallutationCode}
	 */
	@Generated
	public String getSallutationCode() {
		return this.sallutationCode;
	}

	/**
	 * Create a copy of this PersonBaseInfoInTime object with a new value for field {@link #sallutationCode}.<br>
	 *
	 * @param sallutationCode The new value for field {@link #sallutationCode}
	 *
	 * @return A new instance of {@link PersonBaseInfoInTime}
	 */
	@Generated
	public PersonBaseInfoInTime withSallutationCode(String sallutationCode) {
		return new PersonBaseInfoInTime(personId, pointInTime, firstName, middleName, lastName, birthDay, gender, sallutationCode);
	}

	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof PersonBaseInfoInTime == false) return false;
		PersonBaseInfoInTime obj = (PersonBaseInfoInTime) o;
		if(!personId.equals(obj.personId)) return false;
		if(!pointInTime.equals(obj.pointInTime)) return false;
		if(!firstName.equals(obj.firstName)) return false;
		if(middleName != null ? !middleName.equals(obj.middleName) : obj.middleName != null) return false;
		if(!lastName.equals(obj.lastName)) return false;
		if(!birthDay.equals(obj.birthDay)) return false;
		if(!gender.equals(obj.gender)) return false;
		if(!sallutationCode.equals(obj.sallutationCode)) return false;
		return true;
	}

	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (this.personId != null ? this.personId.hashCode() : 0);
		result = 31 * result + (this.pointInTime != null ? this.pointInTime.hashCode() : 0);
		result = 31 * result + (this.firstName != null ? this.firstName.hashCode() : 0);
		result = 31 * result + (this.middleName != null ? this.middleName.hashCode() : 0);
		result = 31 * result + (this.lastName != null ? this.lastName.hashCode() : 0);
		result = 31 * result + (this.birthDay != null ? this.birthDay.hashCode() : 0);
		result = 31 * result + (this.gender != null ? this.gender.hashCode() : 0);
		result = 31 * result + (this.sallutationCode != null ? this.sallutationCode.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "PersonBaseInfoInTime[" +
			"personId=" + personId +
			", pointInTime=" + pointInTime +
			", firstName=" + (firstName == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(firstName), 32, "...") + '\"') +
			", middleName=" + (middleName == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(middleName), 32, "...") + '\"') +
			", lastName=" + (lastName == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(lastName), 32, "...") + '\"') +
			", birthDay=" + birthDay +
			", gender=" + gender +
			", sallutationCode=" + (sallutationCode == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(sallutationCode), 32, "...") + '\"') +
			']';
	}

	@Generated
	public PersonBaseInfoInTime updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setPersonId(this.personId);
		b.setPointInTime(this.pointInTime);
		b.setFirstName(this.firstName);
		b.setMiddleName(this.middleName);
		b.setLastName(this.lastName);
		b.setBirthDay(this.birthDay);
		b.setGender(this.gender);
		b.setSallutationCode(this.sallutationCode);
		b = updater.apply(b);
		return new PersonBaseInfoInTime(b.personId, b.pointInTime, b.firstName, b.middleName, b.lastName, b.birthDay, b.gender, b.sallutationCode);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static PersonBaseInfoInTime build(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET, SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new PersonBaseInfoInTime(b.personId, b.pointInTime, b.firstName, b.middleName, b.lastName, b.birthDay, b.gender, b.sallutationCode);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static Result<PersonBaseInfoInTime> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET, SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new PersonBaseInfoInTime(b.personId, b.pointInTime, b.firstName, b.middleName, b.lastName, b.birthDay, b.gender, b.sallutationCode));
	}
}
