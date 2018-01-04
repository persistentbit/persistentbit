package com.generated.values;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;
import com.persistentbit.string.UString;

import java.util.Objects;
import java.util.function.Function;

public class Address{

	private final String street;
	private final String postalCode;
	private final String city;
	
	
	@Generated
	public Address(String street, String postalCode, String city) {
		this.street = Objects.requireNonNull(street, "street can not be null");
		this.postalCode = Objects.requireNonNull(postalCode, "postalCode can not be null");
		this.city = Objects.requireNonNull(city, "city can not be null");
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3>{

		private String street;
		private String postalCode;
		private String city;


		public Builder<SET, _T2, _T3> setStreet(String street) {
			this.street = street;
			return (Builder<SET, _T2, _T3>) this;
		}

		public Builder<_T1, SET, _T3> setPostalCode(String postalCode) {
			this.postalCode = postalCode;
			return (Builder<_T1, SET, _T3>) this;
		}

		public Builder<_T1, _T2, SET> setCity(String city) {
			this.city = city;
			return (Builder<_T1, _T2, SET>) this;
		}
	}
	/**
	 * Get the value of field {@link #street}.<br>
	 * @return {@link #street}
	 */
	@Generated
	public String getStreet() {
		return this.street;
	}
	/**
	 * Create a copy of this Address object with a new value for field {@link #street}.<br>
	 * @param street The new value for field {@link #street}
	 * @return A new instance of {@link Address}
	 */
	@Generated
	public Address withStreet(String street) {
		return new Address(street, postalCode, city);
	}
	/**
	 * Get the value of field {@link #postalCode}.<br>
	 * @return {@link #postalCode}
	 */
	@Generated
	public String getPostalCode() {
		return this.postalCode;
	}
	/**
	 * Create a copy of this Address object with a new value for field {@link #postalCode}.<br>
	 * @param postalCode The new value for field {@link #postalCode}
	 * @return A new instance of {@link Address}
	 */
	@Generated
	public Address withPostalCode(String postalCode) {
		return new Address(street, postalCode, city);
	}
	/**
	 * Get the value of field {@link #city}.<br>
	 * @return {@link #city}
	 */
	@Generated
	public String getCity() {
		return this.city;
	}
	/**
	 * Create a copy of this Address object with a new value for field {@link #city}.<br>
	 * @param city The new value for field {@link #city}
	 * @return A new instance of {@link Address}
	 */
	@Generated
	public Address withCity(String city) {
		return new Address(street, postalCode, city);
	}
	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof Address == false) return false;
		Address obj = (Address) o;
		if(!street.equals(obj.street)) return false;
		if(!postalCode.equals(obj.postalCode)) return false;
		if(!city.equals(obj.city)) return false;
		return true;
	}
	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (this.street != null ? this.street.hashCode() : 0);
		result = 31 * result + (this.postalCode != null ? this.postalCode.hashCode() : 0);
		result = 31 * result + (this.city != null ? this.city.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public String toString() {
		return "Address[" +
			"street=" + (street == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(street), 32, "...") + '\"') +
			", postalCode=" + (postalCode == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(postalCode), 32, "...") + '\"') +
			", city=" + (city == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(city), 32, "...") + '\"') +
			']';
	}
	@Generated
	public Address updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setStreet(this.street);
		b.setPostalCode(this.postalCode);
		b.setCity(this.city);
		b = updater.apply(b);
		return new Address(b.street, b.postalCode, b.city);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Address build(ThrowingFunction<Builder<NOT, NOT, NOT>, Builder<SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new Address(b.street, b.postalCode, b.city);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Result<Address> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT>, Builder<SET, SET, SET>, Exception> setter
	) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new Address(b.street, b.postalCode, b.city));
	}
}
