import com.persistentbit.logging.ModuleLogging;
import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.select.TypedSelection1;
import com.persistentbit.sql.dsl.statements.select.impl.SubQuery1;
import com.persistentbit.test.TestCase;
import com.persistentbit.test.TestRunner;

/**
 * TODOC
 *
 * @author petermuys
 * @since 19/12/17
 */
public class TypesTest{


	static ExprContext context = new ExprContext();


	static class Db{

		private final ExprContext context;

		public final TPerson person;

		public Db(ExprContext context) {
			this.context = context;
			this.person = new TPerson(context);
			context.addTable(this.person);
		}

		public EBool val(Boolean v) {
			return context.val(v);
		}

		public EInt val(Integer v) {
			return context.val(v);
		}

		public ELong val(Long v) {
			return context.val(v);
		}

		public EString val(String v) {
			return context.val(v);
		}

		public <E1 extends DExpr<J1>, J1, E2 extends DExpr<J2>, J2> ETuple2<E1, J1, E2, J2> tupleOf(E1 e1, E2 e2) {
			return context.of(e1, e2);
		}
	}


	static final TestCase sqlGenTest = TestCase.name("sqlGenTest").code(tc -> {
		context.addType(new PersonTypeFactory(context));
		context.addType(new AddressTypeFactory(context));
		context.addTable(new TPerson(context, null));
		EPerson personTable = context.getTypeFactory(EPerson.class).buildTableField("PERSON_TABLE.", "");
		System.out.println(context.toSql(personTable));

		Address adr = new Address("SnoekStraat 10", "9000", "Gent");

		EPerson personValue = context.getTypeFactory(EPerson.class).buildVal(new Person(1234l, "Peter", "Muys", adr));

		System.out.println(context.toSql(personValue));

		EPerson personAlias = context.getTypeFactory(EPerson.class).buildAlias("menchen.");
		System.out.println(context.toSql(personAlias));

		Db      db      = new Db(context);
		TPerson menchen = db.person.as("menchen");
		TypedSelection1<EPerson, Person> sel1 =
			menchen.query()
				   .where(
					   menchen.id.gt(1234l)
								 .or(menchen.home.city.like("9000"))
				   )
				   .selection(menchen._all);
		System.out.println(sel1);
		System.out.println(sel1.asSubQuery("sq").v1);
		System.out.println(sel1.asSubQuery("sq").toSql());
		System.out.println(
			menchen
				.query()
				.selection(menchen.query().where(menchen.id.eq(100L)).selection(menchen.id).asExpr())
		);
		System.out.println("----");
		SubQuery1<EPerson, Person> subQueryTable = db.person.query()
															.selection(db.person._all)
															.asSubQuery("subPersonen");
		System.out.println(
			subQueryTable
				.query()
				.where(subQueryTable.v1.lastName.like("Muys"))
				.selection(subQueryTable.v1)
		);
		System.out.println(
			menchen
				.query()
				.selection(db.tupleOf(db.tupleOf(menchen.id, menchen.firstName), menchen.home))
		);
	});

	public void testAll() {

		TestRunner.runAndPrint(ModuleLogging.consoleLogPrint, TypesTest.class);
	}

	public static void main(String[] args) {
		new TypesTest().testAll();
	}
}
