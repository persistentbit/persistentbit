package com.persistentbit.people;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.CaseClass;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;
import com.persistentbit.string.UString;

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
public class Address{

	private final long   addressId;
	private final String streetLine1;
	@Nullable
	private final String streetLine2;
	private final String postalCode;
	private final String cityName;
	private final String countryCode;
	@Nullable
	private final String district;
	
	
	@Generated
	public Address(long addressId, String streetLine1, @Nullable String streetLine2, String postalCode, String cityName,
				   String countryCode, @Nullable String district) {
		this.addressId = Objects.requireNonNull(addressId, "addressId can not be null");
		this.streetLine1 = Objects.requireNonNull(streetLine1, "streetLine1 can not be null");
		this.streetLine2 = streetLine2;
		this.postalCode = Objects.requireNonNull(postalCode, "postalCode can not be null");
		this.cityName = Objects.requireNonNull(cityName, "cityName can not be null");
		this.countryCode = Objects.requireNonNull(countryCode, "countryCode can not be null");
		this.district = district;
	}
	@Generated
	public Address(long addressId, String streetLine1, String postalCode, String cityName, String countryCode) {
		this(addressId, streetLine1, null, postalCode, cityName, countryCode, null);
	}
	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3, _T4, _T5>{

		private long   addressId;
		private String streetLine1;
		private String streetLine2;
		private String postalCode;
		private String cityName;
		private String countryCode;
		private String district;


		public Builder<SET, _T2, _T3, _T4, _T5> setAddressId(long addressId) {
			this.addressId = addressId;
			return (Builder<SET, _T2, _T3, _T4, _T5>) this;
		}

		public Builder<_T1, SET, _T3, _T4, _T5> setStreetLine1(String streetLine1) {
			this.streetLine1 = streetLine1;
			return (Builder<_T1, SET, _T3, _T4, _T5>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setStreetLine2(@Nullable String streetLine2) {
			this.streetLine2 = streetLine2;
			return this;
		}

		public Builder<_T1, _T2, SET, _T4, _T5> setPostalCode(String postalCode) {
			this.postalCode = postalCode;
			return (Builder<_T1, _T2, SET, _T4, _T5>) this;
		}

		public Builder<_T1, _T2, _T3, SET, _T5> setCityName(String cityName) {
			this.cityName = cityName;
			return (Builder<_T1, _T2, _T3, SET, _T5>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, SET> setCountryCode(String countryCode) {
			this.countryCode = countryCode;
			return (Builder<_T1, _T2, _T3, _T4, SET>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5> setDistrict(@Nullable String district) {
			this.district = district;
			return this;
		}
	}
	/**
	 * Get the value of field {@link #addressId}.<br>
	 * @return {@link #addressId}
	 */
	@Generated
	public long getAddressId() {
		return this.addressId;
	}
	/**
	 * Create a copy of this Address object with a new value for field {@link #addressId}.<br>
	 * @param addressId The new value for field {@link #addressId}
	 * @return A new instance of {@link Address}
	 */
	@Generated
	public Address withAddressId(long addressId) {
		return new Address(addressId, streetLine1, streetLine2, postalCode, cityName, countryCode, district);
	}
	/**
	 * Get the value of field {@link #streetLine1}.<br>
	 * @return {@link #streetLine1}
	 */
	@Generated
	public String getStreetLine1() {
		return this.streetLine1;
	}
	/**
	 * Create a copy of this Address object with a new value for field {@link #streetLine1}.<br>
	 * @param streetLine1 The new value for field {@link #streetLine1}
	 * @return A new instance of {@link Address}
	 */
	@Generated
	public Address withStreetLine1(String streetLine1) {
		return new Address(addressId, streetLine1, streetLine2, postalCode, cityName, countryCode, district);
	}
	/**
	 * Get the value of field {@link #streetLine2}.<br>
	 * @return {@link #streetLine2}
	 */
	@Generated
	public Optional<String> getStreetLine2() {
		return Optional.ofNullable(this.streetLine2);
	}
	/**
	 * Create a copy of this Address object with a new value for field {@link #streetLine2}.<br>
	 * @param streetLine2 The new value for field {@link #streetLine2}
	 * @return A new instance of {@link Address}
	 */
	@Generated
	public Address withStreetLine2(@Nullable String streetLine2) {
		return new Address(addressId, streetLine1, streetLine2, postalCode, cityName, countryCode, district);
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
		return new Address(addressId, streetLine1, streetLine2, postalCode, cityName, countryCode, district);
	}
	/**
	 * Get the value of field {@link #cityName}.<br>
	 * @return {@link #cityName}
	 */
	@Generated
	public String getCityName() {
		return this.cityName;
	}
	/**
	 * Create a copy of this Address object with a new value for field {@link #cityName}.<br>
	 * @param cityName The new value for field {@link #cityName}
	 * @return A new instance of {@link Address}
	 */
	@Generated
	public Address withCityName(String cityName) {
		return new Address(addressId, streetLine1, streetLine2, postalCode, cityName, countryCode, district);
	}
	/**
	 * Get the value of field {@link #countryCode}.<br>
	 * @return {@link #countryCode}
	 */
	@Generated
	public String getCountryCode() {
		return this.countryCode;
	}
	/**
	 * Create a copy of this Address object with a new value for field {@link #countryCode}.<br>
	 * @param countryCode The new value for field {@link #countryCode}
	 * @return A new instance of {@link Address}
	 */
	@Generated
	public Address withCountryCode(String countryCode) {
		return new Address(addressId, streetLine1, streetLine2, postalCode, cityName, countryCode, district);
	}
	/**
	 * Get the value of field {@link #district}.<br>
	 * @return {@link #district}
	 */
	@Generated
	public Optional<String> getDistrict() {
		return Optional.ofNullable(this.district);
	}
	/**
	 * Create a copy of this Address object with a new value for field {@link #district}.<br>
	 * @param district The new value for field {@link #district}
	 * @return A new instance of {@link Address}
	 */
	@Generated
	public Address withDistrict(@Nullable String district) {
		return new Address(addressId, streetLine1, streetLine2, postalCode, cityName, countryCode, district);
	}
	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof Address == false) return false;
		Address obj = (Address) o;
		if(addressId != obj.addressId) return false;
		if(!streetLine1.equals(obj.streetLine1)) return false;
		if(streetLine2 != null ? !streetLine2.equals(obj.streetLine2) : obj.streetLine2 != null) return false;
		if(!postalCode.equals(obj.postalCode)) return false;
		if(!cityName.equals(obj.cityName)) return false;
		if(!countryCode.equals(obj.countryCode)) return false;
		if(district != null ? !district.equals(obj.district) : obj.district != null) return false;
		return true;
	}
	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (int) (this.addressId ^ (this.addressId >>> 32));
		result = 31 * result + (this.streetLine1 != null ? this.streetLine1.hashCode() : 0);
		result = 31 * result + (this.streetLine2 != null ? this.streetLine2.hashCode() : 0);
		result = 31 * result + (this.postalCode != null ? this.postalCode.hashCode() : 0);
		result = 31 * result + (this.cityName != null ? this.cityName.hashCode() : 0);
		result = 31 * result + (this.countryCode != null ? this.countryCode.hashCode() : 0);
		result = 31 * result + (this.district != null ? this.district.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public String toString() {
		return "Address[" +
			"addressId=" + addressId +
			", streetLine1=" + (streetLine1 == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(streetLine1), 32, "...") + '\"') +
			", streetLine2=" + (streetLine2 == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(streetLine2), 32, "...") + '\"') +
			", postalCode=" + (postalCode == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(postalCode), 32, "...") + '\"') +
			", cityName=" + (cityName == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(cityName), 32, "...") + '\"') +
			", countryCode=" + (countryCode == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(countryCode), 32, "...") + '\"') +
			", district=" + (district == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(district), 32, "...") + '\"') +
			']';
	}
	@Generated
	public Address updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setAddressId(this.addressId);
		b.setStreetLine1(this.streetLine1);
		b.setStreetLine2(this.streetLine2);
		b.setPostalCode(this.postalCode);
		b.setCityName(this.cityName);
		b.setCountryCode(this.countryCode);
		b.setDistrict(this.district);
		b = updater.apply(b);
		return new Address(b.addressId, b.streetLine1, b.streetLine2, b.postalCode, b.cityName, b.countryCode, b.district);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Address build(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new Address(b.addressId, b.streetLine1, b.streetLine2, b.postalCode, b.cityName, b.countryCode, b.district);
	}
	@Generated
	@SuppressWarnings("unchecked")
	public static Result<Address> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new Address(b.addressId, b.streetLine1, b.streetLine2, b.postalCode, b.cityName, b.countryCode, b.district));
	}
}
