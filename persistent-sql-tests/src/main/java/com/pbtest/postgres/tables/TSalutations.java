package com.pbtest.postgres.tables;

import com.pbtest.postgres.expressions.ESalutations;
import com.pbtest.postgres.impl.typefactories.SalutationsTypeFactory;
import com.pbtest.postgres.inserts.InsertSalutations;
import com.pbtest.postgres.values.Salutations;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.Param;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.postgres.rt.statements.PgQuery;
import com.persistentbit.sql.dsl.postgres.rt.statements.impl.PgQueryImpl;
import com.persistentbit.sql.dsl.statements.delete.Delete;
import com.persistentbit.sql.dsl.statements.insert.InsertResult;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.statements.update.Update;
import com.persistentbit.sql.dsl.statements.work.DbWorkP1;
import com.persistentbit.sql.dsl.tables.AbstractTable;
import com.persistentbit.sql.dsl.tables.TableName;
import com.persistentbit.sql.work.DbWork;

import java.util.function.Function;

public class TSalutations extends AbstractTable<ESalutations, Salutations>{

	private final TableName _tableName = new TableName(null, "pbtest", "salutations");
	private final DbWorkP1<String, Salutations> _selectById;
	private final ESalutations                  _all;
	public final  EString                       salutationCode;
	public final  EString                       description;


	private TSalutations(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(ESalutations.class, SalutationsTypeFactory.class);
		this._all = context
			.getTypeFactory(ESalutations.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.salutationCode = _all.salutationCode;
		this.description = _all.description;
		this._selectById = query(p -> q -> {
			Param<EString> paramsalutationCode = context.param(EString.class, "salutationCode");
			return q
				.where(this.salutationCode.eq(paramsalutationCode.getExpr()))
				.selection(all())
				.one(paramsalutationCode);
		});
	}

	public TSalutations(ExprContext context) {
		this(context, null);
	}
	@Override
	public Class<ESalutations> getTypeClass() {
		return ESalutations.class;
	}
	@Override
	protected TableName getTableName() {
		return _tableName;
	}
	@Override
	public TSalutations as(String aliasName) {
		return new TSalutations(context, aliasName);
	}
	@Override
	public ESalutations all() {
		return _all;
	}

	public PgQuery query() {
		return new PgQueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<ESalutations, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertSalutations insert() {
		return new InsertSalutations(context, this);
	}

	public DbWork<Object> insert(@Nullable String salutationCode, @Nullable String description) {
		return insert()
			.add(salutationCode, description)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(InsertResult::getAutoGenKey)));
	}

	public DbWork<Salutations> insert(Salutations p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public ESalutations val(Salutations value) {
		return context.getTypeFactory(ESalutations.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(Salutations value) {
		ESalutations e = val(value);
		return update()
			.set(all(), e)
			.where(this.salutationCode.eq(e.salutationCode));
	}

	public DbWork<Salutations> selectById(String salutationCode) {
		return _selectById.with(salutationCode);
	}

	public Delete delete() {
		return new Delete(context, this);
	}

	public DbWork<Integer> deleteById(String salutationCode) {
		return delete()
			.where(this.salutationCode.eq(salutationCode));
	}
}
