package com.pbtest.postgres.tables;

import com.pbtest.postgres.expressions.ETranslationsSalutation;
import com.pbtest.postgres.impl.typefactories.TranslationsSalutationTypeFactory;
import com.pbtest.postgres.inserts.InsertTranslationsSalutation;
import com.pbtest.postgres.values.TranslationsSalutation;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.EObject;
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

public class TTranslationsSalutation extends AbstractTable<ETranslationsSalutation, TranslationsSalutation>{

	private final TableName _tableName = new TableName("postgres", "pbtest", "translations_salutation");
	private final DbWorkP2<Object, Object, TranslationsSalutation> _selectById;
	private final ETranslationsSalutation                          _all;
	public final  EObject                                          salutationCode;
	public final  EObject                                          languageCode;
	public final  EString                                          description;


	private TTranslationsSalutation(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(ETranslationsSalutation.class, TranslationsSalutationTypeFactory.class);
		this._all = context
			.getTypeFactory(ETranslationsSalutation.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.salutationCode = _all.salutationCode;
		this.languageCode = _all.languageCode;
		this.description = _all.description;
		this._selectById = query(p -> q -> {
			Param<EObject> paramsalutationCode = context.param(EObject.class, "salutationCode");
			Param<EObject> paramlanguageCode   = context.param(EObject.class, "languageCode");
			return q
				.where(this.salutationCode.eq(paramsalutationCode.getExpr())
						   .and(this.languageCode.eq(paramlanguageCode.getExpr())))
				.selection(all())
				.one(paramsalutationCode, paramlanguageCode);
		});
	}

	public TTranslationsSalutation(ExprContext context) {
		this(context, null);
	}

	@Override
	public Class<ETranslationsSalutation> getTypeClass() {
		return ETranslationsSalutation.class;
	}

	@Override
	protected TableName getTableName() {
		return _tableName;
	}

	@Override
	public TTranslationsSalutation as(String aliasName) {
		return new TTranslationsSalutation(context, aliasName);
	}

	@Override
	public ETranslationsSalutation all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<ETranslationsSalutation, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertTranslationsSalutation insert() {
		return new InsertTranslationsSalutation(context, this);
	}

	public DbWork<Integer> insert(@Nullable Object salutationCode, @Nullable Object languageCode,
								  @Nullable String description) {
		return insert()
			.add(salutationCode, languageCode, description)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(ir -> ir.getUpdateCount())));
	}

	public DbWork<TranslationsSalutation> insert(TranslationsSalutation p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public ETranslationsSalutation val(TranslationsSalutation value) {
		return context.getTypeFactory(ETranslationsSalutation.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(TranslationsSalutation value) {
		ETranslationsSalutation e = val(value);
		return update()
			.set(all(), e)
			.where(this.salutationCode.eq(e.salutationCode).and(this.languageCode.eq(e.languageCode)));
	}

	public DbWork<TranslationsSalutation> selectById(Object salutationCode, Object languageCode) {
		return _selectById.with(salutationCode, languageCode);
	}

	public Delete delete() {
		return new Delete(context, this);
	}

	public DbWork<Integer> deleteById(Object salutationCode, Object languageCode) {
		return delete()
			.where(this.salutationCode.eq(salutationCode).and(this.languageCode.eq(languageCode)));
	}
}
