package com.persistentbit.dsl.tests;

import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

import java.time.LocalDateTime;

/**
 * TODOC
 *
 * @author petermuys
 * @since 24/12/17
 */
public class PersonInsert extends Insert<TPerson, Void>{

	static private final PList<String> columnNames  = PList.val(
		"id",
		"first_name",
		"middle_name",
		"last_name",
		"home_street",
		"home_PostalCode",
		"home_City",
		"created"
	);
	static private final PSet<String>  withDefaults = PSet.val("created");

	private PersonInsert(ExprContext context, TPerson into, PList<String> columnNames,
						 PSet<String> withDefaults,
						 String autoGenKeyName,
						 PList<Object[]> rows
	) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public PersonInsert(ExprContext context, TPerson into) {
		this(context, into, columnNames, withDefaults, null, PList.empty());
	}


	public PersonInsert noDefaultForCreated() {
		return new PersonInsert(
			context, into, columnNames,
			withDefaults.filter(n -> n.equals("created")),
			autoGenKeyName, rows
		);
	}


	public PersonInsert add(
		Long id,
		String firstName,
		String middleName,
		String lastName,
		String homeStreet,
		String homePostalCode,
		String homeCity,
		LocalDateTime created
	) {
		Object[] row = new Object[columnNames.size()];
		row[0] = id;
		row[1] = firstName;
		row[2] = middleName;
		row[3] = lastName;
		row[4] = homeStreet;
		row[5] = homePostalCode;
		row[6] = homeCity;
		row[7] = created;
		return new PersonInsert(
			context, into, columnNames, withDefaults, autoGenKeyName,
			rows.plus(row)
		);
	}

	public PersonInsert add(Person person) {
		return add(
			person.getId(),
			person.getFirstName(),
			person.getMiddleName().orElse(null),
			person.getLastName(),
			person.getHome().getStreet(),
			person.getHome().getPostalCode(),
			person.getHome().getCity(),
			person.getCreated()
		);
	}

}
