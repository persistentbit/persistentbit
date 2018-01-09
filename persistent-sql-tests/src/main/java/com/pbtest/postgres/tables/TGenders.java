package com.pbtest.postgres.tables;

import com.pbtest.postgres.expressions.EGenders;
import com.pbtest.postgres.impl.typefactories.GendersTypeFactory;
import com.pbtest.postgres.inserts.InsertGenders;
import com.pbtest.postgres.values.Genders;
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
import com.persistentbit.sql.dsl.statements.work.DbWorkP1;
import com.persistentbit.sql.dsl.tables.AbstractTable;
import com.persistentbit.sql.dsl.tables.TableName;
import com.persistentbit.sql.work.DbWork;

import java.util.function.Function;

public class TGenders extends AbstractTable<EGenders, Genders>{

	private final TableName _tableName = new TableName("postgres", "pbtest", "genders");
	private final DbWorkP1<Object, Genders> _selectById;
	private final EGenders                  _all;
	public final  EObject                   genderCode;
	public final  EString                   description;


	private TGenders(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(EGenders.class, GendersTypeFactory.class);
		this._all = context
			.getTypeFactory(EGenders.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.genderCode = _all.genderCode;
		this.description = _all.description;
		this._selectById = query(p -> q -> {
			Param<EObject> paramgenderCode = context.param(EObject.class, "genderCode");
			return q
				.where(this.genderCode.eq(paramgenderCode.getExpr()))
				.selection(all())
				.one(paramgenderCode);
		});
	}

	public TGenders(ExprContext context) {
		this(context, null);
	}

	@Override
	public Class<EGenders> getTypeClass() {
		return EGenders.class;
	}

	@Override
	protected TableName getTableName() {
		return _tableName;
	}

	@Override
	public TGenders as(String aliasName) {
		return new TGenders(context, aliasName);
	}

	@Override
	public EGenders all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<EGenders, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertGenders insert() {
		return new InsertGenders(context, this);
	}

	public DbWork<Integer> insert(@Nullable Object genderCode, @Nullable String description) {
		return insert()
			.add(genderCode, description)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(ir -> ir.getUpdateCount())));
	}

	public DbWork<Genders> insert(Genders p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public EGenders val(Genders value) {
		return context.getTypeFactory(EGenders.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(Genders value) {
		EGenders e = val(value);
		return update()
			.set(all(), e)
			.where(this.genderCode.eq(e.genderCode));
	}

	public DbWork<Genders> selectById(Object genderCode) {
		return _selectById.with(genderCode);
	}

	public Delete delete() {
		return new Delete(context, this);
	}

	public DbWork<Integer> deleteById(Object genderCode) {
		return delete()
			.where(this.genderCode.eq(genderCode));
	}
}
