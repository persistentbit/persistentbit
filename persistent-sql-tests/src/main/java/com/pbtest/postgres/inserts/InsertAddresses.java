package com.pbtest.postgres.inserts;

import com.pbtest.postgres.tables.TAddresses;
import com.pbtest.postgres.values.Addresses;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

public class InsertAddresses extends Insert<TAddresses, Long>{


	public InsertAddresses(ExprContext context, TAddresses into, PList<String> columnNames, PSet<String> withDefaults,
						   String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertAddresses(ExprContext context, TAddresses into) {
		this(context, into, columnNames, PSet.empty(), "address_id", PList.empty());
	}

	private static final PList<String> columnNames = PList
		.val("address_id", "street_line_1", "street_line_2", "postal_code", "city_name", "country_code", "district");

	public InsertAddresses add(@Nullable Long addressId, @Nullable String streetLine1, @Nullable String streetLine2,
							   @Nullable String postalCode, @Nullable String cityName, @Nullable String countryCode,
							   @Nullable String district) {
		Object[] row = new Object[]{
			addressId
			, streetLine1
			, streetLine2
			, postalCode
			, cityName
			, countryCode
			, district
		};
		return new InsertAddresses(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertAddresses add(Addresses value) {
		return add(
			value.getAddressId(), value.getStreetLine1(), value.getStreetLine2().orElse(null), value
				.getPostalCode(), value.getCityName(), value.getCountryCode(), value.getDistrict().orElse(null)
		);
	}
}
