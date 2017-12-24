import com.persistentbit.collections.PList;
import com.persistentbit.collections.PStream;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.expressions.EDateTime;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeImpl;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.AbstractStructureTypeFactory;
import com.persistentbit.sql.dsl.expressions.impl.typeimpl.StructureField;

import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * Generated {@link ExprTypeFactory} for {@link EPerson}
 *
 * @author petermuys
 * @since 19/12/17
 */
public class PersonTypeFactory extends AbstractStructureTypeFactory<EPerson, Person>{

	public PersonTypeFactory(ExprContext context) {
		super(context);
	}

	@Override
	protected PList<StructureField<EPerson, Person>> buildFields() {
		return PList.val(
			createField(ELong.class, "id", "id", Person::getId, v -> v.id)
			, createField(EString.class, "first_name", "firstName", Person::getFirstName, v -> v.firstName)
			, createField(EString.class, "middle_name", "middleName", v -> v.getMiddleName()
				.orElse(null), v -> v.middleName)
			, createField(EString.class, "last_name", "lastName", Person::getLastName, v -> v.lastName)
			, createField(EAddress.class, "home_", "home", Person::getHome, v -> v.home)
			, createField(EDateTime.class, "created", "created", Person::getCreated, v -> v.created)
		);
	}


	@Override
	protected Person buildValue(Object[] fieldValues) {
		return new Person((Long) fieldValues[0], (String) fieldValues[1], (String) fieldValues[2], (String) fieldValues[3], (Address) fieldValues[4], (LocalDateTime) fieldValues[5]);
	}

	@Override
	protected EPersonImpl createExpression(PStream<DExpr> fieldValues) {
		return new EPersonImpl(fieldValues.iterator());
	}

	@Override
	public Class<? extends DExpr<Person>> getTypeClass() {
		return EPerson.class;
	}

	private final class EPersonImpl extends EPerson implements ExprTypeImpl<EPerson, Person>{

		private EPersonImpl(Iterator<DExpr> iter) {
			super(
				(ELong) iter.next(),
				(EString) iter.next(),
				(EString) iter.next(),
				(EString) iter.next(),
				(EAddress) iter.next(),
				(EDateTime) iter.next()
			);
		}

		@Override
		public ExprTypeFactory<EPerson, Person> getTypeFactory() {
			return PersonTypeFactory.this;
		}

		@Override
		public String toString() {
			return "EPerson[" + id + ", " + firstName + ", " + middleName + ", " + lastName + ", " + home + ", " + created + "]";
		}
	}
}
