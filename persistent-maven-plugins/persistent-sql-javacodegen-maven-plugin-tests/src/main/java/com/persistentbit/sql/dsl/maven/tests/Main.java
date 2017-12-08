package com.persistentbit.sql.dsl.maven.tests;

import com.persistentbit.db.generated.Db;
import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.*;
import com.persistentbit.logging.ModuleLogging;
import com.persistentbit.result.OK;
import com.persistentbit.result.Result;
import com.persistentbit.sql.connect.DbConnector;
import com.persistentbit.sql.dsl.generic.expressions.DExprTuple2;
import com.persistentbit.sql.dsl.generic.query.DSelectionTable;
import com.persistentbit.sql.dsl.generic.query.Selection;
import com.persistentbit.sql.transactions.DbTransaction;
import com.persistentbit.sql.updater.DbBuilder;
import com.persistentbit.sql.work.DbWork;
import com.persistentbit.tuples.Tuple2;

import java.sql.Connection;
import java.util.function.Supplier;

/**
 * TODOC
 *
 * @author petermuys
 * @since 25/11/17
 */
public class Main{
	static private final String driverClassName="org.postgresql.Driver";
	static private final String url="jdbc:postgresql://localhost:5432/persistenttest?currentSchema=persistenttest";
	static private final String userName="persistenttest_usr";
	static private final String password="persistenttest_pwd";

	static final Result<Supplier<Result<Connection>>> connectionSupplier = DbConnector.fromUrl(
		driverClassName,url,userName,password
	).map(c -> c.pooledConnector(10));

	static final Result<Supplier<DbTransaction>> transSupplier = connectionSupplier
		.map(conSup -> () -> DbTransaction.create(conSup));

	static final Result<DbBuilder> builder = DbBuilder.create("persistenttest","sqlMavenTests", Main.class.getResourceAsStream("/dbupdates/createdb.sql"));

	static DbWork<OK> rebuildDb() {

		return DbWork.create(trans -> conn ->
			builder.flatMap(b ->
				b.dropAll().run(trans)
				.flatMap(dropOk -> b.buildOrUpdate().run(trans))
			)
		);

	}



	public static void main(String[] args) {
		ModuleLogging.consoleLogPrint.registerAsGlobalHandler();

		Result<OK> result = transSupplier
			.flatMap(transSup ->
				rebuildDb().run(transSup.get())
			);
		result.orElseThrow();

		Supplier<DbTransaction> newTrans = ()-> transSupplier.map(Supplier::get).orElseThrow();

		Db db = new Db();
		TPersonTable persoon = db.person.alias("menchen");
		Selection per = persoon.query()
				.leftJoin(db.company).on(db.company.ownerPersonId.eq(persoon.id))
			   .where(persoon.street.like("Snoekstraat").and(persoon.houseNumber.eq(77)))
			   .selection(persoon,db.company);
		System.out.println(per);



		System.out.println(transSupplier.flatMap(trans -> per.run(trans.get())).orElseThrow());
		System.out.println("------------------------------");

		TInvoiceLineTable line    = db.invoiceLine.alias("iline");
		TInvoiceTable     invoice = db.invoice.alias("invoice");
		TCompanyTable     company = db.company.alias("company");

		System.out.println(
			company.query().leftJoin(invoice).query().selection(company,invoice));

		System.out.println("------------------------------");

		Selection<Tuple2<Company,Invoice>> allCompanySel =
			company.query()
				   .leftJoin(invoice).on(invoice.fromCompanyId.eq(company.id).or(invoice.invoiceNummer.eq("12345")))
			.selection(company,invoice);

		System.out.println("allCompanySel: " + allCompanySel);
		System.out.println("------------------------------");
		DSelectionTable<Tuple2<Company,Invoice>> allCompany = allCompanySel.asTableExpr("ac");
		System.out.println(allCompany);
		System.out.println("------------------------------");

		System.out.println(allCompany.query().selection(allCompany.all()));
		System.out.println("------------------------------");

		Selection<Tuple2<Person,Company>> persAndCompSel = persoon.query()
			   .leftJoin(company).on(persoon.id.eq(company.id))
			   .selection(persoon,company);

		persAndCompSel.run(newTrans.get()).orElseThrow().forEach(System.out::println);

		System.out.println("------------------------------");

		DSelectionTable<Tuple2<Person,Company>> subSelPC = persAndCompSel.asTableExpr("pc");
		DExprTuple2<Person,Company> subSelPCTuple = DExprTuple2.cast(subSelPC.all());

		TPerson subSelPerson = TPerson.cast(subSelPCTuple.v1());
		TCompany subSelCompany = TCompany.cast(subSelPCTuple.v2());

		invoice.query()
			.leftJoin(subSelPC).on(invoice.fromCompanyId.eq(subSelCompany.id))
			.selection(invoice,subSelPerson,subSelCompany)
		   .run(newTrans.get()).orElseThrow().forEach(t -> {
				System.out.println("Record: ");
				System.out.println("  Invoice: " + t._1);
				System.out.println("  Person:  " + t._2);
				System.out.println("  Company: " + t._3);
		});
		System.out.println("------------------------------");

		TCompanyTable invoiceFrom = db.company.alias("fromCompany");
		TCompanyTable invoiceTo = db.company.alias("toCompany");
		invoice.query()
			   .leftJoin(invoiceFrom).on(invoice.fromCompanyId.eq(invoiceFrom.id))
			   .leftJoin(invoiceTo).on(invoice.toCompanyId.eq(invoiceTo.id))
			   .selection(invoice,invoiceFrom,invoiceTo)
			   .run(newTrans.get()).orElseThrow()
			   .forEach(r -> System.out.println(r));


		Person katrien = Person.build(b -> b
			.setId(0)
			.setUserName("KatrienMuys")
			.setStreet("BakkerijStraat")
			.setHouseNumber(1)
			.setPostalcode("8000")
			.setCity("Bredene")
			.setCountry("BE")
			.setPassword("eclaire")
		);


		//Insert<Person> ip = new Insert<>(new PostgresDbContext(),db.person,db.person.val(
			//katrien
		//));
		//System.out.println(ip);

		System.out.println(db.person.insert(katrien).run(newTrans.get()).orElseThrow());

		db.person.query().selection(db.person).run(newTrans.get()).orElseThrow().forEach(System.out::println);

		//DExprTable<Company> withSubCompany = allCompany.query().selection(allCompany.v1());
		//System.out.println(withSubCompany);
		//System.out.println("------------------------------");


		//Selection<Tuple2<Person,Company>> cp = persoon.query()
//													 .leftJoin(allCompany).query()
//													 .selection(persoon,allCompany);
//		System.out.println(cp);


		/*
		System.out.println(invoice.query()
			.leftJoin(line).on(line.invoiceId.eq(invoice.id))
			.leftJoin(company).on(invoice.fromCompanyId.eq(company.id))
	    .where(company.adresStreet.eq("Snoekstraat 77"))
		.selection(invoice,line.id,line.invoiceId, line.product,company)
		);

		DSelection1<Long> lineSubQuery = line.query().selection(line.invoiceId).asTableExpr("subquery");

		DSelection1 withSub = invoice.query()
			   .leftJoin(lineSubQuery).on(invoice.id.eq(lineSubQuery.v1()))
			   .selection(invoice);
		System.out.println(withSub);*/

		//ModuleLogging.consoleLogPrint.print(result.getLog());
	}

}
