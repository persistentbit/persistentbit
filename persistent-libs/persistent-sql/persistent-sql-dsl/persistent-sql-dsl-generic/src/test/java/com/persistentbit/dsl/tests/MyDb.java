package com.persistentbit.dsl.tests;

import com.persistentbit.collections.PList;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.Param;
import com.persistentbit.sql.dsl.statements.work.DbWorkP1;
import com.persistentbit.sql.dsl.statements.work.DbWorkP2;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/12/17
 */
public class MyDb extends Db{


	static public final DbWorkP1<Long, Person>                  selectPersonById = person.query(p -> q -> {

		Param<ELong> idParam = param("id", ELong.class);

		return q
			.where(idParam.getExpr().eq(p.id))
			.selection(p)
			.one(idParam);
	});
	static public final DbWorkP2<String, String, PList<Person>> selectByName     = person.query(person -> q -> {
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
	static public final DbWorkP1<Address, PList<Person>>        selectByAddress  =
		person.as("menschen").query(menchen -> q -> {
			Param<EAddress> adr = param("adress", EAddress.class);

			return q
				.where(menchen.home.eq(adr.getExpr()))
				.selection(menchen)
				.list(adr);
		});
}
