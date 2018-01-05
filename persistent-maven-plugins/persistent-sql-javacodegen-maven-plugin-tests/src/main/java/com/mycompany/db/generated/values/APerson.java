package com.mycompany.db.generated.values;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.functions.ThrowingFunction;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NOT;
import com.persistentbit.javacodegen.annotations.SET;
import com.persistentbit.result.Result;
import com.persistentbit.string.UString;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class APerson{

	private final long   id;
	private final String userName;
	private final String password;
	private final String street;
	private final int    houseNumber;
	@Nullable
	private final String busNumber;
	private final String postalcode;
	private final String city;
	private final String country;


	@Generated
	public APerson(long id, String userName, String password, String street, int houseNumber,
				   @Nullable String busNumber, String postalcode, String city, String country) {
		this.id = Objects.requireNonNull(id, "id can not be null");
		this.userName = Objects.requireNonNull(userName, "userName can not be null");
		this.password = Objects.requireNonNull(password, "password can not be null");
		this.street = Objects.requireNonNull(street, "street can not be null");
		this.houseNumber = Objects.requireNonNull(houseNumber, "houseNumber can not be null");
		this.busNumber = busNumber;
		this.postalcode = Objects.requireNonNull(postalcode, "postalcode can not be null");
		this.city = Objects.requireNonNull(city, "city can not be null");
		this.country = Objects.requireNonNull(country, "country can not be null");
	}

	@Generated
	public APerson(long id, String userName, String password, String street, int houseNumber, String postalcode,
				   String city, String country) {
		this(id, userName, password, street, houseNumber, null, postalcode, city, country);
	}

	@Generated
	@SuppressWarnings("unchecked")
	static public class Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, _T8>{

		private long   id;
		private String userName;
		private String password;
		private String street;
		private int    houseNumber;
		private String busNumber;
		private String postalcode;
		private String city;
		private String country;


		public Builder<SET, _T2, _T3, _T4, _T5, _T6, _T7, _T8> setId(long id) {
			this.id = id;
			return (Builder<SET, _T2, _T3, _T4, _T5, _T6, _T7, _T8>) this;
		}

		public Builder<_T1, SET, _T3, _T4, _T5, _T6, _T7, _T8> setUserName(String userName) {
			this.userName = userName;
			return (Builder<_T1, SET, _T3, _T4, _T5, _T6, _T7, _T8>) this;
		}

		public Builder<_T1, _T2, SET, _T4, _T5, _T6, _T7, _T8> setPassword(String password) {
			this.password = password;
			return (Builder<_T1, _T2, SET, _T4, _T5, _T6, _T7, _T8>) this;
		}

		public Builder<_T1, _T2, _T3, SET, _T5, _T6, _T7, _T8> setStreet(String street) {
			this.street = street;
			return (Builder<_T1, _T2, _T3, SET, _T5, _T6, _T7, _T8>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, SET, _T6, _T7, _T8> setHouseNumber(int houseNumber) {
			this.houseNumber = houseNumber;
			return (Builder<_T1, _T2, _T3, _T4, SET, _T6, _T7, _T8>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, _T8> setBusNumber(@Nullable String busNumber) {
			this.busNumber = busNumber;
			return this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, SET, _T7, _T8> setPostalcode(String postalcode) {
			this.postalcode = postalcode;
			return (Builder<_T1, _T2, _T3, _T4, _T5, SET, _T7, _T8>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, _T6, SET, _T8> setCity(String city) {
			this.city = city;
			return (Builder<_T1, _T2, _T3, _T4, _T5, _T6, SET, _T8>) this;
		}

		public Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, SET> setCountry(String country) {
			this.country = country;
			return (Builder<_T1, _T2, _T3, _T4, _T5, _T6, _T7, SET>) this;
		}
	}

	/**
	 * Get the value of field {@link #id}.<br>
	 *
	 * @return {@link #id}
	 */
	@Generated
	public long getId() {
		return this.id;
	}

	/**
	 * Create a copy of this APerson object with a new value for field {@link #id}.<br>
	 *
	 * @param id The new value for field {@link #id}
	 *
	 * @return A new instance of {@link APerson}
	 */
	@Generated
	public APerson withId(long id) {
		return new APerson(id, userName, password, street, houseNumber, busNumber, postalcode, city, country);
	}

	/**
	 * Get the value of field {@link #userName}.<br>
	 *
	 * @return {@link #userName}
	 */
	@Generated
	public String getUserName() {
		return this.userName;
	}

	/**
	 * Create a copy of this APerson object with a new value for field {@link #userName}.<br>
	 *
	 * @param userName The new value for field {@link #userName}
	 *
	 * @return A new instance of {@link APerson}
	 */
	@Generated
	public APerson withUserName(String userName) {
		return new APerson(id, userName, password, street, houseNumber, busNumber, postalcode, city, country);
	}

	/**
	 * Get the value of field {@link #password}.<br>
	 *
	 * @return {@link #password}
	 */
	@Generated
	public String getPassword() {
		return this.password;
	}

	/**
	 * Create a copy of this APerson object with a new value for field {@link #password}.<br>
	 *
	 * @param password The new value for field {@link #password}
	 *
	 * @return A new instance of {@link APerson}
	 */
	@Generated
	public APerson withPassword(String password) {
		return new APerson(id, userName, password, street, houseNumber, busNumber, postalcode, city, country);
	}

	/**
	 * Get the value of field {@link #street}.<br>
	 *
	 * @return {@link #street}
	 */
	@Generated
	public String getStreet() {
		return this.street;
	}

	/**
	 * Create a copy of this APerson object with a new value for field {@link #street}.<br>
	 *
	 * @param street The new value for field {@link #street}
	 *
	 * @return A new instance of {@link APerson}
	 */
	@Generated
	public APerson withStreet(String street) {
		return new APerson(id, userName, password, street, houseNumber, busNumber, postalcode, city, country);
	}

	/**
	 * Get the value of field {@link #houseNumber}.<br>
	 *
	 * @return {@link #houseNumber}
	 */
	@Generated
	public int getHouseNumber() {
		return this.houseNumber;
	}

	/**
	 * Create a copy of this APerson object with a new value for field {@link #houseNumber}.<br>
	 *
	 * @param houseNumber The new value for field {@link #houseNumber}
	 *
	 * @return A new instance of {@link APerson}
	 */
	@Generated
	public APerson withHouseNumber(int houseNumber) {
		return new APerson(id, userName, password, street, houseNumber, busNumber, postalcode, city, country);
	}

	/**
	 * Get the value of field {@link #busNumber}.<br>
	 *
	 * @return {@link #busNumber}
	 */
	@Generated
	public Optional<String> getBusNumber() {
		return Optional.ofNullable(this.busNumber);
	}

	/**
	 * Create a copy of this APerson object with a new value for field {@link #busNumber}.<br>
	 *
	 * @param busNumber The new value for field {@link #busNumber}
	 *
	 * @return A new instance of {@link APerson}
	 */
	@Generated
	public APerson withBusNumber(@Nullable String busNumber) {
		return new APerson(id, userName, password, street, houseNumber, busNumber, postalcode, city, country);
	}

	/**
	 * Get the value of field {@link #postalcode}.<br>
	 *
	 * @return {@link #postalcode}
	 */
	@Generated
	public String getPostalcode() {
		return this.postalcode;
	}

	/**
	 * Create a copy of this APerson object with a new value for field {@link #postalcode}.<br>
	 *
	 * @param postalcode The new value for field {@link #postalcode}
	 *
	 * @return A new instance of {@link APerson}
	 */
	@Generated
	public APerson withPostalcode(String postalcode) {
		return new APerson(id, userName, password, street, houseNumber, busNumber, postalcode, city, country);
	}

	/**
	 * Get the value of field {@link #city}.<br>
	 *
	 * @return {@link #city}
	 */
	@Generated
	public String getCity() {
		return this.city;
	}

	/**
	 * Create a copy of this APerson object with a new value for field {@link #city}.<br>
	 *
	 * @param city The new value for field {@link #city}
	 *
	 * @return A new instance of {@link APerson}
	 */
	@Generated
	public APerson withCity(String city) {
		return new APerson(id, userName, password, street, houseNumber, busNumber, postalcode, city, country);
	}

	/**
	 * Get the value of field {@link #country}.<br>
	 *
	 * @return {@link #country}
	 */
	@Generated
	public String getCountry() {
		return this.country;
	}

	/**
	 * Create a copy of this APerson object with a new value for field {@link #country}.<br>
	 *
	 * @param country The new value for field {@link #country}
	 *
	 * @return A new instance of {@link APerson}
	 */
	@Generated
	public APerson withCountry(String country) {
		return new APerson(id, userName, password, street, houseNumber, busNumber, postalcode, city, country);
	}

	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof APerson == false) return false;
		APerson obj = (APerson) o;
		if(id != obj.id) return false;
		if(!userName.equals(obj.userName)) return false;
		if(!password.equals(obj.password)) return false;
		if(!street.equals(obj.street)) return false;
		if(houseNumber != obj.houseNumber) return false;
		if(busNumber != null ? !busNumber.equals(obj.busNumber) : obj.busNumber != null) return false;
		if(!postalcode.equals(obj.postalcode)) return false;
		if(!city.equals(obj.city)) return false;
		if(!country.equals(obj.country)) return false;
		return true;
	}

	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (int) (this.id ^ (this.id >>> 32));
		result = 31 * result + (this.userName != null ? this.userName.hashCode() : 0);
		result = 31 * result + (this.password != null ? this.password.hashCode() : 0);
		result = 31 * result + (this.street != null ? this.street.hashCode() : 0);
		result = 31 * result + this.houseNumber;
		result = 31 * result + (this.busNumber != null ? this.busNumber.hashCode() : 0);
		result = 31 * result + (this.postalcode != null ? this.postalcode.hashCode() : 0);
		result = 31 * result + (this.city != null ? this.city.hashCode() : 0);
		result = 31 * result + (this.country != null ? this.country.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "APerson[" +
			"id=" + id +
			", userName=" + (userName == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(userName), 32, "...") + '\"') +
			", password=" + (password == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(password), 32, "...") + '\"') +
			", street=" + (street == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(street), 32, "...") + '\"') +
			", houseNumber=" + houseNumber +
			", busNumber=" + (busNumber == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(busNumber), 32, "...") + '\"') +
			", postalcode=" + (postalcode == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(postalcode), 32, "...") + '\"') +
			", city=" + (city == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(city), 32, "...") + '\"') +
			", country=" + (country == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(country), 32, "...") + '\"') +
			']';
	}

	@Generated
	public APerson updated(Function<Builder, Builder> updater) {
		Builder b = new Builder();
		b.setId(this.id);
		b.setUserName(this.userName);
		b.setPassword(this.password);
		b.setStreet(this.street);
		b.setHouseNumber(this.houseNumber);
		b.setBusNumber(this.busNumber);
		b.setPostalcode(this.postalcode);
		b.setCity(this.city);
		b.setCountry(this.country);
		b = updater.apply(b);
		return new APerson(b.id, b.userName, b.password, b.street, b.houseNumber, b.busNumber, b.postalcode, b.city, b.country);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static APerson build(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT, NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET, SET, SET, SET, SET>, Exception> setter) {
		Builder b = setter.toNonChecked().apply(new Builder());
		return new APerson(b.id, b.userName, b.password, b.street, b.houseNumber, b.busNumber, b.postalcode, b.city, b.country);
	}

	@Generated
	@SuppressWarnings("unchecked")
	public static Result<APerson> buildExc(
		ThrowingFunction<Builder<NOT, NOT, NOT, NOT, NOT, NOT, NOT, NOT>, Builder<SET, SET, SET, SET, SET, SET, SET, SET>, Exception> setter) {
		return Result.noExceptions(() -> setter.apply(new Builder<>()))
			.mapExc(b -> new APerson(b.id, b.userName, b.password, b.street, b.houseNumber, b.busNumber, b.postalcode, b.city, b.country));
	}
}
