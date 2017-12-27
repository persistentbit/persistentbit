package com.generated.inserts;

import com.generated.tables.TPersons;
import com.generated.values.Persons;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

import java.time.LocalDateTime;

public class InsertPersons extends Insert<TPersons, Long>{


	public InsertPersons(ExprContext context, TPersons into, PList<String> columnNames, PSet<String> withDefaults,
						 String autoGenKeyName, PList<Object[]> rows
	) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertPersons(ExprContext context, TPersons into) {
		this(context, into, columnNames, PSet.empty(), "person_id", PList.empty());
	}

	private static final PList<String> columnNames = PList
		.val("person_id", "first_name", "middle_name", "last_Name", "home_street", "home_postal_code", "home_city", "created");

	public InsertPersons add(@Nullable Long personId, @Nullable String firstName, @Nullable String middleName,
							 @Nullable String lastName, @Nullable String street, @Nullable String postalCode,
							 @Nullable String city, @Nullable LocalDateTime created
	) {
		Object[] row = new Object[]{
			personId
			, firstName
			, middleName
			, lastName
			, street
			, postalCode
			, city
			, created
		};
		return new InsertPersons(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertPersons add(Persons value) {
		return add(
			value.getPersonId(), value.getFirstName(), value.getMiddleName().orElse(null), value.getLastName(), value
				.getHome().getStreet(), value.getHome().getPostalCode(), value.getHome().getCity(), value.getCreated()
		);
	}
}
