package com.pbtest.postgres.inserts;

import com.pbtest.postgres.tables.TTranslationsCountry;
import com.pbtest.postgres.values.TranslationsCountry;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

public class InsertTranslationsCountry extends Insert<TTranslationsCountry, Void>{


	public InsertTranslationsCountry(ExprContext context, TTranslationsCountry into, PList<String> columnNames,
									 PSet<String> withDefaults, String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertTranslationsCountry(ExprContext context, TTranslationsCountry into) {
		this(context, into, columnNames, PSet.empty(), null, PList.empty());
	}

	private static final PList<String> columnNames = PList.val("country_code", "language_code", "name");

	public InsertTranslationsCountry add(@Nullable String countryCode, @Nullable String languageCode,
										 @Nullable String name) {
		Object[] row = new Object[]{
			countryCode
			, languageCode
			, name
		};
		return new InsertTranslationsCountry(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertTranslationsCountry add(TranslationsCountry value) {
		return add(
			value.getCountryCode(), value.getLanguageCode(), value.getName()
		);
	}
}
