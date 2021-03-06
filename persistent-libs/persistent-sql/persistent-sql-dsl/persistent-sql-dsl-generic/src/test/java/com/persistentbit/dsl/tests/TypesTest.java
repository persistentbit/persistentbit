package com.persistentbit.dsl.tests;

import com.persistentbit.logging.ModuleLogging;
import com.persistentbit.result.OK;
import com.persistentbit.result.Result;
import com.persistentbit.sql.connect.DbConnector;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.transactions.DbTransaction;
import com.persistentbit.sql.updater.DbScriptRunner;
import com.persistentbit.sql.updater.SqlSnippets;
import com.persistentbit.test.TestRunner;

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
/*
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

		public <E1 extends DExpr<J>, J> Param<E1> param(String name, Class<E1> cls) {
			ExprTypeFactory<E1, J>                 tf     = context.getTypeFactory(cls);
			Function<PMap<String, Object>, Object> getter = m -> m.get(name);
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
	}*/

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
/*
	static final TestCase sqlGenTest = TestCase.name("sqlGenTest").code(tc -> {
		GenericBinOps.setDefaultBinOpBuilders(context);
		context.registerType(EPerson.class, PersonTypeFactory.class);
		context.registerType(EAddress.class, AddressTypeFactory.class);

		context.addTable(new TPerson(context, null));
		EPerson personTable = context.getTypeFactory(EPerson.class).buildTableField("PERSON_TABLE.", "", "");
		System.out.println(context.toSql(personTable));

		Address adr = new Address("SnoekStraat 10", "9000", "Gent");

		EPerson personValue =
			context.getTypeFactory(EPerson.class)
				.buildVal(new Person(1234l, "Peter", null, "Muys", adr, LocalDateTime.now()));

		System.out.println(context.toSql(personValue));

		EPerson personAlias = context.getTypeFactory(EPerson.class).buildAlias("menchen.");
		System.out.println(context.toSql(personAlias));


		TPerson menchen = person.as("menchen");
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
		SubQuery1<EPerson, Person> subQueryTable = person.query()
			.selection(person._all)
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
				.selection(tupleOf(tupleOf(menchen.id, menchen.firstName), menchen.home))
		);

		buildTestDb().orElseThrow();

		//MyDb myDb  = new DbInst();
		Person peter = selectPersonById.with(1L).run(newTrans.get()).orElseThrow();
		System.out.println(peter);
		System.out.println(selectByName.with("Peter", "Muys").run(newTrans.get()).orElseThrow());
		System.out.println(selectByAddress.with(peter.getHome()).run(newTrans.get()).orElseThrow());
		tags.insert(null, "a tag", null).run(newTrans.get()).orElseThrow();
		tags.insert()
			.add(null, "Tag2", LocalDateTime.now())
			.add(null, "Tag3", null)
			.run(newTrans.get()).orElseThrow();
		person.insert()
			.add(new Person(10L, "Kelly", null, "De Meulemeester", new Address("Korhoenstraat", "9000", "Gent"), LocalDateTime
				.now()))
			.run(newTrans.get()).orElseThrow();
		System.out.println(tags.selectAll().run(newTrans.get()).orElseThrow());


		TimeMeasurement.runAndLog(() -> {
			int          loopCount  = 100;
			PersonInsert insertLoop = person.insert();
			for(int t = 0; t < loopCount; t++) {
				Person p = new Person(t + 100L,
									  "First" + t,
									  "Middle" + t,
									  "Last" + t,
									  new Address("Street" + t, "Post" + t, "city" + t),
									  LocalDateTime.now()
				);
				insertLoop = insertLoop.add(p);
			}
			insertLoop.run(newTrans.get()).orElseThrow();
		});
		System.out.println("Delete count: " + person
			.delete()
			.where(person.id.gtEq(100L))
			.run(newTrans.get())
			.orElseThrow()
		);

		PList<Person> plist =
			TimeMeasurement.runAndLog("selectAll", () -> person.selectAll().run(newTrans.get()).orElseThrow());
		plist.forEach(System.out::println);
		person.update(peter.withHome(new Address("Schoolkaai 27", "9000", "Gent"))).run(newTrans.get())
			.orElseThrow();
		System.out.println(
			person.selectById(peter.getId())
				.run(newTrans.get())
				.orElseThrow());

	});*/

	public void testAll() {

		TestRunner.runAndPrint(ModuleLogging.consoleLogPrint, TypesTest.class);
	}

	public static void main(String[] args) {
		new TypesTest().testAll();
	}
}
