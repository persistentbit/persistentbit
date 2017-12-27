package com.persistentbit.dsl.tests;

/**
 * TODOC
 *
 * @author petermuys
 * @since 19/12/17
 */
public class Address{

	private final String street;
	private final String postalCode;
	private final String city;

	public Address(String street, String postalCode, String city) {
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getCity() {
		return city;
	}

	@Override
	public String toString() {
		return "Address{" +
			"street='" + street + '\'' +
			", postalCode='" + postalCode + '\'' +
			", city='" + city + '\'' +
			'}';
	}
}
