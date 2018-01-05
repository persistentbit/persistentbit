package com.mycompany.db.generated.inserts;

import com.mycompany.db.generated.tables.TCompany;
import com.mycompany.db.generated.values.Company;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

public class InsertCompany extends Insert<TCompany, Long>{


	public InsertCompany(ExprContext context, TCompany into, PList<String> columnNames, PSet<String> withDefaults,
						 String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertCompany(ExprContext context, TCompany into) {
		this(context, into, columnNames, PSet.empty(), "id", PList.empty());
	}

	private static final PList<String> columnNames = PList
		.val("id", "company_name", "adres_street", "adres_house_number", "adres_bus_number", "adres_postalcode", "adres_city", "adres_country", "owner_person_id");

	public InsertCompany add(@Nullable Long id, @Nullable String companyName, @Nullable String adresStreet,
							 @Nullable Integer adresHouseNumber, @Nullable String adresBusNumber,
							 @Nullable String adresPostalcode, @Nullable String adresCity,
							 @Nullable String adresCountry, @Nullable Long ownerPersonId) {
		Object[] row = new Object[]{
			id
			, companyName
			, adresStreet
			, adresHouseNumber
			, adresBusNumber
			, adresPostalcode
			, adresCity
			, adresCountry
			, ownerPersonId
		};
		return new InsertCompany(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertCompany add(Company value) {
		return add(
			value.getId(), value.getCompanyName(), value.getAdresStreet(), value.getAdresHouseNumber(), value
				.getAdresBusNumber().orElse(null), value.getAdresPostalcode(), value.getAdresCity(), value
				.getAdresCountry(), value.getOwnerPersonId().orElse(null)
		);
	}
}
