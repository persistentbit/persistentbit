package com.pbtest.postgres.tables;

import com.pbtest.postgres.expressions.ETranslationsGender;
import com.pbtest.postgres.impl.typefactories.TranslationsGenderTypeFactory;
import com.pbtest.postgres.inserts.InsertTranslationsGender;
import com.pbtest.postgres.values.TranslationsGender;
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

public class TTranslationsGender extends AbstractTable<ETranslationsGender, TranslationsGender>{

	private final TableName _tableName = new TableName("postgres", "pbtest", "translations_gender");
	private final DbWorkP2<String, String, TranslationsGender> _selectById;
	private final ETranslationsGender                          _all;
	public final  EString                                      genderCode;
	public final  EString                                      languageCode;
	public final  EString                                      description;


	private TTranslationsGender(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(ETranslationsGender.class, TranslationsGenderTypeFactory.class);
		this._all = context
			.getTypeFactory(ETranslationsGender.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.genderCode = _all.genderCode;
		this.languageCode = _all.languageCode;
		this.description = _all.description;
		this._selectById = query(p -> q -> {
			Param<EString> paramgenderCode   = context.param(EString.class, "genderCode");
			Param<EString> paramlanguageCode = context.param(EString.class, "languageCode");
			return q
				.where(this.genderCode.eq(paramgenderCode.getExpr())
						   .and(this.languageCode.eq(paramlanguageCode.getExpr())))
				.selection(all())
				.one(paramgenderCode, paramlanguageCode);
		});
	}

	public TTranslationsGender(ExprContext context) {
		this(context, null);
	}
	@Override
	public Class<ETranslationsGender> getTypeClass() {
		return ETranslationsGender.class;
	}
	@Override
	protected TableName getTableName() {
		return _tableName;
	}
	@Override
	public TTranslationsGender as(String aliasName) {
		return new TTranslationsGender(context, aliasName);
	}
	@Override
	public ETranslationsGender all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<ETranslationsGender, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertTranslationsGender insert() {
		return new InsertTranslationsGender(context, this);
	}

	public DbWork<Integer> insert(@Nullable String genderCode, @Nullable String languageCode,
								  @Nullable String description) {
		return insert()
			.add(genderCode, languageCode, description)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(ir -> ir.getUpdateCount())));
	}

	public DbWork<TranslationsGender> insert(TranslationsGender p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public ETranslationsGender val(TranslationsGender value) {
		return context.getTypeFactory(ETranslationsGender.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(TranslationsGender value) {
		ETranslationsGender e = val(value);
		return update()
			.set(all(), e)
			.where(this.genderCode.eq(e.genderCode).and(this.languageCode.eq(e.languageCode)));
	}

	public DbWork<TranslationsGender> selectById(String genderCode, String languageCode) {
		return _selectById.with(genderCode, languageCode);
	}

	public Delete delete() {
		return new Delete(context, this);
	}

	public DbWork<Integer> deleteById(String genderCode, String languageCode) {
		return delete()
			.where(this.genderCode.eq(genderCode).and(this.languageCode.eq(languageCode)));
	}
}
