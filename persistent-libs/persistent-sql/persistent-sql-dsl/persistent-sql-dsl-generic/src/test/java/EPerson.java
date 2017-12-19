import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;

/**
 * TODOC
 *
 * @author petermuys
 * @since 19/12/17
 */
public abstract class EPerson implements DExpr<Person>{

	public final ELong   id;
	public final EString firstName;
	public final EString lastName;
	public final EAddress home;

	public EPerson(ELong id, EString firstName, EString lastName,EAddress home) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.home = home;
	}

}
