package com.pbtest.postgres.tables;

import com.pbtest.postgres.expressions.ETranslationsCountry;
import com.pbtest.postgres.impl.typefactories.TranslationsCountryTypeFactory;
import com.pbtest.postgres.inserts.InsertTranslationsCountry;
import com.pbtest.postgres.values.TranslationsCountry;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.Param;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.delete.Delete;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.statements.select.impl.QueryImpl;
import com.persistentbit.sql.dsl.statements.update.Update;
import com.persistentbit.sql.dsl.statements.work.DbWorkP2;
import com.persistentbit.sql.dsl.tables.AbstractTable;
import com.persistentbit.sql.dsl.tables.TableName;
import com.persistentbit.sql.work.DbWork;

import java.util.function.Function;

public class TTranslationsCountry extends AbstractTable<ETranslationsCountry, TranslationsCountry>{

	private final TableName _tableName = new TableName("postgres", "pbtest", "translations_country");
	private final DbWorkP2<String, String, TranslationsCountry> _selectById;
	private final ETranslationsCountry                          _all;
	public final  EString                                       countryCode;
	public final  EString                                       languageCode;
	public final  EString                                       name;


	private TTranslationsCountry(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(ETranslationsCountry.class, TranslationsCountryTypeFactory.class);
		this._all = context
			.getTypeFactory(ETranslationsCountry.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.countryCode = _all.countryCode;
		this.languageCode = _all.languageCode;
		this.name = _all.name;
		this._selectById = query(p -> q -> {
			Param<EString> paramcountryCode  = context.param(EString.class, "countryCode");
			Param<EString> paramlanguageCode = context.param(EString.class, "languageCode");
			return q
				.where(this.countryCode.eq(paramcountryCode.getExpr())
						   .and(this.languageCode.eq(paramlanguageCode.getExpr())))
				.selection(all())
				.one(paramcountryCode, paramlanguageCode);
		});
	}

	public TTranslationsCountry(ExprContext context) {
		this(context, null);
	}
	@Override
	public Class<ETranslationsCountry> getTypeClass() {
		return ETranslationsCountry.class;
	}
	@Override
	protected TableName getTableName() {
		return _tableName;
	}
	@Override
	public TTranslationsCountry as(String aliasName) {
		return new TTranslationsCountry(context, aliasName);
	}
	@Override
	public ETranslationsCountry all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<ETranslationsCountry, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertTranslationsCountry insert() {
		return new InsertTranslationsCountry(context, this);
	}

	public DbWork<Integer> insert(@Nullable String countryCode, @Nullable String languageCode, @Nullable String name) {
		return insert()
			.add(countryCode, languageCode, name)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(ir -> ir.getUpdateCount())));
	}

	public DbWork<TranslationsCountry> insert(TranslationsCountry p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public ETranslationsCountry val(TranslationsCountry value) {
		return context.getTypeFactory(ETranslationsCountry.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(TranslationsCountry value) {
		ETranslationsCountry e = val(value);
		return update()
			.set(all(), e)
			.where(this.countryCode.eq(e.countryCode).and(this.languageCode.eq(e.languageCode)));
	}

	public DbWork<TranslationsCountry> selectById(String countryCode, String languageCode) {
		return _selectById.with(countryCode, languageCode);
	}

	public Delete delete() {
		return new Delete(context, this);
	}

	public DbWork<Integer> deleteById(String countryCode, String languageCode) {
		return delete()
			.where(this.countryCode.eq(countryCode).and(this.languageCode.eq(languageCode)));
	}
}
