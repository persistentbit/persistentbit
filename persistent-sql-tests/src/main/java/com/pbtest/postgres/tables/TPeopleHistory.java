package com.pbtest.postgres.tables;

import com.pbtest.postgres.expressions.EPeopleHistory;
import com.pbtest.postgres.impl.typefactories.PeopleHistoryTypeFactory;
import com.pbtest.postgres.inserts.InsertPeopleHistory;
import com.pbtest.postgres.values.PeopleHistory;
import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.collections.PList;
import com.persistentbit.result.Result;
import com.persistentbit.sql.dsl.expressions.*;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.postgres.rt.statements.PgQuery;
import com.persistentbit.sql.dsl.postgres.rt.statements.impl.PgQueryImpl;
import com.persistentbit.sql.dsl.statements.delete.Delete;
import com.persistentbit.sql.dsl.statements.insert.InsertResult;
import com.persistentbit.sql.dsl.statements.select.Query;
import com.persistentbit.sql.dsl.statements.update.Update;
import com.persistentbit.sql.dsl.statements.work.DbWorkP2;
import com.persistentbit.sql.dsl.tables.AbstractTable;
import com.persistentbit.sql.dsl.tables.TableName;
import com.persistentbit.sql.work.DbWork;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

public class TPeopleHistory extends AbstractTable<EPeopleHistory, PeopleHistory>{

	private final TableName _tableName = new TableName(null, "pbtest", "people_history");
	private final DbWorkP2<Long, LocalDateTime, PeopleHistory> _selectById;
	private final EPeopleHistory                               _all;
	public final  ELong                                        personId;
	public final  EString                                      salutationCode;
	public final  EString                                      nameFirst;
	public final  EString                                      nameMiddle;
	public final  EString                                      nameLast;
	public final  EString                                      genderCode;
	public final  EDate                                        birthDay;
	public final  EDateTime                                    startTime;
	public final  EDateTime                                    endTime;


	private TPeopleHistory(ExprContext context, String alias) {
		super(context, alias);
		context.registerType(EPeopleHistory.class, PeopleHistoryTypeFactory.class);
		this._all = context
			.getTypeFactory(EPeopleHistory.class)
			.buildTableField(createFullTableNameOrAlias() + ".", "", "");
		this.personId = _all.personId;
		this.salutationCode = _all.salutationCode;
		this.nameFirst = _all.nameFirst;
		this.nameMiddle = _all.nameMiddle;
		this.nameLast = _all.nameLast;
		this.genderCode = _all.genderCode;
		this.birthDay = _all.birthDay;
		this.startTime = _all.startTime;
		this.endTime = _all.endTime;
		this._selectById = query(p -> q -> {
			Param<ELong>     parampersonId  = context.param(ELong.class, "personId");
			Param<EDateTime> paramstartTime = context.param(EDateTime.class, "startTime");
			return q
				.where(this.personId.eq(parampersonId.getExpr()).and(this.startTime.eq(paramstartTime.getExpr())))
				.selection(all())
				.one(parampersonId, paramstartTime);
		});
	}

	public TPeopleHistory(ExprContext context) {
		this(context, null);
	}

	@Override
	public Class<EPeopleHistory> getTypeClass() {
		return EPeopleHistory.class;
	}

	@Override
	protected TableName getTableName() {
		return _tableName;
	}

	@Override
	public TPeopleHistory as(String aliasName) {
		return new TPeopleHistory(context, aliasName);
	}

	@Override
	public EPeopleHistory all() {
		return _all;
	}

	public PgQuery query() {
		return new PgQueryImpl(context, PList.val(this));
	}

	public <R> R query(Function<EPeopleHistory, Function<Query, R>> builder) {
		return builder.apply(all()).apply(query());
	}

	public InsertPeopleHistory insert() {
		return new InsertPeopleHistory(context, this);
	}

	public DbWork<Object> insert(@Nullable Long personId, @Nullable String salutationCode, @Nullable String nameFirst,
								 @Nullable String nameMiddle, @Nullable String nameLast, @Nullable String genderCode,
								 @Nullable LocalDate birthDay, @Nullable LocalDateTime startTime,
								 @Nullable LocalDateTime endTime) {
		return insert()
			.add(personId, salutationCode, nameFirst, nameMiddle, nameLast, genderCode, birthDay, startTime, endTime)
			.flatMap(irList -> Result.fromOpt(irList.headOpt().map(InsertResult::getAutoGenKey)));
	}

	public DbWork<PeopleHistory> insert(PeopleHistory p) {
		return insert().add(p)
			.flatMap(irList -> Result.fromOpt(irList.headOpt())
				.flatMap(ir ->
							 ir.getUpdateCount() == 0
								 ? Result.empty("No record inserted for " + p)
								 : Result.success(p)));
	}

	public EPeopleHistory val(PeopleHistory value) {
		return context.getTypeFactory(EPeopleHistory.class).buildVal(value);
	}

	public Update update() {
		return new Update(context, this);
	}

	public DbWork<Integer> update(PeopleHistory value) {
		EPeopleHistory e = val(value);
		return update()
			.set(all(), e)
			.where(this.personId.eq(e.personId).and(this.startTime.eq(e.startTime)));
	}

	public DbWork<PeopleHistory> selectById(long personId, LocalDateTime startTime) {
		return _selectById.with(personId, startTime);
	}

	public Delete delete() {
		return new Delete(context, this);
	}

	public DbWork<Integer> deleteById(long personId, LocalDateTime startTime) {
		return delete()
			.where(this.personId.eq(personId).and(this.startTime.eq(startTime)));
	}
}
