package com.pbtest.postgres.inserts;

import com.pbtest.postgres.tables.TTranslationsGender;
import com.pbtest.postgres.values.TranslationsGender;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

public class InsertTranslationsGender extends Insert<TTranslationsGender, Void>{


	public InsertTranslationsGender(ExprContext context, TTranslationsGender into, PList<String> columnNames,
									PSet<String> withDefaults, String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertTranslationsGender(ExprContext context, TTranslationsGender into) {
		this(context, into, columnNames, PSet.empty(), null, PList.empty());
	}

	private static final PList<String> columnNames = PList.val("gender_code", "language_code", "description");

	public InsertTranslationsGender add(@Nullable Object genderCode, @Nullable Object languageCode,
										@Nullable String description) {
		Object[] row = new Object[]{
			genderCode
			, languageCode
			, description
		};
		return new InsertTranslationsGender(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertTranslationsGender add(TranslationsGender value) {
		return add(
			value.getGenderCode(), value.getLanguageCode(), value.getDescription()
		);
	}
}
