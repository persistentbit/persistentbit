package com.pbtest.postgres.inserts;

import com.pbtest.postgres.tables.TTranslationsSalutation;
import com.pbtest.postgres.values.TranslationsSalutation;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.collections.PSet;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.insert.Insert;

public class InsertTranslationsSalutation extends Insert<TTranslationsSalutation, Void>{


	public InsertTranslationsSalutation(ExprContext context, TTranslationsSalutation into, PList<String> columnNames,
										PSet<String> withDefaults, String autoGenKeyName, PList<Object[]> rows) {
		super(context, into, columnNames, withDefaults, autoGenKeyName, rows);
	}

	public InsertTranslationsSalutation(ExprContext context, TTranslationsSalutation into) {
		this(context, into, columnNames, PSet.empty(), null, PList.empty());
	}

	private static final PList<String> columnNames = PList.val("salutation_code", "language_code", "description");

	public InsertTranslationsSalutation add(@Nullable String salutationCode, @Nullable String languageCode,
											@Nullable String description) {
		Object[] row = new Object[]{
			salutationCode
			, languageCode
			, description
		};
		return new InsertTranslationsSalutation(
			this.context, this.into, this.columnNames, this.withDefaults, this.autoGenKeyName, this.rows.plus(row));
	}

	public InsertTranslationsSalutation add(TranslationsSalutation value) {
		return add(
			value.getSalutationCode(), value.getLanguageCode(), value.getDescription()
		);
	}
}
