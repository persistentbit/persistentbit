package com.persistentbit.dsl.tests;

import com.persistentbit.code.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * TODOC
 *
 * @author petermuys
 * @since 19/12/17
 */
public class Person{

	private final Long          id;
	private final String        firstName;
	@Nullable
	private final String        middleName;
	private final String        lastName;
	private final Address       home;
	private final LocalDateTime created;

	public Person(Long id, String firstName, @Nullable String middleName, String lastName, Address home,
				  LocalDateTime created
	) {
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.home = home;
		this.created = created;
	}

	public Optional<String> getMiddleName() {
		return Optional.ofNullable(middleName);
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Address getHome() {
		return home;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public Person withHome(Address home) {
		return new Person(id, firstName, middleName, lastName, home, created);
	}

	@Override
	public String toString() {
		return "Person{" +
			"id=" + id +
			", firstName='" + firstName + '\'' +
			", middleName='" + middleName + '\'' +
			", lastName='" + lastName + '\'' +
			", home=" + home +
			", created=" + created +
			'}';
	}
}
