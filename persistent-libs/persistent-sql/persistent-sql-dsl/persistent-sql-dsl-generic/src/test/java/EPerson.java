import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EDateTime;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;

/**
 * Generated {@link DExpr} for Table ...
 *
 * @author petermuys
 * @since 19/12/17
 */
public abstract class EPerson implements DExpr<Person>{

	public final ELong     id;
	public final EString   firstName;
	public final EString   middleName;
	public final EString   lastName;
	public final EAddress  home;
	public final EDateTime created;

	protected EPerson(ELong id, EString firstName, EString middleName, EString lastName, EAddress home,
					  EDateTime created
	) {

		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.home = home;
		this.created = created;
	}

}
