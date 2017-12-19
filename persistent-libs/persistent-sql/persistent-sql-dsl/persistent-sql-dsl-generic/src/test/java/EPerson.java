import com.persistentbit.sql.dsl.generic.expressions.DExpr;
import com.persistentbit.sql.dsl.generic.expressions.ELong;
import com.persistentbit.sql.dsl.generic.expressions.EString;

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

	public EPerson(ELong id, EString firstName, EString lastName) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

}
