package com.pbtest.postgres.inserts;

import com.pbtest.postgres.tables.TPeopleAddresses;
import com.pbtest.postgres.values.PeopleAddresses;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

public class InsertPeopleAddresses extends Insert<TPeopleAddresses, Long>{


	public InsertPeopleAddresses(ExprContext context, TPeopleAddresses into, PList<String> columnNames,
								 PSet<String> withDefaults, String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertPeopleAddresses(ExprContext context, TPeopleAddresses into) {
		this(context, into, columnNames, PSet.empty(), "person_id", PList.empty());
	}

	private static final PList<String> columnNames = PList.val("person_id", "address_relation_code", "address_id");

	public InsertPeopleAddresses add(@Nullable Long personId, @Nullable String addressRelationCode,
									 @Nullable Long addressId) {
		Object[] row = new Object[]{
			personId
			, addressRelationCode
			, addressId
		};
		return new InsertPeopleAddresses(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertPeopleAddresses add(PeopleAddresses value) {
		return add(
			value.getPersonId(), value.getAddressRelationCode(), value.getAddressId()
		);
	}
}
