package com.pbtest.postgres.tables;

import com.pbtest.postgres.expressions.EPeople;
import com.pbtest.postgres.impl.typefactories.PeopleTypeFactory;
import com.pbtest.postgres.inserts.InsertPeople;
import com.pbtest.postgres.values.People;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.EDate;
import com.persistentbit.sql.dsl.expressions.ELong;
import com.persistentbit.sql.dsl.expressions.EString;
import com.persistentbit.sql.dsl.expressions.Param;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.statements.delete.Delete;
import com.persistentbit.sql.dsl.statements.insert.InsertResult;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.statements.select.impl.QueryImpl;
import com.persistentbit.sql.dsl.statements.update.Update;
import com.persistentbit.sql.dsl.statements.work.DbWorkP1;
import com.persistentbit.sql.dsl.tables.AbstractTable;
import com.persistentbit.sql.dsl.tables.TableName;
import com.persistentbit.sql.work.DbWork;

import java.time.LocalDate;
import java.util.function.Function;

public class TPeople extends AbstractTable<EPeople, People>{

	private final TableName _tableName = new TableName(null, "pbtest", "people");
	private final DbWorkP1<Long, People> _selectById;
	private final EPeople                _all;
	public final  ELong                  personId;
	public final  EString                salutationCode;
	public final  EString                nameFirst;
	public final  EString                nameMiddle;
	public final  EString                nameLast;
	public final  EString                genderCode;
	public final  EDate                  birthDay;


	private TPeople(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(EPeople.class, PeopleTypeFactory.class);
		this._all = context
			.getTypeFactory(EPeople.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.personId = _all.personId;
		this.salutationCode = _all.salutationCode;
		this.nameFirst = _all.nameFirst;
		this.nameMiddle = _all.nameMiddle;
		this.nameLast = _all.nameLast;
		this.genderCode = _all.genderCode;
		this.birthDay = _all.birthDay;
		this._selectById = query(p -> q -> {
			Param<ELong> parampersonId = context.param(ELong.class, "personId");
			return q
				.where(this.personId.eq(parampersonId.getExpr()))
				.selection(all())
				.one(parampersonId);
		});
	}

	public TPeople(ExprContext context) {
		this(context, null);
	}
	@Override
	public Class<EPeople> getTypeClass() {
		return EPeople.class;
	}
	@Override
	protected TableName getTableName() {
		return _tableName;
	}
	@Override
	public TPeople as(String aliasName) {
		return new TPeople(context, aliasName);
	}
	@Override
	public EPeople all() {
		return _all;
	}

	public Query query() {
		return new QueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<EPeople, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertPeople insert() {
		return new InsertPeople(context, this);
	}

	public DbWork<Long> insert(@Nullable Long personId, @Nullable String salutationCode, @Nullable String nameFirst,
							   @Nullable String nameMiddle, @Nullable String nameLast, @Nullable String genderCode,
							   @Nullable LocalDate birthDay) {
		return insert()
			.add(personId, salutationCode, nameFirst, nameMiddle, nameLast, genderCode, birthDay)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(InsertResult::getAutoGenKey)));
	}

	public DbWork<People> insert(People p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public EPeople val(People value) {
		return context.getTypeFactory(EPeople.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(People value) {
		EPeople e = val(value);
		return update()
			.set(all(), e)
			.where(this.personId.eq(e.personId));
	}

	public DbWork<People> selectById(long personId) {
		return _selectById.with(personId);
	}

	public Delete delete() {
		return new Delete(context, this);
	}

	public DbWork<Integer> deleteById(long personId) {
		return delete()
			.where(this.personId.eq(personId));
	}
}
