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

import java.time.LocalDateTime;
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

		public final TTag tags;

		public Db(ExprContext context) {
			this.context = context;
			this.context.registerType(EPerson.class, PersonTypeFactory.class);
			this.context.registerType(ETag.class, TagTypeFactory.class);
			this.context.registerType(EAddress.class, AddressTypeFactory.class);
			this.person = new TPerson(context);
			this.tags = new TTag(context);
			context.addTable(this.person);
		}

		public Db() {
			this(new ExprContext());
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

	static public class DbInst extends Db{



		public final DbWorkP1<Long, Person>                  selectPersonById;
		public final DbWorkP2<String, String, PList<Person>> selectByName;
		public final DbWorkP1<Address, PList<Person>>        selectByAddress;

		public DbInst() {
			selectPersonById = person.query(p -> q -> {

				Param<ELong> idParam = param("id", ELong.class);

				return q
					.where(idParam.getExpr().eq(p.id))
					.selection(p)
					.one(idParam);
			});
			selectByName = person.query(person -> q -> {
				Param<EString> firstName = param("firstName", EString.class);
				Param<EString> lastName  = param("lastName", EString.class);
				return q
					.where(
						person.firstName.like(firstName.getExpr())
							.and(person.lastName.like(lastName.getExpr()))
					)
					.selection(person)
					.list(firstName, lastName);
			});
			selectByAddress = person.as("menschen").query(menchen -> q -> {
				Param<EAddress> adr = param("adress", EAddress.class);

				return q
					.where(menchen.home.eq(adr.getExpr()))
					.selection(menchen)
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
		context.registerType(EPerson.class, PersonTypeFactory.class);
		context.registerType(EAddress.class, AddressTypeFactory.class);
		context.addTable(new TPerson(context, null));
		EPerson personTable = context.getTypeFactory(EPerson.class).buildTableField("PERSON_TABLE.", "");
		System.out.println(context.toSql(personTable));

		Address adr = new Address("SnoekStraat 10", "9000", "Gent");

		EPerson personValue =
			context.getTypeFactory(EPerson.class)
				.buildVal(new Person(1234l, "Peter", null, "Muys", adr, LocalDateTime.now()));

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

		DbInst myDb  = new DbInst();
		Person peter = myDb.selectPersonById.with(1L).run(newTrans.get()).orElseThrow();
		System.out.println(peter);
		System.out.println(myDb.selectByName.with("Peter", "Muys").run(newTrans.get()).orElseThrow());
		System.out.println(myDb.selectByAddress.with(peter.getHome()).run(newTrans.get()).orElseThrow());
		myDb.tags.insert(null, "a tag", null).run(newTrans.get()).orElseThrow();
		System.out.println(myDb.tags.selectAll().run(newTrans.get()).orElseThrow());

	});

	public void testAll() {

		TestRunner.runAndPrint(ModuleLogging.consoleLogPrint, TypesTest.class);
	}

	public static void main(String[] args) {
		new TypesTest().testAll();
	}
}
