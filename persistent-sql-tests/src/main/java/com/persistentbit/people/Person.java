package com.persistentbit.people;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.CaseClass;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;

import java.util.Objects;
import java.util.function.Function;

/**
 * TODOC
 *
 * @author petermuys
 * @since 10/01/18
 */
@CaseClass
public class Person{

	private final long                 personId;
	@Nullable
	private final PersonBaseInfo       baseInfo;
	@Nullable
	private final PList<PersonAddress> addresses;
	
	
	@Generated
	public Person(long personId, PersonBaseInfo baseInfo, PList<PersonAddress> addresses) {
		this.personId = Objects.requireNonNull(personId, "personId can not be null");
		this.baseInfo = Objects.requireNonNull(baseInfo, "baseInfo can not be null");
		this.addresses = Objects.requireNonNull(addresses, "addresses can not be null");
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3>{

		private long                 personId;
		private PersonBaseInfo       baseInfo;
		private PList<PersonAddress> addresses;


		public Builder<SET, _T2, _T3> setPersonId(long personId) {
			this.personId = personId;
			return (Builder<SET, _T2, _T3>) this;
		}

		public Builder<_T1, SET, _T3> setBaseInfo(PersonBaseInfo baseInfo) {
			this.baseInfo = baseInfo;
			return (Builder<_T1, SET, _T3>) this;
		}

		public Builder<_T1, _T2, SET> setAddresses(PList<PersonAddress> addresses) {
			this.addresses = addresses;
			return (Builder<_T1, _T2, SET>) this;
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
	 * Create a copy of this Person object with a new value for field {@link #personId}.<br>
	 * @param personId The new value for field {@link #personId}
	 * @return A new instance of {@link Person}
	 */
	@Generated
	public Person withPersonId(long personId) {
		return new Person(personId, baseInfo, addresses);
	}
	/**
	 * Get the value of field {@link #baseInfo}.<br>
	 * @return {@link #baseInfo}
	 */
	@Generated
	public PersonBaseInfo getBaseInfo() {
		return this.baseInfo;
	}
	/**
	 * Create a copy of this Person object with a new value for field {@link #baseInfo}.<br>
	 * @param baseInfo The new value for field {@link #baseInfo}
	 * @return A new instance of {@link Person}
	 */
	@Generated
	public Person withBaseInfo(PersonBaseInfo baseInfo) {
		return new Person(personId, baseInfo, addresses);
	}
	/**
	 * Get the value of field {@link #addresses}.<br>
	 * @return {@link #addresses}
	 */
	@Generated
	public PList<PersonAddress> getAddresses() {
		return this.addresses;
	}
	/**
	 * Create a copy of this Person object with a new value for field {@link #addresses}.<br>
	 * @param addresses The new value for field {@link #addresses}
	 * @return A new instance of {@link Person}
	 */
	@Generated
	public Person withAddresses(PList<PersonAddress> addresses) {
		return new Person(personId, baseInfo, addresses);
	}
	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof Person == false) return false;
		Person obj = (Person) o;
		if(personId != obj.personId) return false;
		if(!baseInfo.equals(obj.baseInfo)) return false;
		if(!addresses.equals(obj.addresses)) return false;
		return true;
	}
	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (int) (this.personId ^ (this.personId >>> 32));
		result = 31 * result + (this.baseInfo != null ? this.baseInfo.hashCode() : 0);
		result = 31 * result + (this.addresses != null ? this.addresses.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public String toString() {
		return "Person[" +
			"personId=" + personId +
			", baseInfo=" + baseInfo +
			", addresses=" + addresses + 
			']';
	}
	@Generated
	public Person updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setPersonId(this.personId);
		b.setBaseInfo(this.baseInfo);
		b.setAddresses(this.addresses);
		b = updater.apply(b);
		return new Person(b.personId, b.baseInfo, b.addresses);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Person build(ThrowingFunction<Builder<NOT, NOT, NOT>, Builder<SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new Person(b.personId, b.baseInfo, b.addresses);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Result<Person> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT>, Builder<SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new Person(b.personId, b.baseInfo, b.addresses));
	}
}
