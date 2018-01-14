package com.persistentbit.people;

import com.persistentbit.code.annotations.Nullable;
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
public class PersonAddress{

	private final long                personId;
	private final AddressRelationCode relationCode;
	private final Address             address;
	
	
	@Generated
	public PersonAddress(long personId, AddressRelationCode relationCode, Address address) {
		this.personId = Objects.requireNonNull(personId, "personId can not be null");
		this.relationCode = Objects.requireNonNull(relationCode, "relationCode can not be null");
		this.address = Objects.requireNonNull(address, "address can not be null");
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3>{

		private long                personId;
		private AddressRelationCode relationCode;
		private Address             address;


		public Builder<SET, _T2, _T3> setPersonId(long personId) {
			this.personId = personId;
			return (Builder<SET, _T2, _T3>) this;
		}

		public Builder<_T1, SET, _T3> setRelationCode(AddressRelationCode relationCode) {
			this.relationCode = relationCode;
			return (Builder<_T1, SET, _T3>) this;
		}

		public Builder<_T1, _T2, SET> setAddress(Address address) {
			this.address = address;
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
	 * Create a copy of this PersonAddress object with a new value for field {@link #personId}.<br>
	 * @param personId The new value for field {@link #personId}
	 * @return A new instance of {@link PersonAddress}
	 */
	@Generated
	public PersonAddress withPersonId(long personId) {
		return new PersonAddress(personId, relationCode, address);
	}
	/**
	 * Get the value of field {@link #relationCode}.<br>
	 * @return {@link #relationCode}
	 */
	@Generated
	public AddressRelationCode getRelationCode() {
		return this.relationCode;
	}
	/**
	 * Create a copy of this PersonAddress object with a new value for field {@link #relationCode}.<br>
	 * @param relationCode The new value for field {@link #relationCode}
	 * @return A new instance of {@link PersonAddress}
	 */
	@Generated
	public PersonAddress withRelationCode(AddressRelationCode relationCode) {
		return new PersonAddress(personId, relationCode, address);
	}
	/**
	 * Get the value of field {@link #address}.<br>
	 * @return {@link #address}
	 */
	@Generated
	public Address getAddress() {
		return this.address;
	}
	/**
	 * Create a copy of this PersonAddress object with a new value for field {@link #address}.<br>
	 * @param address The new value for field {@link #address}
	 * @return A new instance of {@link PersonAddress}
	 */
	@Generated
	public PersonAddress withAddress(Address address) {
		return new PersonAddress(personId, relationCode, address);
	}
	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof PersonAddress == false) return false;
		PersonAddress obj = (PersonAddress) o;
		if(personId != obj.personId) return false;
		if(!relationCode.equals(obj.relationCode)) return false;
		if(!address.equals(obj.address)) return false;
		return true;
	}
	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (int) (this.personId ^ (this.personId >>> 32));
		result = 31 * result + (this.relationCode != null ? this.relationCode.hashCode() : 0);
		result = 31 * result + (this.address != null ? this.address.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public String toString() {
		return "PersonAddress[" +
			"personId=" + personId +
			", relationCode=" + relationCode + 
			", address=" + address + 
			']';
	}
	@Generated
	public PersonAddress updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setPersonId(this.personId);
		b.setRelationCode(this.relationCode);
		b.setAddress(this.address);
		b = updater.apply(b);
		return new PersonAddress(b.personId, b.relationCode, b.address);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static PersonAddress build(
		ThrowingFunction<Builder<NOT, NOT, NOT>, Builder<SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new PersonAddress(b.personId, b.relationCode, b.address);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Result<PersonAddress> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT>, Builder<SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new PersonAddress(b.personId, b.relationCode, b.address));
	}
}
