import java.time.LocalDateTime;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/12/17
 */
public class Tag{

	private final Long          id;
	private final String        name;
	private final LocalDateTime created;

	public Tag(Long id, String name, LocalDateTime created) {
		this.id = id;
		this.name = name;
		this.created = created;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public Tag withId(Long id) {
		return new Tag(id, name, created);
	}

	@Override
	public String toString() {
		return "Tag{" +
			"id=" + id +
			", name='" + name + '\'' +
			", created=" + created +
			'}';
	}
}
