/**
 * TODOC
 *
 * @author petermuys
 * @since 19/12/17
 */
public class Person{
	private final Long id;
	private final String firstName;
	private final String lastName;
	private final Address home;

	public Person(Long id, String firstName, String lastName,Address home) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.home = home;
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
}
