import com.persistentbit.collections.PList;
import com.persistentbit.collections.PMap;
import com.persistentbit.logging.ModuleLogging;
import com.persistentbit.result.OK;
import com.persistentbit.result.Result;
import com.persistentbit.sql.connect.DbConnector;
import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.expressions.impl.ExprTypeFactory;
import com.persistentbit.sql.dsl.statements.select.TypedSelection1;
import com.persistentbit.sql.dsl.statements.select.impl.SubQuery1;
import com.persistentbit.sql.dsl.statements.work.DbWorkP1;
import com.persistentbit.sql.dsl.statements.work.DbWorkP2;
import com.persistentbit.sql.transactions.DbTransaction;
import com.persistentbit.sql.updater.DbScriptRunner;
import com.persistentbit.sql.updater.SqlSnippets;
import com.persistentbit.test.TestCase;
import com.persistentbit.test.TestRunner;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 19/12/17
 */
public class TypesTest{


	static ExprContext context = new ExprContext();

	static DbConnector con = DbConnector
		.fromUrl("org.h2.Driver", "jdbc:h2:mem:db1", "sa", null)
		.map(p -> p.pooledConnector(10))
		.orElseThrow();

	static Supplier<DbTransaction> newTrans = () -> DbTransaction.create(con);

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

		public	<E1 extends DExpr<J>,J> Param<E1> param(String name, Class<E1> cls){
			ExprTypeFactory<E1,J>                tf     =context.getTypeFactory(cls);
			Function<PMap<String,Object>,Object> getter = m -> m.get(name);
			return new Param<>(name, tf.buildParam(getter));
		}
	}

	static public class DbInst {

		private final Db db;

		public final DbWorkP1<Long, Person>                  selectPersonById;
		public final DbWorkP2<String, String, PList<Person>> selectByName;
		public final DbWorkP1<Address, PList<Person>>        selectByAddress;

		public DbInst(Db db) {
			this.db = db;
			selectPersonById = db.person.query(q -> {

				Param<ELong> idParam = db.param("id", ELong.class);

				return q
					.where(idParam.getExpr().eq(db.person.id))
					.selection(db.person._all)
					.one(idParam);
			});
			selectByName = db.person.query(q -> {
				Param<EString> firstName = db.param("firstName", EString.class);
				Param<EString> lastName  = db.param("lastName", EString.class);
				return q
					.where(
						db.person.firstName.like(firstName.getExpr())
							.and(db.person.lastName.like(lastName.getExpr()))
					)
					.selection(db.person._all)
					.list(firstName, lastName);
			});
			selectByAddress = db.person.query(q -> {
				Param<EAddress> adr = db.param("adress", EAddress.class);
				return q
					.where(db.person.home.eq(adr.getExpr()))
					.selection(db.person.all())
					.list(adr);
			});
		}
	}

	static Result<OK> buildTestDb() {
		return SqlSnippets.load(TypesTest.class.getResourceAsStream("/person_test_db.sql"))
			.map(snip -> {
				System.out.println("GOT SNIPPETS " + snip.getAllSnippetNames());
				System.out.println(snip.getAll("all"));
				new DbScriptRunner(con, "test", snip
				).run("all");
				return OK.inst;
			})
			;
	}

	static final TestCase sqlGenTest = TestCase.name("sqlGenTest").code(tc -> {
		context.addType(new PersonTypeFactory(context));
		context.addType(new AddressTypeFactory(context));
		context.addTable(new TPerson(context, null));
		EPerson personTable = context.getTypeFactory(EPerson.class).buildTableField("PERSON_TABLE.", "");
		System.out.println(context.toSql(personTable));

		Address adr = new Address("SnoekStraat 10", "9000", "Gent");

		EPerson personValue =
			context.getTypeFactory(EPerson.class).buildVal(new Person(1234l, "Peter", null, "Muys", adr));

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

		buildTestDb().orElseThrow();

		DbInst myDb  = new DbInst(db);
		Person peter = myDb.selectPersonById.with(1L).run(newTrans.get()).orElseThrow();
		System.out.println(peter);
		System.out.println(myDb.selectByName.with("Peter", "Muys").run(newTrans.get()).orElseThrow());
		System.out.println(myDb.selectByAddress.with(peter.getHome()).run(newTrans.get()));
	});

	public void testAll() {

		TestRunner.runAndPrint(ModuleLogging.consoleLogPrint, TypesTest.class);
	}

	public static void main(String[] args) {
		new TypesTest().testAll();
	}
}
