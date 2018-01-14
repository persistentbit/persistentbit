package com.pbtest.postgres.values;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;
import com.persistentbit.string.UString;

import java.util.Objects;
import java.util.function.Function;

public class PeopleAddresses{

	private final long   personId;
	private final String addressRelationCode;
	private final long   addressId;


	@Generated
	public PeopleAddresses(long personId, String addressRelationCode, long addressId) {
		this.personId = Objects.requireNonNull(personId, "personId can not be null");
		this.addressRelationCode = Objects.requireNonNull(addressRelationCode, "addressRelationCode can not be null");
		this.addressId = Objects.requireNonNull(addressId, "addressId can not be null");
	}

	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3>{

		private long   personId;
		private String addressRelationCode;
		private long   addressId;


		public Builder<SET, _T2, _T3> setPersonId(long personId) {
			this.personId = personId;
			return (Builder<SET, _T2, _T3>) this;
		}

		public Builder<_T1, SET, _T3> setAddressRelationCode(String addressRelationCode) {
			this.addressRelationCode = addressRelationCode;
			return (Builder<_T1, SET, _T3>) this;
		}

		public Builder<_T1, _T2, SET> setAddressId(long addressId) {
			this.addressId = addressId;
			return (Builder<_T1, _T2, SET>) this;
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
	 * Create a copy of this PeopleAddresses object with a new value for field {@link #personId}.<br>
	 *
	 * @param personId The new value for field {@link #personId}
	 *
	 * @return A new instance of {@link PeopleAddresses}
	 */
	@Generated
	public PeopleAddresses withPersonId(long personId) {
		return new PeopleAddresses(personId, addressRelationCode, addressId);
	}

	/**
	 * Get the value of field {@link #addressRelationCode}.<br>
	 *
	 * @return {@link #addressRelationCode}
	 */
	@Generated
	public String getAddressRelationCode() {
		return this.addressRelationCode;
	}

	/**
	 * Create a copy of this PeopleAddresses object with a new value for field {@link #addressRelationCode}.<br>
	 *
	 * @param addressRelationCode The new value for field {@link #addressRelationCode}
	 *
	 * @return A new instance of {@link PeopleAddresses}
	 */
	@Generated
	public PeopleAddresses withAddressRelationCode(String addressRelationCode) {
		return new PeopleAddresses(personId, addressRelationCode, addressId);
	}

	/**
	 * Get the value of field {@link #addressId}.<br>
	 *
	 * @return {@link #addressId}
	 */
	@Generated
	public long getAddressId() {
		return this.addressId;
	}

	/**
	 * Create a copy of this PeopleAddresses object with a new value for field {@link #addressId}.<br>
	 *
	 * @param addressId The new value for field {@link #addressId}
	 *
	 * @return A new instance of {@link PeopleAddresses}
	 */
	@Generated
	public PeopleAddresses withAddressId(long addressId) {
		return new PeopleAddresses(personId, addressRelationCode, addressId);
	}

	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof PeopleAddresses == false) return false;
		PeopleAddresses obj = (PeopleAddresses) o;
		if(personId != obj.personId) return false;
		if(!addressRelationCode.equals(obj.addressRelationCode)) return false;
		if(addressId != obj.addressId) return false;
		return true;
	}

	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (int) (this.personId ^ (this.personId >>> 32));
		result = 31 * result + (this.addressRelationCode != null ? this.addressRelationCode.hashCode() : 0);
		result = 31 * result + (int) (this.addressId ^ (this.addressId >>> 32));
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "PeopleAddresses[" +
			"personId=" + personId +
			", addressRelationCode=" + (addressRelationCode == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(addressRelationCode), 32, "...") + '\"') +
			", addressId=" + addressId +
			']';
	}

	@Generated
	public PeopleAddresses updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setPersonId(this.personId);
		b.setAddressRelationCode(this.addressRelationCode);
		b.setAddressId(this.addressId);
		b = updater.apply(b);
		return new PeopleAddresses(b.personId, b.addressRelationCode, b.addressId);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static PeopleAddresses build(
		ThrowingFunction<Builder<NOT, NOT, NOT>, Builder<SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new PeopleAddresses(b.personId, b.addressRelationCode, b.addressId);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static Result<PeopleAddresses> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT>, Builder<SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new PeopleAddresses(b.personId, b.addressRelationCode, b.addressId));
	}
}
