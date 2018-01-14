package com.persistentbit.people;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.CaseClass;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.result.Result;
import com.persistentbit.string.UString;

import java.util.Optional;
import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 11/01/18
 */
@CaseClass
public class PersonQuery{
	@Nullable
	private final Long   id;
	@Nullable
	private final String firstName;
	@Nullable
	private final String lastName;
	@Nullable
	private final String fullName;
	
	
	@Generated
	public PersonQuery(@Nullable Long id, @Nullable String firstName, @Nullable String lastName,
					   @Nullable String fullName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.fullName = fullName;
	}
	@Generated
	public PersonQuery() {
		this(null, null, null, null);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder{

		private Long   id;
		private String firstName;
		private String lastName;
		private String fullName;


		public Builder setId(@Nullable Long id) {
			this.id = id;
			return this;
		}

		public Builder setFirstName(@Nullable String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder setLastName(@Nullable String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder setFullName(@Nullable String fullName) {
			this.fullName = fullName;
			return this;
		}
	}
	/**
	 * Get the value of field {@link #id}.<br>
	 * @return {@link #id}
	 */
	@Generated
	public Optional<Long> getId() {
		return Optional.ofNullable(this.id);
	}
	/**
	 * Create a copy of this PersonQuery object with a new value for field {@link #id}.<br>
	 * @param id The new value for field {@link #id}
	 * @return A new instance of {@link PersonQuery}
	 */
	@Generated
	public PersonQuery withId(@Nullable Long id) {
		return new PersonQuery(id, firstName, lastName, fullName);
	}
	/**
	 * Get the value of field {@link #firstName}.<br>
	 * @return {@link #firstName}
	 */
	@Generated
	public Optional<String> getFirstName() {
		return Optional.ofNullable(this.firstName);
	}
	/**
	 * Create a copy of this PersonQuery object with a new value for field {@link #firstName}.<br>
	 * @param firstName The new value for field {@link #firstName}
	 * @return A new instance of {@link PersonQuery}
	 */
	@Generated
	public PersonQuery withFirstName(@Nullable String firstName) {
		return new PersonQuery(id, firstName, lastName, fullName);
	}
	/**
	 * Get the value of field {@link #lastName}.<br>
	 * @return {@link #lastName}
	 */
	@Generated
	public Optional<String> getLastName() {
		return Optional.ofNullable(this.lastName);
	}
	/**
	 * Create a copy of this PersonQuery object with a new value for field {@link #lastName}.<br>
	 * @param lastName The new value for field {@link #lastName}
	 * @return A new instance of {@link PersonQuery}
	 */
	@Generated
	public PersonQuery withLastName(@Nullable String lastName) {
		return new PersonQuery(id, firstName, lastName, fullName);
	}
	/**
	 * Get the value of field {@link #fullName}.<br>
	 * @return {@link #fullName}
	 */
	@Generated
	public Optional<String> getFullName() {
		return Optional.ofNullable(this.fullName);
	}
	/**
	 * Create a copy of this PersonQuery object with a new value for field {@link #fullName}.<br>
	 * @param fullName The new value for field {@link #fullName}
	 * @return A new instance of {@link PersonQuery}
	 */
	@Generated
	public PersonQuery withFullName(@Nullable String fullName) {
		return new PersonQuery(id, firstName, lastName, fullName);
	}
	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof PersonQuery == false) return false;
		PersonQuery obj = (PersonQuery) o;
		if(id != null ? !id.equals(obj.id) : obj.id != null) return false;
		if(firstName != null ? !firstName.equals(obj.firstName) : obj.firstName != null) return false;
		if(lastName != null ? !lastName.equals(obj.lastName) : obj.lastName != null) return false;
		if(fullName != null ? !fullName.equals(obj.fullName) : obj.fullName != null) return false;
		return true;
	}
	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (this.id != null ? this.id.hashCode() : 0);
		result = 31 * result + (this.firstName != null ? this.firstName.hashCode() : 0);
		result = 31 * result + (this.lastName != null ? this.lastName.hashCode() : 0);
		result = 31 * result + (this.fullName != null ? this.fullName.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public String toString() {
		return "PersonQuery[" +
			"id=" + id +
			", firstName=" + (firstName == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(firstName), 32, "...") + '\"') +
			", lastName=" + (lastName == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(lastName), 32, "...") + '\"') +
			", fullName=" + (fullName == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(fullName), 32, "...") + '\"') +
			']';
	}
	@Generated
	public PersonQuery updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setId(this.id);
		b.setFirstName(this.firstName);
		b.setLastName(this.lastName);
		b.setFullName(this.fullName);
		b = updater.apply(b);
		return new PersonQuery(b.id, b.firstName, b.lastName, b.fullName);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static PersonQuery build(ThrowingFunction<Builder, Builder, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new PersonQuery(b.id, b.firstName, b.lastName, b.fullName);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Result<PersonQuery> buildExc(ThrowingFunction<Builder, Builder, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder()))
			.mapExc(b -> new PersonQuery(b.id, b.firstName, b.lastName, b.fullName));
	}
}
