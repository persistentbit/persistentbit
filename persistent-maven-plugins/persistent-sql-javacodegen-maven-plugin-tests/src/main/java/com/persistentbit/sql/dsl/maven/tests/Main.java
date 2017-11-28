package com.persistentbit.sql.dsl.maven.tests;

import com.persistentbit.db.generated.Db;
import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.TCompany;
import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.TInvoice;
import com.persistentbit.db.generated.c_persistenttest.s_persistenttest.TInvoiceLine;
import com.persistentbit.logging.ModuleLogging;
import com.persistentbit.result.OK;
import com.persistentbit.result.Result;
import com.persistentbit.sql.connect.DbConnector;
import com.persistentbit.sql.dsl.postgres.rt.PostgresDbContext;
import com.persistentbit.sql.transactions.DbTransaction;
import com.persistentbit.sql.updater.DbBuilder;
import com.persistentbit.sql.work.DbWork;

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

		Db db = new Db(new PostgresDbContext());

		System.out.println(db.person.query().where(db.person.userName.eq("mup")).selection(db.person));

		TInvoiceLine line = db.invoiceLine.alias("iline");
		TInvoice invoice = db.invoice.alias("invoice");
		TCompany company = db.company.alias("company");
		System.out.println(invoice.query()
			.leftJoin(line).on(line.invoiceId.eq(invoice.id))
			.leftJoin(company).on(invoice.fromCompanyId.eq(company.id))
	    .where(company.adresStreet.eq("Snoekstraat 77"))
		.selection(invoice,line.id,line.invoiceId, line.product,company)
		);


		//ModuleLogging.consoleLogPrint.print(result.getLog());
	}

}
