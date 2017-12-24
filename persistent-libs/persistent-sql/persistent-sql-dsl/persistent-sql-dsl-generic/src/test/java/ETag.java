import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EDateTime;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/12/17
 */
public abstract class ETag implements DExpr<Tag>{

	public final ELong     id;
	public final EString   name;
	public final EDateTime created;

	public ETag(ELong id, EString name, EDateTime created) {
		this.id = id;
		this.name = name;
		this.created = created;
	}
}
