package com.persistentbit.sqltests;

import com.persistentbit.collections.PList;
import com.persistentbit.logging.ModuleLogging;
import com.persistentbit.sql.dsl.expressions.DExpr;
import com.persistentbit.sql.dsl.orderby.OrderBy;
import com.persistentbit.sql.dsl.postgres.rt.windowover.Frame;
import com.persistentbit.sql.dsl.postgres.rt.windowover.FrameEnd;
import com.persistentbit.sql.dsl.postgres.rt.windowover.FrameStart;
import com.persistentbit.test.TestCase;
import com.persistentbit.test.TestRunner;
import com.persistentbit.tuples.Tuple4;

import static com.pbtest.postgres.DbPg.productGroups;
import static com.pbtest.postgres.DbPg.products;
import static com.persistentbit.sql.dsl.genericdb.DbGeneric.avg;
import static com.persistentbit.sql.dsl.postgres.rt.DbPostgres.*;

/**
 * TODOC
 *
 * @author petermuys
 * @since 7/01/18
 */
public class TestPostgresWindowFunctions extends AbstractTestClass{

	static <E1 extends DExpr<J>, J> void tryFun(TestRunner tr, E1 aggrFun, PList<J> expected) {
		tr.info("Testing " + aggrFun);
		PList<Tuple4<String, Double, String, J>> records = products.query()
			.innerJoin(productGroups).on(products.groupId.eq(productGroups.groupId))
			.selection(products.productName, products.price, productGroups.groupName, aggrFun)
			.list(transPg.get());
		tr.info("Got " + records);
		tr.isEquals(records.map(t -> t._4), expected);
	}

	static TestCase windowFuncTests = TestCase.name("windowFunctionTests").code(tr -> {
		tryFun(tr,
			   over(avg(products.price),
					win -> win.partitionBy(productGroups.groupName)
			   ),
			   PList.val(850.0, 850.0, 850.0, 850.0, 500.0, 500.0, 500.0, 500.0, 350.0, 350.0, 350.0)
		);

		tryFun(tr, over(row_number(), win -> win
				   .partitionBy(productGroups.groupName)
				   .orderBy(OrderBy.createAsc(products.price))
			   )
			, PList.val(1L, 2L, 3L, 4L, 1L, 2L, 3L, 4L, 1L, 2L, 3L));

		tryFun(tr, over(dense_rank(), win -> win
				   .partitionBy(productGroups.groupName)
				   .orderBy(OrderBy.createAsc(products.price))
			   )
			, PList.val(1L, 1L, 2L, 3L, 1L, 2L, 3L, 4L, 1L, 2L, 3L));
		//LOWEST PRICE PER GROUP
		tryFun(tr, over(first_value(products.price), win -> win
				   .partitionBy(productGroups.groupName)
				   .orderBy(OrderBy.createAsc(products.price))
			   )
			, PList.val(700.0, 700.0, 700.0, 700.0, 200.0, 200.0, 200.0, 200.0, 150.0, 150.0, 150.0));
		//HIGHEST PRICE PER GROUP
		tryFun(tr, over(last_value(products.price), win -> win
				   .partitionBy(productGroups.groupName)
				   .frame(Frame.rangeBetween(FrameStart.unboundedPreceding(), FrameEnd.unboundedFollowing()))
				   .orderBy(OrderBy.createAsc(products.price))
			   )
			, PList.val(1200.0, 1200.0, 1200.0, 1200.0, 900.0, 900.0, 900.0, 900.0, 700.0, 700.0, 700.0));
		//PREVIOUS PRICE PER GROUP
		tryFun(tr, over(lag(products.price, val(1), null), win -> win
				   .partitionBy(productGroups.groupName)
				   .orderBy(OrderBy.createAsc(products.price))
			   )
			, PList.val(null, 700.0, 700.0, 800.0, null, 200.0, 400.0, 500.0, null, 150.0, 200.0));
		tryFun(tr, over(lag(products.price), win -> win
				   .partitionBy(productGroups.groupName)
				   .orderBy(OrderBy.createAsc(products.price))
			   )
			, PList.val(null, 700.0, 700.0, 800.0, null, 200.0, 400.0, 500.0, null, 150.0, 200.0));


	});


	public void testAll() {
		TestRunner.runAndPrint(ModuleLogging.consoleLogPrint, TestPostgresWindowFunctions.class);
	}

	public static void main(String[] args) {
		ModuleLogging.consoleLogPrint.registerAsGlobalHandler();
		new TestPostgresWindowFunctions().testAll();
	}
}
