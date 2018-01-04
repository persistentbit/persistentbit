package com.persistentbit.sql.dsl.maven.tests;


import com.persistentbit.logging.ModuleLogging;
import com.persistentbit.result.OK;
import com.persistentbit.result.Result;
import com.persistentbit.sql.connect.DbConnector;
import com.persistentbit.sql.transactions.DbTransaction;
import com.persistentbit.sql.updater.DbBuilder;
import com.persistentbit.sql.work.DbWork;

import java.sql.Connection;
import java.util.function.Supplier;

import static com.mycompany.db.generated.GoatData.authApp;
import static com.mycompany.db.generated.GoatData.authUser;
import static com.persistentbit.sql.dsl.postgres.rt.DbPostgres.upper;


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
		Supplier<DbTransaction> newTrans = () -> transSupplier.map(Supplier::get).orElseThrow();

		Long idPersistentBit = authApp
			.insert()
			.add(null, "persistentbit", "pw", true, true, 3)
			.run(newTrans.get()).orElseThrow().head().getAutoGenKey();
		Long idcvcity = authApp.insert()
			.add(null, "cvcity", "cvcitypw", false, true, 5)
			.run(newTrans.get())
			.orElseThrow()
			.head().getAutoGenKey();

		Long idPeter = authUser.insert()
			.add(null, idPersistentBit, "petermuys", "pwpetermuys", null, null, null, null, null, null, null, null
			)
			.run(newTrans.get()).orElseThrow().head().getAutoGenKey();


		authApp.query()
			.leftJoin(authUser).on(authUser.authAppId.eq(idPersistentBit))
			.selection(upper(authApp.name), authApp.all(), authUser.all())
			.list()
			.run(newTrans.get())
			.orElseThrow()
			.forEach(System.out::println);


		/*
		ModuleLogging.consoleLogPrint.registerAsGlobalHandler();

		Result<OK> result = transSupplier
			.flatMap(transSup ->
				rebuildDb().run(transSup.get())
			);
		result.orElseThrow();

		Supplier<DbTransaction> newTrans = ()-> transSupplier.map(Supplier::get).orElseThrow();

		DbInvoices    db      = new DbInvoices();
		TAPersonTable persoon = db.aPerson.alias("menchen");
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

		Selection<Tuple2<APerson,Company>> persAndCompSel = persoon.query()
																   .leftJoin(company).on(persoon.id.eq(company.id))
																   .selection(persoon,company);

		persAndCompSel.run(newTrans.get()).orElseThrow().forEach(System.out::println);

		System.out.println("------------------------------");

		DSelectionTable<Tuple2<APerson,Company>> subSelPC      = persAndCompSel.asTableExpr("pc");
		ETuple2<APerson,Company>                 subSelPCTuple = ETuple2.cast(subSelPC.all());

		TAPerson subSelPerson  = TAPerson.cast(subSelPCTuple.v1());
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


		APerson katrien = APerson.build(b -> b
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

		System.out.println(db.aPerson.insert(katrien).run(newTrans.get()).orElseThrow());

		db.aPerson.query().selection(db.aPerson).run(newTrans.get()).orElseThrow().forEach(System.out::println);


		newTrans.get().run(con -> {
			try(PreparedStatement s = con.prepareStatement("select * from all_generic")){
				try(ResultSet rs = s.executeQuery()){
					ResultSetMetaData meta = rs.getMetaData();
					for(int t=1; t<=meta.getColumnCount();t++){
						System.out.println(meta.getColumnName(t) + ": " + meta.getColumnClassName(t) + " " + meta.getColumnType(t));
					}

				}
			}


			return OK.result;
		});


		AllGeneric ag = AllGeneric.build(b -> b
			 .setABigint(1234567890L)
			 //.setABit(PBitList.val(true))
			 //.setABit40(PBitList.val(true,false,true))
			 //.setABitVarying(PBitList.val(true,true,true,false,false,false))
			.setABoolean(true)
			.setABytea(PByteList.val((byte)0, (byte)1, (byte)2, (byte)3, (byte)4))
			.setAChar("@")
			.setAChar10("char10")
			.setADate(LocalDate.now())
			.setADecimal72(new BigDecimal("2105.72"))
			.setADouble(1122.3344)
			.setAnInt2((short)1234)
			.setAnInt4(12341234)
			.setAnInt8(1234123412341234l)
			.setAnInteger(123456)
			.setANumeric(new BigDecimal("1234"))
			.setANumeric6(new BigDecimal("666666"))
			.setAReal(12.34f)
			.setAText("A text")
			.setATime(LocalTime.now())
			.setATimestamp(LocalDateTime.now())
			.setATimestampWithZone(ZonedDateTime.now())
			.setATimestamp3(LocalDateTime.now())
			.setATimeWithZone(LocalTime.now())
			.setAVarchar("A VarChar")
			.setAVarchar10("AVarChar10")
			.setIdPart1("idpart1")
			.setIdPart2(123456l)
			.setSer(2)
			.setSerBig(3)
			.setSerSmall((short)1)

		);

		AllGeneric fromInsert = db.allGeneric.insert(ag).run(newTrans.get()).orElseThrow();
		System.out.println(fromInsert);

		System.out.println(db.allGeneric.query().selection(db.allGeneric).run(newTrans.get()).orElseThrow());

		AllGenericNulls agn = AllGenericNulls.build(b->b
			.setIdPart1("part1")
			.setIdPart2(12345L)
			.setSerSmall((short)0)
			.setSer(1)
			.setSerBig(2)
		);

		AllGenericNulls fromInsertNulls = db.allGenericNulls.insert(agn).run(newTrans.get()).orElseThrow();
		System.out.println(fromInsertNulls);

		System.out.println(db.allGeneric.query().selection(db.allGeneric).run(newTrans.get()).orElseThrow());
		//DExprTable<Company> withSubCompany = allCompany.query().selection(allCompany.v1());
		//System.out.println(withSubCompany);
		//System.out.println("------------------------------");


		//Selection<Tuple2<Person,Company>> cp = persoon.query()
//													 .leftJoin(allCompany).query()
//													 .selection(persoon,allCompany);
//		System.out.println(cp);


//
//		System.out.println(invoice.query()
//			.leftJoin(line).on(line.invoiceId.eq(invoice.id))
//			.leftJoin(company).on(invoice.fromCompanyId.eq(company.id))
//	    .where(company.adresStreet.eq("Snoekstraat 77"))
//		.selection(invoice,line.id,line.invoiceId, line.product,company)
//		);
//
//		DSelection1<Long> lineSubQuery = line.query().selection(line.invoiceId).asTableExpr("subquery");
//
//		DSelection1 withSub = invoice.query()
//			   .leftJoin(lineSubQuery).on(invoice.id.eq(lineSubQuery.v1()))
//			   .selection(invoice);
//		System.out.println(withSub);

		//ModuleLogging.consoleLogPrint.print(result.getLog());
		*/
	}

}
