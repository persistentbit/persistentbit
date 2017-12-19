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

	public Person(Long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
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
}
