package com.mycompany.db.generated.inserts;

import com.mycompany.db.generated.tables.TAPerson;
import com.mycompany.db.generated.values.APerson;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

public class InsertAPerson extends Insert<TAPerson, Long>{


	public InsertAPerson(ExprContext context, TAPerson into, PList<String> columnNames, PSet<String> withDefaults,
						 String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertAPerson(ExprContext context, TAPerson into) {
		this(context, into, columnNames, PSet.empty(), "id", PList.empty());
	}

	private static final PList<String> columnNames = PList
		.val("id", "user_name", "password", "street", "house_number", "bus_number", "postalcode", "city", "country");

	public InsertAPerson add(@Nullable Long id, @Nullable String userName, @Nullable String password,
							 @Nullable String street, @Nullable Integer houseNumber, @Nullable String busNumber,
							 @Nullable String postalcode, @Nullable String city, @Nullable String country) {
		Object[] row = new Object[]{
			id
			, userName
			, password
			, street
			, houseNumber
			, busNumber
			, postalcode
			, city
			, country
		};
		return new InsertAPerson(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertAPerson add(APerson value) {
		return add(
			value.getId(), value.getUserName(), value.getPassword(), value.getStreet(), value.getHouseNumber(), value
				.getBusNumber().orElse(null), value.getPostalcode(), value.getCity(), value.getCountry()
		);
	}
}
