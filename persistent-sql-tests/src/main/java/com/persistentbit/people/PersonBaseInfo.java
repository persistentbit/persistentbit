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
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 11/01/18
 */
@CaseClass
public class PersonBaseInfo{

	private final String       firstName;
	@Nullable
	private final String       middleName;
	private final String       lastName;
	@Nullable
	private final LocalDate    birthDay;
	private final PersonGender gender;
	private final String       sallutationCode;
	
	
	@Generated
	public PersonBaseInfo(String firstName, @Nullable String middleName, String lastName, @Nullable LocalDate birthDay,
						  PersonGender gender, String sallutationCode) {
		this.firstName = Objects.requireNonNull(firstName, "firstName can not be null");
		this.middleName = middleName;
		this.lastName = Objects.requireNonNull(lastName, "lastName can not be null");
		this.birthDay = birthDay;
		this.gender = Objects.requireNonNull(gender, "gender can not be null");
		this.sallutationCode = Objects.requireNonNull(sallutationCode, "sallutationCode can not be null");
	}
	@Generated
	public PersonBaseInfo(String firstName, String lastName, PersonGender gender, String sallutationCode) {
		this(firstName, null, lastName, null, gender, sallutationCode);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3, _T4>{

		private String       firstName;
		private String       middleName;
		private String       lastName;
		private LocalDate    birthDay;
		private PersonGender gender;
		private String       sallutationCode;


		public Builder<SET, _T2, _T3, _T4> setFirstName(String firstName) {
			this.firstName = firstName;
			return (Builder<SET, _T2, _T3, _T4>) this;
		}

		public Builder<_T1, _T2, _T3, _T4> setMiddleName(@Nullable String middleName) {
			this.middleName = middleName;
			return this;
		}

		public Builder<_T1, SET, _T3, _T4> setLastName(String lastName) {
			this.lastName = lastName;
			return (Builder<_T1, SET, _T3, _T4>) this;
		}

		public Builder<_T1, _T2, _T3, _T4> setBirthDay(@Nullable LocalDate birthDay) {
			this.birthDay = birthDay;
			return this;
		}

		public Builder<_T1, _T2, SET, _T4> setGender(PersonGender gender) {
			this.gender = gender;
			return (Builder<_T1, _T2, SET, _T4>) this;
		}

		public Builder<_T1, _T2, _T3, SET> setSallutationCode(String sallutationCode) {
			this.sallutationCode = sallutationCode;
			return (Builder<_T1, _T2, _T3, SET>) this;
		}
	}
	/**
	 * Get the value of field {@link #firstName}.<br>
	 * @return {@link #firstName}
	 */
	@Generated
	public String getFirstName() {
		return this.firstName;
	}
	/**
	 * Create a copy of this PersonBaseInfo object with a new value for field {@link #firstName}.<br>
	 * @param firstName The new value for field {@link #firstName}
	 * @return A new instance of {@link PersonBaseInfo}
	 */
	@Generated
	public PersonBaseInfo withFirstName(String firstName) {
		return new PersonBaseInfo(firstName, middleName, lastName, birthDay, gender, sallutationCode);
	}
	/**
	 * Get the value of field {@link #middleName}.<br>
	 * @return {@link #middleName}
	 */
	@Generated
	public Optional<String> getMiddleName() {
		return Optional.ofNullable(this.middleName);
	}
	/**
	 * Create a copy of this PersonBaseInfo object with a new value for field {@link #middleName}.<br>
	 * @param middleName The new value for field {@link #middleName}
	 * @return A new instance of {@link PersonBaseInfo}
	 */
	@Generated
	public PersonBaseInfo withMiddleName(@Nullable String middleName) {
		return new PersonBaseInfo(firstName, middleName, lastName, birthDay, gender, sallutationCode);
	}
	/**
	 * Get the value of field {@link #lastName}.<br>
	 * @return {@link #lastName}
	 */
	@Generated
	public String getLastName() {
		return this.lastName;
	}
	/**
	 * Create a copy of this PersonBaseInfo object with a new value for field {@link #lastName}.<br>
	 * @param lastName The new value for field {@link #lastName}
	 * @return A new instance of {@link PersonBaseInfo}
	 */
	@Generated
	public PersonBaseInfo withLastName(String lastName) {
		return new PersonBaseInfo(firstName, middleName, lastName, birthDay, gender, sallutationCode);
	}
	/**
	 * Get the value of field {@link #birthDay}.<br>
	 * @return {@link #birthDay}
	 */
	@Generated
	public Optional<LocalDate> getBirthDay() {
		return Optional.ofNullable(this.birthDay);
	}
	/**
	 * Create a copy of this PersonBaseInfo object with a new value for field {@link #birthDay}.<br>
	 * @param birthDay The new value for field {@link #birthDay}
	 * @return A new instance of {@link PersonBaseInfo}
	 */
	@Generated
	public PersonBaseInfo withBirthDay(@Nullable LocalDate birthDay) {
		return new PersonBaseInfo(firstName, middleName, lastName, birthDay, gender, sallutationCode);
	}
	/**
	 * Get the value of field {@link #gender}.<br>
	 * @return {@link #gender}
	 */
	@Generated
	public PersonGender getGender() {
		return this.gender;
	}
	/**
	 * Create a copy of this PersonBaseInfo object with a new value for field {@link #gender}.<br>
	 * @param gender The new value for field {@link #gender}
	 * @return A new instance of {@link PersonBaseInfo}
	 */
	@Generated
	public PersonBaseInfo withGender(PersonGender gender) {
		return new PersonBaseInfo(firstName, middleName, lastName, birthDay, gender, sallutationCode);
	}
	/**
	 * Get the value of field {@link #sallutationCode}.<br>
	 * @return {@link #sallutationCode}
	 */
	@Generated
	public String getSallutationCode() {
		return this.sallutationCode;
	}
	/**
	 * Create a copy of this PersonBaseInfo object with a new value for field {@link #sallutationCode}.<br>
	 * @param sallutationCode The new value for field {@link #sallutationCode}
	 * @return A new instance of {@link PersonBaseInfo}
	 */
	@Generated
	public PersonBaseInfo withSallutationCode(String sallutationCode) {
		return new PersonBaseInfo(firstName, middleName, lastName, birthDay, gender, sallutationCode);
	}
	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof PersonBaseInfo == false) return false;
		PersonBaseInfo obj = (PersonBaseInfo) o;
		if(!firstName.equals(obj.firstName)) return false;
		if(middleName != null ? !middleName.equals(obj.middleName) : obj.middleName != null) return false;
		if(!lastName.equals(obj.lastName)) return false;
		if(birthDay != null ? !birthDay.equals(obj.birthDay) : obj.birthDay != null) return false;
		if(!gender.equals(obj.gender)) return false;
		if(!sallutationCode.equals(obj.sallutationCode)) return false;
		return true;
	}
	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (this.firstName != null ? this.firstName.hashCode() : 0);
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
		return "PersonBaseInfo[" +
			"firstName=" + (firstName == null ? "null" : '\"' + UString
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
	public PersonBaseInfo updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setFirstName(this.firstName);
		b.setMiddleName(this.middleName);
		b.setLastName(this.lastName);
		b.setBirthDay(this.birthDay);
		b.setGender(this.gender);
		b.setSallutationCode(this.sallutationCode);
		b = updater.apply(b);
		return new PersonBaseInfo(b.firstName, b.middleName, b.lastName, b.birthDay, b.gender, b.sallutationCode);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static PersonBaseInfo build(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new PersonBaseInfo(b.firstName, b.middleName, b.lastName, b.birthDay, b.gender, b.sallutationCode);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Result<PersonBaseInfo> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new PersonBaseInfo(b.firstName, b.middleName, b.lastName, b.birthDay, b.gender, b.sallutationCode));
	}
}
