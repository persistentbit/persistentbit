package com.generated.values;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;
import com.persistentbit.string.UString;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class Persons{

	private final long          personId;
	private final String        firstName;
	@Nullable
	private final String        middleName;
	private final String        lastName;
	private final Address       home;
	private final LocalDateTime created;


	@Generated
	public Persons(long personId, String firstName, @Nullable String middleName, String lastName, Address home,
				   LocalDateTime created
	) {
		this.personId = Objects.requireNonNull(personId, "personId can not be null");
		this.firstName = Objects.requireNonNull(firstName, "firstName can not be null");
		this.middleName = middleName;
		this.lastName = Objects.requireNonNull(lastName, "lastName can not be null");
		this.home = Objects.requireNonNull(home, "home can not be null");
		this.created = Objects.requireNonNull(created, "created can not be null");
	}

	@Generated
	public Persons(long personId, String firstName, String lastName, Address home, LocalDateTime created) {
		this(personId, firstName, null, lastName, home, created);
	}

	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3, _T4, _T5>{

		private long          personId;
		private String        firstName;
		private String        middleName;
		private String        lastName;
		private Address       home;
		private LocalDateTime created;


		public Builder<SET, _T2, _T3, _T4, _T5> setPersonId(long personId) {
			this.personId = personId;
			return (Builder<SET, _T2, _T3, _T4, _T5>) this;
		}

		public Builder<_T1, SET, _T3, _T4, _T5> setFirstName(String firstName) {
			this.firstName = firstName;
			return (Builder<_T1, SET, _T3, _T4, _T5>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setMiddleName(@Nullable String middleName) {
			this.middleName = middleName;
			return this;
		}

		public Builder<_T1, _T2, SET, _T4, _T5> setLastName(String lastName) {
			this.lastName = lastName;
			return (Builder<_T1, _T2, SET, _T4, _T5>) this;
		}

		public Builder<_T1, _T2, _T3, SET, _T5> setHome(Address home) {
			this.home = home;
			return (Builder<_T1, _T2, _T3, SET, _T5>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, SET> setCreated(LocalDateTime created) {
			this.created = created;
			return (Builder<_T1, _T2, _T3, _T4, SET>) this;
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
	 * Create a copy of this Persons object with a new value for field {@link #personId}.<br>
	 *
	 * @param personId The new value for field {@link #personId}
	 *
	 * @return A new instance of {@link Persons}
	 */
	@Generated
	public Persons withPersonId(long personId) {
		return new Persons(personId, firstName, middleName, lastName, home, created);
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
	 * Create a copy of this Persons object with a new value for field {@link #firstName}.<br>
	 *
	 * @param firstName The new value for field {@link #firstName}
	 *
	 * @return A new instance of {@link Persons}
	 */
	@Generated
	public Persons withFirstName(String firstName) {
		return new Persons(personId, firstName, middleName, lastName, home, created);
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
	 * Create a copy of this Persons object with a new value for field {@link #middleName}.<br>
	 *
	 * @param middleName The new value for field {@link #middleName}
	 *
	 * @return A new instance of {@link Persons}
	 */
	@Generated
	public Persons withMiddleName(@Nullable String middleName) {
		return new Persons(personId, firstName, middleName, lastName, home, created);
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
	 * Create a copy of this Persons object with a new value for field {@link #lastName}.<br>
	 *
	 * @param lastName The new value for field {@link #lastName}
	 *
	 * @return A new instance of {@link Persons}
	 */
	@Generated
	public Persons withLastName(String lastName) {
		return new Persons(personId, firstName, middleName, lastName, home, created);
	}

	/**
	 * Get the value of field {@link #home}.<br>
	 *
	 * @return {@link #home}
	 */
	@Generated
	public Address getHome() {
		return this.home;
	}

	/**
	 * Create a copy of this Persons object with a new value for field {@link #home}.<br>
	 *
	 * @param home The new value for field {@link #home}
	 *
	 * @return A new instance of {@link Persons}
	 */
	@Generated
	public Persons withHome(Address home) {
		return new Persons(personId, firstName, middleName, lastName, home, created);
	}

	/**
	 * Get the value of field {@link #created}.<br>
	 *
	 * @return {@link #created}
	 */
	@Generated
	public LocalDateTime getCreated() {
		return this.created;
	}

	/**
	 * Create a copy of this Persons object with a new value for field {@link #created}.<br>
	 *
	 * @param created The new value for field {@link #created}
	 *
	 * @return A new instance of {@link Persons}
	 */
	@Generated
	public Persons withCreated(LocalDateTime created) {
		return new Persons(personId, firstName, middleName, lastName, home, created);
	}

	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof Persons == false) return false;
		Persons obj = (Persons) o;
		if(personId != obj.personId) return false;
		if(!firstName.equals(obj.firstName)) return false;
		if(middleName != null ? !middleName.equals(obj.middleName) : obj.middleName != null) return false;
		if(!lastName.equals(obj.lastName)) return false;
		if(!home.equals(obj.home)) return false;
		if(!created.equals(obj.created)) return false;
		return true;
	}

	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (int) (this.personId ^ (this.personId >>> 32));
		result = 31 * result + (this.firstName != null ? this.firstName.hashCode() : 0);
		result = 31 * result + (this.middleName != null ? this.middleName.hashCode() : 0);
		result = 31 * result + (this.lastName != null ? this.lastName.hashCode() : 0);
		result = 31 * result + (this.home != null ? this.home.hashCode() : 0);
		result = 31 * result + (this.created != null ? this.created.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "Persons[" +
			"personId=" + personId +
			", firstName=" + (firstName == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(firstName), 32, "...") + '\"') +
			", middleName=" + (middleName == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(middleName), 32, "...") + '\"') +
			", lastName=" + (lastName == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(lastName), 32, "...") + '\"') +
			", home=" + home +
			", created=" + created +
			']';
	}

	@Generated
	public Persons updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setPersonId(this.personId);
		b.setFirstName(this.firstName);
		b.setMiddleName(this.middleName);
		b.setLastName(this.lastName);
		b.setHome(this.home);
		b.setCreated(this.created);
		b = updater.apply(b);
		return new Persons(b.personId, b.firstName, b.middleName, b.lastName, b.home, b.created);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static Persons build(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET, SET>, Exception> setter
	) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new Persons(b.personId, b.firstName, b.middleName, b.lastName, b.home, b.created);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static Result<Persons> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET, SET>, Exception> setter
	) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new Persons(b.personId, b.firstName, b.middleName, b.lastName, b.home, b.created));
	}
}
