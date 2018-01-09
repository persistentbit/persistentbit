package com.pbtest.postgres.inserts;

import com.pbtest.postgres.tables.TAddressRelations;
import com.pbtest.postgres.values.AddressRelations;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

public class InsertAddressRelations extends Insert<TAddressRelations, Void>{


	public InsertAddressRelations(ExprContext context, TAddressRelations into, PList<String> columnNames,
								  PSet<String> withDefaults, String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertAddressRelations(ExprContext context, TAddressRelations into) {
		this(context, into, columnNames, PSet.empty(), null, PList.empty());
	}

	private static final PList<String> columnNames = PList.val("address_relation_code", "description");

	public InsertAddressRelations add(@Nullable Object addressRelationCode, @Nullable String description) {
		Object[] row = new Object[]{
			addressRelationCode
			, description
		};
		return new InsertAddressRelations(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertAddressRelations add(AddressRelations value) {
		return add(
			value.getAddressRelationCode(), value.getDescription()
		);
	}
}
